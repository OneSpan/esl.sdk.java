package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Signer;

import static com.silanis.esl.sdk.SignatureImageFormat.JPG;
import static com.silanis.esl.sdk.SignatureImageFormat.PNG;
import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 10/16/15.
 */
public class SignatureImageExample extends SDKSample {

    public static void main( String... args ) {
        new SignatureImageExample().run();
    }

    public void execute() {
        Signer signer1 = newSignerWithEmail(email1)
                .withCustomId("signer1")
                .withFirstName("John1")
                .withLastName("Smith1").build();
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
                .withSigner(signer1)
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withSignature(signatureFor(email1)
                                                             .onPage(0)
                                                             .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage(packageId);

        eslClient.getSignatureImageService().getSignatureImageForSender(senderUID, PNG);
        eslClient.getSignatureImageService().getSignatureImageForPackageRole(packageId, signer1.getId(), JPG);
    }
}
