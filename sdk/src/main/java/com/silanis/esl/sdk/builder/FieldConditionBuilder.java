package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.FieldCondition;

public class FieldConditionBuilder {

    private String id;
    private String condition;
    private String action;

    public static FieldConditionBuilder newFieldCondition() {
        return new FieldConditionBuilder();
    }

    public FieldConditionBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public FieldConditionBuilder withCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public FieldConditionBuilder withAction(String action) {
        this.action = action;
        return this;
    }

    public FieldCondition build() {
        FieldCondition fieldCondition = new FieldCondition();
        fieldCondition.setId(this.id);
        fieldCondition.setCondition(this.condition);
        fieldCondition.setAction(this.action);
        return fieldCondition;
    }
}
