package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.ExtractionType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Page;
import com.silanis.esl.sdk.PageRequest;
import com.silanis.esl.sdk.Visibility;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Basic package with in-person mode set at the document package level. Expires in a month.
 */
public class ListTemplatesExample extends SDKSample {

    public static final String TEMPLATE_DESCRIPTION = "This is a template created using the eSignLive SDK";
    public static final String TEMPLATE_EMAIL_MESSAGE = "This message should be delivered to all signers";
    public static final String TEMPLATE_SIGNER_FIRST = "John";
    public static final String TEMPLATE_SIGNER_LAST = "Smith";
    public static final String DOCUMENT1_NAME = "First Document";
    public static final String DOCUMENT2_NAME = "Second Document";
    public static final String DOCUMENT3_NAME = "Third Document";
    public static final String DOCUMENT_ID = "doc1";

    public PackageId templateId;
    public String packageName;

    private Page<DocumentPackage> templatesPage;
    private List<DocumentPackage> templates = new ArrayList<DocumentPackage>();
    private InputStream documentInputStream3;

    public static void main( String... args ) {
        new ListTemplatesExample().run();
    }

    @Override
    public void execute() {

        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document_with_text_tag_and_form_field.pdf");
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream("document_with_text_tag_and_form_field.pdf");
        documentInputStream3 = this.getClass().getClassLoader().getResourceAsStream("document_with_text_tag_and_form_field.pdf");

        packageName = getPackageName();
        DocumentPackage superDuperTemplate = newPackageNamed(packageName)
                .describedAs(TEMPLATE_DESCRIPTION)
                .withEmailMessage(TEMPLATE_EMAIL_MESSAGE)
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName(TEMPLATE_SIGNER_FIRST)
                        .withLastName(TEMPLATE_SIGNER_LAST))
                .withDocument(newDocumentWithName(DOCUMENT1_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .enableExtraction())
                .withDocument(newDocumentWithName(DOCUMENT2_NAME)
                        .fromStream(documentInputStream2, DocumentType.PDF)
                        .withExtractionType(ExtractionType.TEXT_TAGS)
                        .enableExtraction())
                .withDocument(newDocumentWithName(DOCUMENT3_NAME)
                        .fromStream(documentInputStream3, DocumentType.PDF)
                        .withExtractionType(ExtractionType.TEXT_TAGS)
                        .withExtractionType(ExtractionType.ACROFIELDS)
                        .enableExtraction())
                .build();

        templateId = eslClient.getTemplateService().createTemplate(superDuperTemplate);

        templatesPage = eslClient.getPackageService().getTemplates(new PageRequest(0), Visibility.SENDER);
        templates.addAll(templatesPage.getResults());
    }

    public List<DocumentPackage> getTemplates() {
        return templates;
    }
}