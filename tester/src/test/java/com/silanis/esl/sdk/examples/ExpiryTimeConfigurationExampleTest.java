package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.ExpiryTimeConfiguration;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExpiryTimeConfigurationExampleTest {

    @Test
    public void verifyResult() {
        ExpiryTimeConfigurationExample example = new ExpiryTimeConfigurationExample();
        example.run();

        // Verify if the ExpiryTimeConfiguration was updated correctly.
        assertThat(example.expiryTimeConfigurationAfterUpdate.getMaximumRemainingDays(), is(60));
        assertThat(example.expiryTimeConfigurationAfterUpdate.getRemainingDays(), is(60));
    }
}
