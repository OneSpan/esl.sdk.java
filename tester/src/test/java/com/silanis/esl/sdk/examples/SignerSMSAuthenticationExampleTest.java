package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationMethod;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
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
        SignerSMSAuthenticationExample signerSMSAuthenticationExample = new SignerSMSAuthenticationExample(Props.get());
        signerSMSAuthenticationExample.run();
        DocumentPackage documentPackage = signerSMSAuthenticationExample.getEslClient().getPackage(signerSMSAuthenticationExample.getPackageId());

        assertThat(documentPackage.getSigner(signerSMSAuthenticationExample.email1).getAuthenticationMethod(), is(equalTo(AuthenticationMethod.SMS)));
    }
    
}
