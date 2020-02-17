package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DataManagementPolicy;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class DataManagementPolicyExampleTest {

    @Test
    public void verifyResult() {
        DataManagementPolicyExample example = new DataManagementPolicyExample();
        example.run();

        DataManagementPolicy dataManagementPolicy = example.getDataManagementPolicyAfterUpdate();

        // Verify if the DataManagementPolicy was updated correctly.
        assertThat(dataManagementPolicy.getTransactionRetention(), is(notNullValue()));
        assertThat(dataManagementPolicy.getTransactionRetention().getArchived(), is(60));
        assertThat(dataManagementPolicy.getTransactionRetention().getCompleted(), is(60));
        assertThat(dataManagementPolicy.getTransactionRetention().getDeclined(), is(60));
        assertThat(dataManagementPolicy.getTransactionRetention().getDraft(), is(60));
        assertThat(dataManagementPolicy.getTransactionRetention().getExpired(), is(60));
        assertThat(dataManagementPolicy.getTransactionRetention().getOptedOut(), is(60));
    }
}
