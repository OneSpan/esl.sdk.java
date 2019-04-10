package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConditionalField extends Field {

    @JsonIgnore
    private static final String FIELD_CONDITIONS = "conditions";

    private List<FieldCondition> conditions;

    public ConditionalField() { }

    public Field setConditions(List<FieldCondition> value) {

        this.conditions = value;
        setDirty(FIELD_CONDITIONS);
        return this;
    }

    public List<FieldCondition> getConditions() {
        return conditions;
    }
}
