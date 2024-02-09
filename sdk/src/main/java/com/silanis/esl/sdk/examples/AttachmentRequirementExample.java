package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AttachmentRequirement;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.RequirementStatus;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import com.silanis.esl.sdk.builder.internal.StreamDocumentSource;
import com.silanis.esl.sdk.io.DownloadedFile;
import com.silanis.esl.sdk.io.Files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;

import static com.silanis.esl.sdk.builder.AttachmentRequirementBuilder.newAttachmentRequirementWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;

public class AttachmentRequirementExample extends SDKSample {

    private InputStream attachmentInputStream1, attachmentInputStream2, attachmentInputStream3, attachmentInputStream4;

    private Signer signer1;
    private String attachment1Id;

    public static final String NAME1 = "Driver's license";
    public static final String DESCRIPTION1 = "Please upload a scanned copy of your driver's license.";
    public static final String NAME2 = "Medicare card";
    public static final String DESCRIPTION2 = "Optional attachment.";
    public static final String NAME3 = "Attachment3";
    public static final String DESCRIPTION3 = "Third description";
    public static final String SIGNER1_ID = "signer1Id";
    public static final String SIGNER2_ID = "signer2Id";
    public static final String REJECTION_COMMENT = "Reject: uploaded wrong attachment.";

    public static final String ATTACHMENT_FILE_NAME1 = "The attachment1 for signer1.pdf";
    public static final String ATTACHMENT_FILE_NAME2 = DocumentType.PDF.normalizeName("The attachment1 for signer2");
    public static final String ATTACHMENT_FILE_NAME3 = "The attachment2 for signer2.pdf";
    public static final String ATTACHMENT_FILE_NAME4 = "The attachment3 for signer2.pdf";
    public static final String DOWNLOADED_ALL_ATTACHMENTS_FOR_PACKAGE_ZIP = "downloadedAllAttachmentsForPackage.zip";
    public static final String DOWNLOADED_ALL_ATTACHMENTS_FOR_SIGNER1_IN_PACKAGE_ZIP = "downloadedAllAttachmentsForSigner1InPackage.zip";
    public static final String DOWNLOADED_ALL_ATTACHMENTS_FOR_SIGNER2_IN_PACKAGE_ZIP = "downloadedAllAttachmentsForSigner2InPackage.zip";

    public DocumentPackage retrievedPackageAfterRejection, retrievedPackageAfterAccepting;
    public List<AttachmentRequirement> signer1Attachments, signer2Attachments;
    public AttachmentRequirement signer1Att1, signer2Att1, signer2Att2;
    public RequirementStatus retrievedSigner1Att1RequirementStatus, retrievedSigner2Att1RequirementStatus,
            retrievedSigner2Att2RequirementStatus, retrievedSigner1Att1RequirementStatusAfterRejection,
            retrievedSigner1Att1RequirementStatusAfterAccepting;

    public String retrievedSigner1Att1RequirementSenderCommentAfterRejection,
            retrievedSigner1Att1RequirementSenderCommentAfterAccepting;

    public File downloadedAttachemnt1;
    public long attachment1ForSigner1FileSize;
    public ZipFile downloadedAllAttachmentsForPackageZip, downloadedAllAttachmentsForSigner1InPackageZip,
            downloadedAllAttachmentsForSigner2InPackageZip;

    public static void main(String... args) {
        new AttachmentRequirementExample().run();
    }

