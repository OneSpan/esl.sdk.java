package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class DocumentRetrievalExample extends SDKSample {
    public byte[] pdfDocumentBytes, originalPdfDocumentBytes, zippedDocumentsBytes;

    public static void main(String... args) {
        new DocumentRetrievalExample().run();
    }

    @Override
    public void execute() {

        String documentId = "myDocumentId";
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package with a document workflow created using the eSignLive SDK")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName("Second Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withId(documentId)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);

        pdfDocumentBytes = eslClient.downloadDocument(packageId, documentId);
        originalPdfDocumentBytes = eslClient.downloadOriginalDocument(packageId, documentId);
        zippedDocumentsBytes = eslClient.downloadZippedDocuments(packageId);

        // To write the byte[] to a file, use:
        // Files.saveTo(pdfDocumentBytes, "/path/to/directory/myDocument.pdf")
    }
}
