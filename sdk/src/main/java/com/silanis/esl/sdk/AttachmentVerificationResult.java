package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentVerificationResult {

    private String attachmentUuid;
    private String fileName;
    private String fileId;
    private String extension;
    private AttachmentClassificationResult classificationResult;
    private ExtractionResult extractionResult;
    private boolean extractionFailed;
    private String extractionErrorCode;
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

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
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

    public boolean isExtractionFailed() {
        return extractionFailed;
    }

    public void setExtractionFailed(boolean extractionFailed) {
        this.extractionFailed = extractionFailed;
    }

    public String getExtractionErrorCode() {
        return extractionErrorCode;
    }

    public void setExtractionErrorCode(String extractionErrorCode) {
        this.extractionErrorCode = extractionErrorCode;
    }

    public boolean isTypeMatch() {
        return typeMatch;
    }

    public void setTypeMatch(boolean typeMatch) {
        this.typeMatch = typeMatch;
    }
}
