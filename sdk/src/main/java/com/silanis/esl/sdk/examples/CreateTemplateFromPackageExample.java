package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by lena on 2014-05-01.
 */
public class CreateTemplateFromPackageExample extends SDKSample {

    private String email1;
    private String email2;
    private InputStream documentInputStream1;
    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "doc1";
    public static final String PACKAGE_NAME = "CreateTemplateFromPackageExample: " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String PACKAGE_NAME_NEW = "Template name";
    public static final String PACKAGE_DESCRIPTION = "This is a package created using the e-SignLive SDK";
    public static final String PACKAGE_EMAIL_MESSAGE = "This message should be delivered to all signers";
    public static final String PACKAGE_SIGNER1_FIRST = "John";
    public static final String PACKAGE_SIGNER1_LAST = "Smith";
    public static final String PACKAGE_SIGNER2_FIRST = "Patty";
    public static final String PACKAGE_SIGNER2_LAST = "Galant";
    private PackageId retrievedPackageId;

    public static void main(String... args) {
        new CreateTemplateFromPackageExample(Props.get()).run();
    }

    public CreateTemplateFromPackageExample(Properties properties) {
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"),
                properties.getProperty("1.email"),
                properties.getProperty("2.email"));
    }

    public CreateTemplateFromPackageExample(String apiKey, String apiUrl, String email1, String email2) {
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

        Document document = DocumentBuilder.newDocumentWithName(DOCUMENT_NAME)
                .withId(DOCUMENT_ID)
                .fromStream(documentInputStream1, DocumentType.PDF)
                .build();

        DocumentPackage superDuperPackage = PackageBuilder.newPackageNamed(PACKAGE_NAME)
                .describedAs(PACKAGE_DESCRIPTION)
                .withEmailMessage(PACKAGE_EMAIL_MESSAGE)
                .withSigner(SignerBuilder.newSignerWithEmail(email1)
                        .withFirstName(PACKAGE_SIGNER1_FIRST)
                        .withLastName(PACKAGE_SIGNER1_LAST))
                .withSigner(SignerBuilder.newSignerWithEmail(email2)
                        .withFirstName(PACKAGE_SIGNER2_FIRST)
                        .withLastName(PACKAGE_SIGNER2_LAST))
                .withDocument(document)
                .build();

        packageId = eslClient.createPackage(superDuperPackage);

        retrievedPackageId = eslClient.getTemplateService().createTemplateFromPackage(packageId, PACKAGE_NAME_NEW);

    }

    public PackageId getRetrievedPackageId() {
        return retrievedPackageId;
    }
}
