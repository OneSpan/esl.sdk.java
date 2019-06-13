package com.silanis.esl.api.model;

import static com.silanis.esl.api.util.SchemaSanitizer.throwOnNull;
import static com.silanis.esl.api.util.SchemaSanitizer.trim;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferencedDocument extends Model implements Serializable {

    @JsonIgnore
    public static final String FIELD_ID = "documentId";
    @JsonIgnore
    public static final String REF_FIELDS = "fields";

    private String documentId;
    private List<ReferencedField> fields;

    public ReferencedDocument setDocumentId(String value) {

        throwOnNull(FIELD_ID, value);
        value = trim(value);

        this.documentId = value;
        setDirty(FIELD_ID);
        return this;
    }

    public String getDocumentId() {
        return documentId;
    }

    public ReferencedDocument setFields(List<ReferencedField> value) {

        throwOnNull(REF_FIELDS, value);

        this.fields = value;
        setDirty(REF_FIELDS);
        return this;
    }

    public List<ReferencedField> getFields() {
        return fields;
    }
}

