package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.SignerVerification;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

/**
 * Created by aafrasiabian on 23/05/17.
 */
public class SignerVerificationBuilderTest {

    @Test
    public void buildsSignerVerificationWithBasicInformation() {
        String expectedTypeId = "PROVIDER1";
        String expectedPayload = "HJKs2H7UvtFDUi73GswE";

        SignerVerification actual = SignerVerificationBuilder.newSignerVerification(expectedTypeId)
                .withPayload(expectedPayload)
                .build();


        assertThat(actual.getTypeId(), is(expectedTypeId));
        assertThat(actual.getPayload(), is(expectedPayload));
    }

    @Test
    public void signerVerificationTypeIdCannotBeEmpty() {
        try {

            SignerVerificationBuilder.newSignerVerification(" ")
                    .withPayload("HJKs2H7UvtFDUi73GswE")
                    .build();

            fail("Should not be able to build a SignerVerification with a blanch typeId!");
        } catch (EslException ex) {
        }
    }

}