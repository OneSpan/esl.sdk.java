package com.silanis.esl.sdk;

import java.util.List;

public class ReferencedConditions {

    private String packageId;
    private List<ReferencedDocument> documents;

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public List<ReferencedDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<ReferencedDocument> documents) {
        this.documents = documents;
    }
}
