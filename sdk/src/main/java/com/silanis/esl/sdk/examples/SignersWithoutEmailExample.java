package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Placeholder;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Creates a package with two signers without email and reads it back.
 * <p>
 * Requires an account with the optionalSignerEmail account feature ON.
 * Give each signer without email a custom id and assign its signatures by that id
 * ({@code signatureFor(new Placeholder(id))}), not by email.
 */
public class SignersWithoutEmailExample extends SDKSample {

    public static final String FIRST_SIGNER_ID = "firstSignerWithoutEmail";
    public static final String SECOND_SIGNER_ID = "secondSignerWithoutEmail";
    public static final String DOCUMENT_NAME = "First Document";

    public static void main(String... args) {
        new SignersWithoutEmailExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This example creates a package with two signers without email")
                .withSigner(newSignerWithEmail("")
                        .withCustomId(FIRST_SIGNER_ID)
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withSigner(newSignerWithEmail("")
                        .withCustomId(SECOND_SIGNER_ID)
                        .withFirstName("Jane")
                        .withLastName("Doe"))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(new Placeholder(FIRST_SIGNER_ID))
                                .onPage(0)
                                .atPosition(100, 100))
                        .withSignature(signatureFor(new Placeholder(SECOND_SIGNER_ID))
                                .onPage(0)
                                .atPosition(100, 300)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        retrievedPackage = eslClient.getPackage(packageId);
    }
}
