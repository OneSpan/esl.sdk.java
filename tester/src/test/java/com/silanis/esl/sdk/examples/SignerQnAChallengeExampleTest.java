package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Challenge;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.AuthenticationMethod.CHALLENGE;
import static com.silanis.esl.sdk.AuthenticationMethod.QASMS;
import static com.silanis.esl.sdk.examples.SignerQnAChallengeExample.FIRST_QUESTION;
import static com.silanis.esl.sdk.examples.SignerQnAChallengeExample.SECOND_QUESTION;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * User: jessica
 * Date: 23/01/14
 * Time: 10:21 AM
 * <p/>
 * Test SignerQnAChallengeExample.
 */
public class SignerQnAChallengeExampleTest {
    @Test
    public void verifyResult() {
        SignerQnAChallengeExample example = new SignerQnAChallengeExample();
        example.run();
        DocumentPackage documentPackage = example.getRetrievedPackage();

        // Note that for security reasons, the backend doesn't return challenge answers, so we don't verify the answers here.
        for (Challenge challenge: documentPackage.getSigner(example.email1).getChallengeQuestions()) {
            assertTrue(challenge.getQuestion().contentEquals(FIRST_QUESTION)
                               || challenge.getQuestion().contentEquals(SECOND_QUESTION));

        }
        assertThat(documentPackage.getSigner(example.email1).getAuthenticationMethod(), is(CHALLENGE));
        assertThat(documentPackage.getSigner(example.email1).getChallengeQuestions().size(), is(2));
    }
}
