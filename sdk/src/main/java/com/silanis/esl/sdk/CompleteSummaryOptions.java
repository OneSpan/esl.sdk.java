package com.silanis.esl.sdk;

public class CompleteSummaryOptions
{

    private Boolean from = true;
    private Boolean title = true;
    private Boolean message = true;
    private Boolean download = true;
    private Boolean review = true;
    private Boolean _continue = true;

    public Boolean getFrom() {
        return from;
    }

    public void setFrom(Boolean from) {
        this.from = from;
    }

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
}