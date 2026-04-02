package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the outcome of verifying a single uploaded attachment file against
 * the expected {@code attachmentType} set on the requirement.
 *
 * <p>Returned by {@link com.silanis.esl.sdk.service.AttachmentRequirementService#getAttachmentVerificationResults}.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentVerificationResult {

    private String attachmentUuid;
    private String fileName;
    private String extension;
    private AttachmentClassificationResult classificationResult;
    private boolean typeMatch;

    public String getAttachmentUuid() {
        return attachmentUuid;
    }

    public void setAttachmentUuid(String attachmentUuid) {
        this.attachmentUuid = attachmentUuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public AttachmentClassificationResult getClassificationResult() {
        return classificationResult;
    }

    public void setClassificationResult(AttachmentClassificationResult classificationResult) {
        this.classificationResult = classificationResult;
    }

    public boolean isTypeMatch() {
        return typeMatch;
    }

    public void setTypeMatch(boolean typeMatch) {
        this.typeMatch = typeMatch;
    }
}
