package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class DeliverSignedDocumentsByEmailExample extends SDKSample {

    public static void main( String... args ) {
        new DeliverSignedDocumentsByEmailExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withSigner(newSignerWithEmail("davelawson@hotmail.com")
                        .withFirstName("John")
                        .withLastName("Smith")
                        .deliverSignedDocumentsByEmail() )
                .withDocument(newDocumentWithName("First Document")
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature(signatureFor("davelawson@hotmail.com")
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );

        eslClient.sendPackage( packageId );
    }
}
