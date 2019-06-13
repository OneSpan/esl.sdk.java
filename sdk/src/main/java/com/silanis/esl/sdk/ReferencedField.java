package com.silanis.esl.sdk;

public class ReferencedField {

    private String fieldId;
    private ReferencedFieldConditions conditions;

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public ReferencedFieldConditions getConditions() {
        return conditions;
    }

    public void setConditions(ReferencedFieldConditions conditions) {
        this.conditions = conditions;
    }
}
