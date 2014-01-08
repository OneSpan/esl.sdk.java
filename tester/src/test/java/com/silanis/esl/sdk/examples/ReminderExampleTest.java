package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.ReminderSchedule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class ReminderExampleTest {
    @Test
    public void verifyResult() {
        ReminderExample reminderExample = new ReminderExample( Props.get() );
        reminderExample.run();

        // Verify that a reminder schedule has been created for this package -- we need the packageId for that
        ReminderSchedule reminderSchedule = reminderExample.getEslClient().getReminderService().getReminderScheduleForPackage( reminderExample.getPackageId() );
        assertThat( reminderSchedule, is( notNullValue() ) );
        assertThat( reminderSchedule.getPackageId(), is( equalTo( reminderExample.getPackageId() ) ) );
        assertThat( reminderSchedule.getDaysUntilFirstReminder(), is( equalTo( reminderExample.getReminderSchedule().getDaysUntilFirstReminder() ) ) );
        assertThat( reminderSchedule.getDaysBetweenReminders(), is( equalTo( reminderExample.getReminderSchedule().getDaysBetweenReminders() ) ) );
        assertThat( reminderSchedule.getNumberOfRepetitions(), is( equalTo( reminderExample.getReminderSchedule().getNumberOfRepetitions() ) ) );
    }
}
