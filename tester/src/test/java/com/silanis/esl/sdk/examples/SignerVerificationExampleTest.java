package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.SignerVerification;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by schoi on 19/04/17.
 */
public class SignerVerificationExampleTest {

    @Test
    public void verifyResult() {
        SignerVerificationExample example = new SignerVerificationExample();
        example.run();

        // Create
        SignerVerification actualAfterCreate = example.retrievedSignerVerificationAfterCreate;
        SignerVerification expectedAfterCreate = example.signerVerificationToBeCreated;


        assertThat("Retrieved Signer Verification 'typeId' doesn't match with the created one.",
                actualAfterCreate.getTypeId(), is(expectedAfterCreate.getTypeId()));

        assertThat("Retrieved Signer Verification 'payload' doesn't match with the created one.",
                actualAfterCreate.getPayload(), is(expectedAfterCreate.getPayload()));


        // Update
        SignerVerification actualAfterUpdate = example.retrievedSignerVerificationAfterUpdate;
        SignerVerification expectedAfterUpdate = example.signerVerificationToBeUpdated;


        assertThat("Retrieved Signer Verification 'typeId' doesn't match with the updated one.",
                actualAfterUpdate.getTypeId(), is(expectedAfterUpdate.getTypeId()));

        assertThat("Retrieved Signer Verification 'payload' doesn't match with the updated one.",
                actualAfterUpdate.getPayload(), is(expectedAfterUpdate.getPayload()));


        // Update
        SignerVerification actualAfterDelete = example.retrievedSignerVerificationAfterDelete;


        assertThat("Retrieved Signer Verification should be null after deleting it.",
                actualAfterDelete, nullValue());
    }
}
