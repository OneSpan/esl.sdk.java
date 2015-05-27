package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Challenge;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.SignerInformationForEquifaxCanada;
import com.silanis.esl.sdk.SignerInformationForEquifaxUSA;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Created by schoi on 9/12/14.
 */
public class MixedSignerAuthenticationExampleTest {

    @Test
    public void verifyResult() {

        MixedSignerAuthenticationExample example = new MixedSignerAuthenticationExample(Props.get());
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        SignerInformationForEquifaxCanada signerInformationForEquifaxCanada = documentPackage.getSigner(example.signer1Email).getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada();

        assertThat( "first name in signerInformationForEquifaxCanada was not set correctly.", signerInformationForEquifaxCanada.getFirstName(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getFirstName() ) );
        assertThat( "last name in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getLastName(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getLastName() ) );
        assertThat( "address in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getStreetAddress(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getStreetAddress() ) );
        assertThat( "city in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getCity(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getCity() ) );
        assertThat( "postal code in signerInformationForEquifaxCanada code was not set correctly.",signerInformationForEquifaxCanada.getPostalCode(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getPostalCode() ) );
        assertThat( "province in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getProvince(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getProvince() ) );
        assertThat( "date of birth in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getDateOfBirth().toString(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getDateOfBirth().toString() ) );
        assertThat( "time at address in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getTimeAtAddress(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getTimeAtAddress() ) );
        assertThat( "driver's license in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getDriversLicenseNumber(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getDriversLicenseNumber() ) );
        assertThat( "SIN number in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getSocialInsuranceNumber(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getSocialInsuranceNumber() ) );
        assertThat( "Home phone number in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getHomePhoneNumber(),
                is( example.signerWithAuthenticationEquifaxCanada.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada().getHomePhoneNumber() ) );

        for (Challenge challenge: documentPackage.getSigner(example.signer1Email).getChallengeQuestions()) {
            assertThat(challenge.getQuestion().contentEquals(example.signerWithAuthenticationEquifaxCanada.getChallengeQuestions().get(0).getQuestion())
                    || challenge.getQuestion().contentEquals(example.signerWithAuthenticationEquifaxCanada.getChallengeQuestions().get(1).getQuestion()), is(equalTo(true)));
        }

        SignerInformationForEquifaxUSA signerInformationForEquifaxUSA = documentPackage.getSigner(example.signer2Email).getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA();

        assertThat( "first name in signerInformationForEquifaxUSA was not set correctly.", signerInformationForEquifaxUSA.getFirstName(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getFirstName() ) );
        assertThat( "last name in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getLastName(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getLastName() ) );
        assertThat( "address in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getStreetAddress(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getStreetAddress() ) );
        assertThat( "city in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getCity(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getCity() ) );
        assertThat( "zip code in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getZip(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getZip() ) );
        assertThat( "state in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getState(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getState() ) );
        assertThat( "date of birth in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getDateOfBirth().toString(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getDateOfBirth().toString() ) );
        assertThat( "social security number in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getSocialSecurityNumber(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getSocialSecurityNumber() ) );
        assertThat( "home phone number in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getHomePhoneNumber(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getHomePhoneNumber() ) );
        assertThat( "driver's license in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getDriversLicenseNumber(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getDriversLicenseNumber() ) );
        assertThat( "time at address in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getTimeAtAddress(),
                is( example.signerWithAuthenticationEquifaxUSA.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA().getTimeAtAddress() ) );

        for (Challenge challenge: documentPackage.getSigner(example.signer2Email).getChallengeQuestions()) {
            assertThat(challenge.getQuestion().contentEquals(example.signerWithAuthenticationEquifaxUSA.getChallengeQuestions().get(0).getQuestion())
                    || challenge.getQuestion().contentEquals(example.signerWithAuthenticationEquifaxUSA.getChallengeQuestions().get(1).getQuestion()), is(equalTo(true)));
        }
    }
}
