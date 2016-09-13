package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import org.joda.time.DateTime;

import java.util.Date;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static com.silanis.esl.sdk.builder.SignerInformationForEquifaxCanadaBuilder.newSignerInformationForEquifaxCanada;

/**
 * Created by schoi on 9/5/14.
 */
public class SignerInformationForEquifaxCanadaExample extends SDKSample {

    private final String PACKAGE_DESCRIPTION = "This is a SignerInformation for EquifaxCanada example";

    public static final String FIRST_NAME = "Patty";
    public static final String LAST_NAME = "Galant";
    public static final String ADDRESS = "1234 Decarie";
    public static final String CITY = "Montreal";
    public static final String POSTAL_CODE = "A2A5D4";
    public static final String PROVINCE = "QC";
    public static final Integer TIME_AT_ADDRESS = 1;
    public static final Date   DATE_OF_BIRTH = new DateTime().minusYears(25).toDate();
    public static final String DRIVERS_LICENSE_NUMBER = "1234567";
    public static final String SOCIAL_INSURANCE_NUMBER = "123456789";
    public static final String HOME_PHONE_NUMBER = "+16485923567";
    public static final String FIRST_DOCUMENT_NAME = "First Document pdf";

    public static void main( String... args ) {
        new SignerInformationForEquifaxCanadaExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs(PACKAGE_DESCRIPTION)
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName(FIRST_NAME)
                        .withLastName(LAST_NAME)
                        .challengedWithKnowledgeBasedAuthentication(newSignerInformationForEquifaxCanada()
                                .withFirstName(FIRST_NAME)
                                .withLastName(LAST_NAME)
                                .withStreetAddress(ADDRESS)
                                .withCity(CITY)
                                .withPostalCode(POSTAL_CODE)
                                .withTimeAtAddress(TIME_AT_ADDRESS)
                                .withProvince(PROVINCE)
                                .withDateOfBirth(DATE_OF_BIRTH)
                                .withDriversLicenseNumber(DRIVERS_LICENSE_NUMBER)
                                .withSocialInsuranceNumber(SOCIAL_INSURANCE_NUMBER)
                                .withHomePhoneNumber(HOME_PHONE_NUMBER)))
                        .withDocument(newDocumentWithName(FIRST_DOCUMENT_NAME)
                                .fromStream(documentInputStream1, DocumentType.PDF)
                                .withSignature(signatureFor(email1).build()))
                        .build();

        packageId = eslClient.createAndSendPackage(superDuperPackage);
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
