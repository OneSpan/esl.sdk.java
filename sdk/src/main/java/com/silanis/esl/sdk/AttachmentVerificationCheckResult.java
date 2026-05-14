package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentVerificationCheckResult {

    private String ruleName;
    private String field;
    private AttachmentVerificationStatus status;
    private String message;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public AttachmentVerificationStatus getStatus() {
        return status;
    }

    public void setStatus(AttachmentVerificationStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
