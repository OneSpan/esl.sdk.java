package com.silanis.esl.sdk;

public class OverviewOptions {

    private Boolean title = true;
    private Boolean body = true;
    private Boolean documentSection = true;
    private Boolean uploadSection = true;

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