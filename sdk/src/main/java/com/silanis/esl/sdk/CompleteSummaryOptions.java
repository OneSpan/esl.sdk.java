package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompleteSummaryOptions
{

    private Boolean title;
    private Boolean message;
    private Boolean download;
    private Boolean review;
    private Boolean _continue;
    private Boolean documentSection;
    private Boolean uploadSection;

    public Boolean getTitle() {
        return title;
    }

    public void setTitle(Boolean title) {
        this.title = title;
    }

    public Boolean getMessage() {
        return message;
    }

    public void setMessage(Boolean message) {
        this.message = message;
    }

    public Boolean getDownload() {
        return download;
    }

    public void setDownload(Boolean download) {
        this.download = download;
    }

    public Boolean getReview() {
        return review;
    }

    public void setReview(Boolean review) {
        this.review = review;
    }

    public Boolean getContinue() {
        return _continue;
    }

    public void setContinue(Boolean _continue) {
        this._continue = _continue;
    }

    public Boolean getDocumentSection() {
        return documentSection;
    }

    public void setDocumentSection(Boolean documentSection) {
        this.documentSection = documentSection;
    }

    public Boolean getUploadSection() {
        return uploadSection;
    }

    public void setUploadSection(Boolean uploadSection) {
        this.uploadSection = uploadSection;
    }
}