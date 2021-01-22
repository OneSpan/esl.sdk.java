package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotaryHostThankYouOptions {

    private Boolean title;
    private Boolean body;
    private Boolean recipientName;
    private Boolean recipientEmail;
    private Boolean recipientRole;
    private Boolean notaryTag;
    private Boolean recipientStatus;
    private Boolean downloadButton;
    private Boolean reviewDocumentsButton;

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

    public Boolean getRecipientRole() {
        return recipientRole;
    }

    public void setRecipientRole(Boolean recipientRole) {
        this.recipientRole = recipientRole;
    }

    public Boolean getNotaryTag() {
        return notaryTag;
    }

    public void setNotaryTag(Boolean notaryTag) {
        this.notaryTag = notaryTag;
    }

    public Boolean getRecipientStatus() {
        return recipientStatus;
    }

    public void setRecipientStatus(Boolean recipientStatus) {
        this.recipientStatus = recipientStatus;
    }

    public Boolean getDownloadButton() {
        return downloadButton;
    }

    public void setDownloadButton(Boolean downloadButton) {
        this.downloadButton = downloadButton;
    }

    public Boolean getReviewDocumentsButton() {
        return reviewDocumentsButton;
    }

    public void setReviewDocumentsButton(Boolean reviewDocumentsButton) {
        this.reviewDocumentsButton = reviewDocumentsButton;
    }
}