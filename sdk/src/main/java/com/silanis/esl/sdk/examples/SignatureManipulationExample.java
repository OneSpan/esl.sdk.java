package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.SignatureId;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Created by chi-wing on 6/19/14.
 */
public class SignatureManipulationExample extends SDKSample {
    public final String email1;
    public final String email2;
    public final String email3;

    public final String documentId = "documentId";
    private final SignatureId signatureId1 = new SignatureId("signatureId1");
    private final SignatureId signatureId2 = new SignatureId("signatureId2");
    private final SignatureId signatureId3 = new SignatureId("signatureId3");

    public Signature signature1;
    public Signature signature2;
    public Signature signature3;
    public Signature updatedSignature;

    private InputStream documentInputStream;

    public Collection<Signature> addedSignatures;
    public Collection<Signature> deletedSignatures;
    public Collection<Signature> updatedSignatures;

    public DocumentPackage createdPackage;

    public SignatureManipulationExample(Properties properties) {
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"),
                properties.getProperty("1.email"),
                properties.getProperty("2.email"),
                properties.getProperty("3.email"));
    }

    public SignatureManipulationExample(String apiKey, String apiUrl, String email1, String email2, String email3) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
    }

    @Override
    void execute() {
        DocumentPackage superDuperPackage = newPackageNamed("SignatureManipulationExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
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
                .withDocument(newDocumentWithName("SignatureManipulationExample")
                                .fromStream(documentInputStream, DocumentType.PDF)
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

        updatedSignature = signatureFor(email1)
                .onPage(0)
                .atPosition(100, 300)
                .withId(signatureId3)
                .build();

        // Adding the signatures
        createdPackage = eslClient.getPackageService().getPackage(packageId);
        eslClient.getApprovalService().addSignature(createdPackage, documentId, signature1);
        eslClient.getApprovalService().addSignature(createdPackage, documentId, signature2);
        eslClient.getApprovalService().addSignature(createdPackage, documentId, signature3);
        addedSignatures = eslClient.getPackage(packageId).getDocument("SignatureManipulationExample").getSignatures();

        // Deleting signature for signer 1
        eslClient.getApprovalService().deleteSignature(packageId, documentId, signatureId1);
        deletedSignatures = eslClient.getPackage(packageId).getDocument("SignatureManipulationExample").getSignatures();

        // Updating the information for the third signature
        createdPackage = eslClient.getPackageService().getPackage(packageId);
        eslClient.getApprovalService().modifySignature(createdPackage, documentId, updatedSignature);
        updatedSignatures = eslClient.getPackage(packageId).getDocument("SignatureManipulationExample").getSignatures();

    }
}
