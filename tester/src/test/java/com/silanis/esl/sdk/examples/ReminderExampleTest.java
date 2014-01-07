package com.silanis.esl.sdk.examples;

import org.junit.Test;

public class ReminderExampleTest {
    @Test
    public void verifyResult() {
        ReminderExample reminderExample = new ReminderExample( Props.get() );
        reminderExample.run();
    }
}
