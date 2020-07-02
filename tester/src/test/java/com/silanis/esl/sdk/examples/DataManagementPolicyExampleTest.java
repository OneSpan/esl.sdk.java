package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class DataManagementPolicyExampleTest {

    @Test
    public void verifyResult() {
        DataManagementPolicyExample example = new DataManagementPolicyExample();
        example.run();

        // Verify if the DataManagementPolicy was updated correctly.
        assertThat(example.dataManagementPolicyAfterUpdate.getTransactionRetention(), is(notNullValue()));
        assertThat(example.dataManagementPolicyAfterUpdate.getTransactionRetention().getArchived(), is(60));
        assertThat(example.dataManagementPolicyAfterUpdate.getTransactionRetention().getCompleted(), is(60));
        assertThat(example.dataManagementPolicyAfterUpdate.getTransactionRetention().getDeclined(), is(60));
        assertThat(example.dataManagementPolicyAfterUpdate.getTransactionRetention().getDraft(), is(60));
        assertThat(example.dataManagementPolicyAfterUpdate.getTransactionRetention().getExpired(), is(60));
        assertThat(example.dataManagementPolicyAfterUpdate.getTransactionRetention().getOptedOut(), is(60));
    }
}
