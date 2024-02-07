package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Placeholder;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 8/17/15.
 */
public class ChangePlaceholderNameExample extends SDKSample {

    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "doc1";
    public static final String TEMPLATE_NAME = "ChangePlaceholderNameExample Template: " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String TEMPLATE_DESCRIPTION = "This is a template created using OneSpan Sign SDK";
    public static final String TEMPLATE_EMAIL_MESSAGE = "This message should be delivered to all signers";
    public static final String TEMPLATE_SIGNER_FIRST = "John";
    public static final String TEMPLATE_SIGNER_LAST = "Smith";

    public static final String PLACEHOLDER_ID = "placeholderId";
    public Placeholder newPlaceholder, updatedPlaceholder;
    public DocumentPackage updatedTemplate;

    public static void main(String... args) {
        new ChangePlaceholderNameExample().run();
    }

    @Override
    public void execute() {
        newPlaceholder = new Placeholder(PLACEHOLDER_ID, "placeholderName");
        updatedPlaceholder = new Placeholder(PLACEHOLDER_ID, "updatedPlaceholderName");

        DocumentPackage template = newPackageNamed(TEMPLATE_NAME)
                .describedAs(TEMPLATE_DESCRIPTION)
                .withEmailMessage(TEMPLATE_EMAIL_MESSAGE)
                .withSigner(newSignerWithEmail(email1)
                                    .withFirstName(TEMPLATE_SIGNER_FIRST)
                                    .withLastName(TEMPLATE_SIGNER_LAST))
                .withSigner(SignerBuilder.newSignerPlaceholder(newPlaceholder))
                .withDocument(DocumentBuilder.newDocumentWithName(DOCUMENT_NAME)
                                             .withId(DOCUMENT_ID)
                                             .fromStream(documentInputStream1, DocumentType.PDF)
                                             .withSignature(SignatureBuilder.signatureFor(email1)
                                                                            .onPage(0)
                                                                            .atPosition(100, 100))
                                             .withSignature(SignatureBuilder.signatureFor(newPlaceholder)
                                                                            .onPage(0)
                                                                            .atPosition(400, 100)))
                .build();

        PackageId templateId = eslClient.getTemplateService().createTemplate(template);
        retrievedPackage = eslClient.getPackage(templateId);

        eslClient.getTemplateService().updatePlaceholder(templateId, updatedPlaceholder);

        updatedTemplate = eslClient.getPackage(templateId);
    }
}
