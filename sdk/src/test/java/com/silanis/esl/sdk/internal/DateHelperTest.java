package com.silanis.esl.sdk.internal;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by lena on 2014-05-29.
 */
public class DateHelperTest {

    static final String expectedDateInUTC = "2010-01-01T12:30:00Z";

    @Test
    public void dateToIsoUtcFormatTest() {
        Calendar calendar = new GregorianCalendar(2009, 12, 1, 12, 30, 0);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = calendar.getTime();

        String result = DateHelper.dateToIsoUtcFormat(date);

        assertThat("Date format is not correct.", result, is(expectedDateInUTC));
    }
}
