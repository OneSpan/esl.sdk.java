package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.SignatureId;
import com.silanis.esl.sdk.SignatureStyle;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder.newDocumentPackageSettings;
import static com.silanis.esl.sdk.builder.FieldBuilder.textField;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 09/02/18.
 */
public class AdaPackageExample extends SDKSample {

    public static final String DOCUMENT_NAME = "First Document";

    public static void main(String... args) {
        new AdaPackageExample().run();
    }

    public void execute() {
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("taggedDocument.pdf");

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withSettings(newDocumentPackageSettings()
                        .withAda())
                .describedAs("This is a package created using the eSignLive SDK")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John1")
                        .withLastName("Smith1"))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .enableExtraction()
                        .withSignature(signatureFor(email1)
                                .withId(new SignatureId("CustomerSignature"))
                                .withName("CustomerSignature")
                                .withStyle(SignatureStyle.FULL_NAME)
                                .withPositionExtracted()
                                .withField(textField()
                                        .withId(new FieldId("APR"))
                                        .withName("APR")
                                        .withPositionExtracted()))
                        .withSignature(signatureFor(email1)
                                .withId(new SignatureId("Agent"))
                                .withName("Agent")
                                .withPositionExtracted()))
                .build();

        packageId = eslClient.createPackageOneStep(superDuperPackage);
        eslClient.sendPackage(packageId);
    }
}