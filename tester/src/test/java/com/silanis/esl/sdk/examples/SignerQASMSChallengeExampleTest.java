package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.AuthenticationMethod.QASMS;
import static com.silanis.esl.sdk.examples.SignerQASMSChallengeExample.PHONE_NUMBER;
import static com.silanis.esl.sdk.examples.SignerQASMSChallengeExample.FIRST_QUESTION;
import static com.silanis.esl.sdk.examples.SignerQASMSChallengeExample.SECOND_QUESTION;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import com.silanis.esl.sdk.Challenge;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

/**
 * User: jessica
 * Date: 23/01/14
 * Time: 10:21 AM
 * <p/>
 * Test SignerQASMSChallengeExample.
 */
public class SignerQASMSChallengeExampleTest {
    @Test
    public void verifyResult() {
        SignerQASMSChallengeExample example = new SignerQASMSChallengeExample();
        example.run();
        DocumentPackage documentPackage = example.getRetrievedPackage();

        // Note that for security reasons, the backend doesn't return challenge answers, so we don't verify the answers here.
        for (Challenge challenge: documentPackage.getSigner(example.email1).getChallengeQuestions()) {
            assertTrue(challenge.getQuestion().contentEquals(FIRST_QUESTION)
                               || challenge.getQuestion().contentEquals(SECOND_QUESTION)
                                || challenge.getQuestion().contentEquals(PHONE_NUMBER));

        }
        assertThat(documentPackage.getSigner(example.email1).getAuthenticationMethod(), is(QASMS));
        assertThat(documentPackage.getSigner(example.email1).getChallengeQuestions().size(), is(3));

    }
}
