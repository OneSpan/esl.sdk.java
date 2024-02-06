package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.CapturedSignature;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.captureFor;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 12/7/15.
 */
public class SignDocumentsExample extends SDKSample {
    private String signer1Id = "signer1";

    public DocumentPackage retrievedPackageBeforeSigning, retrievedPackageAfterSigningApproval1, retrievedPackageAfterSigningApproval2;

    public static void main(String... args) {
        new SignDocumentsExample().run();
    }

    public void execute() {
        CapturedSignature capturedSignature = new CapturedSignature("AQAAAIPGDPtxqL+RsL7/w/7eEX+cAtwAAwADAFAAAAADAAAAnALcACMAAAACq5ZQg105VH9Z/1l+UM9QF3A0v3BEv2BmYYFgSGAYQBZAJkA0QDVAREBmQENAQ0BRUJFQg1CDUFKbQENASUBGQERFUA==");
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId(signer1Id)
                        .withFirstName("John1")
                        .withLastName("Smith1"))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(captureFor(senderEmail)
                                .onPage(0)
                                .atPosition(100, 100))
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(400, 100)))
                .withDocument(newDocumentWithName("Second Document")
                        .fromStream(documentInputStream2, DocumentType.PDF)
                        .withSignature(signatureFor(senderEmail)
                                .onPage(0)
                                .atPosition(100, 100))
                        .withSignature(captureFor(email1)
                                .onPage(0)
                                .atPosition(400, 100)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);
        retrievedPackageBeforeSigning = eslClient.getPackage(packageId);

        eslClient.signDocuments(packageId, capturedSignature);
        retrievedPackageAfterSigningApproval1 = eslClient.getPackage(packageId);

        eslClient.signDocuments(packageId, signer1Id, capturedSignature);
        retrievedPackageAfterSigningApproval2 = eslClient.getPackage(packageId);
    }
}
