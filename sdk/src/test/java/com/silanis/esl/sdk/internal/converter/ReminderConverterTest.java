package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.PackageReminder;
import com.silanis.esl.sdk.Reminder;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class ReminderConverterTest {
    @Test
    public void toAPI() {

        Date date = Calendar.getInstance().getTime();
        Calendar sentCalendar = Calendar.getInstance();
        sentCalendar.add( Calendar.MONTH, 1  );
        Date sentDate = sentCalendar.getTime();
        Reminder sdk = new Reminder( date, sentDate );
        PackageReminder api = new ReminderConverter( sdk ).toAPIPackageReminder();

        assertThat( api, is( notNullValue() ) );
        assertThat( api.getDate(), is( equalTo( sdk.getDate() ) ) );
        assertThat( api.getSentDate(), is( equalTo( sdk.getSentDate() ) ) );
    }

    @Test
    public void toAPIWithNullSentDate() {
        Date date = Calendar.getInstance().getTime();
        Reminder sdk = new Reminder( date, null );
        PackageReminder api = new ReminderConverter( sdk ).toAPIPackageReminder();

        assertThat( api, is( notNullValue() ) );
        assertThat( api.getDate(), is( equalTo( sdk.getDate() ) ) );
        assertThat( api.getSentDate(), is( nullValue() ) );
    }

    @Test
    public void toSDK() {
        Date date = Calendar.getInstance().getTime();
        Calendar sentCalendar = Calendar.getInstance();
        sentCalendar.add( Calendar.MONTH, 1  );
        Date sentDate = sentCalendar.getTime();
        PackageReminder api = new PackageReminder();
        api.setDate( date );
        api.setSentDate( sentDate );
        Reminder sdk = new ReminderConverter( api ).toSDKReminder();

        assertThat( sdk, is( notNullValue() ) );
        assertThat( sdk.getDate(), is( equalTo( api.getDate() ) ) );
        assertThat( sdk.getSentDate(), is( equalTo( api.getSentDate() ) ) );
    }

    @Test
    public void toSDKWithNullSentDate() {
        Date date = Calendar.getInstance().getTime();
        PackageReminder api = new PackageReminder();
        api.setDate( date );
        api.setSentDate( null );
        Reminder sdk = new ReminderConverter( api ).toSDKReminder();

        assertThat( sdk, is( notNullValue() ) );
        assertThat( sdk.getDate(), is( equalTo( api.getDate() ) ) );
        assertThat( sdk.getSentDate(), is( nullValue() ) );
    }
}
