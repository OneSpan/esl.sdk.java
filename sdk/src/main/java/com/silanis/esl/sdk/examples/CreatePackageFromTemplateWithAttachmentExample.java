package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Placeholder;
import com.silanis.esl.sdk.builder.AttachmentRequirementBuilder;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 2/6/15.
 */
public class CreatePackageFromTemplateWithAttachmentExample extends SDKSample {

    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "doc1";
    public static final String PACKAGE_DESCRIPTION = "This is a package created using OneSpan Sign SDK";
    public static final String PACKAGE_EMAIL_MESSAGE = "This message should be delivered to all signers";
    public static final String PACKAGE_EMAIL_MESSAGE2 = "Changed the email message";

    public static final String PLACEHOLDER_ID = "PlaceholderId1";

    public static final String SIGNER1_FIRST = "John";
    public static final String SIGNER1_LAST = "Smith";
    public static final String SIGNER1_TITLE = "Manager";
    public static final String SIGNER1_COMPANY = "Acme Inc.";

    public static final String ATTACHMENT_REQUIREMENT_NAME = "Driver's license";
    public static final String ATTACHMENT_REQUIREMENT_DESCRIPTION = "Please upload a scanned copy of your driver's license.";

    public static void main(String... args) {
        new CreatePackageFromTemplateWithAttachmentExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage template = PackageBuilder.newPackageNamed("Template " + getPackageName())
             .describedAs("first message")
             .withEmailMessage(PACKAGE_EMAIL_MESSAGE)
             .withSigner(SignerBuilder.newSignerPlaceholder(new Placeholder(PLACEHOLDER_ID)))
             .withDocument(DocumentBuilder.newDocumentWithName(DOCUMENT_NAME)
                                          .fromStream(documentInputStream1, DocumentType.PDF)
                                          .withId(DOCUMENT_ID)
                                          .withSignature(SignatureBuilder.signatureFor(new Placeholder(PLACEHOLDER_ID))
                                                                         .onPage(0)
                                                                         .atPosition(100, 100))
                                          .build())
             .build();

        template.setId(eslClient.getTemplateService().createTemplate(template));

        DocumentPackage newPackage = PackageBuilder.newPackageNamed(getPackageName())
           .describedAs(PACKAGE_DESCRIPTION)
           .withEmailMessage(PACKAGE_EMAIL_MESSAGE2)
           .withSigner(newSignerWithEmail(email1)
                               .withFirstName(SIGNER1_FIRST)
                               .withLastName(SIGNER1_LAST)
                               .withTitle(SIGNER1_TITLE)
                               .withCompany(SIGNER1_COMPANY)
                               .withCustomId(PLACEHOLDER_ID)
                               .withAttachmentRequirement(AttachmentRequirementBuilder.newAttachmentRequirementWithName(ATTACHMENT_REQUIREMENT_NAME)
                                  .withDescription(ATTACHMENT_REQUIREMENT_DESCRIPTION)
                                  .isRequiredAttachment()
                                  .build()))
           .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings()
                                                       .withInPerson()
                                                       .build())
           .build();

        packageId = eslClient.getTemplateService().createPackageFromTemplate(template.getId(), newPackage);
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
