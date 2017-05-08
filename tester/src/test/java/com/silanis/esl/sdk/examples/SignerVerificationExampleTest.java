package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by schoi on 19/04/17.
 */
public class SignerVerificationExampleTest {

    @Test
    public void verifyResult() {
        SignerVerificationExample example = new SignerVerificationExample();
        example.run();

        assertThat("Signer Verification is not set correctly.", example.sentPackage.getSigner(example.email1).getVerificationType(), is("CERTIFICATE"));
    }
}
