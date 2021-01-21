package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.NotaryHostThankYouOptions;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class NotaryHostThankYouOptionsBuilderTest {
    @Test
    public void buildWithSpecifiedValues() {
        NotaryHostThankYouOptionsBuilder builder = NotaryHostThankYouOptionsBuilder.newNotaryHostThankYouOptions();
        builder.withTitle();
        builder.withBody();
        builder.withRecipientName();
        builder.withRecipientEmail();
        builder.withRecipientRole();
        builder.withNotaryTag();
        builder.withRecipientStatus();
        builder.withDownloadButton();
        builder.withReviewDocumentsButton();

        NotaryHostThankYouOptions result = builder.build();
        assertThat( "build returned a null object", result, is( notNullValue() ) );
        assertTrue( "'title' was not set correctly", result.getTitle());
        assertTrue( "'body' was not set correctly", result.getBody());
        assertTrue( "'recipientName' was not set correctly", result.getRecipientName());
        assertTrue( "'recipientEmail' was not set correctly", result.getRecipientEmail());
        assertTrue( "'recipientRole' was not set correctly", result.getRecipientRole());
        assertTrue( "'notaryTag' was not set correctly", result.getNotaryTag());
        assertTrue( "'recipientStatus' was not set correctly", result.getRecipientStatus());
        assertTrue( "'downloadButton' was not set correctly", result.getDownloadButton());
        assertTrue( "'reviewDocumentsButton' was not set correctly", result.getReviewDocumentsButton());

    }
}
