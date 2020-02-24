package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.TransactionRetention;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TransactionRetentionBuilderTest {

    @Test
    public void withSpecifiedValues() {
        TransactionRetentionBuilder builder = TransactionRetentionBuilder.newTransactionRetention()
                .withArchived(10)
                .withCompleted(20)
                .withDeclined(30)
                .withDraft(40)
                .withExpired(50)
                .withOptedOut(60)
                .withSent(70);

        TransactionRetention result = builder.build();

        assertThat( "build returned a null object", result, is( Matchers.notNullValue() ) );
        assertThat( "Archived value is not set properly", result.getArchived(), is( 10 ) );
        assertThat( "Completed value is not set properly", result.getCompleted(), is( 20 ) );
        assertThat( "Declined value is not set properly", result.getDeclined(), is( 30 ) );
        assertThat( "Draft value is not set properly", result.getDraft(), is( 40 ) );
        assertThat( "Expired value is not set properly", result.getExpired(), is( 50 ) );
        assertThat( "OptedOut value is not set properly", result.getOptedOut(), is( 60 ) );
        assertThat( "Sent value is not set properly", result.getSent(), is( 70 ) );
    }
}
