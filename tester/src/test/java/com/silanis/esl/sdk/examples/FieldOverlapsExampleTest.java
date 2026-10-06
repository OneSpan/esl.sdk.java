package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.ConflictingField;
import com.silanis.esl.sdk.FieldOverlap;
import com.silanis.esl.sdk.FieldOverlapValidationResult;
import com.silanis.esl.sdk.OverlapType;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.silanis.esl.sdk.examples.FieldOverlapsExample.CHECKBOX_PAGE;
import static com.silanis.esl.sdk.examples.FieldOverlapsExample.DOCUMENT_ID;
import static com.silanis.esl.sdk.examples.FieldOverlapsExample.FIRST_CHECKBOX_ID;
import static com.silanis.esl.sdk.examples.FieldOverlapsExample.SECOND_CHECKBOX_ID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FieldOverlapsExampleTest {
    @Test
    public void verifyResult() {
        FieldOverlapsExample example = new FieldOverlapsExample();
        example.run();

        FieldOverlapValidationResult result = example.getFieldOverlaps();

        // The overlapping pair is reported once, anchored on whichever checkbox comes first.
        assertThat(result.getOverlaps().size(), is(1));
        FieldOverlap overlap = result.getOverlaps().get(0);
        assertThat(overlap.getField().getDocumentId(), is(DOCUMENT_ID));
        assertThat(overlap.getField().getPage(), is(CHECKBOX_PAGE));
        assertThat(overlap.getConflictsWith().size(), is(1));

        ConflictingField conflict = overlap.getConflictsWith().get(0);
        assertThat(conflict.getOverlapType(), is(OverlapType.FIELD_VS_FIELD));

        Set<String> reportedIds = new HashSet<String>(Arrays.asList(overlap.getField().getId(), conflict.getField().getId()));
        // The separate checkbox is not reported.
        assertThat(reportedIds, is((Set<String>) new HashSet<String>(Arrays.asList(FIRST_CHECKBOX_ID, SECOND_CHECKBOX_ID))));
    }
}
