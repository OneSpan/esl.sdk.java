package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.util.Arrays;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

public class DocumentsDeleteExample extends SDKSample {

    public DocumentPackage retrievedPackageWithDocuments;
    public DocumentPackage retrievedPackageWithDeletedDocuments;

    public static final String DOCUMENT_NAME1 = "Document name";
    public static final String DOCUMENT_NAME2 = "Document name2";

    @Override
    protected void execute() {

        // 1. Construct a documents
        Document document1 = newDocumentWithName(DOCUMENT_NAME1)
                .fromStream(documentInputStream1, DocumentType.PDF)
                .withId("documentId1")
                .withSignature(signatureFor(email1)
                        .atPosition(100, 100)
                        .onPage(0)
                        .withField(FieldBuilder.textField()
                                .onPage(0)
                                .atPosition(200, 200)))
                .withDescription("Document description")
                .build();

        Document document2 = newDocumentWithName(DOCUMENT_NAME2)
                .fromStream(documentInputStream2, DocumentType.PDF)
                .withId("documentId2")
                .withSignature(signatureFor(email1)
                        .atPosition(100, 100)
                        .onPage(0)
                        .withField(FieldBuilder.textField()
                                .onPage(0)
                                .atPosition(200, 200)))
                .withDescription("Document description2")
                .build();

        // 2. Create a package with documents
        DocumentPackage builtPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package demonstrating document upload")
                .expiresAt(now().plusMonths(1).toDate())
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .withDocument(document1)
                .withDocument(document2)
                .build();

        packageId = eslClient.createPackage(builtPackage);

        retrievedPackageWithDocuments = eslClient.getPackage(packageId);

        //This is how you would delete a documents from a package
        eslClient.getPackageService().deleteDocuments(packageId, Arrays.asList(document1.getId().toString(), document2.getId().toString()));

        retrievedPackageWithDeletedDocuments = eslClient.getPackage(packageId);
    }
}
