package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Visibility;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import static com.silanis.esl.sdk.Visibility.SENDER;

/**
 * Created by mina on 16/02/15.
 */
public class CreateSenderTemplateExample extends SDKSample {

    public PackageId templateId;
    public Visibility visibility = SENDER;

    public static void main(String... args) {
        new CreateSenderTemplateExample().run();
    }

    @Override
    public void execute() {

        DocumentPackage superDuperPackage = PackageBuilder.newPackageNamed(getPackageName())
                .describedAs("This is a Template created using OneSpan Sign SDK")
                .withVisibility(visibility)
                .withEmailMessage("This message should be delivered to all signers")
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

        templateId = eslClient.getTemplateService().createTemplate(superDuperPackage);
    }
}
