package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.SignerVerification;
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


        SignerVerification retrievedSignerVerification = example.retrievedSignerVerification;


        assertThat("Signer Verification is not created correctly.", example.VERIFICATION_TYPE_ID, is(retrievedSignerVerification.getTypeId()));
        assertThat("Signer Verification is not deleted correctly.", example.VERIFICATION_PAYLOAD, is(retrievedSignerVerification.getPayload()));
    }
}
