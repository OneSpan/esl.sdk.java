package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Placeholder;
import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 5/25/15.
 */
public class UpdateTemplateWithPlaceholderExample extends SDKSample {

    public PackageId templateId;

    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "doc1";
    public static final String TEMPLATE_NAME = "UpdateTemplateWithPlaceholderExample Template: " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String TEMPLATE_DESCRIPTION = "This is a template created using the e-SignLive SDK";
    public static final String TEMPLATE_EMAIL_MESSAGE = "This message should be delivered to all signers";
    public static final String TEMPLATE_SIGNER_FIRST = "John";
    public static final String TEMPLATE_SIGNER_LAST = "Smith";

    public static final String PLACEHOLDER_ID = "PlaceholderId1";
    public static final String PLACEHOLDER2_ID = "PlaceholderId2";

    public DocumentPackage retrievedTemplate, updatedTemplate;

    public static void main(String... args) {
        new UpdateTemplateWithPlaceholderExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage template = newPackageNamed(TEMPLATE_NAME)
                .describedAs(TEMPLATE_DESCRIPTION)
                .withEmailMessage(TEMPLATE_EMAIL_MESSAGE)
                .withSigner(newSignerWithEmail(email1)
                                    .withFirstName(TEMPLATE_SIGNER_FIRST)
                                    .withLastName(TEMPLATE_SIGNER_LAST))
                .withSigner(SignerBuilder.newSignerPlaceholder(new Placeholder(PLACEHOLDER_ID)))
                .withDocument(DocumentBuilder.newDocumentWithName(DOCUMENT_NAME)
                    .withId(DOCUMENT_ID)
                    .fromStream(documentInputStream1, DocumentType.PDF)
                    .withSignature(signatureFor(email1)
                                           .onPage(0)
                                           .atPosition(100, 100))
                    .withSignature(signatureFor(new Placeholder(PLACEHOLDER_ID))
                                           .onPage(0)
                                           .atPosition(400, 100)))
                .build();

        templateId = eslClient.getTemplateService().createTemplate(template);
        retrievedTemplate = eslClient.getPackage(templateId);

        eslClient.getTemplateService().addPlaceholder(templateId, new Placeholder(PLACEHOLDER2_ID));
        updatedTemplate = eslClient.getPackage(templateId);

        Signature newSignature = signatureFor(new Placeholder(PLACEHOLDER2_ID))
                .onPage(0)
                .atPosition(400, 300).build();

        eslClient.getApprovalService().addSignature(updatedTemplate, DOCUMENT_ID, newSignature);
        updatedTemplate = eslClient.getPackage(templateId);
    }
}
