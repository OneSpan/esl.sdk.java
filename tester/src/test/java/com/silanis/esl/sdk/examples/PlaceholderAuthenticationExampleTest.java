package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Signer;
import org.junit.Test;

import static com.silanis.esl.sdk.AuthenticationMethod.CHALLENGE;
import static com.silanis.esl.sdk.AuthenticationMethod.QASMS;
import static com.silanis.esl.sdk.AuthenticationMethod.SMS;
import static com.silanis.esl.sdk.AuthenticationMethod.SSO;
import static com.silanis.esl.sdk.examples.PlaceholderAuthenticationExample.KBA_PLACEHOLDER_ID;
import static com.silanis.esl.sdk.examples.PlaceholderAuthenticationExample.QASMS_PLACEHOLDER_ID;
import static com.silanis.esl.sdk.examples.PlaceholderAuthenticationExample.QNA_PLACEHOLDER_ID;
import static com.silanis.esl.sdk.examples.PlaceholderAuthenticationExample.SMS_PLACEHOLDER_ID;
import static com.silanis.esl.sdk.examples.PlaceholderAuthenticationExample.SSO_PLACEHOLDER_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Verifies that OneSpan Sign accepts placeholders configured with SMS, KBA, Q&amp;A and QASMS
 * authentication even when the detailed information (phone number, questions/answers, KBA
 * identity information) is left unfilled, since a placeholder has no known identity yet.
 */
public class PlaceholderAuthenticationExampleTest {

    @Test
    public void verifyResult() {
        PlaceholderAuthenticationExample example = new PlaceholderAuthenticationExample();
        example.run();

        Signer smsPlaceholder = example.retrievedTemplate.getPlaceholder(SMS_PLACEHOLDER_ID);
        Signer ssoPlaceholder = example.retrievedTemplate.getPlaceholder(SSO_PLACEHOLDER_ID);
        Signer kbaPlaceholder = example.retrievedTemplate.getPlaceholder(KBA_PLACEHOLDER_ID);
        Signer qnaPlaceholder = example.retrievedTemplate.getPlaceholder(QNA_PLACEHOLDER_ID);
        Signer qasmsPlaceholder = example.retrievedTemplate.getPlaceholder(QASMS_PLACEHOLDER_ID);

        assertNotNull("SMS placeholder should be present", smsPlaceholder);
        assertNotNull("SSO placeholder should be present", ssoPlaceholder);
        assertNotNull("KBA placeholder should be present", kbaPlaceholder);
        assertNotNull("Q&A placeholder should be present", qnaPlaceholder);
        assertNotNull("QASMS placeholder should be present", qasmsPlaceholder);

        assertTrue("SMS placeholder should be a new placeholder signer", smsPlaceholder.isNewPlaceholderSigner());
        assertTrue("SSO placeholder should be a new placeholder signer", ssoPlaceholder.isNewPlaceholderSigner());
        assertTrue("KBA placeholder should be a new placeholder signer", kbaPlaceholder.isNewPlaceholderSigner());
        assertTrue("Q&A placeholder should be a new placeholder signer", qnaPlaceholder.isNewPlaceholderSigner());
        assertTrue("QASMS placeholder should be a new placeholder signer", qasmsPlaceholder.isNewPlaceholderSigner());

        // SMS authentication is accepted without a phone number.
        assertThat("SMS placeholder authentication method was not set correctly",
                smsPlaceholder.getAuthentication().getMethod(), is(SMS));
        assertThat("SMS placeholder should not require a phone number",
                smsPlaceholder.getAuthentication().getPhoneNumber(), nullValue());

        assertThat("SSO placeholder authentication method was not set correctly",
                ssoPlaceholder.getAuthentication().getMethod(), is(SSO));

        // KBA authentication is accepted without the detailed signer information.
        assertNotNull("KBA placeholder should have a knowledge based authentication entry",
                kbaPlaceholder.getKnowledgeBasedAuthentication());
        assertNotNull("KBA placeholder should have a LexisNexis signer information entry, even if empty",
                kbaPlaceholder.getKnowledgeBasedAuthentication().getSignerInformationForLexisNexis());

        // Q&A authentication is accepted without any questions or answers.
        assertThat("Q&A placeholder authentication method was not set correctly",
                qnaPlaceholder.getAuthentication().getMethod(), is(CHALLENGE));
        assertTrue("Q&A placeholder should not require any challenge questions",
                qnaPlaceholder.getAuthentication().getChallenges().isEmpty());

        // QASMS authentication is accepted without a phone number, questions or answers.
        assertThat("QASMS placeholder authentication method was not set correctly",
                qasmsPlaceholder.getAuthentication().getMethod(), is(QASMS));
        assertTrue("QASMS placeholder should not require any challenges",
                qasmsPlaceholder.getAuthentication().getChallenges().isEmpty());
    }
}
