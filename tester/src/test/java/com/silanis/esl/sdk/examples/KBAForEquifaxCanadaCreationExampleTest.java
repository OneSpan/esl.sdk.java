package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.SignerInformationForEquifaxCanada;
import com.silanis.esl.sdk.VersionUtil;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.KBAForEquifaxCanadaCreationExample.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.joda.time.DateMidnight.now;

/**
 * Created by schoi on 9/8/14.
 */
public class KBAForEquifaxCanadaCreationExampleTest {
    public static final double DEFAULT_DOUBLE_TOLERANCE = 0.01f;

    @Test
    public void verifyResult() {

        KBAForEquifaxCanadaCreationExample kbaForEquifaxCanadaCreationExample = new KBAForEquifaxCanadaCreationExample( Props.get() );
        kbaForEquifaxCanadaCreationExample.run();

        DocumentPackage documentPackage = kbaForEquifaxCanadaCreationExample.getEslClient().getPackage(kbaForEquifaxCanadaCreationExample.getPackageId());

        // Verify if the package is created correctly.

        assertThat( "Package description was not set correctly.", documentPackage.getDescription(), is( "This is a package created using the e-SignLive SDK" ) );
        assertThat( "Package expiry date was not set correctly.", documentPackage.getExpiryDate(), is( now().plusMonths( 1 ).toDate() ) );
        assertThat( "Package message was not set correctly.", documentPackage.getPackageMessage(), is( "This message should be delivered to all signers" ) );

        // Verify if the sdk version is set correctly
        assertThat("Package attributes are null", documentPackage.getAttributes(), is(notNullValue()));
        assertThat("Package attributes are empty", documentPackage.getAttributes().getContents(), is(notNullValue()));
        assertThat("SDK version was not set", documentPackage.getAttributes().toMap().containsKey("sdk"), is(true) );
        assertThat("SDK version was not set to the correct value", documentPackage.getAttributes().toMap().get("sdk").toString(), is(equalTo("Java v" + VersionUtil.getVersion()) ) );

        // Signer 2
        Signer signer = documentPackage.getSigner(kbaForEquifaxCanadaCreationExample.email2);

        // Signer 2 KBA Information
        SignerInformationForEquifaxCanada signerKBA = signer.getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada();
        assertThat( "Signer 2 KBA first name was not set correctly.", signerKBA.getFirstName(), is( CUSTOM2_FIRST_NAME ) );
        assertThat( "Signer 2 KBA last name was not set correctly.",signerKBA.getLastName(), is( CUSTOM2_LAST_NAME ) );
        assertThat( "Signer 2 KBA address was not set correctly.",signerKBA.getAddress(), is( CUSTOM2_ADDRESS ) );
        assertThat( "Signer 2 KBA city was not set correctly.",signerKBA.getCity(), is( CUSTOM2_CITY ) );
        assertThat( "Signer 2 KBA zip code was not set correctly.",signerKBA.getZipCode(), is( CUSTOM2_ZIP_CODE ) );
        assertThat( "Signer 2 KBA state was not set correctly.",signerKBA.getState(), is( CUSTOM2_STATE ) );
        assertThat( "Signer 2 KBA time at address was not set correctly.",signerKBA.getTimeAtAddress(), is( CUSTOM2_TIME_AT_ADDRESS ) );
        assertThat( "Signer 2 KBA driver's license was not set correctly.",signerKBA.getDriverslicense(), is( CUSTOM2_DRIVERS_LICENSE ) );
        assertThat( "Signer 2 KBA SIN number was not set correctly.",signerKBA.getSocialInsuranceNumber(), is( CUSTOM2_SOCIAL_INSURANCE_NUMBER ) );

    }
}
