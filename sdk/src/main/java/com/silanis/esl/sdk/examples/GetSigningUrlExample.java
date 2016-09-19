package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;

import java.util.UUID;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 1/23/15.
 */
public class GetSigningUrlExample extends SDKSample {

    public String signingUrlForSigner1;
    public String signingUrlForSigner2;

    public static void main( String... args ) {
        new GetSigningUrlExample().run();
    }

    public void execute() {
        String signer1Id = UUID.randomUUID().toString();
        String signer2Id = UUID.randomUUID().toString();

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the eSignLive SDK")
                .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings().withInPerson())
                .withSigner(newSignerWithEmail(email1)
                                    .withFirstName("John1")
                                    .withLastName("Smith1")
                                    .withCustomId(signer1Id))
                .withSigner(newSignerWithEmail(email2)
                                    .withFirstName("John2")
                                    .withLastName("Smith2")
                                    .withCustomId(signer2Id))
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withSignature(signatureFor(email1)
                                                             .onPage(0)
                                                             .atPosition(100, 100))
                                      .withSignature(signatureFor(email2)
                                                             .onPage(0)
                                                             .atPosition(100, 200)))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        signingUrlForSigner1 = eslClient.getPackageService().getSigningUrl(packageId, signer1Id);
        signingUrlForSigner2 = eslClient.getPackageService().getSigningUrl(packageId, signer2Id);
    }
}
