package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

/**
 * This example shows how to send a package with signer's local language.
 */
public class LocalLanguageExample extends SDKSample {

    public static void main(String... args) {
        new LocalLanguageExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
            .describedAs("This is a package created using the eSignLive SDK")
            .withSigner(newSignerWithEmail(email1)
                .withFirstName("One1")
                .withLastName("Span1")
                .withLocalLanguage())
            .withDocument(newDocumentWithName("First Document")
                .fromStream(documentInputStream1, DocumentType.PDF)
                .withSignature(signatureFor(email1)
                    .onPage(0)
                    .atPosition(100, 100)))
            .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);
    }
}