    public AttachmentRequirementExample() {
        this.attachmentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document-for-anchor-extraction.pdf");
        this.attachmentInputStream2 = this.getClass().getClassLoader().getResourceAsStream("document-with-fields.pdf");
        this.attachmentInputStream3 = this.getClass().getClassLoader().getResourceAsStream("extract_document.pdf");
        this.attachmentInputStream4 = this.getClass().getClassLoader().getResourceAsStream("document_with_text_tag_and_form_field.pdf");
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
                .withCustomId(SIGNER2_ID)
                .withAttachmentRequirement(newAttachmentRequirementWithName(NAME2)
                                                   .withDescription(DESCRIPTION2)
                                                   .build())
                .withAttachmentRequirement(newAttachmentRequirementWithName(NAME3)
                    .withDescription(DESCRIPTION3)
                    .isRequiredAttachment()
                    .build())
                .build();

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
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

        attachment1Id = retrievedPackage.getSigner(email1).getAttachmentRequirement(NAME1).getId();
        signer1 = retrievedPackage.getSigner(email1);

        signer1Attachments = retrievedPackage.getSigner(email1).getAttachmentRequirements();
        signer2Attachments = retrievedPackage.getSigner(email2).getAttachmentRequirements();

        signer1Att1 = signer1Attachments.get(0);
        signer2Att1 = signer2Attachments.get(0);
        signer2Att2 = signer2Attachments.get(1);

        retrievedSigner1Att1RequirementStatus = signer1Att1.getStatus();
        retrievedSigner2Att1RequirementStatus = signer2Att1.getStatus();
        retrievedSigner2Att2RequirementStatus = signer2Att2.getStatus();

        // upload attachment
        byte[] attachment1ForSigner1FileContent = new StreamDocumentSource(attachmentInputStream1).content();
        eslClient.uploadAttachment(packageId, signer1Att1.getId(), ATTACHMENT_FILE_NAME1,
                                   attachment1ForSigner1FileContent, SIGNER1_ID);
        attachment1ForSigner1FileSize = attachment1ForSigner1FileContent.length;

        eslClient.uploadAttachment(packageId, signer2Att1.getId(), ATTACHMENT_FILE_NAME2,
                                   new StreamDocumentSource(attachmentInputStream2).content(), SIGNER2_ID);

        Map<String, byte[]> signer2att2files = new HashMap<String, byte[]>();
        signer2att2files.put(ATTACHMENT_FILE_NAME3, new StreamDocumentSource(attachmentInputStream3).content());
        signer2att2files.put(ATTACHMENT_FILE_NAME4, new StreamDocumentSource(attachmentInputStream4).content());
        eslClient.uploadAttachment(packageId, signer2Att2.getId(), signer2att2files, SIGNER2_ID);

        // Sender rejects Signer1's uploaded attachment
        eslClient.getAttachmentRequirementService().rejectAttachment(packageId, signer1, NAME1, REJECTION_COMMENT);
        retrievedPackageAfterRejection = eslClient.getPackage(packageId);

        retrievedSigner1Att1RequirementStatusAfterRejection = retrievedPackageAfterRejection.getSigner(email1).getAttachmentRequirement(NAME1).getStatus();
        retrievedSigner1Att1RequirementSenderCommentAfterRejection = retrievedPackageAfterRejection.getSigner(email1).getAttachmentRequirement(NAME1).getSenderComment();

        // Sender accepts Signer1's uploaded attachment
        eslClient.getAttachmentRequirementService().acceptAttachment(packageId, signer1, NAME1);
        retrievedPackageAfterAccepting = eslClient.getPackage(packageId);

        retrievedSigner1Att1RequirementStatusAfterAccepting = retrievedPackageAfterAccepting.getSigner(email1).getAttachmentRequirement(NAME1).getStatus();
        retrievedSigner1Att1RequirementSenderCommentAfterAccepting = retrievedPackageAfterAccepting.getSigner(email1).getAttachmentRequirement(NAME1).getSenderComment();

        // Download signer1's attachment
        DownloadedFile downloadedAttachment = eslClient.getAttachmentRequirementService().downloadAttachmentFile(packageId, attachment1Id);
        Files.saveTo(downloadedAttachment.getContents(), downloadedAttachment.getFilename());

        // Download all attachments for the package
        DownloadedFile downloadedAllAttachmentsForPackage = eslClient.getAttachmentRequirementService().downloadAllAttachmentFilesForPackage(packageId);
        Files.saveTo(downloadedAllAttachmentsForPackage.getContents(), DOWNLOADED_ALL_ATTACHMENTS_FOR_PACKAGE_ZIP);

        // Download all attachments for the signer1 in the package
        DownloadedFile downloadedAllAttachmentsForSigner1InPackage = eslClient.getAttachmentRequirementService().downloadAllAttachmentFilesForSignerInPackage(retrievedPackage, signer1);
        Files.saveTo(downloadedAllAttachmentsForSigner1InPackage.getContents(), DOWNLOADED_ALL_ATTACHMENTS_FOR_SIGNER1_IN_PACKAGE_ZIP);

        // Download all attachments for the signer2 in the package
        DownloadedFile downloadedAllAttachmentsForSigner2InPackage = eslClient.getAttachmentRequirementService().downloadAllAttachmentFilesForSignerInPackage(retrievedPackage, signer2);
        Files.saveTo(downloadedAllAttachmentsForSigner2InPackage.getContents(), DOWNLOADED_ALL_ATTACHMENTS_FOR_SIGNER2_IN_PACKAGE_ZIP);

        try {
            downloadedAttachemnt1 = new File(downloadedAttachment.getFilename());
            downloadedAllAttachmentsForPackageZip = new ZipFile(DOWNLOADED_ALL_ATTACHMENTS_FOR_PACKAGE_ZIP);
            downloadedAllAttachmentsForSigner1InPackageZip = new ZipFile(DOWNLOADED_ALL_ATTACHMENTS_FOR_SIGNER1_IN_PACKAGE_ZIP);
            downloadedAllAttachmentsForSigner2InPackageZip = new ZipFile(DOWNLOADED_ALL_ATTACHMENTS_FOR_SIGNER2_IN_PACKAGE_ZIP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
