package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;

/**
 * Created by schoi on 1/27/15.
 */
public class SendSmsToSignerExample extends SDKSample {

    public static final String SIGNER1_FIRST = "John";
    public static final String SIGNER1_LAST = "Smith";
    public static final String SIGNER2_FIRST = "Patty";
    public static final String SIGNER2_LAST = "Galant";

    public static void main(String... args) {
        new SendSmsToSignerExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the eSignLive SDK")
                .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings().withInPerson())
                .withSigner(SignerBuilder.newSignerWithEmail(email1)
                        .withFirstName(SIGNER1_FIRST)
                        .withLastName(SIGNER1_LAST))
                .withSigner(SignerBuilder.newSignerWithEmail(email2)
                        .withFirstName(SIGNER2_FIRST)
                        .withLastName(SIGNER2_LAST))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100))
                        .withSignature(signatureFor(email2)
                                .onPage(0)
                                .atPosition(400, 100)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);
        retrievedPackage = eslClient.getPackage(packageId);

        eslClient.getPackageService().sendSmsToSigner(packageId, retrievedPackage.getSigner(email1));
        eslClient.getPackageService().sendSmsToSigner(packageId, retrievedPackage.getSigner(email2));
        retrievedPackage = eslClient.getPackage(packageId);
    }
}
