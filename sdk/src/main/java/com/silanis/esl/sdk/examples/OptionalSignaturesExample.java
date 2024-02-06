package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Signature;

import java.util.List;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class OptionalSignaturesExample extends SDKSample {

    private String signer1Id = "signer1Id";
    private String signer2Id = "signer2Id";
    private String documentId = "documentId";

    public List<Signature> signer1SignableSignatures, signer2SignableSignatures;

    public static void main( String... args ) {
        new OptionalSignaturesExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
            .describedAs("This is a package created using OneSpan Sign SDK")
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
                                  .withId(documentId)
                                  .withSignature(signatureFor(email1)
                                                         .onPage(0)
                                                         .atPosition(100, 100))
                                  .withSignature(signatureFor(email1)
                                                         .onPage(0)
                                                         .atPosition(150, 100))
                                  .withSignature(signatureFor(email2)
                                                         .onPage(0)
                                                         .atPosition(200, 100))
                                  .withSignature(signatureFor(email2)
                                                         .onPage(0)
                                                         .atPosition(250, 100)
                                                         .makeOptional()))
            .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        retrievedPackage = eslClient.getPackage( packageId );
        signer1SignableSignatures = eslClient.getApprovalService().getAllSignableSignatures(retrievedPackage, documentId, signer1Id);
        signer2SignableSignatures = eslClient.getApprovalService().getAllSignableSignatures(retrievedPackage, documentId, signer2Id);
    }
}
