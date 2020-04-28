package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AttachmentFile;
import com.silanis.esl.sdk.AttachmentRequirement;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import com.silanis.esl.sdk.builder.internal.StreamDocumentSource;
import com.silanis.esl.sdk.io.DownloadedFile;
import com.silanis.esl.sdk.io.Files;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import static com.silanis.esl.sdk.builder.AttachmentRequirementBuilder.newAttachmentRequirementWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;

public class AttachmentFileExample extends SDKSample {

    private InputStream attachmentInputStream;
    private Signer signer;
    public static final String NAME = "Driver's license";
    public static final String DESCRIPTION = "Please upload a scanned copy of your driver's license.";
    public static final String SIGNER1_ID = "signerId";
    private List<AttachmentRequirement> signerAttachments;
    private AttachmentRequirement signerAtt;
    public static final String ATTACHMENT_FILE_NAME = "The attachment for signer.pdf";
    public List<AttachmentFile> filesAfterUpload;
    public List<AttachmentFile> filesAfterDelete;

    public File downloadedAttachmentFile;
    public long signerAttachmentFileSize;

    public static void main(String... args) {
        new AttachmentFileExample().run();
    }

    public AttachmentFileExample() {
        this.attachmentInputStream = this.getClass().getClassLoader().getResourceAsStream("document-for-anchor-extraction.pdf");
    }

    @Override
    protected void execute() {

        // Signer with 1 attachment requirement
        signer = SignerBuilder.newSignerWithEmail(email1)
                .withFirstName("John")
                .withLastName("Smith")
                .withCustomId(SIGNER1_ID)
                .withAttachmentRequirement(newAttachmentRequirementWithName(NAME)
                        .withDescription(DESCRIPTION)
                        .isRequiredAttachment()
                        .build())
                .build();


        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the eSignLive SDK")
                .withSigner(signer)
                .withDocument(DocumentBuilder.newDocumentWithName("test document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(SignatureBuilder.signatureFor(email1)
                                .build())
                        .build())
                .build();

        packageId = eslClient.createAndSendPackage(superDuperPackage);

        retrievedPackage = eslClient.getPackage(packageId);
        signerAttachments = retrievedPackage.getSigner(email1).getAttachmentRequirements();
        signerAtt = signerAttachments.get(0);

        byte[] attachmentForSignerFileContent = new StreamDocumentSource(attachmentInputStream).content();
        eslClient.uploadAttachment(packageId, signerAtt.getId(), ATTACHMENT_FILE_NAME,
                attachmentForSignerFileContent, SIGNER1_ID);
        signerAttachmentFileSize = attachmentForSignerFileContent.length;

        retrievedPackage = eslClient.getPackage(packageId);
        signerAttachments = retrievedPackage.getSigner(email1).getAttachmentRequirements();
        signerAtt = signerAttachments.get(0);

        filesAfterUpload = signerAtt.getFiles();
        AttachmentFile attachmentFile = filesAfterUpload.get(0);

        // Download signer attachment file
        DownloadedFile downloadedAttachment = eslClient.getAttachmentRequirementService().downloadAttachmentFile(packageId, signerAtt.getId(), attachmentFile.getId());
        Files.saveTo(downloadedAttachment.getContents(), downloadedAttachment.getFilename());
        downloadedAttachmentFile = new File(downloadedAttachment.getFilename());

        eslClient.deleteAttachmentFile(packageId, signerAtt.getId(), attachmentFile.getId(), SIGNER1_ID);

        retrievedPackage = eslClient.getPackage(packageId);
        signerAttachments = retrievedPackage.getSigner(email1).getAttachmentRequirements();
        signerAtt = signerAttachments.get(0);

        filesAfterDelete = signerAtt.getFiles();
    }
}
