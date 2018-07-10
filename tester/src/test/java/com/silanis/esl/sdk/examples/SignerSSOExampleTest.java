package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.AuthenticationMethod.SSO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

/**
 * Created by schoi on 09/07/18.
 */
public class SignerSSOExampleTest {
    @Test
    public void verifyResult() {
        SignerSSOExample example = new SignerSSOExample();
        example.run();
        DocumentPackage documentPackage = example.getRetrievedPackage();

        assertThat(documentPackage.getSigner(example.email4).getAuthenticationMethod(), is(SSO));
        assertThat(documentPackage.getSigner(example.email4).getChallengeQuestions(), hasSize(0));
    }
}