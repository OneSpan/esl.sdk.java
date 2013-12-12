package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.PackageReminder;
import com.silanis.esl.api.model.PackageReminderSchedule;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Reminder;
import com.silanis.esl.sdk.ReminderSchedule;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReminderScheduleConverterTest {
    @Test
    public void toAPIWithNoIDAndNoReminders() {
        ReminderSchedule sdk = new ReminderSchedule();
        sdk.setNumberOfRepetitions( 5 );
        sdk.setDaysUntilFirstReminder( 10 );
        sdk.setDaysBetweenReminders( 15 );
        PackageReminderSchedule api = new ReminderScheduleConverter( sdk ).toAPIPackageReminderSchedule();

        assertThat( api.getPackageId(), is( equalTo( "" ) ) );
        assertThat( api.getRepetitionsCount(), is( equalTo( sdk.getNumberOfRepetitions() ) ) );
        assertThat( api.getStartInDaysDelay(), is( equalTo( sdk.getDaysUntilFirstReminder() ) ) );
        assertThat( api.getIntervalInDays(), is( equalTo( sdk.getDaysBetweenReminders() ) ) );
        assertThat( api.getReminders(), is( notNullValue() ) );
        assertThat( api.getReminders().isEmpty(), is( true ) );
    }

    @Test
    public void toAPI() {
        ReminderSchedule sdk = new ReminderSchedule();
        sdk.setPackageId( new PackageId( "bob" ) );
        sdk.setNumberOfRepetitions( 5 );
        sdk.setDaysUntilFirstReminder( 10 );
        sdk.setDaysBetweenReminders( 15 );
        Calendar calendar1 = Calendar.getInstance();
        sdk.getReminders().add( new Reminder( calendar1.getTime(), calendar1.getTime() ) );
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add( Calendar.HOUR, 24 );
        sdk.getReminders().add( new Reminder( calendar2.getTime(), calendar2.getTime() ) );
        Calendar calendar3 = Calendar.getInstance();
        calendar3.add( Calendar.HOUR, 48 );
        sdk.getReminders().add( new Reminder( calendar3.getTime(), calendar3.getTime() ) );

        PackageReminderSchedule api = new ReminderScheduleConverter( sdk ).toAPIPackageReminderSchedule();

        assertThat( api.getPackageId(), is( notNullValue() ) );
        assertThat( api.getPackageId(), is( equalTo( "bob" ) ) );
        assertThat( api.getRepetitionsCount(), is( equalTo( sdk.getNumberOfRepetitions() ) ) );
        assertThat( api.getStartInDaysDelay(), is( equalTo( sdk.getDaysUntilFirstReminder() ) ) );
        assertThat( api.getIntervalInDays(), is( equalTo( sdk.getDaysBetweenReminders() ) ) );
        assertThat( api.getReminders(), is( notNullValue() ) );
        assertThat( api.getReminders().size(), is( equalTo( sdk.getReminders().size() ) ) );

        for ( Reminder reminder : sdk.getReminders() ) {
            PackageReminder apiReminder = null;
            for ( PackageReminder packageReminder : api.getReminders() ) {
                if ( packageReminder.getDate().equals( reminder.getDate() ) && packageReminder.getSentDate().equals( reminder.getSentDate() ) ) {
                    apiReminder = packageReminder;
                    break;
                }
            }
            assertThat( apiReminder, is( notNullValue() ) );
        }
    }

    @Test
    public void toSDKWithNoIDAndNoReminders() {
        PackageReminderSchedule api = new PackageReminderSchedule();
        api.setRepetitionsCount( 5 );
        api.setIntervalInDays( 10 );
        api.setStartInDaysDelay( 15 );
        ReminderSchedule sdk = new ReminderScheduleConverter( api ).toSDKReminderSchedule();

        assertThat( sdk.getPackageId(), is( nullValue() ) );
        assertThat( sdk.getNumberOfRepetitions(), is( equalTo( api.getRepetitionsCount() ) ) );
        assertThat( sdk.getDaysUntilFirstReminder(), is( equalTo( api.getStartInDaysDelay() ) ) );
        assertThat( sdk.getDaysBetweenReminders(), is( equalTo( api.getIntervalInDays() ) ) );
        assertThat( sdk.getReminders(), is( notNullValue() ) );
        assertThat( sdk.getReminders().isEmpty(), is( true ) );
    }

    @Test
    public void toSDK() {
        PackageReminderSchedule api = new PackageReminderSchedule();
        api.setPackageId( "bob" );
        api.setRepetitionsCount( 5 );
        api.setIntervalInDays( 10 );
        api.setStartInDaysDelay( 15 );
        Calendar calendar1 = Calendar.getInstance();
        api.getReminders().add( new PackageReminder().setDate( calendar1.getTime() ).setSentDate( calendar1.getTime() ) );
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add( Calendar.HOUR, 24 );
        api.getReminders().add( new PackageReminder().setDate( calendar2.getTime() ).setSentDate( calendar2.getTime() ) );
        Calendar calendar3 = Calendar.getInstance();
        calendar3.add( Calendar.HOUR, 48 );
        api.getReminders().add( new PackageReminder().setDate( calendar3.getTime() ).setSentDate( calendar3.getTime() ) );
        ReminderSchedule sdk = new ReminderScheduleConverter( api ).toSDKReminderSchedule();

        assertThat( sdk.getPackageId(), is( notNullValue() ) );
        assertThat( sdk.getPackageId().getId(), is( equalTo( "bob" ) ) );
        assertThat( sdk.getNumberOfRepetitions(), is( equalTo( api.getRepetitionsCount() ) ) );
        assertThat( sdk.getDaysUntilFirstReminder(), is( equalTo( api.getStartInDaysDelay() ) ) );
        assertThat( sdk.getDaysBetweenReminders(), is( equalTo( api.getIntervalInDays() ) ) );
        assertThat( sdk.getReminders(), is( notNullValue() ) );
        assertThat( sdk.getReminders().size(), is( equalTo( api.getReminders().size() ) ) );

        for ( PackageReminder packageReminder : api.getReminders() ) {
            Reminder sdkReminder = null;
            for ( Reminder reminder : sdk.getReminders() ) {
                if ( packageReminder.getDate().equals( reminder.getDate() ) && packageReminder.getSentDate().equals( reminder.getSentDate() ) ) {
                    sdkReminder = reminder;
                    break;
                }
            }
            assertThat( sdkReminder, is( notNullValue() ) );
        }
    }
}
