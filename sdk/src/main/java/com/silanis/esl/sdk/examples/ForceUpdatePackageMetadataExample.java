package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentPackageAttributes;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * End-to-end check for {@code forceUpdatePackageMetadata} (PB-130297).
 * <p>
 * Prerequisite: the account behind {@code api.key} must have the {@code manipulateMetadata}
 * feature enabled. Without it the force call fails with a validation error
 * ({@code manipulateMetadata.featureDisabled}).
 * <p>
 * The transaction is created and SENT so it is no longer editable, then the example shows that
 * {@code forceUpdatePackageMetadata} updates the transaction's own custom metadata (its attributes)
 * regardless of the transaction's status.
 * <p>
 * Run: fill {@code signers.properties} (webpage.url, api.key, 1.email) on the classpath, then run
 * this class's {@code main}.
 */
public class ForceUpdatePackageMetadataExample extends SDKSample {

    public static void main(String... args) {
        new ForceUpdatePackageMetadataExample().run();
    }

    @Override
    public void execute() {
        // 1. Create a package with one signer, upload a signable document, then SEND it so it is no
        //    longer editable.
        DocumentPackage builtPackage = newPackageNamed(getPackageName())
                .describedAs("forceUpdatePackageMetadata smoke test")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith"))
                .build();

        packageId = eslClient.createPackage(builtPackage);

        Document document = newDocumentWithName("Contract")
                .fromStream(documentInputStream1, DocumentType.PDF)
                .withSignature(signatureFor(email1)
                        .onPage(0)
                        .atPosition(100, 100))
                .build();
        eslClient.uploadDocument(document, packageId);

        eslClient.sendPackage(packageId);
        System.out.println("Sent transaction " + packageId.getId() + " (status is now SENT, no longer editable).");

        // 2. Reload the sent transaction and force-update its custom metadata (transaction attributes).
        DocumentPackage sent = eslClient.getPackage(packageId);

        DocumentPackageAttributes attributes = new DocumentPackageAttributes();
        attributes.append("customerId", "12345");
        attributes.append("region", "EMEA");
        sent.setAttributes(attributes);

        eslClient.getPackageService().forceUpdatePackageMetadata(sent);
        System.out.println("forceUpdatePackageMetadata succeeded on a SENT transaction.");

        // 3. Read the metadata back and confirm the force-update was applied.
        DocumentPackage reloaded = eslClient.getPackage(packageId);
        System.out.println("Transaction metadata after force-update: " + reloaded.getAttributes().toMap());
    }
}
