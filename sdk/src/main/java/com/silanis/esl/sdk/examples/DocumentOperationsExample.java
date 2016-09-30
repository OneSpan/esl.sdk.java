package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.FieldBuilder;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Created by chi-wing on 5/2/14.
 */
public class DocumentOperationsExample extends SDKSample {

    public DocumentPackage builtPackage;
    public DocumentPackage retrievedPackage;
    public DocumentPackage retrievedPackageWithNewDocument;
    public Document retrievedUpdatedDocument;
    public DocumentPackage retrievedPackageWithUpdatedDocument;
    public DocumentPackage retrievedPackageWithDeletedDocument;

    public static final String ORIGINAL_DOCUMENT_NAME = "Original Document Name";
    public static final String UPDATED_DOCUMENT_NAME = "Updated Document Name";

    public static final String ORIGINAL_DOCUMENT_DESCRIPTION = "Original Document Description";
    public static final String UPDATED_DOCUMENT_DESCRIPTION = "Updated Document Description";

    public static void main(String... args) {
        new DocumentOperationsExample().run();
    }

    public void execute() {

        // 1. Create a package
        builtPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package demonstrating document upload")
                .expiresAt(now().plusMonths(1).toDate())
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .build();

        packageId = eslClient.createPackage(builtPackage);

        retrievedPackage = eslClient.getPackage(packageId);

        // 2. Construct a document
        Document document = newDocumentWithName(ORIGINAL_DOCUMENT_NAME)
                .fromStream(documentInputStream1, DocumentType.PDF)
                .withId("documentId")
                .withSignature(signatureFor(email1)
                        .atPosition(100, 100)
                        .onPage(0)
                        .withField(FieldBuilder.textField()
                                .onPage(0)
                                .atPosition(200, 200)))
                .withDescription(ORIGINAL_DOCUMENT_DESCRIPTION)
                .build();

        // 3. Attach the document to the created package by uploading the document.
        eslClient.uploadDocument(document.getFileName(), document.getContent(), document, packageId);

        retrievedPackageWithNewDocument = eslClient.getPackage(packageId);

        //This is how you would update and get a document's metadata (name, description)
        document.setName(UPDATED_DOCUMENT_NAME);
        document.setDescription(UPDATED_DOCUMENT_DESCRIPTION);

        eslClient.getPackageService().updateDocumentMetadata(retrievedPackage, document);
        retrievedUpdatedDocument = eslClient.getPackageService().getDocumentMetadata(retrievedPackage, document.getId().getId());
        retrievedPackageWithUpdatedDocument = eslClient.getPackage(packageId);

        //This is how you would delete a document from a package
        eslClient.getPackageService().deleteDocument(packageId, document.getId().toString());

        retrievedPackageWithDeletedDocument = eslClient.getPackage(packageId);
    }
}
