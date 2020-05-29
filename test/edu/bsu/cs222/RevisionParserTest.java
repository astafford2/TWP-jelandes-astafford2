package edu.bsu.cs222;

import com.google.gson.JsonArray;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

public class RevisionParserTest {

    @Test
    public void testParserReturnRevisionObject() {
        final RevisionParser revisionParser = new RevisionParser();
        final InputStream input = getClass().getClassLoader().getResourceAsStream("sample.json");
        final JsonArray output = revisionParser.revisionsParse(input);
        Assert.assertEquals(4, output.size());
    }

    @Test
    public void testParserReturnFromURLConnection() throws Exception {
        final RevisionParser revisionParser = new RevisionParser();
        URLConnection urlConnection = new URLConnection("Zappa");
        final InputStream input = urlConnection.in;
        final JsonArray output = revisionParser.revisionsParse(input);
        Assert.assertEquals(24, output.size());
    }
}
