package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.examples.SignerVerificationExample.CERTIFICATE;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by schoi on 19/04/17.
 */
public class SignerVerificationExampleTest {

    @Test
    public void verifyResult() {
        SignerVerificationExample example = new SignerVerificationExample();
        example.run();

        assertThat("Signer Verification is not created correctly.", example.firstVerificationType, is(CERTIFICATE));
        assertThat("Signer Verification is not deleted correctly.", example.deletedVerificationType, isEmptyOrNullString());
    }
}
