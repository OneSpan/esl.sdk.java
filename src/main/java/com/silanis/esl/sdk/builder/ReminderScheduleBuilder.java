package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.ReminderSchedule;

public class ReminderScheduleBuilder {
    private PackageId packageId;
    public static final int DEFAULT_DAYS_UNTIL_FIRST_REMINDER = 1;
    private int daysUntilFirstReminder;
    public static final int DEFAULT_DAYS_BETWEEN_REMINDERS = 1;
    private int daysBetweenReminders;
    public static final int DEFAULT_NUMBER_OF_REPETITIONS = 1;
    private int numberOfRepetitions;

    private ReminderScheduleBuilder( PackageId packageId ) {
        this.packageId = packageId;
        daysUntilFirstReminder = DEFAULT_DAYS_UNTIL_FIRST_REMINDER;
        daysBetweenReminders = DEFAULT_DAYS_BETWEEN_REMINDERS;
        numberOfRepetitions = DEFAULT_NUMBER_OF_REPETITIONS;
    }

    public static ReminderScheduleBuilder forPackageWithId( String packageId ) {
        return forPackageWithId( new PackageId( packageId ) );
    }

    public static ReminderScheduleBuilder forPackageWithId( PackageId packageId ) {
        return new ReminderScheduleBuilder( packageId );
    }

    public ReminderScheduleBuilder withDaysUntilFirstReminder( int daysUntilFirstReminder ) {
        this.daysUntilFirstReminder = daysUntilFirstReminder;
        return this;
    }

    public ReminderScheduleBuilder withDaysBetweenReminders( int daysBetweenReminders ) {
        this.daysBetweenReminders = daysBetweenReminders;
        return this;
    }

    public ReminderScheduleBuilder withNumberOfRepetitions( int numberOfRepetitions ) {
        this.numberOfRepetitions = numberOfRepetitions;
        return this;
    }

    public ReminderSchedule build() {
        ReminderSchedule result = new ReminderSchedule();
        result.setPackageId( packageId );
        result.setDaysUntilFirstReminder( daysUntilFirstReminder );
        result.setDaysBetweenReminders( daysBetweenReminders );
        result.setNumberOfRepetitions( numberOfRepetitions );

        return result;
    }
}
