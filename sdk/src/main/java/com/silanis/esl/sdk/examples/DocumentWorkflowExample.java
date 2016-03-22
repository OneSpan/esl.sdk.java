package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example of document workflow (signing order)
 */
public class DocumentWorkflowExample extends SDKSample {

    public DocumentPackage preOrderDocumentsPackage;
    public DocumentPackage postOrderDocumentsPackage;
    public static final String FIRST_DOCUMENT_NAME = "First Document";
    public static final String SECOND_DOCUMENT_NAME = "Second Document";

    public static void main( String... args ) {
        new DocumentWorkflowExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package with a document workflow created using the e-SignLive SDK")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName(SECOND_DOCUMENT_NAME)
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .atIndex(2)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .withDocument(newDocumentWithName(FIRST_DOCUMENT_NAME)
                        .fromStream( documentInputStream2, DocumentType.PDF )
                        .atIndex(1)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackageOneStep( superDuperPackage );
        preOrderDocumentsPackage = eslClient.getPackage(packageId);

        postOrderDocumentsPackage = eslClient.getPackage(packageId);
        postOrderDocumentsPackage.getDocument(FIRST_DOCUMENT_NAME).setIndex(2);
        postOrderDocumentsPackage.getDocument(SECOND_DOCUMENT_NAME).setIndex(1);

        eslClient.getPackageService().orderDocuments(postOrderDocumentsPackage);

        eslClient.sendPackage(packageId);
    }
}