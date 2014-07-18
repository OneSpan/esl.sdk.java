package com.silanis.esl.sdk;

import java.io.Serializable;

public class FieldSummary implements Serializable {

    private static final long serialVersionUID = 1L;

    private String signerId;
    private String documentId;
    private String fieldId;
    private String fieldValue;
    private String fieldName;

    public String getSignerId() {
        return signerId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

}
