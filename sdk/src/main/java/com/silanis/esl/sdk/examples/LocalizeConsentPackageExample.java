package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

import com.silanis.esl.sdk.ConsentLocalizationData;
import com.silanis.esl.sdk.ConsentLocalizationPayload;
import com.silanis.esl.sdk.DocumentPackage;

public class LocalizeConsentPackageExample extends SDKSample {

    private ConsentLocalizationData consentLocalizationData;

    public ConsentLocalizationData getConsentLocalizationResponse() {
        return consentLocalizationData;
    }

    public static void main( String... args ) {
        new LocalizeConsentPackageExample().run();
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

        ConsentLocalizationPayload localizationPayload = new ConsentLocalizationPayload.Builder()
            .withLanguage("fr")
            .build();
        // 2. Localize consent document
        consentLocalizationData = eslClient.getPackageService().localizeDefaultConsentDocument(localizationPayload, packageId);
    }
}
