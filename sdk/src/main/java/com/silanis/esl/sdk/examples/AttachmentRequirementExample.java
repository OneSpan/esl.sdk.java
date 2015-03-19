package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import com.silanis.esl.sdk.builder.internal.StreamDocumentSource;
import com.silanis.esl.sdk.io.Files;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.AttachmentRequirementBuilder.*;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;

public class AttachmentRequirementExample extends SDKSample {

    private InputStream documentInputStream, attachmentInputStream;

    public String email1;
    public String email2;
    public Signer signer1;
    private String attachment1Id;

    public static final String NAME1 = "Driver's license";
    public static final String DESCRIPTION1 = "Please upload a scanned copy of your driver's license.";
    public static final String NAME2 = "Medicare card";
    public static final String DESCRIPTION2 = "Optional attachment.";
    public static final String NAME3 = "Third Attachment";
    public static final String DESCRIPTION3 = "Third description";
    public static final String SIGNER1_ID = "signer1Id";
    public static final String REJECTION_COMMENT = "Reject: uploaded wrong attachment.";

    public DocumentPackage retrievedPackageAfterRejection, retrievedPackageAfterAccepting;
    public Map<String, AttachmentRequirement> signer1Attachments, signer2Attachments;
    public AttachmentRequirement signer1Att1, signer2Att1, signer2Att2;
    public RequirementStatus retrievedSigner1Att1RequirementStatus, retrievedSigner2Att1RequirementStatus,
            retrievedSigner2Att2RequirementStatus, retrievedSigner1Att1RequirementStatusAfterRejection,
            retrievedSigner1Att1RequirementStatusAfterAccepting;
    public String retrievedSigner1Att1RequirementSenderCommentAfterRejection,
            retrievedSigner1Att1RequirementSenderCommentAfterAccepting;

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
        this.documentInputStream = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        this.attachmentInputStream = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
    }

    @Override
    public void execute() {

        // Signer1 with 1 attachment requirement
        signer1 = SignerBuilder.newSignerWithEmail(email1)
                .withFirstName("John")
                .withLastName("Smith")
                .withCustomId(SIGNER1_ID)
                .withAttachmentRequirement(newAttachmentRequirementWithName(NAME1)
                        .withDescription(DESCRIPTION1)
                        .isRequiredAttachment()
                        .build())
                .build();

        // Signer2 with 2 attachment requirements
        Signer signer2 = SignerBuilder.newSignerWithEmail(email2)
                .withFirstName("Patty")
                .withLastName("Galant")
                .withAttachmentRequirement(newAttachmentRequirementWithName(NAME2)
                        .withDescription(DESCRIPTION2)
                        .build())
                .withAttachmentRequirement(newAttachmentRequirementWithName(NAME3)
                    .withDescription(DESCRIPTION3)
                    .isRequiredAttachment()
                    .build())
                .build();

        DocumentPackage superDuperPackage = newPackageNamed("AttachmentRequirementExample: " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .withSigner(signer1)
                .withSigner(signer2)
                .withDocument(DocumentBuilder.newDocumentWithName("test document")
                        .fromStream(documentInputStream, DocumentType.PDF)
                        .withSignature(SignatureBuilder.signatureFor(email1)
                                .build())
                        .build())
                .build();

        packageId = eslClient.createAndSendPackage(superDuperPackage);

        retrievedPackage = eslClient.getPackage(packageId);

        attachment1Id = retrievedPackage.getSigner(email1).getAttachmentRequirement().get(NAME1).getId();
        signer1 = retrievedPackage.getSigner(email1);

        signer1Attachments = retrievedPackage.getSigner(email1).getAttachmentRequirement();
        signer2Attachments = retrievedPackage.getSigner(email2).getAttachmentRequirement();

        signer1Att1 = signer1Attachments.get(NAME1);
        signer2Att1 = signer2Attachments.get(NAME2);
        signer2Att2 = signer2Attachments.get(NAME3);

        retrievedSigner1Att1RequirementStatus = signer1Att1.getStatus();
        retrievedSigner2Att1RequirementStatus = signer2Att1.getStatus();
        retrievedSigner2Att2RequirementStatus = signer2Att2.getStatus();

        // upload attachment
        eslClient.uploadAttachment(packageId, signer1Att1.getId(), DocumentType.PDF.normalizeName("Test Attachment"),
                                   new StreamDocumentSource(attachmentInputStream).content(), SIGNER1_ID);

        // Sender rejects Signer1's uploaded attachment
        eslClient.getAttachmentRequirementService().rejectAttachment(packageId, signer1, NAME1, REJECTION_COMMENT);
        retrievedPackageAfterRejection = eslClient.getPackage(packageId);

        retrievedSigner1Att1RequirementStatusAfterRejection = retrievedPackageAfterRejection.getSigner(email1).getAttachmentRequirement().get(NAME1).getStatus();
        retrievedSigner1Att1RequirementSenderCommentAfterRejection = retrievedPackageAfterRejection.getSigner(email1).getAttachmentRequirement().get(NAME1).getSenderComment();

        // Sender accepts Signer1's uploaded attachment
        eslClient.getAttachmentRequirementService().acceptAttachment(packageId, signer1, NAME1);
        retrievedPackageAfterAccepting = eslClient.getPackage(packageId);

        retrievedSigner1Att1RequirementStatusAfterAccepting = retrievedPackageAfterAccepting.getSigner(email1).getAttachmentRequirement().get(NAME1).getStatus();
        retrievedSigner1Att1RequirementSenderCommentAfterAccepting = retrievedPackageAfterAccepting.getSigner(email1).getAttachmentRequirement().get(NAME1).getSenderComment();

        // Download signer1's attachment
        byte[] downloadedAttachment = eslClient.getAttachmentRequirementService().downloadAttachment(packageId, attachment1Id);
        Files.saveTo(downloadedAttachment, "downloadedAttachment.pdf");

        // Download all attachments for the package
        byte[] downloadedAllAttachmentsForPackage = eslClient.getAttachmentRequirementService().downloadAllAttachmentsForPackage(packageId);
        Files.saveTo(downloadedAllAttachmentsForPackage, "downloadedAllAttachmentsForPackage.zip");

        // Download all attachments for the signer in the package
        byte[] downloadedAllAttachmentsForSignerInPackage = eslClient.getAttachmentRequirementService().downloadAllAttachmentsForSignerInPackage(retrievedPackage, signer1);
        Files.saveTo(downloadedAllAttachmentsForSignerInPackage, "downloadedAllAttachmentsForSignerInPackage.zip");
    }
}
