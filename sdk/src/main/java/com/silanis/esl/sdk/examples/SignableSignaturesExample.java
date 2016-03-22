package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Signature;

import java.util.List;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 4/1/15.
 */
public class SignableSignaturesExample extends SDKSample {

    private DocumentPackage sentPackage;

    private String signer1Id = "signer1Id";
    private String signer2Id = "signer2Id";
    private String documentId = "documentId";

    public List<Signature> signer1SignableSignatures, signer2SignableSignatures;

    public static void main( String... args ) {
        new SignableSignaturesExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
            .describedAs("This is a package created using the e-SignLive SDK")
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
                                                         .atPosition(300, 100))
                                  .withSignature(signatureFor(email2)
                                                         .onPage(0)
                                                         .atPosition(500, 100)))
            .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        sentPackage = eslClient.getPackage( packageId );
        signer1SignableSignatures = eslClient.getApprovalService().getAllSignableSignatures(sentPackage, documentId, signer1Id);
        signer2SignableSignatures = eslClient.getApprovalService().getAllSignableSignatures(sentPackage, documentId, signer2Id);
    }
}
