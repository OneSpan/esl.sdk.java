package com.silanis.esl.sdk;

import java.util.List;

public class ReferencedDocument {

    private String documentId;
    private List<ReferencedField> fields;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public List<ReferencedField> getFields() {
        return fields;
    }

    public void setFields(List<ReferencedField> fields) {
        this.fields = fields;
    }
}
