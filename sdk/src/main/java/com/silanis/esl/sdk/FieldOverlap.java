package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.List;

public class FieldOverlap {

    private OverlappingField field;
    private List<ConflictingField> conflictsWith = new ArrayList<ConflictingField>();

    public OverlappingField getField() {
        return field;
    }

    public void setField(OverlappingField field) {
        this.field = field;
    }

    public List<ConflictingField> getConflictsWith() {
        return conflictsWith;
    }

    public void setConflictsWith(List<ConflictingField> conflictsWith) {
        this.conflictsWith = conflictsWith;
    }
}
