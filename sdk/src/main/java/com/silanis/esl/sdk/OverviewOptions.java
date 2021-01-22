package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OverviewOptions {

    private Boolean title;
    private Boolean body;
    private Boolean documentSection;
    private Boolean uploadSection;

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