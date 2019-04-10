package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import static com.silanis.esl.api.util.SchemaSanitizer.throwOnNull;
import static com.silanis.esl.api.util.SchemaSanitizer.trim;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldCondition extends Model implements Serializable {

    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_CONDITION = "condition";
    @JsonIgnore
    public static final String FIELD_ACTION = "action";

    protected String id;
    protected String condition;
    protected String action;

    public FieldCondition() {

    }

    public FieldCondition setId(String value) {

        throwOnNull(FIELD_ID, value);
        value = trim(value);

        this.id = value;
        setDirty(FIELD_ID);
        return this;
    }

    public String getId() {
        return id;
    }

    public FieldCondition setCondition(String value) {

        throwOnNull(FIELD_CONDITION, value);
        value = trim(value);

        this.condition = value;
        setDirty(FIELD_CONDITION);
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public FieldCondition setAction(String value) {

        throwOnNull(FIELD_ACTION, value);
        value = trim(value);

        this.action = value;
        setDirty(FIELD_ACTION);
        return this;
    }

    public String getAction() {
        return action;
    }
}


