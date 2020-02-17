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

        ExpiryTimeConfiguration expiryTimeConfiguration = example.getExpiryTimeConfigurationAfterUpdate();

        // Verify if the ExpiryTimeConfiguration was updated correctly.
        assertThat(expiryTimeConfiguration.getMaximumRemainingDays(), is(60));
        assertThat(expiryTimeConfiguration.getRemainingDays(), is(60));
    }
}
