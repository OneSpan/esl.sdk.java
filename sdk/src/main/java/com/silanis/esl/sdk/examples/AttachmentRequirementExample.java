package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import com.silanis.esl.sdk.io.Files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.AttachmentRequirementBuilder.*;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;

public class AttachmentRequirementExample extends SDKSample {

    private InputStream documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");

    private String email1;
    private String email2;
    public Signer signer1;
    private String attachment1Id;
    private DocumentPackage retrievedPackage;

    public static final String name1 = "Driver's license";
    public static final String description1 = "Please upload a scanned copy of your driver's license.";
    public static final String name2 = "Medicare card";
    public static final String description2 = "Optional attachment.";
    public static final String name3 = "Third Attachment";
    public static final String description3 = "Third description";
    public static final String signer1Id = "signer1Id";
    public static final String rejectionComment = "Reject: uploaded wrong attachment.";

    public static void main(String... args) {
        new AttachmentRequirementExample(Props.get()).run();
    }

    public AttachmentRequirementExample(Properties properties) {
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"),
                properties.getProperty("1.email"),
                properties.getProperty("2.email"));
    }

    public AttachmentRequirementExample(String apiKey, String apiUrl, String email1, String email2) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        this.email2 = email2;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public DocumentPackage getRetrievedPackage() {
        return retrievedPackage;
    }

    @Override
    public void execute() {

        // Signer1 with 1 attachment requirement
        signer1 = SignerBuilder.newSignerWithEmail(email1)
                .withFirstName("John")
                .withLastName("Smith")
                .withCustomId(signer1Id)
                .withAttachmentRequirement(newAttachmentRequirementWithName(name1)
                        .withDescription(description1)
                        .isRequiredAttachment()
                        .build())
                .build();

        // Signer2 with 2 attachment requirements
        Signer signer2 = SignerBuilder.newSignerWithEmail(email2)
                .withFirstName("Patty")
                .withLastName("Galant")
                .withAttachmentRequirement(newAttachmentRequirementWithName(name2)
                        .withDescription(description2)
                        .build())
                .withAttachmentRequirement(newAttachmentRequirementWithName(name3)
                    .withDescription(description3)
                    .isRequiredAttachment()
                    .build())
                .build();

        DocumentPackage superDuperPackage = newPackageNamed("AttachmentRequirementExample: " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .withSigner(signer1)
                .withSigner(signer2)
                .withDocument(DocumentBuilder.newDocumentWithName("test document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(SignatureBuilder.signatureFor(email1)
                                .build())
                        .build())
                .build();

        packageId = eslClient.createAndSendPackage(superDuperPackage);

        retrievedPackage = eslClient.getPackage(packageId);

        attachment1Id = retrievedPackage.getSigner(email1).getAttachmentRequirement().get(name1).getId();
        signer1 = retrievedPackage.getSigner(email1);

        // Signer1 uploads required attachment
        // Sender can accept/reject the uploaded attachment

    }

    // Sender rejects Signer1's uploaded attachment
    public void rejectAttachment() {
        eslClient.getAttachmentRequirementService().rejectAttachment(packageId, signer1, name1, rejectionComment);
        retrievedPackage = eslClient.getPackage(packageId);
    }

    // Sender accepts Signer1's uploaded attachment
    public void acceptAttachment() {
        eslClient.getAttachmentRequirementService().acceptAttachment(packageId, signer1, name1);
        retrievedPackage = eslClient.getPackage(packageId);
    }

    // Sender downloads Signer1's attachment
    public byte[] downloadAttachment() {
        return eslClient.getAttachmentRequirementService().downloadAttachment(packageId, attachment1Id);
    }
}
