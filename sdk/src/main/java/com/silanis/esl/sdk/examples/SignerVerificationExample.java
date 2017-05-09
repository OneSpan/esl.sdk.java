package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Signer;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 19/04/17.
 */
public class SignerVerificationExample extends SDKSample {

    public DocumentPackage createdPackage, updatedPackage;
    public String firstVerificationType, deletedVerificationType;
    public static final String CERTIFICATE =  "personalCertificateSigning";

    public static void main( String... args ) {
        new SignerVerificationExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the eSignLive SDK")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John1")
                        .withLastName("Smith1")
                        .withSignerVerification(CERTIFICATE))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        createdPackage = eslClient.getPackage( packageId );

        Signer signer = createdPackage.getSigner(email1);
        firstVerificationType = signer.getVerificationType();

        signer.setVerificationType("");
        eslClient.updatePackage(packageId, createdPackage);
        updatedPackage = eslClient.getPackage( packageId );

        signer = updatedPackage.getSigner(email1);
        deletedVerificationType = signer.getVerificationType();
    }
}
