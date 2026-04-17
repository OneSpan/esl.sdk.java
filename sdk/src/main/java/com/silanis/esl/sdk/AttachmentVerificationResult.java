package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentVerificationResult {

    private String attachmentUuid;
    private String fileName;
    private String extension;
    private AttachmentClassificationResult classificationResult;
    private ExtractionResult extractionResult;
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

    public ExtractionResult getExtractionResult() {
        return extractionResult;
    }

    public void setExtractionResult(ExtractionResult extractionResult) {
        this.extractionResult = extractionResult;
    }

    public boolean isTypeMatch() {
        return typeMatch;
    }

    public void setTypeMatch(boolean typeMatch) {
        this.typeMatch = typeMatch;
    }
}
