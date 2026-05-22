package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AttachmentRequirement;
import com.silanis.esl.sdk.AttachmentClassificationResult;
import com.silanis.esl.sdk.AttachmentType;
import com.silanis.esl.sdk.AttachmentVerificationCheckResult;
import com.silanis.esl.sdk.AttachmentVerificationResult;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.ExtractionResult;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import com.silanis.esl.sdk.builder.internal.StreamDocumentSource;

import java.io.InputStream;
import java.util.List;

import static com.silanis.esl.sdk.builder.AttachmentRequirementBuilder.newAttachmentRequirementWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;

public class AttachmentExtractionExample extends SDKSample {

    public static final String ATTACHMENT_NAME = "Driver's license";
    public static final String ATTACHMENT_DESCRIPTION = "Please upload a scanned copy of your driver's license.";
    public static final AttachmentType ATTACHMENT_TYPE = AttachmentType.DRIVERS_LICENSE;
    public static final String SIGNER1_ID = "signer1Id";
    public static final String ATTACHMENT_FILE_NAME = "attachment-drivers-license.pdf";

    public AttachmentRequirement retrievedAttachmentRequirement;
    public List<AttachmentVerificationResult> verificationResults;
    public AttachmentVerificationResult verificationResult;
    public AttachmentClassificationResult classificationResult;
    public ExtractionResult extractionResult;
    public List<AttachmentVerificationCheckResult> verificationCheckResults;
    public boolean extractionFailed;
    public String extractionErrorCode;

    private final InputStream attachmentInputStream;

    public static void main(String... args) {
        AttachmentExtractionExample example = new AttachmentExtractionExample();
        example.run();
    }

    public AttachmentExtractionExample() {
        this.attachmentInputStream = this.getClass().getClassLoader()
                .getResourceAsStream("document-for-anchor-extraction.pdf");
    }

    @Override
    public void execute() {

        Signer signer = SignerBuilder.newSignerWithEmail(email1)
                .withFirstName("John")
                .withLastName("Smith")
                .withCustomId(SIGNER1_ID)
                .withAttachmentRequirement(newAttachmentRequirementWithName(ATTACHMENT_NAME)
                        .withDescription(ATTACHMENT_DESCRIPTION)
                        .isRequiredAttachment()
                        .withAttachmentType(ATTACHMENT_TYPE)
                        .withExtractionEnabled(true)
                        .build())
                .build();

        DocumentPackage pkg = newPackageNamed(getPackageName())
                .describedAs("Extraction example – demonstrates extractionEnabled and ExtractionResult")
                .withSigner(signer)
                .withDocument(DocumentBuilder.newDocumentWithName("test document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(SignatureBuilder.signatureFor(email1).build())
                        .build())
                .build();

        packageId = eslClient.createAndSendPackage(pkg);

        retrievedPackage = eslClient.getPackage(packageId);
        retrievedAttachmentRequirement = retrievedPackage.getSigner(email1)
                .getAttachmentRequirement(ATTACHMENT_NAME);

        String attachmentId = retrievedAttachmentRequirement.getId();
        byte[] fileContent = new StreamDocumentSource(attachmentInputStream).content();
        eslClient.uploadAttachment(packageId, attachmentId, ATTACHMENT_FILE_NAME, fileContent, SIGNER1_ID);

        verificationResults = eslClient.getAttachmentRequirementService()
                .getAttachmentVerificationResults(packageId);

        if (verificationResults.isEmpty()) {
            throw new IllegalStateException("No attachment verification results were returned for package " + packageId.getId());
        }

        verificationResult = verificationResults.get(0);
        classificationResult = verificationResult.getClassificationResult();
        extractionResult = verificationResult.getExtractionResult();
        extractionFailed = verificationResult.isExtractionFailed();
        extractionErrorCode = verificationResult.getExtractionErrorCode();
        if (extractionResult != null) {
            verificationCheckResults = extractionResult.getVerificationCheckResults();
        }
    }
}
