package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Created by chi-wing on 5/2/14.
 */
public class DocumentOperationsExample extends SDKSample {

    private InputStream documentInputStream;

    public DocumentPackage builtPackage;
    public DocumentPackage retrievedPackage;
    public DocumentPackage retrievedPackageWithNewDocument;
    public DocumentPackage retrievedPackageWithUpdatedDocument;
    public DocumentPackage retrievedPackageWithDeletedDocument;

    public static final String ORIGINAL_DOCUMENT_NAME = "Original Document Name";
    public static final String UPDATED_DOCUMENT_NAME = "Updated Document Name";

    public static final String ORIGINAL_DOCUMENT_DESCRIPTION = "Original Document Description";
    public static final String UPDATED_DOCUMENT_DESCRIPTION = "Updated Document Description";

    public static void main(String... args) {
        new DocumentOperationsExample(Props.get()).run();
    }

    public DocumentOperationsExample(Properties props) {
        this(props.getProperty("api.key"),
                props.getProperty("api.url"));
    }

    public DocumentOperationsExample(String apiKey, String apiUrl) {
        super(apiKey, apiUrl);
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
    }

    public void execute() {

        // 1. Create a package
        builtPackage = newPackageNamed("Policy " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package demonstrating document upload")
                .expiresAt(now().plusMonths(1).toDate())
                .withSigner(newSignerWithEmail("john.smith@email.com")
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .build();

        packageId = eslClient.createPackage(builtPackage);

        retrievedPackage = eslClient.getPackage(packageId);

        // 2. Construct a document
        Document document = newDocumentWithName(ORIGINAL_DOCUMENT_NAME)
                .fromStream(documentInputStream, DocumentType.PDF)
                .withId("documentId")
                .withSignature(signatureFor("john.smith@email.com")
                        .onPage(0))
                .withDescription(ORIGINAL_DOCUMENT_DESCRIPTION)
                .build();

        // 3. Attach the document to the created package by uploading the document.
        eslClient.uploadDocument(document.getFileName(), document.getContent(), document, retrievedPackage);

        retrievedPackageWithNewDocument = eslClient.getPackage(packageId);

        //This is how you would update a document's metadata (name, description)
        document.setName(UPDATED_DOCUMENT_NAME);
        document.setDescription(UPDATED_DOCUMENT_DESCRIPTION);

        eslClient.getPackageService().updateDocumentMetadata(retrievedPackage, document);

        retrievedPackageWithUpdatedDocument = eslClient.getPackage(packageId);

        //This is how you would delete a document from a package
        eslClient.getPackageService().deleteDocument(packageId, document.getId().toString());

        retrievedPackageWithDeletedDocument = eslClient.getPackage(packageId);

    }
}
