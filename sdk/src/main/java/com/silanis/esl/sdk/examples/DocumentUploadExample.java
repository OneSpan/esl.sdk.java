package com.silanis.esl.sdk.examples;

/**
 * User: jessica
 * Date: 07/11/13
 * Time: 11:45 AM
 */

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.SessionToken;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.util.List;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Example to show how to upload a document to a package.
 */
public class DocumentUploadExample extends SDKSample{

    public static final String DOCUMENT1_NAME = "First Document";
    public static final String DOCUMENT2_NAME = "Second Document";
    public Document document1, document2;
    public List<Document> uploadedDocuments;

    public static void main( String... args ) {
        new DocumentUploadExample().run();
    }

    public void execute() {

        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream("taggedDocument.pdf");

        // 1. Create a package
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the eSignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                                .withCustomId("Client1")
                                .withFirstName("John")
                                .withLastName("Smith")
                                .withTitle("Managing Director")
                                .withCompany("Acme Inc."))
                        .build();

        packageId = eslClient.createPackage( superDuperPackage );

        superDuperPackage.setId(packageId);

        // 2. Construct documents
        document1 = newDocumentWithName(DOCUMENT1_NAME)
                .fromStream(documentInputStream1, DocumentType.PDF)
                .withSignature(signatureFor(email1)
                        .onPage(0)
                        .withField(FieldBuilder.checkBox()
                                .onPage(0)
                                .atPosition(400, 200)
                                .withValue(FieldBuilder.CHECKBOX_CHECKED))
                        .atPosition(100, 100))
                .build();

        document2 = newDocumentWithName(DOCUMENT2_NAME)
                .fromStream(documentInputStream2, DocumentType.PDF)
                .withSignature(signatureFor(email1)
                        .onPage(0)
                        .withField(FieldBuilder.checkBox()
                                .onPage(0)
                                .atPosition(400, 200)
                                .withValue(FieldBuilder.CHECKBOX_CHECKED))
                        .atPosition(100, 100))
                .build();

        // 3. Upload the documents to the created package by uploading the document.
        uploadedDocuments = eslClient.uploadDocuments(packageId, document1, document2);

        eslClient.sendPackage(superDuperPackage.getId());

        SessionToken sessionToken = eslClient.getSessionService().createSessionToken( superDuperPackage.getId().toString(), "Client1" );
    }
}

