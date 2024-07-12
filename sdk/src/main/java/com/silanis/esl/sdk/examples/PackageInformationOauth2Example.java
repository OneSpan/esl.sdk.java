package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.SupportConfiguration;

public class PackageInformationOauth2Example extends Oauth2SDKSample {

    public SupportConfiguration supportConfiguration;


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
        eslClient.sendPackage( packageId );

        supportConfiguration = eslClient.getPackageService().getConfig(packageId);
    }
}
