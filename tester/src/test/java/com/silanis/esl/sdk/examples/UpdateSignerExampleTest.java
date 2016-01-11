package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.AuthenticationMethod.*;
import static com.silanis.esl.sdk.examples.UpdateSignerExample.*;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 1/11/16.
 */
public class UpdateSignerExampleTest {

    @Test
    public void verifyResult() {
        UpdateSignerExample example = new UpdateSignerExample(Props.get());
        example.run();

        assertNotNull("Package signer1 is not set correctly.", example.getRetrievedPackage().getSigner(example.email1));
        assertThat("Package signer1 is not set correctly.", example.getRetrievedPackage().getSigner(example.email1).getAuthentication().getMethod(), is(EMAIL));
        assertNotNull("Package signer2 is not set correctly.", example.getRetrievedPackage().getSigner(example.email2));
        assertThat("Package signer2 is not set correctly.", example.getRetrievedPackage().getSigner(example.email2).getAuthentication().getMethod(), is(EMAIL));

        assertNull("Package signer1 is not updated correctly.", example.updatedPackage.getSigner(example.email1));
        assertNotNull("Package signer1 is not updated correctly.", example.updatedPackage.getSigner(example.email3));
        assertThat("Package signer1's authentication method is not updated correctly.", example.updatedPackage.getSigner(example.email3).getAuthentication().getMethod(), is(CHALLENGE));
        assertThat("Package signer1's authentication method is not updated correctly.", example.updatedPackage.getSigner(example.email3).getAuthentication().getChallenges().get(0).getQuestion(), is(SIGNER3_FIRST_QUESTION));
        assertThat("Package signer1's authentication method is not updated correctly.", example.updatedPackage.getSigner(example.email3).getAuthentication().getChallenges().get(0).getAnswer(), is(SIGNER3_FIRST_ANSWER));
        assertThat("Package signer1's authentication method is not updated correctly.", example.updatedPackage.getSigner(example.email3).getAuthentication().getChallenges().get(1).getQuestion(), is(SIGNER3_SECOND_QUESTION));
        assertThat("Package signer1's authentication method is not updated correctly.", example.updatedPackage.getSigner(example.email3).getAuthentication().getChallenges().get(1).getAnswer(), is(SIGNER3_SECOND_ANSWER));

        assertNotNull("Package signer2 is not updated correctly.", example.updatedPackage.getSigner(example.email2));
        assertThat("Package signer2's authentication method is not updated correctly.", example.updatedPackage.getSigner(example.email2).getAuthentication().getMethod(), is(SMS));
        assertThat("Package signer2's authentication method is not updated correctly.", example.updatedPackage.getSigner(example.email2).getAuthentication().getPhoneNumber(), is(example.sms1));
    }
}
