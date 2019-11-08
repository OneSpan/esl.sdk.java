package com.silanis.esl.sdk.builder;

import static org.junit.Assert.assertEquals;

import com.silanis.esl.sdk.FieldCondition;
import org.junit.Test;

public class FieldConditionBuilderTest {

    private static final String ID = "Condition1";
    private static final String CONDITION = "document['doc1'].field['field1'].empty == true";
    private static final String ACTION = "document['doc1'].field['field2'].dislabed = false";

    @Test
    public void buildApiTest() {
        FieldCondition fieldCondition = FieldConditionBuilder.newFieldCondition()
            .withId(ID)
            .withCondition(CONDITION)
            .withAction(ACTION)
            .build();

        assertEquals(ID, fieldCondition.getId());
        assertEquals(CONDITION, fieldCondition.getCondition());
        assertEquals(ACTION, fieldCondition.getAction());
    }
}
