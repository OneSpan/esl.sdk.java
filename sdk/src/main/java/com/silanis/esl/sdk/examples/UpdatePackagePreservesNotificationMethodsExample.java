package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.PackageBuilder;

import static com.silanis.esl.sdk.builder.SignerBuilder.NotificationMethodsBuilder.newNotificationMethods;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by Yzhang on 7/2/25.
 */
public class UpdatePackagePreservesNotificationMethodsExample extends SDKSample {
    public static final String OLD_PACKAGE_NAME = "Old Package Name";

    public static final boolean OLD_NOTARIZED = false;

    public static final String NEW_PACKAGE_NAME = "new package name";

    public static final boolean NEW_NOTARIZED = true;

    public DocumentPackage packageToCreate, createdPackage, packageToUpdate, updatedPackage;

    public static void main( String... args ) {
        new UpdatePackageExample().run();
    }

    public void execute() {

        packageToCreate = PackageBuilder.newPackageNamed(OLD_PACKAGE_NAME)
                .describedAs("This example is created to demonstrate Notification methods")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John1")
                        .withLastName("Smith1")
                        .withNotificationMethods(newNotificationMethods()
                                .withPrimaryMethods(NotificationMethod.EMAIL)))
                .build();

        packageId = eslClient.createPackage(packageToCreate);

        createdPackage = eslClient.getPackage( packageId );

        packageToUpdate = PackageBuilder.newPackageNamed(NEW_PACKAGE_NAME)
                .build();

        eslClient.updatePackage(packageId, packageToUpdate);

        updatedPackage = eslClient.getPackage( packageId );
    }

}