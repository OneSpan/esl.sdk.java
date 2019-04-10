package com.silanis.esl.sdk;

import java.io.Serializable;
import java.util.List;

public class ConditionalField extends Field implements Serializable {

    private List<FieldCondition> conditions;

    public void setConditions( List<FieldCondition> conditions ) {
        this.conditions = conditions;
    }

    public List<FieldCondition> getConditions() {
        return conditions;
    }
}
