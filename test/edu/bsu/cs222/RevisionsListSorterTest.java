package edu.bsu.cs222;

import com.google.gson.JsonArray;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RevisionsListSorterTest {

    @Test
    public void testSortedListLength() throws Exception {
        final RevisionsListSorter revSort = new RevisionsListSorter();
        URLConnection urlConnection = new URLConnection("Zappa");
        RevisionParser revParser = new RevisionParser();
        JsonArray revisionsArray = revParser.revisionsParse(urlConnection.in);
        final List<Revision> input = revParser.createRevisionsList(revisionsArray);
        final List<Revision> output = revSort.revisionsSort(input);
        Assert.assertEquals(24, output.size());
    }
}
