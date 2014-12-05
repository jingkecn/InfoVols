package com.company.utilitaires;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by King on 2014-11-19.
 */
public class TimeFormatConverter {
    private static DateFormat formatter;
    private static Date time;
    private static String timeStr;

    public static Date getTime(String timeStr, String format) throws ParseException {
        formatter = new SimpleDateFormat(format);
        time = formatter.parse(timeStr);
        return time;
    }

    public static String formatTime(Date time, String format) {
        formatter = new SimpleDateFormat(format);
        timeStr = formatter.format(time);
        return timeStr;
    }
}
