package com.silanis.esl.api.model;

import static com.silanis.esl.api.util.SchemaSanitizer.throwOnNull;
import static com.silanis.esl.api.util.SchemaSanitizer.trim;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferencedField extends Model implements Serializable {

    @JsonIgnore
    public static final String FIELD_ID = "fieldId";
    @JsonIgnore
    public static final String REF_CONDITIONS = "conditions";

    private String fieldId;
    private ReferencedFieldConditions conditions;

    public ReferencedField setFieldId(String value) {

        throwOnNull(FIELD_ID, value);
        value = trim(value);

        this.fieldId = value;
        setDirty(FIELD_ID);
        return this;
    }

    public String getFieldId() {
        return fieldId;
    }

    public ReferencedField setConditions(ReferencedFieldConditions value) {

        throwOnNull(REF_CONDITIONS, value);

        this.conditions = value;
        setDirty(REF_CONDITIONS);
        return this;
    }

    public ReferencedFieldConditions getConditions() {
        return conditions;
    }
}