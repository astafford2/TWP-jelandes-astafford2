package edu.bsu.cs222;

import com.google.gson.JsonArray;
import javafx.scene.control.Alert;

import java.io.InputStream;

class ErrorHandling {
    void connectionError(InputStream in) {
        if(in == null) {
            Alert noConnect = new Alert(Alert.AlertType.ERROR);
            noConnect.setTitle("Connection Error");
            noConnect.setHeaderText("A connection to Wikipedia could not be made.\n" +
                    "Please check your network connection and try again later.");
            noConnect.showAndWait();
        }
    }

    void pageError(JsonArray revisionsArray) {
        if(revisionsArray == null) {
            Alert noPage = new Alert(Alert.AlertType.ERROR);
            noPage.setTitle("Page Error");
            noPage.setHeaderText("There was no page by this name found,\n" +
                    "please try a different search.");
            noPage.showAndWait();
        }
    }

    void noSortChoiceError(String sortChoice){
        if(sortChoice == null) {
            Alert noChoice = new Alert(Alert.AlertType.ERROR);
            noChoice.setTitle("No Choice Made");
            noChoice.setHeaderText("There was no sorting choice made, \n" +
                    "please make a choice and try again.");
            noChoice.showAndWait();
        }
    }
}
