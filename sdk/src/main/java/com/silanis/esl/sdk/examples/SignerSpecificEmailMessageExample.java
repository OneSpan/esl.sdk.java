package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
/**
 * Example of how to customize the e-mail message received by a signer when a DocumentPackage is sent for signing.
 */
public class SignerSpecificEmailMessageExample extends SDKSample {

    public static final String EMAIL_MESSAGE =  "Hi John, could you sign this ASAP please?";

    public static void main( String... args ) {
        new SignerSpecificEmailMessageExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withEmailMessage(EMAIL_MESSAGE))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(500, 100)))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage(packageId);
        retrievedPackage = eslClient.getPackage( packageId );
    }
}