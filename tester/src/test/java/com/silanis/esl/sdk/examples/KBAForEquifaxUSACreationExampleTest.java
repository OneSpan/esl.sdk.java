package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Challenge;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.SignerInformationForEquifaxUSA;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.KBAForEquifaxUSACreationExample.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Created by schoi on 9/8/14.
 */
public class KBAForEquifaxUSACreationExampleTest {
    public static final double DEFAULT_DOUBLE_TOLERANCE = 0.01f;

    @Test
    public void verifyResult() {

        KBAForEquifaxUSACreationExample kbaForEquifaxUSACreationExample = new KBAForEquifaxUSACreationExample( Props.get() );
        kbaForEquifaxUSACreationExample.run();

        DocumentPackage documentPackage = kbaForEquifaxUSACreationExample.getEslClient().getPackage(kbaForEquifaxUSACreationExample.getPackageId());

        // Signer 1
        for (Challenge challenge: documentPackage.getSigner(kbaForEquifaxUSACreationExample.email1).getChallengeQuestions()) {
            assertThat(challenge.getQuestion().contentEquals(FIRST_QUESTION)
                    || challenge.getQuestion().contentEquals(SECOND_QUESTION), is(equalTo(true)));

        }
        // Signer 2
        Signer signer = documentPackage.getSigner(kbaForEquifaxUSACreationExample.email2);
        SignerInformationForEquifaxUSA signerKBA = signer.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA();
        assertThat( "Signer 2 KBA first name was not set correctly.", signerKBA.getFirstName(), is( SIGNER2_FIRST_NAME ) );
        assertThat( "Signer 2 KBA last name was not set correctly.",signerKBA.getLastName(), is( SIGNER2_LAST_NAME ) );
        assertThat( "Signer 2 KBA address was not set correctly.",signerKBA.getAddress(), is( SIGNER2_ADDRESS ) );
        assertThat( "Signer 2 KBA city was not set correctly.",signerKBA.getCity(), is( SIGNER2_CITY ) );
        assertThat( "Signer 2 KBA zip code was not set correctly.",signerKBA.getZipCode(), is( SIGNER2_ZIP_CODE ) );
        assertThat( "Signer 2 KBA state was not set correctly.",signerKBA.getState(), is( SIGNER2_STATE ) );
        assertThat( "Signer 2 KBA social security number was not set correctly.",signerKBA.getSocialSecurityNumber(), is( SIGNER2_SOCIAL_SECURITY_NUMBER ) );
        assertThat( "Signer 2 KBA home phone number was not set correctly.",signerKBA.getHomePhone(), is( SIGNER2_HOME_PHONE ) );

    }
}
