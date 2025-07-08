package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.PackageBuilder;

import static com.silanis.esl.sdk.builder.SignerBuilder.NotificationMethodsBuilder.newNotificationMethods;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by Yzhang on 7/2/25.
 */
public class UpdatePackagePreservesNotificationMethodsExample extends SDKSample {
    public static final String PACKAGE_NAME = "This example is to demonstrate that package update doesn't affect signer's NM";

    public DocumentPackage createdPackage, updatedPackage;

    public static void main( String... args ) {
        new UpdatePackageExample().run();
    }

    public void execute() {

        DocumentPackage packageToCreate = PackageBuilder.newPackageNamed(PACKAGE_NAME)
                .describedAs("This example is created to demonstrate Notification methods")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John1")
                        .withLastName("Smith1")
                        .withNotificationMethods(newNotificationMethods()
                                .withPrimaryMethods(NotificationMethod.EMAIL, NotificationMethod.SMS)
                                .withPhoneNumber("+12042345678")))
                .build();

        packageId = eslClient.createPackage(packageToCreate);
        createdPackage = eslClient.getPackage(packageId);

        DocumentPackage packageToUpdate = eslClient.getPackage(packageId);
        Signer signer1 = packageToUpdate.getSigner(email1);
        signer1.setNotificationPrimaryMethods(NotificationMethod.EMAIL);
        signer1.setNotificationPhoneNumber(null);
        eslClient.updatePackage(packageId, packageToUpdate);

        updatedPackage = eslClient.getPackage(packageId);
    }

}