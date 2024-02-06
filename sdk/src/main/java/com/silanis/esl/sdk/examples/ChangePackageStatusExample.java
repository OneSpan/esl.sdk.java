package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 1/22/15.
 */
public class ChangePackageStatusExample extends SDKSample {
    public DocumentPackage sentPackage, trashedPackage, restoredPackage;

    public static void main( String... args ) {
        new ChangePackageStatusExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
                .withSigner(newSignerWithEmail(email1)
                                    .withFirstName("John1")
                                    .withLastName("Smith1"))
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withSignature(signatureFor(email1)
                                                             .onPage(0)
                                                             .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage(packageId);
        sentPackage = eslClient.getPackage(packageId);
        eslClient.changePackageStatusToDraft(packageId);
        retrievedPackage = eslClient.getPackage(packageId);
        eslClient.getPackageService().trash(packageId);
        trashedPackage = eslClient.getPackage(packageId);
        eslClient.getPackageService().restore(packageId);
        restoredPackage = eslClient.getPackage(packageId);
    }
}
