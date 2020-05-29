package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

public class ConvertLocalTimeTest {

    @Test
    public void testConvertLocalTime() {
        final ConvertLocalTime convertTime = new ConvertLocalTime();
        final String input = "2019-08-02T19:38:23Z";
        final String output = convertTime.convertLocalTime(input);
        Assert.assertEquals("2019-08-02 15:38:23", output);
    }
}
