package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.NotificationMethodsBuilder.newNotificationMethods;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentPackageRequestExtension;
import com.silanis.esl.sdk.NotificationMethod;

/**
 * Creates and retrieves a package which might have alerts associated with it.
 * The alerts are added by the system and must not be added through the SDK.
 * <p>
 * The alerts are for now only generated for the cases when SMS notification is enabled and a number of SMS sent for a particular package exceeds the SMS limit defined in the account settings.
 * In this case the alert notifies the user that no more SMS will be sent for this package. Though the system will keep sending EMAILs for such packages.
 */
public class GetPackageWithAlertsExample extends SDKSample {
    public static void main( String... args ) {
        new GetPackageWithAlertsExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage aPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
                .expiresAt(now().plusDays(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withCompany("Acme Inc.")
                        .withNotificationMethods(newNotificationMethods()
                                .withPrimaryMethods(NotificationMethod.EMAIL, NotificationMethod.SMS)
                                .withPhoneNumber("+12042345678")))
                .build();

        packageId = eslClient.createPackage(aPackage);
        retrievedPackage = eslClient.getPackageWithExtensions(packageId, DocumentPackageRequestExtension.ALERTS);
    }
}
