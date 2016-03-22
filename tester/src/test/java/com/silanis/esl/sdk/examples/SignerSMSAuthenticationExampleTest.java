package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.AuthenticationMethod.SMS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 23/01/14
 * Time: 11:33 AM
 * 
 * Test SignerSMSAuthenticationExample.
 * 
 */
public class SignerSMSAuthenticationExampleTest {
    @Test
    public void verifyResult() {
        SignerSMSAuthenticationExample example = new SignerSMSAuthenticationExample();
        example.run();
        DocumentPackage documentPackage = example.getRetrievedPackage();

        assertThat(documentPackage.getSigner(example.email1).getAuthenticationMethod(), is(SMS));
    }
}
