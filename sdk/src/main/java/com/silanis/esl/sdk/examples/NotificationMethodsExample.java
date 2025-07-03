package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.NotificationMethod;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.NotificationMethodsBuilder.newNotificationMethods;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class NotificationMethodsExample extends SDKSample{
    public static void main(String... args) {
        new NotificationMethodsExample().run();
    }


    public void execute() {
        DocumentPackage package1 = newPackageNamed(getPackageName())
                .describedAs("This example is created to demonstrate Notification methods")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("David")
                        .withLastName("Miller"))
                .withSigner(newSignerWithEmail(email2)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withNotificationMethods(newNotificationMethods()
                                .withPrimaryMethods(NotificationMethod.EMAIL)))
                .withSigner(newSignerWithEmail(email3)
                        .withFirstName("Jane")
                        .withLastName("Cooked")
                        .withNotificationMethods(newNotificationMethods()
                                .withPrimaryMethods(NotificationMethod.EMAIL, NotificationMethod.SMS)
                                .withPhoneNumber("+1 204-234-5678")))
                .build();

        packageId = eslClient.createPackage(package1);
        retrievedPackage = eslClient.getPackage(packageId);
    }

}
