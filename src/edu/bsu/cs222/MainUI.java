package edu.bsu.cs222;

import com.google.gson.JsonArray;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class MainUI extends Application {

    public void start(Stage primaryStage) {
        primaryStage.setScene(createScene());
        primaryStage.setTitle("Wikipedia Search");
        primaryStage.setWidth(500);
        primaryStage.setHeight(600);
        primaryStage.show();
    }

    private Scene createScene(){
        VBox parent = new VBox();
        parent.setSpacing(10);
        parent.getChildren().add(new Label("Wikipedia Search"));

        HBox searchArea = new HBox(new Label("Search:"));
        final TextField inputField = new TextField();
        searchArea.getChildren().add(inputField);
        parent.getChildren().add(searchArea);

        final ComboBox<String> sortChoice = new ComboBox<>();
        sortChoice.getItems().addAll("Most Recent", "Most Active");
        parent.getChildren().addAll(new Label("Sort by:"), sortChoice);

        Button searchButton = new Button("Search Wiki");
        parent.getChildren().add(searchButton);

        final ListView<String> revisionsView = new ListView<>();
        parent.getChildren().addAll(new Label("Revisions:"), revisionsView);

        final ListView<String> redirectsView = new ListView<>();
        parent.getChildren().addAll(new Label("Redirects:"), redirectsView);

        searchButton.setOnAction(event -> {
            try {
                revisionsView.getItems().clear();
                redirectsView.getItems().clear();
                ErrorHandling errors = new ErrorHandling();
                String searched = inputField.getText();
                URLConnection urlConnection = new URLConnection(searched);
                errors.connectionError(urlConnection.in);


                RevisionParser revParser = new RevisionParser();

                JsonArray revisionsArray = revParser.revisionsParse(urlConnection.in);
                errors.pageError(revisionsArray);

                List<Revision> revisionList = revParser.createRevisionsList(revisionsArray);

                ObservableList<String> revisionsObserveList = FXCollections.observableArrayList();

                try {
                    if (sortChoice.getValue().equals("Most Recent")) {
                        for (Revision revision : revisionList) {
                            revisionsObserveList.add("Username: " + revision.username + "\t" + "Timestamp: " + revision.timestamp);
                        }
                    }
                    if (sortChoice.getValue().equals("Most Active")) {
                        RevisionsListSorter sorter = new RevisionsListSorter();
                        for (Revision revision : sorter.revisionsSort(revisionList)) {
                            revisionsObserveList.add("Username: " + revision.username + "\t" + "Timestamp: " + revision.timestamp);
                        }
                    }
                } catch (NullPointerException e) {
                    errors.noSortChoiceError(sortChoice.getValue());
                    return;
                }

                revisionsView.setItems(revisionsObserveList);


                URLConnection urlConnectionRedirects = new URLConnection(searched);
                errors.connectionError(urlConnectionRedirects.in);

                RevisionParser redParser = new RevisionParser();
                JsonArray redirectsArray = redParser.redirectsParser(urlConnectionRedirects.in);
                if(redirectsArray != null) {
                    List<Redirect> redirectList = redParser.createRedirectsList(redirectsArray);

                    ObservableList<String> redirectsObserveList = FXCollections.observableArrayList();

                    for (Redirect redirect : redirectList) {
                        redirectsObserveList.add("From: " + redirect.from + "\t" + "To: " + redirect.to);
                    }

                    redirectsView.setItems(redirectsObserveList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return new Scene(parent);
    }
}
