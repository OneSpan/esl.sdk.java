package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractionResult {

    private String documentUuid;
    private Map<String, String> extractedFields;
    private String providerName;
    private List<AttachmentVerificationCheckResult> verificationCheckResults;
    private Boolean failed;
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

    public Boolean getFailed() {
        return failed;
    }

    public void setFailed(Boolean failed) {
        this.failed = failed;
    }

    public String getErrorCode() {
        return errorCode;
    }

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
