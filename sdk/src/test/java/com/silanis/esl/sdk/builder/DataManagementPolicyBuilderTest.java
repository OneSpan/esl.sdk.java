package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.DataManagementPolicy;
import com.silanis.esl.sdk.TransactionRetention;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DataManagementPolicyBuilderTest {

    @Test
    public void withSpecifiedValues() {
        TransactionRetention retention = TransactionRetentionBuilder.newTransactionRetention().build();
        DataManagementPolicyBuilder builder = DataManagementPolicyBuilder.newDataManagementPolicy()
                .withTransactionRetention( retention );

        DataManagementPolicy result = builder.build();

        assertThat( "build returned a null object", result, is( Matchers.notNullValue() ) );
        assertThat( "Transaction retention returned a null object", result.getTransactionRetention(), is( Matchers.notNullValue() ) );
    }
}
