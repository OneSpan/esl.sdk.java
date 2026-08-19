package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractionResult {

    private String documentUuid;
    private Map<String, String> extractedFields;
    private String providerName;
    private List<AttachmentVerificationCheckResult> verificationCheckResults;

    /**
     * Unknown values are read as {@code null} so that a reason code added by a newer
     * server release does not break deserialization in an older SDK.
     */
    @JsonFormat(with = JsonFormat.Feature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
    private ExtractionStatus extractionStatus;

    @JsonFormat(with = JsonFormat.Feature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
    private ExtractionReasonCode reasonCode;

    private Boolean failed;
    @Deprecated
    private String errorCode;
    private String failureMessage;

    public String getDocumentUuid() {
        return documentUuid;
    }

    public void setDocumentUuid(String documentUuid) {
        this.documentUuid = documentUuid;
    }

    public Map<String, String> getExtractedFields() {
        return extractedFields;
    }

    public void setExtractedFields(Map<String, String> extractedFields) {
        this.extractedFields = extractedFields;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public List<AttachmentVerificationCheckResult> getVerificationCheckResults() {
        return verificationCheckResults;
    }

    public void setVerificationCheckResults(List<AttachmentVerificationCheckResult> verificationCheckResults) {
        this.verificationCheckResults = verificationCheckResults;
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
     * Coarse flag derived by the server from {@link #getExtractionStatus()}: {@code true}
     * whenever the status is not {@link ExtractionStatus#COMPLETED}.
     *
     * @deprecated prefer {@link #getExtractionStatus()} and {@link #getReasonCode()}, which
     *             distinguish "not performed" from a genuine failure.
     */
    @Deprecated
    public Boolean getFailed() {
        return failed;
    }

    /**
     * @deprecated see {@link #getFailed()}.
     */
    @Deprecated
    public void setFailed(Boolean failed) {
        this.failed = failed;
    }

    /**
     * @deprecated no longer returned by the server; use {@link #getReasonCode()} instead.
     *             Always {@code null} against a server that reports the structured outcome.
     */
    @Deprecated
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @deprecated see {@link #getErrorCode()}.
     */
    @Deprecated
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }
}
