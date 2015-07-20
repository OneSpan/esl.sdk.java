package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.SignerInformationForEquifaxUSA;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.SignerInformationForEquifaxUSAExample.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 9/8/14.
 */
public class SignerInformationForEquifaxUSAExampleTest {

    @Test
    public void verifyResult() {

        SignerInformationForEquifaxUSAExample example = new SignerInformationForEquifaxUSAExample( Props.get() );
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();
        SignerInformationForEquifaxUSA signerInformationForEquifaxUSA = documentPackage.getSigner(example.email).getKnowledgeBasedAuthentication().getSignerInformationForEquifaxUSA();

        assertThat( "first name in signerInformationForEquifaxUSA was not set correctly.", signerInformationForEquifaxUSA.getFirstName(), is( FIRST_NAME ) );
        assertThat( "last name in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getLastName(), is( LAST_NAME ) );
        assertThat( "address in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getStreetAddress(), is( ADDRESS ) );
        assertThat( "city in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getCity(), is( CITY ) );
        assertThat( "zip code in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getZip(), is(ZIP) );
        assertThat( "state in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getState(), is( STATE ) );
        assertThat( "date of birth in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getDateOfBirth().toString(), is( equalTo(DATE_OF_BIRTH.toString()) ) );
        assertThat( "social security number in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getSocialSecurityNumber(), is( SOCIAL_SECURITY_NUMBER ) );
        assertThat( "home phone number in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getHomePhoneNumber(), is(HOME_PHONE_NUMBER) );
        assertThat( "driver's license in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getDriversLicenseNumber(), is(DRIVERS_LICENSE_NUMBER) );
        assertThat( "time at address in signerInformationForEquifaxUSA was not set correctly.",signerInformationForEquifaxUSA.getTimeAtAddress(), is(TIME_AT_ADDRESS) );
    }
}
