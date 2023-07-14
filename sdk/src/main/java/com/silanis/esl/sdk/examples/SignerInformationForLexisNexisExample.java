package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import org.joda.time.DateTime;


import java.util.Date;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static com.silanis.esl.sdk.builder.SignerInformationForLexisNexisBuilder.newSignerInformationForLexisNexis;

public class SignerInformationForLexisNexisExample extends SDKSample{
    private final String PACKAGE_DESCRIPTION = "This is a SignerInformation for Lexis Nexis example";

    public static final String FIRST_NAME = "First name";
    public static final String LAST_NAME = "Last name";
    public static final String FLAT_OR_APARTMENT_NUMBER = "1234";
    public static final String HOUSE_NAME = "Decarie";
    public static final String HOUSE_NUMBER = "5678";
    public static final String CITY = "Montreal";
    public static final String STATE = "Quebec";
    public static final String ZIP = "11111";
    public static final String SOCIAL_SECURITY_NUMBER = "111222333";

    public static final Date DATE_OF_BIRTH = new DateTime().minusYears(42).toDate();

    public static final String FIRST_DOCUMENT_NAME = "First Document pdf";

    public static void main( String... args ) {
        new SignerInformationForLexisNexisExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs(PACKAGE_DESCRIPTION)
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName(FIRST_NAME)
                        .withLastName(LAST_NAME)
                        .challengedWithKnowledgeBasedAuthentication(newSignerInformationForLexisNexis()
                                .withFirstName(FIRST_NAME)
                                .withLastName(LAST_NAME)
                                .withFlatOrApartmentNumber(FLAT_OR_APARTMENT_NUMBER)
                                .withHouseName(HOUSE_NAME)
                                .withHouseNumber(HOUSE_NUMBER)
                                .withCity(CITY)
                                .withZip(ZIP)
                                .withState(STATE)
                                .withSocialSecurityNumber(SOCIAL_SECURITY_NUMBER)
                                .withDateOfBirth(DATE_OF_BIRTH)))
                .withDocument(newDocumentWithName(FIRST_DOCUMENT_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1).build()))
                .build();

        packageId = eslClient.createAndSendPackage(superDuperPackage);
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
