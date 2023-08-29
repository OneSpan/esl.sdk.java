package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class AccountEmailReminderSettingsExampleTest {

    @Test
    public void verifyResult() {

        AccountEmailReminderSettingsExample example = new AccountEmailReminderSettingsExample();
        example.run();

        //email reminder settings
        assertNotNull("'intervalInDays' in AccountEmailReminderSettings should be returned", example.defaultAccountEmailReminderSettings.getIntervalInDays());
        assertNotNull("'repetitionsCount' in AccountEmailReminderSettings should be returned", example.defaultAccountEmailReminderSettings.getRepetitionsCount());
        assertNotNull("'startInDaysDelay' in AccountEmailReminderSettings should be returned", example.defaultAccountEmailReminderSettings.getStartInDaysDelay());

        assertTrue("'intervalInDays' in AccountEmailReminderSettings should be updated correctly", example.patchedAccountEmailReminderSettings.getIntervalInDays() == 400);
        assertTrue("'repetitionsCount' in AccountEmailReminderSettings should be updated correctly", example.patchedAccountEmailReminderSettings.getRepetitionsCount() == 2);
        assertTrue("'startInDaysDelay' in AccountEmailReminderSettings should be updated correctly", example.patchedAccountEmailReminderSettings.getStartInDaysDelay() == 20);

        assertTrue("'intervalInDays' in AccountEmailReminderSettings should be updated correctly", example.patchedAccountEmailReminderSettings1.getIntervalInDays() == 30);
        assertTrue("'repetitionsCount' in AccountEmailReminderSettings should be updated correctly", example.patchedAccountEmailReminderSettings1.getRepetitionsCount() == 2);
        assertTrue("'startInDaysDelay' in AccountEmailReminderSettings should be updated correctly", example.patchedAccountEmailReminderSettings1.getStartInDaysDelay() == 5);

    }
}
