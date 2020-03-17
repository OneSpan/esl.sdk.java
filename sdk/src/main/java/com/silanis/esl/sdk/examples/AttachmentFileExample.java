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
import com.silanis.esl.sdk.internal.EslServerException;

import java.io.InputStream;
import java.util.List;

import static com.silanis.esl.sdk.builder.AttachmentRequirementBuilder.newAttachmentRequirementWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;

public class AttachmentFileExample extends SDKSample {

    private InputStream attachmentInputStream1;
    private Signer signer1;
    public static final String NAME1 = "Driver's license";
    public static final String DESCRIPTION1 = "Please upload a scanned copy of your driver's license.";
    public static final String SIGNER1_ID = "signer1Id";
    private String attachment1Id;
    private List<AttachmentRequirement> signer1Attachments;
    private AttachmentRequirement signer1Att1;
    public static final String ATTACHMENT_FILE_NAME1 = "The attachment1 for signer1.pdf";
    public static final String ATTACHMENT_FILE_NAME2 = "The attachment1 for signer2.pdf";
    public List<AttachmentFile> filesAfterUpload;
    public List<AttachmentFile> filesAfterDelete;
    public DocumentPackage completedPackage;
    public EslServerException exception;

    public static void main(String... args) {
        new AttachmentFileExample().run();
    }

    public AttachmentFileExample() {
        this.attachmentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document-for-anchor-extraction.pdf");
    }

    @Override
    protected void execute() {

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


        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the eSignLive SDK")
                .withSigner(signer1)
                .withDocument(DocumentBuilder.newDocumentWithName("test document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(SignatureBuilder.signatureFor(email1)
                                .build())
                        .build())
                .build();

        packageId = eslClient.createAndSendPackage(superDuperPackage);

        retrievedPackage = eslClient.getPackage(packageId);
        signer1Attachments = retrievedPackage.getSigner(email1).getAttachmentRequirements();
        signer1Att1 = signer1Attachments.get(0);

        byte[] attachment1ForSigner1FileContent = new StreamDocumentSource(attachmentInputStream1).content();
        eslClient.uploadAttachment(packageId, signer1Att1.getId(), ATTACHMENT_FILE_NAME1,
                attachment1ForSigner1FileContent, SIGNER1_ID);

        eslClient.uploadAttachment(packageId, signer1Att1.getId(), ATTACHMENT_FILE_NAME2,
                attachment1ForSigner1FileContent, SIGNER1_ID);

        retrievedPackage = eslClient.getPackage(packageId);
        signer1Attachments = retrievedPackage.getSigner(email1).getAttachmentRequirements();
        signer1Att1 = signer1Attachments.get(0);

        filesAfterUpload = signer1Att1.getFiles();
        AttachmentFile attachmentFile = filesAfterUpload.get(0);

        eslClient.deleteAttachmentFile(packageId, signer1Att1.getId(), attachmentFile.getId(), SIGNER1_ID);

        retrievedPackage = eslClient.getPackage(packageId);
        signer1Attachments = retrievedPackage.getSigner(email1).getAttachmentRequirements();
        signer1Att1 = signer1Attachments.get(0);

        filesAfterDelete = signer1Att1.getFiles();

        eslClient.signDocuments(packageId, SIGNER1_ID);
        eslClient.getPackageService().markComplete(packageId);
        completedPackage = eslClient.getPackage(packageId);

        try {
            eslClient.deleteAttachmentFile(packageId, signer1Att1.getId(), filesAfterUpload.get(1).getId(), SIGNER1_ID);
        } catch (EslServerException excp) {
            exception = excp;
        }

    }
}
