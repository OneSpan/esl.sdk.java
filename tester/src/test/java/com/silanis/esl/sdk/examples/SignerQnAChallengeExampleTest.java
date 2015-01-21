package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Challenge;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
        SignerQnAChallengeExample signerQnAChallengeExample = new SignerQnAChallengeExample(Props.get());
        signerQnAChallengeExample.run();
        DocumentPackage documentPackage = signerQnAChallengeExample.getRetrievedPackage();

        // Note that for security reasons, the backend doesn't return challenge answers, so we don't verify the answers here.
        for (Challenge challenge: documentPackage.getSigner(signerQnAChallengeExample.email1).getChallengeQuestions()) {
            assertThat(challenge.getQuestion().contentEquals(SignerQnAChallengeExample.FIRST_QUESTION)
                    || challenge.getQuestion().contentEquals(SignerQnAChallengeExample.SECOND_QUESTION), is(equalTo(true)));

        }
    }

}
