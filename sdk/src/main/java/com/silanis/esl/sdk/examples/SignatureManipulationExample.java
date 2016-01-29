package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.SignatureId;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Created by chi-wing on 6/19/14.
 */
public class SignatureManipulationExample extends SDKSample {

    public static final String DOCUMENT_NAME = "SignatureManipulationExample";

    public final String documentId = "documentId";
    private final SignatureId signatureId1 = new SignatureId("signatureId1");
    private final SignatureId signatureId2 = new SignatureId("signatureId2");
    private final SignatureId signatureId3 = new SignatureId("signatureId3");

    public Signature signature1;
    public Signature signature2;
    public Signature signature3;
    public Signature modifiedSignature;
    public Signature updatedSignature1;
    public Signature updatedSignature2;

    public Collection<Signature> addedSignatures;
    public Collection<Signature> deletedSignatures;
    public Collection<Signature> modifiedSignatures;
    public Collection<Signature> updatedSignatures;

    public DocumentPackage createdPackage;

    public static void main( String... args ) {
        new SignatureManipulationExample().run();
    }

    @Override
    void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the e-SignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("signer1")
                        .withFirstName("firstName1")
                        .withLastName("lastName1"))
                .withSigner(newSignerWithEmail(email2)
                        .withCustomId("signer2")
                        .withFirstName("firstName2")
                        .withLastName("lastName2"))
                .withSigner(newSignerWithEmail(email3)
                        .withCustomId("signer3")
                        .withFirstName("firstName3")
                        .withLastName("lastName3"))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                                .fromStream(documentInputStream1, DocumentType.PDF)
                                .withId(documentId)
                )
                .build();

        packageId = eslClient.createPackage(superDuperPackage);

        signature1 = signatureFor(email1)
                .onPage(0)
                .atPosition(100, 100)
                .withId(signatureId1)
                .build();

        signature2 = signatureFor(email2)
                .onPage(0)
                .atPosition(100, 200)
                .withId(signatureId2)
                .build();

        signature3 = signatureFor(email3)
                .onPage(0)
                .atPosition(100, 300)
                .withId(signatureId3)
                .build();

        modifiedSignature = signatureFor(email1)
                .onPage(0)
                .atPosition(100, 300)
                .withId(signatureId3)
                .build();

        // Adding the signatures
        createdPackage = eslClient.getPackageService().getPackage(packageId);
        eslClient.getApprovalService().addSignature(createdPackage, documentId, signature1);
        eslClient.getApprovalService().addSignature(createdPackage, documentId, signature2);
        eslClient.getApprovalService().addSignature(createdPackage, documentId, signature3);
        addedSignatures = eslClient.getPackage(packageId).getDocument(DOCUMENT_NAME).getSignatures();

        // Deleting signature for signer 1
        eslClient.getApprovalService().deleteSignature(packageId, documentId, signatureId1);
        deletedSignatures = eslClient.getPackage(packageId).getDocument(DOCUMENT_NAME).getSignatures();

        // Updating the information for the third signature
        createdPackage = eslClient.getPackageService().getPackage(packageId);
        eslClient.getApprovalService().modifySignature(createdPackage, documentId, modifiedSignature);
        modifiedSignatures = eslClient.getPackage(packageId).getDocument(DOCUMENT_NAME).getSignatures();

        // Update all the signatures in the document with the provided list of signatures
        updatedSignature1 = signatureFor(email2)
                .onPage(0)
                .atPosition(300, 300)
                .withId(signatureId2)
                .withField(FieldBuilder
                        .signerName()
                        .atPosition(100, 100)
                        .onPage(0))
                .build();
        updatedSignature2 = signatureFor(email3)
                .onPage(0)
                .atPosition(300, 500)
                .withId(signatureId3)
                .build();

        List<Signature> signatureList = new ArrayList<Signature>();
        signatureList.add(updatedSignature1);
        signatureList.add(updatedSignature2);
        eslClient.getApprovalService().updateSignatures(createdPackage, documentId, signatureList);
        updatedSignatures = eslClient.getPackage(packageId).getDocument(DOCUMENT_NAME).getSignatures();
    }
}
