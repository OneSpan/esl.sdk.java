package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.util.Date;
import java.util.Locale;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.*;
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
    public static final String PACKAGE_TIMEZONE_ID = "Canada/Mountain";

    public static final String SIGNER1_CUSTOM_ID = "Client1";
    public static final String SIGNER1_FIRST_NAME = "John";
    public static final String SIGNER1_LAST_NAME = "Smith";
    public static final String SIGNER1_TITLE = "Managing Director";
    public static final String SIGNER1_COMPANY = "Acme Inc.";
    public static final Locale SIGNER1_LANGUAGE = Locale.SIMPLIFIED_CHINESE;

    public static final String SIGNER2_FIRST_NAME = "Patty";
    public static final String SIGNER2_LAST_NAME = "Galant";

    public static final String DOCUMENT1_NAME = "First Document";
    public static final String DOCUMENT2_NAME = "Second Document";

    public static final Integer SIGNATURE_FONT_SIZE = 10;
    public static final Integer AUTO_FIELD_FONT_SIZE = 9;

    public static void main(String... args) {
        new BasicPackageCreationExample().run();
    }

    public void execute() {
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document_with_text_tag_and_form_field.pdf");
        email2 = "CapitalLetters@email.com";

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withTimezoneId(PACKAGE_TIMEZONE_ID)
                .describedAs(PACKAGE_DESC)
                .expiresAt(PACKAGE_EXPIRY)
                .withEmailMessage(PACKAGE_EMAIL_MSG)
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId(SIGNER1_CUSTOM_ID)
                        .withFirstName(SIGNER1_FIRST_NAME)
                        .withLastName(SIGNER1_LAST_NAME)
                        .withLanguage(SIGNER1_LANGUAGE)
                        .withTitle(SIGNER1_TITLE)
                        .withCompany(SIGNER1_COMPANY))
                .withSigner(newSignerWithEmail(email2)
                        .withFirstName(SIGNER2_FIRST_NAME)
                        .withLastName(SIGNER2_LAST_NAME))
                .withDocument(newDocumentWithName(DOCUMENT1_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .withFontSize(SIGNATURE_FONT_SIZE)
                                .onPage(0)
                                .withField(checkBox()
                                        .onPage(0)
                                        .atPosition(50, 50)
                                        .withValue(FieldBuilder.RADIO_SELECTED))
                                .withField(signerName()
                                        .onPage(0)
                                        .atPosition(150, 50)
                                        .withFontSize(AUTO_FIELD_FONT_SIZE))
                                .atPosition(100, 100)))
                .withDocument(newDocumentWithName(DOCUMENT2_NAME)
                        .fromStream(documentInputStream2, DocumentType.PDF)
                        .withSignature(signatureFor(email2)
                                .onPage(0)
                                .withField(radioButton(group1)
                                        .withName("firstField")
                                        .onPage(0)
                                        .atPosition(400, 300)
                                        .withSize(20, 20)
                                        .withValue(false))
                                .withField(radioButton(group1)
                                        .withName("secondField")
                                        .onPage(0)
                                        .atPosition(400, 400)
                                        .withSize(20, 20)
                                        .withValue(true))
                                .withField(radioButton(group2)
                                        .withName("thirdField")
                                        .onPage(0)
                                        .atPosition(400, 500)
                                        .withSize(20, 20)
                                        .withValue(true))
                                .withField(radioButton(group2)
                                        .withName("fourthField")
                                        .onPage(0)
                                        .atPosition(400, 600)
                                        .withSize(20, 20)
                                        .withValue(false))
                                .atPosition(100, 200)
                        )
                )
                .build();

        packageId = eslClient.createPackageOneStep(superDuperPackage);
        eslClient.sendPackage(packageId);
        retrievedPackage = eslClient.getPackage(packageId);
    }
}
