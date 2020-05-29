package edu.bsu.cs222;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

class ConvertLocalTime {
    String convertLocalTime(String timestamp) {
        SimpleDateFormat timestampForm = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        timestampForm.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = new Date();
        try {
            date = timestampForm.parse(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        timestampForm.setTimeZone(TimeZone.getDefault());

        Date localDate = new Date();
        String localTime = timestampForm.format(date.getTime());
        try {
            localDate = timestampForm.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat localDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(localDate);
    }
}
