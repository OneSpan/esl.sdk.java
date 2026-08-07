package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example class demonstrating a transaction with a carbon copy recipient.
 * <p>
 * A carbon copy recipient receives a copy of the completed documents but never participates in
 * the signing ceremony. They are excluded from the signing order and are only notified once the
 * transaction is complete, so no signature is placed for them.
 * <p>
 * The carbonCopyRecipient feature must be enabled on the account for this example to run.
 */
public class CarbonCopyRecipientExample extends SDKSample {

    public static final String DOCUMENT_NAME = "Carbon Copy Recipient Document";
    public static final String DOCUMENT_ID = "doc1";

    public static final String SIGNER_FIRST = "John";
    public static final String SIGNER_LAST = "Smith";
    public static final String SIGNER_ID = "regular-signer";

    public static final String CARBON_COPY_FIRST = "Jane";
    public static final String CARBON_COPY_LAST = "Doe";
    public static final String CARBON_COPY_ID = "carbon-copy-recipient";

    public static void main(String... args) {
        new CarbonCopyRecipientExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage pkg = newPackageNamed(getPackageName())
                .describedAs("This transaction demonstrates a carbon copy recipient, created using the OneSpan Sign SDK")
                // Role 1: regular external signer
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId(SIGNER_ID)
                        .withFirstName(SIGNER_FIRST)
                        .withLastName(SIGNER_LAST))
                // Role 2: carbon copy recipient — receives the completed documents, never signs
                .withSigner(newSignerWithEmail(email2)
                        .withCustomId(CARBON_COPY_ID)
                        .withFirstName(CARBON_COPY_FIRST)
                        .withLastName(CARBON_COPY_LAST)
                        .asCarbonCopyRecipient())
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                        .withId(DOCUMENT_ID)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        // Only the signer gets a signature; a carbon copy recipient cannot have
                        // signatures or fields.
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackageOneStep(pkg);
        eslClient.sendPackage(packageId);
        retrievedPackage = eslClient.getPackage(packageId);
    }
}
