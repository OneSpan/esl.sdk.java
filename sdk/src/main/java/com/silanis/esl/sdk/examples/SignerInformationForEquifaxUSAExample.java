package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import org.joda.time.DateTime;

import java.util.Date;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static com.silanis.esl.sdk.builder.SignerInformationForEquifaxUSABuilder.newSignerInformationForEquifaxUSA;

/**
 * Created by schoi on 9/8/14.
 */
public class SignerInformationForEquifaxUSAExample extends SDKSample {

    private final String PACKAGE_DESCRIPTION = "This is a SignerInformation for EquifaxUSA example";

    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Smith";
    public static final String ADDRESS = "PO BOX 451";
    public static final String CITY = "CALERA";
    public static final String ZIP = "35040";
    public static final String STATE = "AL";
    public static final Integer TIME_AT_ADDRESS = 1;
    public static final String SOCIAL_SECURITY_NUMBER = "666110007";
    public static final Date   DATE_OF_BIRTH = new DateTime().minusYears(42).toDate();
    public static final String HOME_PHONE_NUMBER = "+12055551212";
    public static final String DRIVERS_LICENSE_NUMBER = "251689216";
    public static final String FIRST_DOCUMENT_NAME = "First Document pdf";

    public static void main( String... args ) {
        new SignerInformationForEquifaxUSAExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs(PACKAGE_DESCRIPTION)
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName(FIRST_NAME)
                        .withLastName(LAST_NAME)
                        .challengedWithKnowledgeBasedAuthentication(newSignerInformationForEquifaxUSA()
                                .withFirstName(FIRST_NAME)
                                .withLastName(LAST_NAME)
                                .withStreetAddress(ADDRESS)
                                .withCity(CITY)
                                .withZip(ZIP)
                                .withState(STATE)
                                .withSocialSecurityNumber(SOCIAL_SECURITY_NUMBER)
                                .withDateOfBirth(DATE_OF_BIRTH)
                                .withHomePhoneNumber(HOME_PHONE_NUMBER)
                                .withDriversLicenseNumber(DRIVERS_LICENSE_NUMBER)
                                .withTimeAtAddress(TIME_AT_ADDRESS)))
                .withDocument(newDocumentWithName(FIRST_DOCUMENT_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1).build()))
                .build();

        packageId = eslClient.createAndSendPackage(superDuperPackage);
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
