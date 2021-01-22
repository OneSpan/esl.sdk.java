package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotaryWelcomeOptions {

    private Boolean title;
    private Boolean body;
    private Boolean recipientName;
    private Boolean recipientEmail;
    private Boolean recipientActionRequired;
    private Boolean notaryTag;
    private Boolean recipientRole;
    private Boolean recipientStatus;

    public Boolean getTitle() {
        return title;
    }

    public void setTitle(Boolean title) {
        this.title = title;
    }

    public Boolean getBody() {
        return body;
    }

    public void setBody(Boolean body) {
        this.body = body;
    }

    public Boolean getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(Boolean recipientName) {
        this.recipientName = recipientName;
    }

    public Boolean getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(Boolean recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public Boolean getRecipientActionRequired() {
        return recipientActionRequired;
    }

    public void setRecipientActionRequired(Boolean recipientActionRequired) {
        this.recipientActionRequired = recipientActionRequired;
    }

    public Boolean getNotaryTag() {
        return notaryTag;
    }

    public void setNotaryTag(Boolean notaryTag) {
        this.notaryTag = notaryTag;
    }

    public Boolean getRecipientRole() {
        return recipientRole;
    }

    public void setRecipientRole(Boolean recipientRole) {
        this.recipientRole = recipientRole;
    }

    public Boolean getRecipientStatus() {
        return recipientStatus;
    }

    public void setRecipientStatus(Boolean recipientStatus) {
        this.recipientStatus = recipientStatus;
    }
}