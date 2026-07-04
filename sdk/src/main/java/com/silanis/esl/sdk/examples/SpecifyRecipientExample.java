package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PlaceholderSigner;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newPlaceholderSigner;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example class demonstrating a transaction with three role types:
 * a regular external signer, a PLACEHOLDER role (contains an empty signer),
 * and a specifier signer (specifier = true).
 */
public class SpecifyRecipientExample extends SDKSample {

    public static final String DOCUMENT_NAME = "Specify Recipient Document";
    public static final String DOCUMENT_ID = "doc1";

    public static final String REGULAR_SIGNER_FIRST = "John";
    public static final String REGULAR_SIGNER_LAST  = "Smith";
    public static final String REGULAR_SIGNER_ID    = "regular-signer";

    public static final String PLACEHOLDER_ID = "placeholder-signer-id";

    public static final String SPECIFIER_FIRST = "Jane";
    public static final String SPECIFIER_LAST  = "Doe";
    public static final String SPECIFIER_ID    = "specifier-signer";

    public static void main(String... args) {
        new SpecifyRecipientExample().run();
    }

    @Override
    public void execute() {
        PlaceholderSigner placeholder = new PlaceholderSigner(PLACEHOLDER_ID);

        DocumentPackage pkg = newPackageNamed(getPackageName())
                .describedAs("This transaction demonstrates the specify recipient, created using the OneSpan Sign SDK")
                // Role 1: regular external signer
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId(REGULAR_SIGNER_ID)
                        .withFirstName(REGULAR_SIGNER_FIRST)
                        .withLastName(REGULAR_SIGNER_LAST))
                // Role 2: PLACEHOLDER role type — role contains a signer but signer fields are empty
                .withSigner(newPlaceholderSigner(placeholder))
                // Role 3: specifier — regular signer with specifier flag set to true
                .withSigner(newSignerWithEmail(email2)
                        .withCustomId(SPECIFIER_ID)
                        .withFirstName(SPECIFIER_FIRST)
                        .withLastName(SPECIFIER_LAST)
                        .withSpecifier(true))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                        .withId(DOCUMENT_ID)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100))
                        .withSignature(signatureFor(placeholder)
                                .onPage(0)
                                .atPosition(100, 200))
                        .withSignature(signatureFor(email2)
                                .onPage(0)
                                .atPosition(100, 300)))
                .build();

        packageId = eslClient.createPackageOneStep(pkg);
        eslClient.sendPackage(packageId);
        retrievedPackage = eslClient.getPackage(packageId);
    }
}
