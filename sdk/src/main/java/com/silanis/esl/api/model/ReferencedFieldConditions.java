package com.silanis.esl.api.model;

import static com.silanis.esl.api.util.SchemaSanitizer.throwOnNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferencedFieldConditions extends Model implements Serializable {

    @JsonIgnore
    public static final String REF_IN_CONDITION = "referencedInCondition";
    @JsonIgnore
    public static final String REF_IN_ACTION = "referencedInAction";

    private List<FieldCondition> referencedInCondition;
    private List<FieldCondition> referencedInAction;

    public ReferencedFieldConditions() {
    }

    public ReferencedFieldConditions(List<FieldCondition> referencedInCondition, List<FieldCondition> referencedInAction) {
        this.referencedInCondition = referencedInCondition;
        this.referencedInAction = referencedInAction;
    }

    public ReferencedFieldConditions setReferencedInCondition(List<FieldCondition> value) {

        throwOnNull(REF_IN_CONDITION, value);

        this.referencedInCondition = value;
        setDirty(REF_IN_CONDITION);
        return this;
    }

    public List<FieldCondition> getReferencedInCondition() {
        return referencedInCondition;
    }

    public ReferencedFieldConditions setReferencedInAction(List<FieldCondition> value) {

        throwOnNull(REF_IN_ACTION, value);

        this.referencedInAction = value;
        setDirty(REF_IN_ACTION);
        return this;
    }

    public List<FieldCondition> getReferencedInAction() {
        return referencedInAction;
    }
}