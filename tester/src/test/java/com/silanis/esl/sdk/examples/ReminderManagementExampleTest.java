package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.ReminderSchedule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

public class ReminderManagementExampleTest {
    @Test
    public void verifyResult() {
        ReminderManagementExample reminderExample = new ReminderManagementExample( Props.get() );
        reminderExample.run();

        // Verify that a reminder schedule has been created for this package -- we need the packageId for that
        ReminderSchedule reminderSchedule = reminderExample.getEslClient().getReminderService().getReminderScheduleForPackage( reminderExample.getPackageId() );
        assertThat( reminderSchedule, is( nullValue() ) );
    }
}
