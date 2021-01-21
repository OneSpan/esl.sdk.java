package com.silanis.esl.sdk;

public class InpersonWelcomeOptions{

    private Boolean title = true;
    private Boolean body = true;
    private Boolean recipientName = true;
    private Boolean recipientEmail = true;
    private Boolean recipientActionRequired = true;
    private Boolean recipientRole = true;
    private Boolean recipientStatus = true;

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