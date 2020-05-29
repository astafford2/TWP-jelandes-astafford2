package edu.bsu.cs222;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

class URLConnection {
    InputStream in;
    URLConnection(String searched) throws Exception {
        String encodedSearched = URLEncoder.encode(searched, "UTF-8");
        URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles="+ encodedSearched +
                "&rvprop=timestamp|user&rvlimit=24&redirects");
        java.net.URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Revision Tracker/0.1 (astafford2@bsu.edu)");
        try {
            in = connection.getInputStream();
        }
        catch (Exception e){
            in = null;
        }
    }
}
