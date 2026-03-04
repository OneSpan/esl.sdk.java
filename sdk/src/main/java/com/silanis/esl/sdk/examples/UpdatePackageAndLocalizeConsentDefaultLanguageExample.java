package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

import java.util.Locale;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.PackageUpdateWorkflowResult;

public class UpdatePackageAndLocalizeConsentDefaultLanguageExample extends SDKSample {

    private PackageUpdateWorkflowResult result;

    public PackageUpdateWorkflowResult getResult() {
        return result;
    }

    public void setResult(PackageUpdateWorkflowResult result) {
        this.result = result;
    }

    public static void main( String... args ) {
        new UpdatePackageAndLocalizeConsentDefaultLanguageExample().run();
    }

    public void execute() {

        // 1. Create a package
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName()).describedAs("This is a package created using OneSpan Sign SDK")
                .expiresAt(now().plusMonths(1)
                        .toDate())
                // .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1).withCustomId("Client1")
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);

        DocumentPackage sdkPackage = eslClient.getPackageService().getPackage(packageId);
        sdkPackage.setLanguage(Locale.FRANCE);

        // 2. Update package and localize consent automatically
        setResult(eslClient.getPackageService().updatePackageAndLocalizeConsent(packageId, sdkPackage));
    }
}
