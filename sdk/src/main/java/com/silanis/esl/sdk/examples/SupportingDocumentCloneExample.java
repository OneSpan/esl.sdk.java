package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.DocumentInfo;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import com.silanis.esl.sdk.builder.internal.StreamDocumentSource;
import com.silanis.esl.sdk.service.SupportingDocumentsService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class SupportingDocumentCloneExample extends SDKSample {

    // Document constants
    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "doc1";
    private static final String DOCUMENT_RESOURCE = "document.pdf";

    // Package constants
    public static final String PACKAGE_DESCRIPTION = "This is a package created using OneSpan Sign SDK";
    public static final String PACKAGE_EMAIL_MESSAGE = "This message should be delivered to all signers";
    public static final String PACKAGE_EMAIL_MESSAGE2 = "Changed the email message";

    // Signer 1 constants
    public static final String PACKAGE_SIGNER1_FIRST = "John";
    public static final String PACKAGE_SIGNER1_LAST = "Smith";
    public static final String PACKAGE_SIGNER1_TITLE = "Manager";
    public static final String PACKAGE_SIGNER1_COMPANY = "Acme Inc.";
    public static final String PACKAGE_SIGNER1_CUSTOM_ID = "Signer1";

    // Signer 2 constants
    public static final String PACKAGE_SIGNER2_FIRST = "Elvis";
    public static final String PACKAGE_SIGNER2_LAST = "Presley";
    public static final String PACKAGE_SIGNER2_TITLE = "The King";
    public static final String PACKAGE_SIGNER2_COMPANY = "Elvis Presley International";
    public static final String PACKAGE_SIGNER2_CUSTOM_ID = "Signer2";

    // Supporting document constants
    private static final String[] SUPPORTING_DOCUMENT_NAMES = {
            "The supporting document number one.pdf",
            "The supporting document number two.pdf",
            "The supporting document number three.pdf"
    };

    // Instance variables
    private final byte[] supportingDocumentContent;
    private final SupportingDocumentsService supportingDocumentsService;

    // Results - encapsulated with defensive copying
    private List<DocumentInfo> supportingDocumentBeforeCloning;
    private List<DocumentInfo> supportingDocumentAfterCloning;

    public static void main(String... args) {
        new SupportingDocumentCloneExample().run();
    }

    public SupportingDocumentCloneExample() {
        this.supportingDocumentContent = loadDocumentContent();
        this.supportingDocumentsService = eslClient.getSupportingDocumentsService();
    }

    @Override
    public void execute() {
        DocumentPackage template = createTemplate();
        PackageId templateId = eslClient.getTemplateService().createTemplate(template);
        template.setId(templateId);

        uploadSupportingDocuments(templateId.getId());
        supportingDocumentBeforeCloning = supportingDocumentsService.getListOfSupportingDocuments(templateId.getId());

        DocumentPackage newPackage = createPackageFromTemplate();
        packageId = eslClient.getTemplateService().createPackageFromTemplate(template.getId(), newPackage);
        retrievedPackage = eslClient.getPackage(packageId);

        supportingDocumentAfterCloning = supportingDocumentsService.getListOfSupportingDocuments(packageId.getId());
    }

    private byte[] loadDocumentContent() {
        try (InputStream inputStream = getResourceAsStream(DOCUMENT_RESOURCE)) {
            if (inputStream == null) {
                throw new EslException("Resource not found: " + DOCUMENT_RESOURCE);
            }
            return new StreamDocumentSource(inputStream).content();
        } catch (Exception e) {
            throw new EslException("Failed to load document resource: " + DOCUMENT_RESOURCE, e);
        }
    }

    private DocumentPackage createTemplate() {
        return PackageBuilder.newPackageNamed("Template " + getPackageName())
                .describedAs("first message")
                .withEmailMessage(PACKAGE_EMAIL_MESSAGE)
                .withSigner(createSigner1())
                .withDocument(createDocument())
                .build();
    }

    private DocumentPackage createPackageFromTemplate() {
        return PackageBuilder.newPackageNamed(getPackageName())
                .describedAs(PACKAGE_DESCRIPTION)
                .withEmailMessage(PACKAGE_EMAIL_MESSAGE2)
                .withSigner(createSigner2())
                .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings()
                        .withoutInPerson()
                        .withoutDecline()
                        .withoutOptOut()
                        .withWatermark()
                        .build())
                .build();
    }

    private SignerBuilder createSigner1() {
        return newSignerWithEmail(email1)
                .withFirstName(PACKAGE_SIGNER1_FIRST)
                .withLastName(PACKAGE_SIGNER1_LAST)
                .withTitle(PACKAGE_SIGNER1_TITLE)
                .withCompany(PACKAGE_SIGNER1_COMPANY)
                .withCustomId(PACKAGE_SIGNER1_CUSTOM_ID);
    }

    private SignerBuilder createSigner2() {
        return newSignerWithEmail(email2)
                .withFirstName(PACKAGE_SIGNER2_FIRST)
                .withLastName(PACKAGE_SIGNER2_LAST)
                .withTitle(PACKAGE_SIGNER2_TITLE)
                .withCompany(PACKAGE_SIGNER2_COMPANY)
                .withCustomId(PACKAGE_SIGNER2_CUSTOM_ID);
    }

    private DocumentBuilder createDocument() {
        return DocumentBuilder.newDocumentWithName(DOCUMENT_NAME)
                .fromStream(documentInputStream1, DocumentType.PDF)
                .withId(DOCUMENT_ID);
    }

    private void uploadSupportingDocuments(String templateId) {
        Map<String, byte[]> supportingDocuments = createSupportingDocumentsMap();
        supportingDocumentsService.uploadSupportingDocuments(templateId, supportingDocuments);
    }

    private InputStream getResourceAsStream(String resourceName) {
        return this.getClass().getClassLoader().getResourceAsStream(resourceName);
    }

    private Map<String, byte[]> createSupportingDocumentsMap() {
        Map<String, byte[]> documents = new HashMap<>();
        for (String docName : SUPPORTING_DOCUMENT_NAMES) {
            documents.put(docName, supportingDocumentContent);
        }
        return documents;
    }

    // Getters with defensive copying
    public List<DocumentInfo> getSupportingDocumentBeforeCloning() {
        return supportingDocumentBeforeCloning != null ?
                new ArrayList<>(supportingDocumentBeforeCloning) : null;
    }

    public List<DocumentInfo> getSupportingDocumentAfterCloning() {
        return supportingDocumentAfterCloning != null ?
                new ArrayList<>(supportingDocumentAfterCloning) : null;
    }
}
