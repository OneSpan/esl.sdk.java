package com.silanis.esl.sdk;

/**
 * A field overlapping the anchor field of a {@link FieldOverlap}, and how it overlaps. It shares the
 * anchor field's document, page and signer, so only its id and name are returned.
 */
public class ConflictingField {

    private FieldRef field;
    private OverlapType overlapType;

    public FieldRef getField() {
        return field;
    }

    public void setField(FieldRef field) {
        this.field = field;
    }

    public OverlapType getOverlapType() {
        return overlapType;
    }

    public void setOverlapType(OverlapType overlapType) {
        this.overlapType = overlapType;
    }
}
