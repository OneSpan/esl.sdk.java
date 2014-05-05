package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Basic package from template.
 */
public class CreatePackageFromTemplateExample2 extends SDKSample {

    private String email1;
    private String email2;
    private PackageId retrievedPackageId;
    private InputStream documentInputStream1;
    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "doc1";
    public static final String PACKAGE_NAME = "CreateTemplateFromPackageExample: " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String PACKAGE_DESCRIPTION = "This is a package created using the e-SignLive SDK";
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
        new CreateTemplateFromPackageExample(Props.get()).run();
    }

    public CreatePackageFromTemplateExample2(Properties properties) {
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"),
                properties.getProperty("1.email"),
                properties.getProperty("2.email"));
    }

    public CreatePackageFromTemplateExample2(String apiKey, String apiUrl, String email1, String email2) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        this.email2 = email2;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    @Override
    public void execute() {
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");

        DocumentPackage template = PackageBuilder.newPackageNamed("Template")
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

        DocumentPackage newPackage = PackageBuilder.newPackageNamed(PACKAGE_NAME)
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

        retrievedPackageId = eslClient.getTemplateService().createPackageFromTemplate(template.getId(), newPackage);
    }

    public PackageId getRetrievedPackageId() {
        return retrievedPackageId;
    }
}
