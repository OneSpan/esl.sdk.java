package com.silanis.esl.sdk;

import java.util.List;

public class ReferencedFieldConditions {

    private List<FieldCondition> referencedInCondition;
    private List<FieldCondition> referencedInAction;

    public List<FieldCondition> getReferencedInCondition() {
        return referencedInCondition;
    }

    public void setReferencedInCondition(List<FieldCondition> referencedInCondition) {
        this.referencedInCondition = referencedInCondition;
    }

    public List<FieldCondition> getReferencedInAction() {
        return referencedInAction;
    }

    public void setReferencedInAction(List<FieldCondition> referencedInAction) {
        this.referencedInAction = referencedInAction;
    }
}
