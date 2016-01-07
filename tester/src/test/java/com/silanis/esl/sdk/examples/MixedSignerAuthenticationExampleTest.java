package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Challenge;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.SignerInformationForEquifaxCanada;
import com.silanis.esl.sdk.SignerInformationForEquifaxUSA;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * Created by schoi on 9/12/14.
 */
public class MixedSignerAuthenticationExampleTest {

    @Test
    public void verifyResult() {

        MixedSignerAuthenticationExample example = new MixedSignerAuthenticationExample(Props.get());
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        SignerInformationForEquifaxCanada informationForEquifaxCanada = documentPackage.getSigner(example.signer1Email).getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada();

        assertThat( "first name in informationForEquifaxCanada was not set correctly.", informationForEquifaxCanada.getFirstName(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getFirstName() ) );
        assertThat( "last name in informationForEquifaxCanada was not set correctly.",informationForEquifaxCanada.getLastName(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getLastName() ) );
        assertThat( "address in informationForEquifaxCanada was not set correctly.",informationForEquifaxCanada.getStreetAddress(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getStreetAddress() ) );
        assertThat( "city in informationForEquifaxCanada was not set correctly.",informationForEquifaxCanada.getCity(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getCity() ) );
        assertThat( "postal code in informationForEquifaxCanada code was not set correctly.",informationForEquifaxCanada.getPostalCode(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getPostalCode() ) );
        assertThat( "province in informationForEquifaxCanada was not set correctly.",informationForEquifaxCanada.getProvince(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getProvince() ) );
        assertThat( "date of birth in informationForEquifaxCanada was not set correctly.",informationForEquifaxCanada.getDateOfBirth().toString(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getDateOfBirth().toString() ) );
        assertThat( "time at address in informationForEquifaxCanada was not set correctly.",informationForEquifaxCanada.getTimeAtAddress(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getTimeAtAddress() ) );
        assertThat( "driver's license in informationForEquifaxCanada was not set correctly.",informationForEquifaxCanada.getDriversLicenseNumber(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getDriversLicenseNumber() ) );
        assertThat( "SIN number in informationForEquifaxCanada was not set correctly.",informationForEquifaxCanada.getSocialInsuranceNumber(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getSocialInsuranceNumber() ) );
        assertThat( "Home phone number in informationForEquifaxCanada was not set correctly.",informationForEquifaxCanada.getHomePhoneNumber(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getHomePhoneNumber() ) );

        for (Challenge challenge: documentPackage.getSigner(example.signer1Email).getChallengeQuestions()) {
            assertTrue(challenge.getQuestion().contentEquals(example.signerWithAuthenticationEquifaxCanada.getChallengeQuestions().get(0).getQuestion())
                               || challenge.getQuestion().contentEquals(example.signerWithAuthenticationEquifaxCanada.getChallengeQuestions().get(1).getQuestion()));
        }

        SignerInformationForEquifaxUSA informationForEquifaxUSA = documentPackage.getSigner(example.signer2Email).getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA();

        assertThat( "first name in informationForEquifaxUSA was not set correctly.", informationForEquifaxUSA.getFirstName(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getFirstName() ) );
        assertThat( "last name in informationForEquifaxUSA was not set correctly.",informationForEquifaxUSA.getLastName(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getLastName() ) );
        assertThat( "address in informationForEquifaxUSA was not set correctly.",informationForEquifaxUSA.getStreetAddress(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getStreetAddress() ) );
        assertThat( "city in informationForEquifaxUSA was not set correctly.",informationForEquifaxUSA.getCity(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getCity() ) );
        assertThat( "zip code in informationForEquifaxUSA was not set correctly.",informationForEquifaxUSA.getZip(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getZip() ) );
        assertThat( "state in informationForEquifaxUSA was not set correctly.",informationForEquifaxUSA.getState(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getState() ) );
        assertThat( "date of birth in informationForEquifaxUSA was not set correctly.",informationForEquifaxUSA.getDateOfBirth().toString(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getDateOfBirth().toString() ) );
        assertThat( "social security number in informationForEquifaxUSA was not set correctly.",informationForEquifaxUSA.getSocialSecurityNumber(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getSocialSecurityNumber() ) );
        assertThat( "home phone number in informationForEquifaxUSA was not set correctly.",informationForEquifaxUSA.getHomePhoneNumber(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getHomePhoneNumber() ) );
        assertThat( "driver's license in informationForEquifaxUSA was not set correctly.",informationForEquifaxUSA.getDriversLicenseNumber(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getDriversLicenseNumber() ) );
        assertThat( "time at address in informationForEquifaxUSA was not set correctly.",informationForEquifaxUSA.getTimeAtAddress(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getTimeAtAddress() ) );

        for (Challenge challenge: documentPackage.getSigner(example.signer2Email).getChallengeQuestions()) {
            assertTrue(challenge.getQuestion().contentEquals(example.signerWithAuthenticationEquifaxUSA.getChallengeQuestions().get(0).getQuestion())
                               || challenge.getQuestion().contentEquals(example.signerWithAuthenticationEquifaxUSA.getChallengeQuestions().get(1).getQuestion()));
        }
    }
}
