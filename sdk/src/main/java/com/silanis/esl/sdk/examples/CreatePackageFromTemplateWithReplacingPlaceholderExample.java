package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Placeholder;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerPlaceholder;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 3/10/15.
 */
public class CreatePackageFromTemplateWithReplacingPlaceholderExample extends SDKSample {

    public PackageId templateId;

    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "doc1";
    public static final String TEMPLATE_NAME = "CreatePackageFromTemplateWithReplacingPlaceholderExample Template: " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String TEMPLATE_DESCRIPTION = "This is a template created using OneSpan Sign SDK";
    public static final String TEMPLATE_EMAIL_MESSAGE = "This message should be delivered to all signers";
    public static final String TEMPLATE_SIGNER_FIRST = "John";
    public static final String TEMPLATE_SIGNER_LAST = "Smith";

    public static final String PACKAGE_DESCRIPTION = "This is a package created using OneSpan Sign SDK";
    public static final String PACKAGE_EMAIL_MESSAGE = "This message should be delivered to all signers";
    public static final String PACKAGE_SIGNER_FIRST = "Patty";
    public static final String PACKAGE_SIGNER_LAST = "Galant";

    public static final String PLACEHOLDER_ID1 = "PlaceholderId1";
    public static final String PLACEHOLDER_ID2 = "PlaceholderId2";

    public static void main(String... args) {
        new CreatePackageFromTemplateWithReplacingPlaceholderExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage template = newPackageNamed(TEMPLATE_NAME)
                .describedAs(TEMPLATE_DESCRIPTION)
                .withEmailMessage(TEMPLATE_EMAIL_MESSAGE)
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName(TEMPLATE_SIGNER_FIRST)
                        .withLastName(TEMPLATE_SIGNER_LAST)
                        .signingOrder(3))
                .withSigner(newSignerPlaceholder(new Placeholder(PLACEHOLDER_ID1))
                        .signingOrder(1))
                .withSigner(newSignerPlaceholder(new Placeholder(PLACEHOLDER_ID2))
                        .signingOrder(2))
                .withDocument(DocumentBuilder.newDocumentWithName(DOCUMENT_NAME)
                        .withId(DOCUMENT_ID)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(SignatureBuilder.signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100))
                        .withSignature(SignatureBuilder.signatureFor(new Placeholder(PLACEHOLDER_ID1))
                                .onPage(0)
                                .atPosition(400, 100))
                        .withSignature(SignatureBuilder.signatureFor(new Placeholder(PLACEHOLDER_ID2))
                                .onPage(0)
                                .atPosition(100, 300)))
                .build();

        templateId = eslClient.getTemplateService().createTemplate(template);

        DocumentPackage newPackage = PackageBuilder.newPackageNamed(getPackageName())
                .describedAs(PACKAGE_DESCRIPTION)
                .withEmailMessage(PACKAGE_EMAIL_MESSAGE)
                .withSigner(newSignerWithEmail(email2)
                        .withFirstName(PACKAGE_SIGNER_FIRST)
                        .withLastName(PACKAGE_SIGNER_LAST).replacing(new Placeholder(PLACEHOLDER_ID1)))
                .withSigner(newSignerWithEmail(email3)
                        .withFirstName(PACKAGE_SIGNER_FIRST)
                        .withLastName(PACKAGE_SIGNER_LAST).replacing(new Placeholder(PLACEHOLDER_ID2)))
                .withSigner(newSignerWithEmail(email4)
                        .withFirstName(PACKAGE_SIGNER_FIRST)
                        .withLastName(PACKAGE_SIGNER_LAST))
                .withSigner(newSignerWithEmail(email5)
                        .withFirstName(PACKAGE_SIGNER_FIRST)
                        .withLastName(PACKAGE_SIGNER_LAST))
                .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings().withInPerson())
                .build();

        packageId = eslClient.getTemplateService().createPackageFromTemplate(templateId, newPackage);
        retrievedPackage = eslClient.getPackage(packageId);
    }
}
