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

    /**
     * Unknown values are read as {@code null} so that a reason code added by a newer
     * server release does not break deserialization in an older SDK.
     */
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

    /**
     * @deprecated no longer returned by the server; use {@link #getExtractionStatus()} instead.
     *             Always {@code false} against a server that reports the structured outcome.
     */
    @Deprecated
    public boolean isExtractionFailed() {
        return extractionFailed;
    }

    /**
     * @deprecated see {@link #isExtractionFailed()}.
     */
    @Deprecated
    public void setExtractionFailed(boolean extractionFailed) {
        this.extractionFailed = extractionFailed;
    }

    /**
     * @deprecated no longer returned by the server; use {@link #getReasonCode()} instead.
     *             Always {@code null} against a server that reports the structured outcome.
     */
    @Deprecated
    public String getExtractionErrorCode() {
        return extractionErrorCode;
    }

    /**
     * @deprecated see {@link #getExtractionErrorCode()}.
     */
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
