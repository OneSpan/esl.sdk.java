package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.SignerInformationForEquifaxCanada;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.SignerInformationForEquifaxCanadaExample.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 9/8/14.
 */
public class SignerInformationForEquifaxCanadaExampleTest {

    @Test
    public void verifyResult() {

        SignerInformationForEquifaxCanadaExample example = new SignerInformationForEquifaxCanadaExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();
        SignerInformationForEquifaxCanada signerInformationForEquifaxCanada = documentPackage.getSigner(example.email1).getKnowledgeBasedAuthentication().getSignerInformationForEquifaxCanada();

        assertThat( "first name in signerInformationForEquifaxCanada was not set correctly.", signerInformationForEquifaxCanada.getFirstName(), is( FIRST_NAME ) );
        assertThat( "last name in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getLastName(), is( LAST_NAME ) );
        assertThat( "address in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getStreetAddress(), is( ADDRESS ) );
        assertThat( "city in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getCity(), is( CITY ) );
        assertThat( "postal code in signerInformationForEquifaxCanada code was not set correctly.",signerInformationForEquifaxCanada.getPostalCode(), is(POSTAL_CODE) );
        assertThat( "province in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getProvince(), is(PROVINCE) );
        assertThat("date of birth in signerInformationForEquifaxCanada was not set correctly.", signerInformationForEquifaxCanada.getDateOfBirth().toString(), is(DATE_OF_BIRTH.toString()));
        assertThat( "time at address in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getTimeAtAddress(), is( TIME_AT_ADDRESS ) );
        assertThat( "driver's license in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getDriversLicenseNumber(), is(DRIVERS_LICENSE_NUMBER) );
        assertThat( "SIN number in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getSocialInsuranceNumber(), is( SOCIAL_INSURANCE_NUMBER ) );
        assertThat( "Home phone number in signerInformationForEquifaxCanada was not set correctly.",signerInformationForEquifaxCanada.getHomePhoneNumber(), is( HOME_PHONE_NUMBER ) );
    }
}
