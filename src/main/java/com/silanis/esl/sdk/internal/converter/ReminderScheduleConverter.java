package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.PackageReminder;
import com.silanis.esl.api.model.PackageReminderSchedule;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Reminder;
import com.silanis.esl.sdk.ReminderSchedule;

public class ReminderScheduleConverter {
    final private PackageReminderSchedule api;
    final private ReminderSchedule sdk;

    public ReminderScheduleConverter( PackageReminderSchedule api ) {
        this.api = api;
        this.sdk = null;
    }

    public ReminderScheduleConverter( ReminderSchedule sdk ) {
        this.sdk = sdk;
        this.api = null;
    }

    public PackageReminderSchedule toAPIPackageReminderSchedule() {
        if ( api != null ) {
            return api;
        } else {
            PackageReminderSchedule result = new PackageReminderSchedule();
            if ( sdk.getPackageId() != null ) {
                result.setPackageId( sdk.getPackageId().getId() );
            }
            result.setStartInDaysDelay( sdk.getDaysUntilFirstReminder() );
            result.setIntervalInDays( sdk.getDaysBetweenReminders() );
            result.setRepetitionsCount( sdk.getNumberOfRepetitions() );

            for ( Reminder sdkReminder : sdk.getReminders() ) {
                result.getReminders().add( new ReminderConverter( sdkReminder ).toAPIPackageReminder() );
            }
            return result;
        }
    }

    public ReminderSchedule toSDKReminderSchedule() {
        if ( sdk != null ) {
            return sdk;
        } else {
            ReminderSchedule result = new ReminderSchedule();
            if ( api.getPackageId() != null && !api.getPackageId().equals( "" ) ) {
                result.setPackageId( new PackageId( api.getPackageId() ) );
            }
            result.setDaysBetweenReminders( api.getIntervalInDays() );
            result.setDaysUntilFirstReminder( api.getStartInDaysDelay() );
            result.setNumberOfRepetitions( api.getRepetitionsCount() );

            for ( PackageReminder apiReminder : api.getReminders() ) {
                result.getReminders().add( new ReminderConverter( apiReminder ).toSDKReminder() );
            }
            return result;
        }
    }


}
