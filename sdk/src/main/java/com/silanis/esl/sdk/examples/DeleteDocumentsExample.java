package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentId;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.FieldBuilder;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

public class DeleteDocumentsExample extends SDKSample {

    public DocumentPackage retrievedPackageWithDeletedDocuments;

    public static final String DOCUMENT1_NAME = "First Document";
    public static final String DOCUMENT2_NAME = "Second Document";

    public static final String DOCUMENT1_ID = "doc1";
    public static final String DOCUMENT2_ID = "doc2";

    public static void main(String... args) {
        new DeleteDocumentsExample().run();
    }

    @Override
    protected void execute() {

        DocumentPackage builtPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package demonstrating document upload")
                .expiresAt(now().plusMonths(1).toDate())
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .withDocument(newDocumentWithName(DOCUMENT1_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withId(DOCUMENT1_ID)
                        .withSignature(signatureFor(email1)
                                .atPosition(100, 100)
                                .onPage(0)
                                .withField(FieldBuilder.textField()
                                        .onPage(0)
                                        .atPosition(200, 200)))
                        .withDescription("Document description")
                        .build())
                .withDocument(newDocumentWithName(DOCUMENT2_NAME)
                        .fromStream(documentInputStream2, DocumentType.PDF)
                        .withId(DOCUMENT2_ID)
                        .withSignature(signatureFor(email1)
                                .atPosition(100, 100)
                                .onPage(0)
                                .withField(FieldBuilder.textField()
                                        .onPage(0)
                                        .atPosition(200, 200)))
                        .withDescription("Document description2")
                        .build())
                .build();

        packageId = eslClient.createPackage(builtPackage);

        retrievedPackage = eslClient.getPackage(packageId);

        //This is how you would delete a documents from a package
        eslClient.getPackageService().deleteDocuments(packageId, new DocumentId(DOCUMENT1_ID), new DocumentId(DOCUMENT2_ID));

        retrievedPackageWithDeletedDocuments = eslClient.getPackage(packageId);
    }
}
