package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Challenge;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.SignerInformationForEquifaxCanada;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.KBAForEquifaxCanadaCreationExample.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 9/8/14.
 */
public class KBAForEquifaxCanadaCreationExampleTest {

    @Test
    public void verifyResult() {

        KBAForEquifaxCanadaCreationExample kbaForEquifaxCanadaCreationExample = new KBAForEquifaxCanadaCreationExample( Props.get() );
        kbaForEquifaxCanadaCreationExample.run();

        DocumentPackage documentPackage = kbaForEquifaxCanadaCreationExample.getEslClient().getPackage(kbaForEquifaxCanadaCreationExample.getPackageId());

        Signer signer1 = documentPackage.getSigner(kbaForEquifaxCanadaCreationExample.SIGNER1_EMAIL);
        for (Challenge challenge: signer1.getChallengeQuestions()) {
            assertThat(challenge.getQuestion().contentEquals(FIRST_QUESTION)
                    || challenge.getQuestion().contentEquals(SECOND_QUESTION), is(equalTo(true)));

        }

        Signer signer2 = documentPackage.getSigner(kbaForEquifaxCanadaCreationExample.SIGNER2_EMAIL);
        SignerInformationForEquifaxCanada signerKBA = signer2.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada();
        assertThat( "Signer 2 KBA first name was not set correctly.", signerKBA.getFirstName(), is( SIGNER2_FIRST_NAME ) );
        assertThat( "Signer 2 KBA last name was not set correctly.",signerKBA.getLastName(), is( SIGNER2_LAST_NAME ) );
        assertThat( "Signer 2 KBA address was not set correctly.",signerKBA.getAddress(), is( SIGNER2_ADDRESS ) );
        assertThat( "Signer 2 KBA city was not set correctly.",signerKBA.getCity(), is( SIGNER2_CITY ) );
        assertThat( "Signer 2 KBA zip code was not set correctly.",signerKBA.getZipCode(), is( SIGNER2_ZIP_CODE ) );
        assertThat( "Signer 2 KBA state was not set correctly.",signerKBA.getState(), is( SIGNER2_STATE ) );
        assertThat( "Signer 2 KBA time at address was not set correctly.",signerKBA.getTimeAtAddress(), is( SIGNER2_TIME_AT_ADDRESS ) );
        assertThat( "Signer 2 KBA driver's license was not set correctly.",signerKBA.getDriverslicense(), is( SIGNER2_DRIVERS_LICENSE ) );
        assertThat( "Signer 2 KBA SIN number was not set correctly.",signerKBA.getSocialInsuranceNumber(), is( SIGNER2_SOCIAL_INSURANCE_NUMBER ) );

    }
}
