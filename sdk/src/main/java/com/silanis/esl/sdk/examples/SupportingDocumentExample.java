package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.DocumentInfo;
import com.silanis.esl.api.model.DocumentMetadata;
import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import com.silanis.esl.sdk.builder.internal.StreamDocumentSource;
import com.silanis.esl.sdk.io.DownloadedFile;
import com.silanis.esl.sdk.io.Files;
import com.silanis.esl.sdk.service.SupportingDocumentsService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;

public class SupportingDocumentExample extends SDKSample {

    // Constants
    private static final String SIGNER1_ID = "signerId";
    private static final String SUPPORTING_DOCUMENT_NAME_1 = "The supporting document number one.pdf";
    private static final String SUPPORTING_DOCUMENT_NAME_2 = "The supporting document number two.pdf";
    private static final String SUPPORTING_DOCUMENT_NAME_3 = "The supporting document number three.pdf";
    private static final Map<String, String> RENAME_PAYLOAD = Collections.singletonMap("fileName", "renamed");
    private static final String DOCUMENT_RESOURCE = "document.pdf";
    private static final String TEST_DOCUMENT_NAME = "test document";
    private static final String PACKAGE_DESCRIPTION = "This is a package created using OneSpan Sign SDK";
    public static final String DOWNLOADED_ALL_SUPPORTING_DOCUMENTS_FOR_PACKAGE_ZIP = "downloadedAllSupportingDocumentsForPackage.zip";


    // Instance variables
    private final byte[] supportingDocumentContent;
    private SupportingDocumentsService supportingDocumentsService;

    // Results
    public List<DocumentInfo> supportingDocumentAfterUpload;
    public List<DocumentInfo> supportingDocumentAfterDelete;
    public DocumentInfo supportingDocumentAfterRename;
    public DocumentMetadata documentMetadata;
    public ZipFile downloadedAllSupportingDocumentsForPackageZip;

    public static void main(String... args) {
        new SupportingDocumentExample().run();
    }

    public SupportingDocumentExample() {
        // Use try-with-resources to auto-close the InputStream
        try (InputStream inputStream = getResourceAsStream(DOCUMENT_RESOURCE)) {
            this.supportingDocumentContent = new StreamDocumentSource(inputStream).content();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load document resource", e);
        }
        supportingDocumentsService = eslClient.getSupportingDocumentsService();
    }

    @Override
    protected void execute() {
        packageId = createAPackageWithDocument();
        supportingDocumentsService = eslClient.getSupportingDocumentsService();

        uploadSupportingDocuments();
        downloadFirstDocument();
        renameSecondDocument();
        deleteThirdDocument();
        downloadAllDocuments();
    }

    private PackageId createAPackageWithDocument() {
        Signer signer = createSigner();

        DocumentPackage documentPackage = newPackageNamed(getPackageName())
                .describedAs(PACKAGE_DESCRIPTION)
                .withSigner(signer)
                .withDocument(createTestDocument(signer))
                .build();

        return eslClient.createPackage(documentPackage);
    }

    private Signer createSigner() {
        return SignerBuilder.newSignerWithEmail(email1)
                .withFirstName("John")
                .withLastName("Smith")
                .withCustomId(SIGNER1_ID)
                .build();
    }

    private Document createTestDocument(Signer signer) {
        return DocumentBuilder.newDocumentWithName(TEST_DOCUMENT_NAME)
                .fromStream(documentInputStream1, DocumentType.PDF)
                .withSignature(SignatureBuilder.signatureFor(email1).build())
                .build();
    }

    private void uploadSupportingDocuments() {
        Map<String, byte[]> supportingDocuments = createSupportingDocumentsMap();
        supportingDocumentAfterUpload = supportingDocumentsService.uploadSupportingDocuments(packageId.getId(), supportingDocuments);
    }

    private Map<String, byte[]> createSupportingDocumentsMap() {
        Map<String, byte[]> documents = new HashMap<>();
        documents.put(SUPPORTING_DOCUMENT_NAME_1, supportingDocumentContent);
        documents.put(SUPPORTING_DOCUMENT_NAME_2, supportingDocumentContent);
        documents.put(SUPPORTING_DOCUMENT_NAME_3, supportingDocumentContent);
        return documents;
    }

    private void downloadFirstDocument() {
        documentMetadata = supportingDocumentsService.downloadSupportingDocument(
                packageId.getId(),
                supportingDocumentAfterUpload.get(0).getId()
        );
    }

    private void renameSecondDocument() {
        if (supportingDocumentAfterUpload.size() > 1) {
            supportingDocumentAfterRename = supportingDocumentsService.renameSupportingDocument(
                    packageId.getId(),
                    supportingDocumentAfterUpload.get(1).getId(),
                    RENAME_PAYLOAD
            );
        }
    }

    private void deleteThirdDocument() {
        if (supportingDocumentAfterUpload.size() > 2) {
            supportingDocumentsService.deleteSupportingDocument(
                    packageId.getId(),
                    supportingDocumentAfterUpload.get(2).getId()
            );
            supportingDocumentAfterDelete = supportingDocumentsService.getListOfSupportingDocuments(packageId.getId());
        }
    }

    private void downloadAllDocuments() {
        DownloadedFile downloadedFiles = supportingDocumentsService.downloadAllSupportingDocuments(packageId.getId());
        Files.saveTo(downloadedFiles.getContents(), DOWNLOADED_ALL_SUPPORTING_DOCUMENTS_FOR_PACKAGE_ZIP);
        try {
            downloadedAllSupportingDocumentsForPackageZip = new ZipFile(DOWNLOADED_ALL_SUPPORTING_DOCUMENTS_FOR_PACKAGE_ZIP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream getResourceAsStream(String resourceName) {
        return this.getClass().getClassLoader().getResourceAsStream(resourceName);
    }

    public List<DocumentInfo> getSupportingDocumentAfterUpload() {
        return supportingDocumentAfterUpload;
    }

    public List<DocumentInfo> getSupportingDocumentAfterDelete() {
        return supportingDocumentAfterDelete;
    }

    public DocumentInfo getSupportingDocumentAfterRename() {
        return supportingDocumentAfterRename;
    }

    public DocumentMetadata getDocumentMetadata() {
        return documentMetadata;
    }

    public ZipFile getDownloadedAllSupportingDocumentsForPackageZip() {
        return downloadedAllSupportingDocumentsForPackageZip;
    }
}