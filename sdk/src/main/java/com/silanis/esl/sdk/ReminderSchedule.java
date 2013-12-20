package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.List;

public class ReminderSchedule {
    private PackageId packageId;
    private int daysUntilFirstReminder;
    private int daysBetweenReminders;
    private int numberOfRepetitions;
    private List<Reminder> reminders;

    public ReminderSchedule() {
        this.reminders = new ArrayList<Reminder>();
    }

    public PackageId getPackageId() {
        return packageId;
    }

    public void setPackageId( PackageId packageId ) {
        this.packageId = packageId;
    }

    public int getDaysUntilFirstReminder() {
        return daysUntilFirstReminder;
    }

    public void setDaysUntilFirstReminder( int daysUntilFirstReminder ) {
        this.daysUntilFirstReminder = daysUntilFirstReminder;
    }

    public int getDaysBetweenReminders() {
        return daysBetweenReminders;
    }

    public void setDaysBetweenReminders( int daysBetweenReminders ) {
        this.daysBetweenReminders = daysBetweenReminders;
    }

    public int getNumberOfRepetitions() {
        return numberOfRepetitions;
    }

    public void setNumberOfRepetitions( int numberOfRepetitions ) {
        this.numberOfRepetitions = numberOfRepetitions;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders( List<Reminder> reminders ) {
        this.reminders = reminders;
    }
}
