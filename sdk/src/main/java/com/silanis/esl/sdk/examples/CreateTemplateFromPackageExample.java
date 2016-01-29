package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

/**
 * Created by lena on 2014-05-01.
 */
public class CreateTemplateFromPackageExample extends SDKSample {

    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "doc1";
    public static final String PACKAGE_NAME_NEW = "Template name";
    public static final String PACKAGE_DESCRIPTION = "This is a package created using the e-SignLive SDK";
    public static final String PACKAGE_EMAIL_MESSAGE = "This message should be delivered to all signers";
    public static final String PACKAGE_SIGNER1_FIRST = "John";
    public static final String PACKAGE_SIGNER1_LAST = "Smith";
    public static final String PACKAGE_SIGNER2_FIRST = "Patty";
    public static final String PACKAGE_SIGNER2_LAST = "Galant";
    public PackageId templateId;

    public static void main(String... args) {
        new CreateTemplateFromPackageExample().run();
    }

    @Override
    public void execute() {
        Document document = DocumentBuilder.newDocumentWithName(DOCUMENT_NAME)
                .withId(DOCUMENT_ID)
                .fromStream(documentInputStream1, DocumentType.PDF)
                .build();

        DocumentPackage superDuperPackage = PackageBuilder.newPackageNamed(getPackageName())
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
        templateId = eslClient.getTemplateService().createTemplateFromPackage(packageId, PACKAGE_NAME_NEW);
    }
}
