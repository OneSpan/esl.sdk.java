package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;

import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Basic package from template.
 */
public class CreatePackageFromTemplateExample extends SDKSample {

    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "doc1";
    public static final String PACKAGE_DESCRIPTION = "This is a package created using the eSignLive SDK";
    public static final String PACKAGE_EMAIL_MESSAGE = "This message should be delivered to all signers";
    public static final String PACKAGE_EMAIL_MESSAGE2 = "Changed the email message";
    public static final String PACKAGE_SIGNER1_FIRST = "John";
    public static final String PACKAGE_SIGNER1_LAST = "Smith";
    public static final String PACKAGE_SIGNER1_TITLE = "Manager";
    public static final String PACKAGE_SIGNER1_COMPANY = "Acme Inc.";
    public static final String PACKAGE_SIGNER1_CUSTOM_ID = "Signer1";

    public static final String PACKAGE_SIGNER2_FIRST = "Elvis";
    public static final String PACKAGE_SIGNER2_LAST = "Presley";
    public static final String PACKAGE_SIGNER2_TITLE = "The King";
    public static final String PACKAGE_SIGNER2_COMPANY = "Elvis Presley International";
    public static final String PACKAGE_SIGNER2_CUSTOM_ID = "Signer2";

    public static void main(String... args) {
        new CreateTemplateFromPackageExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage template = PackageBuilder.newPackageNamed("Template " + getPackageName())
                .describedAs("first message")
                .withEmailMessage(PACKAGE_EMAIL_MESSAGE)
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName(PACKAGE_SIGNER1_FIRST)
                        .withLastName(PACKAGE_SIGNER1_LAST)
                        .withTitle(PACKAGE_SIGNER1_TITLE)
                        .withCompany(PACKAGE_SIGNER1_COMPANY)
                        .withCustomId(PACKAGE_SIGNER1_CUSTOM_ID))
                .withDocument(DocumentBuilder.newDocumentWithName(DOCUMENT_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withId(DOCUMENT_ID)
                        .build())
                .build();

        template.setId(eslClient.getTemplateService().createTemplate(template));

        DocumentPackage newPackage = PackageBuilder.newPackageNamed(getPackageName())
                .describedAs(PACKAGE_DESCRIPTION)
                .withEmailMessage(PACKAGE_EMAIL_MESSAGE2)
                .withSigner(newSignerWithEmail(email2)
                        .withFirstName(PACKAGE_SIGNER2_FIRST)
                        .withLastName(PACKAGE_SIGNER2_LAST)
                        .withTitle(PACKAGE_SIGNER2_TITLE)
                        .withCompany(PACKAGE_SIGNER2_COMPANY)
                        .withCustomId(PACKAGE_SIGNER2_CUSTOM_ID))
                .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings()
                        .withoutInPerson()
                        .withoutDecline()
                        .withoutOptOut()
                        .withWatermark()
                        .build())
                .build();

        packageId = eslClient.getTemplateService().createPackageFromTemplate(template.getId(), newPackage);
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
