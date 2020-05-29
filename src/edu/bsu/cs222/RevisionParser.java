package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class RevisionParser {
    JsonArray revisionsParse(InputStream input) {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(input);
        JsonElement rootElement = parser.parse(reader);

        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray revisionsArray = null;
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            revisionsArray = entryObject.getAsJsonArray("revisions");
        }

        return revisionsArray;
    }

    List<Revision> createRevisionsList(JsonArray revisionsArray){
        List<Revision> revisionsList = new ArrayList<>();
        ConvertLocalTime convertTime = new ConvertLocalTime();
        String username = null;
        String timestamp = null;

        for(JsonElement rev : revisionsArray) {
            JsonObject revObject = rev.getAsJsonObject();
            for(Map.Entry<String, JsonElement> entry : revObject.entrySet()) {
                String entryKey = entry.getKey();
                JsonElement entryElement = entry.getValue();
                if (entryKey.equals("user")) {
                    username = entryElement.getAsString();
                }
                else if (entryKey.equals("timestamp")) {
                    timestamp = convertTime.convertLocalTime(entryElement.getAsString());
                }
            }

            Revision revision = new Revision(username, timestamp);
            revisionsList.add(revision);
        }

        return revisionsList;
    }

    JsonArray redirectsParser(InputStream input) {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(input);
        JsonElement rootElement = parser.parse(reader);

        JsonObject rootObject = rootElement.getAsJsonObject();
        return rootObject.getAsJsonObject("query").getAsJsonArray("redirects");
    }

    List<Redirect> createRedirectsList(JsonArray redirectsArray) {
        List<Redirect> redirectsList = new ArrayList<>();
        String from = null;
        String to = null;

        for(JsonElement red : redirectsArray) {
            JsonObject redirectObject = red.getAsJsonObject();
            for(Map.Entry<String, JsonElement> entry : redirectObject.entrySet()) {
                String entryKey = entry.getKey();
                JsonElement entryElement = entry.getValue();
                if(entryKey.equals("from")) {
                    from = entryElement.getAsString();
                }
                else if(entryKey.equals("to")) {
                    to = entryElement.getAsString();
                }
            }

            Redirect redirect = new Redirect(from, to);
            redirectsList.add(redirect);
        }
        return redirectsList;
    }
}
