package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.ReminderSchedule;

/**
 * Helper class useful to define the schedule at which reminder emails will be
 * sent out to signer who have not yet completed signing a given package.
 *
 */
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

    /**
     * Defines a new email reminder schedule for a given package.
     * @param packageId id of the package for which to apply the reminder schedule.
     * @return
     */
    public static ReminderScheduleBuilder forPackageWithId( String packageId ) {
        return forPackageWithId( new PackageId( packageId ) );
    }

    /**
     * Defines a new email reminder schedule for a given package.
     * @see #ReminderScheduleBuilder(PackageId)
     * @param packageId id of the package for which to apply the reminder schedule.
     * @return
     */
    public static ReminderScheduleBuilder forPackageWithId( PackageId packageId ) {
        return new ReminderScheduleBuilder( packageId );
    }

    /**
     * Set the number of days after the package is created and its status is set
     * to SENT when the first email reminder should be sent to signers who have
     * not completed signing their documents.
     *
     * @param daysUntilFirstReminder min="1" max="65535"
     * @return
     */
    public ReminderScheduleBuilder withDaysUntilFirstReminder( int daysUntilFirstReminder ) {
        this.daysUntilFirstReminder = daysUntilFirstReminder;
        return this;
    }

    /**
     * Set the number of days between sending of reminders.
     * @param daysBetweenReminders min="1"" max="65535"
     * @return
     */
    public ReminderScheduleBuilder withDaysBetweenReminders( int daysBetweenReminders ) {
        this.daysBetweenReminders = daysBetweenReminders;
        return this;
    }

    /**
     * Set the maximum number of reminders that should be sent if a signer has
     * not completed his signing process.
     *
     * @param numberOfRepetitions min="1"" max="65535"
     * @return
     */
    public ReminderScheduleBuilder withNumberOfRepetitions( int numberOfRepetitions ) {
        this.numberOfRepetitions = numberOfRepetitions;
        return this;
    }

    /**
     * Builds the actual ReminderSchedule with the values specified.
     *
     * @return
     */

    public ReminderSchedule build() {
        ReminderSchedule result = new ReminderSchedule();
        result.setPackageId( packageId );
        result.setDaysUntilFirstReminder( daysUntilFirstReminder );
        result.setDaysBetweenReminders( daysBetweenReminders );
        result.setNumberOfRepetitions( numberOfRepetitions );

        return result;
    }
}
