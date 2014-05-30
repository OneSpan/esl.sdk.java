package com.silanis.esl.sdk.internal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by lena on 2014-05-28.
 */
public class DateHelper {

    /**
     * Convert java.util.Date to ISO UTC date format.
     * Acceptable date format: 2010-01-01T12:30:00Z
     *
     * @param date
     * @return the string date in ISO UTC format
     */
    public static String dateToIsoUtcFormat(Date date) {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(timeZone);

        return dateFormat.format(date);
    }

}
