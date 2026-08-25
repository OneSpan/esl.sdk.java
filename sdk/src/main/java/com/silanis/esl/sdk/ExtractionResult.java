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

    @Deprecated
    public Boolean getFailed() {
        return failed;
    }

    @Deprecated
    public void setFailed(Boolean failed) {
        this.failed = failed;
    }

    @Deprecated
    public String getErrorCode() {
        return errorCode;
    }

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
