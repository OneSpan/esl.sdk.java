package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.captureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class FromFileCaptureSignatureExample extends SDKSample {

    public static void main(String... args) {
        new FromFileCaptureSignatureExample().run();
    }

    @Override
    protected void execute() {

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
                .withSigner(newSignerWithEmail(senderEmail)
                        .withFirstName("John1")
                        .withLastName("Smith1"))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(captureFor(senderEmail)
                                .onPage(0)
                                .atPosition(100, 100)
                                .setFromFile(true)))
                .build();

        packageId = eslClient.createPackageOneStep(superDuperPackage);
        eslClient.sendPackage(packageId);
        retrievedPackage = eslClient.getPackage(packageId);
    }
}
