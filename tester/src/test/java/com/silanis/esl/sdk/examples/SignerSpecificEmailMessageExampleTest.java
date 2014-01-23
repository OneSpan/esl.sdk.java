package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 23/01/14
 * Time: 11:54 AM
 * 
 * Test SignerSpecificEmailMessageExample.
 */
public class SignerSpecificEmailMessageExampleTest {
    @Test
    public void verifyResult() {
        SignerSpecificEmailMessageExample signerSpecificEmailMessageExample = new SignerSpecificEmailMessageExample(Props.get());
        signerSpecificEmailMessageExample.run();
        DocumentPackage documentPackage = signerSpecificEmailMessageExample.getEslClient().getPackage(signerSpecificEmailMessageExample.getPackageId());

        assertThat(documentPackage.getSigner(signerSpecificEmailMessageExample.email1).getMessage(), is(equalTo(SignerSpecificEmailMessageExample.EMAIL_MESSAGE)));
    }
}
