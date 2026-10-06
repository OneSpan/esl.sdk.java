package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.List;

public class FieldOverlapValidationResult {

    private List<FieldOverlap> overlaps = new ArrayList<FieldOverlap>();

    public List<FieldOverlap> getOverlaps() {
        return overlaps;
    }

    public void setOverlaps(List<FieldOverlap> overlaps) {
        this.overlaps = overlaps;
    }

    public boolean hasOverlaps() {
        return overlaps != null && !overlaps.isEmpty();
    }
}
