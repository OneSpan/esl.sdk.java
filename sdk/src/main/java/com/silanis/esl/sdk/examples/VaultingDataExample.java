package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.DocumentPackageAttributesBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * VaultingData API must work with elend or eVM workflow,
 * and need to config proper settings in the backoffice
 */
public class VaultingDataExample extends SDKSample {

    public static final String PACKAGE_NAME = "packageName";
    public static final String VAULTING_DATA_KEY = "vaulting_data";
    public static final String VAULTING_DATA_OLD_VALUE = "base64EncodedOldVaultingData";
    public static final String VAULTING_DATA_NEW_VALUE = "base64EncodedNewVaultingData";

    public DocumentPackage packageToCreate, createdPackage, packageToUpdate, updatedPackage;

    public static void main( String... args ) {
        new VaultingDataExample().run();
    }

    public void execute() {
        DocumentPackageAttributesBuilder documentPackageAttributesBuilder = DocumentPackageAttributesBuilder
                .newDocumentPackageAttributes();

        packageToCreate = PackageBuilder.newPackageNamed(PACKAGE_NAME)
                .withSigner(newSignerWithEmail(email1)
                                    .withFirstName("John1")
                                    .withLastName("Smith1"))
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withSignature(signatureFor(email1)
                                                             .onPage(0)
                                                             .atPosition(100, 100)))
                .withAttributes(documentPackageAttributesBuilder.withAttribute(VAULTING_DATA_KEY, VAULTING_DATA_OLD_VALUE))
                .build();

        packageId = eslClient.createPackage(packageToCreate);

        createdPackage = eslClient.getPackage(packageId);


        packageToUpdate = PackageBuilder.newPackageNamed(PACKAGE_NAME)
                .withAttributes(documentPackageAttributesBuilder.withAttribute(VAULTING_DATA_KEY, VAULTING_DATA_NEW_VALUE))
                .build();

        eslClient.updateVaultingData(packageId, packageToUpdate);
        eslClient.getVaultingData(packageId);
        eslClient.revault(packageId);

        updatedPackage = eslClient.getPackage( packageId );

    }

}
