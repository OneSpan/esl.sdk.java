package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Field;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chi-wing on 7/16/14.
 */
public class FieldManipulationExampleTest {
    @Test
    public void verifyResult() {
        FieldManipulationExample fieldManipulationExample = new FieldManipulationExample(Props.get());
        fieldManipulationExample.run();

        Map<String,Field> fieldMap = convertListToMap(fieldManipulationExample.addedFields);
        assertThat("Signature 1 was not set correctly", fieldMap.containsKey(fieldManipulationExample.field1.getName()), is(true));
        assertThat("Signature 2 was not set correctly", fieldMap.containsKey(fieldManipulationExample.field2.getName()), is(true));
        assertThat("Signature 3 was not set correctly", fieldMap.containsKey(fieldManipulationExample.field3.getName()), is(true));

    }

    private Map<String, Field> convertListToMap(Collection<Field> fields){
        Map<String,Field> fieldMap = new HashMap<String, Field>();
        for (Field field : fields){
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }

}
