package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Verification;
import com.silanis.esl.sdk.SignerVerification;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by aafrasiabian on 23/05/17.
 */
public class SignerVerificationConverterTest {

    @Test
    public void convertNullAPIToSDK() {
        SignerVerification signerVerification = new SignerVerificationConverter().toSDKSignerVerification(null);

        assertThat(signerVerification, nullValue());
    }

    @Test
    public void convertNullSDKToAPI() {
        Verification verification = new SignerVerificationConverter().toAPISignerVerification(null);

        assertThat(verification, nullValue());
    }

    @Test
    public void convertSDKToAPI() {
        String expectedTypeId = "dummy type id";
        String expectedPayload = "dummy payload";

        SignerVerification signerVerification = new SignerVerification();
        signerVerification.setTypeId(expectedTypeId);
        signerVerification.setPayload(expectedPayload);


        Verification actual = new SignerVerificationConverter().toAPISignerVerification(signerVerification);


        assertThat(actual.getTypeId(), is(expectedTypeId));
        assertThat(actual.getPayload(), is(expectedPayload));
    }

    @Test
    public void convertAPIToSDK() {
        String expectedTypeId = "dummy type id";
        String expectedPayload = "dummy payload";

        Verification verification = new Verification();
        verification.setTypeId(expectedTypeId);
        verification.setPayload(expectedPayload);


        SignerVerification actual = new SignerVerificationConverter().toSDKSignerVerification(verification);


        assertThat(actual.getTypeId(), is(expectedTypeId));
        assertThat(actual.getPayload(), is(expectedPayload));
    }

}