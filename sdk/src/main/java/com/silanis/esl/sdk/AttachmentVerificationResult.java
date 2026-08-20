package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentVerificationResult {

    private String attachmentUuid;
    private String fileName;
    private String fileId;
    private String extension;
    private AttachmentClassificationResult classificationResult;
    private ExtractionResult extractionResult;

    @JsonFormat(with = JsonFormat.Feature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
    private ExtractionStatus extractionStatus;

    @JsonFormat(with = JsonFormat.Feature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
    private ExtractionReasonCode reasonCode;

    @Deprecated
    private boolean extractionFailed;
    @Deprecated
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

    public ExtractionStatus getExtractionStatus() {
        return extractionStatus;
    }

    public void setExtractionStatus(ExtractionStatus extractionStatus) {
        this.extractionStatus = extractionStatus;
    }

    public ExtractionReasonCode getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(ExtractionReasonCode reasonCode) {
        this.reasonCode = reasonCode;
    }

    @Deprecated
    public boolean isExtractionFailed() {
        return extractionFailed;
    }

    @Deprecated
    public void setExtractionFailed(boolean extractionFailed) {
        this.extractionFailed = extractionFailed;
    }

    @Deprecated
    public String getExtractionErrorCode() {
        return extractionErrorCode;
    }

    @Deprecated
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
