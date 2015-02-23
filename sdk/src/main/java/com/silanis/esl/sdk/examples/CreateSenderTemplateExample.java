package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.*;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.Visibility.ACCOUNT;

/**
 * Created by mina on 16/02/15.
 */
public class CreateSenderTemplateExample extends SDKSample {

    private InputStream documentInputStream1;
    private String senderEmail;
    private String email1;

    public PackageId templateId;
    public Visibility visibility = ACCOUNT;

    public static void main(String... args) {
        new CreateSenderTemplateExample(Props.get()).run();
    }

    public CreateSenderTemplateExample(Properties properties) {
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"),
                properties.getProperty("1.email"));
    }

    public CreateSenderTemplateExample(String apiKey, String apiUrl, String email1) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        this.senderEmail = getRandomEmail();

        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
    }

    @Override
    public void execute() {
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

        // Create the template
        DocumentPackage superDuperPackage = PackageBuilder.newPackageNamed("CreateSenderTemplateExample: " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a Template created using the e-SignLive SDK")
                .withVisibility(visibility)
                .withEmailMessage("This message should be delivered to all signers")
                .withSenderInfo(SenderInfoBuilder.newSenderInfo(senderEmail)
                                                 .withName("John", "Smith"))
                .withSigner(SignerBuilder.newSignerWithEmail(email1)
                                         .withFirstName("Patty")
                                         .withLastName("Galant"))
                .withDocument(DocumentBuilder.newDocumentWithName("First Document")
                        .withId("documentId")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(SignatureBuilder.signatureFor(email1)
                                .atPosition(200, 400)
                                .onPage(0)))
                .build();

        // Create a template on behalf of another sender
        templateId = eslClient.getTemplateService().createTemplate(superDuperPackage);
    }

}
