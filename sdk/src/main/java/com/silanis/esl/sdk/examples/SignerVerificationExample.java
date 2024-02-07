package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.SignerVerification;
import com.silanis.esl.sdk.builder.SignerVerificationBuilder;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 19/04/17.
 */
public class SignerVerificationExample extends SDKSample {

    private static final String CREATE_VERIFICATION_TYPE_ID = "DIGIPASS";
    private static final String CREATE_VERIFICATION_PAYLOAD  = "bSxW5aAFG2yTW5NaqaAF";
    private static final String UPDATE_VERIFICATION_TYPE_ID = "personalCertificateSigning";
    private static final String UPDATE_VERIFICATION_PAYLOAD  = "";

    public SignerVerification signerVerificationToBeCreated;
    public SignerVerification signerVerificationToBeUpdated;
    public SignerVerification retrievedSignerVerificationAfterCreate;
    public SignerVerification retrievedSignerVerificationAfterUpdate;
    public SignerVerification retrievedSignerVerificationAfterDelete;


    public static void main( String... args ) {
        new SignerVerificationExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John1")
                        .withLastName("Smith1"))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        retrievedPackage = eslClient.getPackage(packageId);

        Signer signer = retrievedPackage.getSigner(email1);

        // Create
        signerVerificationToBeCreated = SignerVerificationBuilder
                .newSignerVerification(CREATE_VERIFICATION_TYPE_ID)
                .withPayload(CREATE_VERIFICATION_PAYLOAD)
                .build();

        eslClient.createSignerVerification(packageId, signer.getId(), signerVerificationToBeCreated);

        retrievedSignerVerificationAfterCreate = eslClient.getSignerVerification(packageId, signer.getId());

        // Update
        signerVerificationToBeUpdated = SignerVerificationBuilder
                .newSignerVerification(UPDATE_VERIFICATION_TYPE_ID)
                .withPayload(UPDATE_VERIFICATION_PAYLOAD)
                .build();

        eslClient.updateSignerVerification(packageId, signer.getId(), signerVerificationToBeUpdated);

        retrievedSignerVerificationAfterUpdate = eslClient.getSignerVerification(packageId, signer.getId());

        // Delete
        eslClient.deleteSignerVerification(packageId, signer.getId());

        retrievedSignerVerificationAfterDelete = eslClient.getSignerVerification(packageId, signer.getId());
    }
}
