package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.SignerInformationForLexisNexis;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.SignerInformationForLexisNexisExample.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SignerInformationForLexisNexisExampleTest {

    @Test
    public void verifyResult() {

        SignerInformationForLexisNexisExample example = new SignerInformationForLexisNexisExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();
        SignerInformationForLexisNexis signerInformationForLexisNexis = documentPackage.getSigner(example.email1).getKnowledgeBasedAuthentication().getSignerInformationForLexisNexis();

        assertThat( "first name in signerInformationForLexisNexis was not set correctly.", signerInformationForLexisNexis.getFirstName(), is( FIRST_NAME ) );
        assertThat( "last name in signerInformationForLexisNexis was not set correctly.",signerInformationForLexisNexis.getLastName(), is( LAST_NAME ) );
        assertThat( "flatOrApartmentNumber in signerInformationForLexisNexis was not set correctly.",signerInformationForLexisNexis.getFlatOrApartmentNumber(), is( FLAT_OR_APARTMENT_NUMBER ) );
        assertThat( "houseName in signerInformationForLexisNexis was not set correctly.",signerInformationForLexisNexis.getHouseName(), is( HOUSE_NAME ) );
        assertThat( "houseNumber code in signerInformationForLexisNexis was not set correctly.",signerInformationForLexisNexis.getHouseNumber(), is(HOUSE_NUMBER) );
        assertThat( "city in signerInformationForLexisNexis was not set correctly.",signerInformationForLexisNexis.getCity(), is( CITY ) );
        assertThat( "state of birth in signerInformationForLexisNexis was not set correctly.",signerInformationForLexisNexis.getState(), is( STATE) );
        assertThat( "zip in signerInformationForLexisNexis was not set correctly.",signerInformationForLexisNexis.getZip(), is( ZIP ) );
        assertThat( "socialSecurityNumber in signerInformationForLexisNexis was not set correctly.",signerInformationForLexisNexis.getSocialSecurityNumber(), is(SOCIAL_SECURITY_NUMBER) );

    }
}
