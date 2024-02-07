package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 3/8/16.
 */
public class CreateTemplateExample extends SDKSample {

    public static final String TEMPLATE_DESCRIPTION = "This is a template created using OneSpan Sign SDK";
    public static final String TEMPLATE_EMAIL_MESSAGE = "This message should be delivered to all signers";
    public static final String TEMPLATE_SIGNER_FIRST = "John";
    public static final String TEMPLATE_SIGNER_LAST = "Smith";
    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "doc1";
    public PackageId templateId;

    public static void main(String... args) {
        new CreateTemplateExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
            .describedAs(TEMPLATE_DESCRIPTION)
            .withEmailMessage(TEMPLATE_EMAIL_MESSAGE)
            .withSigner(newSignerWithEmail(email1)
                            .withFirstName(TEMPLATE_SIGNER_FIRST)
                            .withLastName(TEMPLATE_SIGNER_LAST))
            .withDocument(newDocumentWithName(DOCUMENT_NAME)
                              .withId(DOCUMENT_ID)
                              .fromStream(documentInputStream1, DocumentType.PDF)
                              .withSignature(signatureFor(senderEmail)
                                                 .atPosition(200, 200)
                                                 .onPage(0))
                              .withSignature(signatureFor(email1)
                                                 .atPosition(200, 400)
                                                 .onPage(1)))
            .build();

        templateId = eslClient.getTemplateService().createTemplate(superDuperPackage);
    }
}
