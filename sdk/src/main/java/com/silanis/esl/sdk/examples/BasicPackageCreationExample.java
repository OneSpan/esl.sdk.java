package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.util.Date;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Basic package with in-person mode set at the document package level. Expires in a month.
 */
public class BasicPackageCreationExample extends SDKSample {

    public final String group1 = "group1";
    public final String group2 = "group2";

    public static final String PACKAGE_DESC = "This is a package created using the eSignLive SDK";
    public static final Date PACKAGE_EXPIRY = now().plusMonths(1).toDate();
    public static final String PACKAGE_EMAIL_MSG = "This message should be delivered to all signers";

    public static final String SIGNER1_CUSTOM_ID = "Client1";
    public static final String SIGNER1_FIRST_NAME = "John";
    public static final String SIGNER1_LAST_NAME = "Smith";
    public static final String SIGNER1_TITLE = "Managing Director";
    public static final String SIGNER1_COMPANY = "Acme Inc.";

    public static final String SIGNER2_FIRST_NAME = "Patty";
    public static final String SIGNER2_LAST_NAME = "Galant";

    public static final String DOCUMENT1_NAME = "First Document";
    public static final String DOCUMENT2_NAME = "Second Document";

    public static void main( String... args ) {
        new BasicPackageCreationExample().run();
    }

    public void execute() {
        email2 = "CapitalLetters@email.com";

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs(PACKAGE_DESC)
                .expiresAt(PACKAGE_EXPIRY)
                .withEmailMessage(PACKAGE_EMAIL_MSG)
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId(SIGNER1_CUSTOM_ID)
                        .withFirstName(SIGNER1_FIRST_NAME)
                        .withLastName(SIGNER1_LAST_NAME)
                        .withTitle(SIGNER1_TITLE)
                        .withCompany(SIGNER1_COMPANY))
                .withSigner(newSignerWithEmail(email2)
                        .withFirstName(SIGNER2_FIRST_NAME)
                        .withLastName(SIGNER2_LAST_NAME))
                .withDocument(newDocumentWithName(DOCUMENT1_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .withField(FieldBuilder.checkBox()
                                        .onPage(0)
                                        .atPosition(50, 50)
                                        .withValue(FieldBuilder.RADIO_SELECTED))
                                .atPosition(100, 100)))
                .withDocument(newDocumentWithName(DOCUMENT2_NAME)
                                .fromStream(documentInputStream2, DocumentType.PDF)
                                .withSignature(signatureFor(email2)
                                                .onPage(0)
                                                .withField(FieldBuilder.radioButton(group1)
                                                        .withName("firstField")
                                                        .onPage(0)
                                                        .atPosition(400, 300)
                                                        .withSize(20, 20)
                                                        .withValue(false))
                                                .withField(FieldBuilder.radioButton(group1)
                                                        .withName("secondField")
                                                        .onPage(0)
                                                        .atPosition(400, 400)
                                                        .withSize(20, 20)
                                                        .withValue(true))
                                                .withField(FieldBuilder.radioButton(group2)
                                                        .withName("thirdField")
                                                        .onPage(0)
                                                        .atPosition(400, 500)
                                                        .withSize(20, 20)
                                                        .withValue(true))
                                                .withField(FieldBuilder.radioButton(group2)
                                                        .withName("fourthField")
                                                        .onPage(0)
                                                        .atPosition(400, 600)
                                                        .withSize(20, 20)
                                                        .withValue(false))
                                                .atPosition(100, 200)
                                )
                )
                .build();

        packageId = eslClient.createPackageOneStep( superDuperPackage );
        eslClient.sendPackage( packageId );
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
