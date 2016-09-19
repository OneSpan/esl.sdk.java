package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.SenderStatus;
import com.silanis.esl.sdk.builder.AccountMemberBuilder;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.builder.SenderInfoBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTemplateOnBehalfOfAnotherSenderExample extends SDKSample {

    public PackageId templateId;

    public static final String SENDER_FIRST_NAME = "Rob";
    public static final String SENDER_LAST_NAME = "Mason";
    public static final String SENDER_TITLE = "Chief Vizier";
    public static final String SENDER_COMPANY = "The Masons";

    public static void main(String... args) {
        new CreateTemplateOnBehalfOfAnotherSenderExample().run();
    }

    @Override
    public void execute() {
        senderEmail = getRandomEmail();

        // Invite the sender to account
        eslClient.getAccountService().inviteUser(
                AccountMemberBuilder.newAccountMember(senderEmail)
                        .withFirstName("firstName")
                        .withLastName("lastName")
                        .withCompany("company")
                        .withTitle("title")
                        .withPhoneNumber("phoneNumber")
                        .withStatus(SenderStatus.ACTIVE)
                        .build()
        );

        // Create the template specifying the sender
        DocumentPackage superDuperPackage = PackageBuilder.newPackageNamed(getPackageName())
                .describedAs("This is a package created using the eSignLive SDK")
                .withEmailMessage("This message should be delivered to all signers")
                .withSenderInfo(SenderInfoBuilder.newSenderInfo(senderEmail)
                        .withName(SENDER_FIRST_NAME, SENDER_LAST_NAME)
                        .withTitle(SENDER_TITLE)
                        .withCompany(SENDER_COMPANY))
                .withSigner(SignerBuilder.newSignerWithEmail(email1)
                        .withFirstName("Patty")
                        .withLastName("Galant"))
                .withDocument(DocumentBuilder.newDocumentWithName("First Document")
                        .withId("documentId")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(SignatureBuilder.signatureFor(senderEmail)
                                .atPosition(200, 200)
                                .onPage(0))
                        .withSignature(SignatureBuilder.signatureFor(email1)
                                .atPosition(200, 400)
                                .onPage(0)))
                .build();

        // Create a template on behalf of another sender
        templateId = eslClient.getTemplateService().createTemplate(superDuperPackage);

        DocumentPackage packageFromTemplate = PackageBuilder.newPackageNamed("PackageFromTemplateOnBehalfOfSender" + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .withSenderInfo(SenderInfoBuilder.newSenderInfo(senderEmail)
                        .withName(SENDER_FIRST_NAME, SENDER_LAST_NAME)
                        .withTitle(SENDER_TITLE)
                        .withCompany(SENDER_COMPANY))
                .withDocument(DocumentBuilder.newDocumentWithName("Second Document")
                        .withId("documentId2")
                        .fromStream(documentInputStream2, DocumentType.PDF))
                .build();

        // Create package from template on behalf of another sender
        packageId = eslClient.getTemplateService().createPackageFromTemplate(templateId, packageFromTemplate);
    }
}
