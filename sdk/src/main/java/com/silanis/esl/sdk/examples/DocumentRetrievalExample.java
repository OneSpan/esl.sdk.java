package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.io.DownloadedFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class DocumentRetrievalExample extends SDKSample {
    private String email1;
    private InputStream documentInputStream;

    public DownloadedFile pdfDocumentFile, originalPdfDocumentFile, zippedDocumentsFile;

    public static void main(String... args) {
        new DocumentRetrievalExample(Props.get()).run();
    }

    public DocumentRetrievalExample(Properties properties) {
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"),
                properties.getProperty("1.email"));
    }

    public DocumentRetrievalExample(String apiKey, String apiUrl, String email1) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
    }

    @Override
    public void execute() {

        String documentId = "myDocumentId";
        DocumentPackage superDuperPackage = newPackageNamed("DocumentRetrievalExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package with a document workflow created using the e-SignLive SDK")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName("Second Document")
                        .fromStream(documentInputStream, DocumentType.PDF)
                        .withId(documentId)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);

        pdfDocumentFile = eslClient.downloadDocument(packageId, documentId);
        originalPdfDocumentFile = eslClient.downloadOriginalDocument(packageId, documentId);
        zippedDocumentsFile = eslClient.downloadZippedDocuments(packageId);

        // To write the byte[] to a file, use:
        // Files.saveTo(pdfDocumentBytes, "/path/to/directory/myDocument.pdf")
    }
}
