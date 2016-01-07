package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Field;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by chi-wing on 7/16/14.
 */
public class FieldManipulationExampleTest {
    @Test
    public void verifyResult() {
        FieldManipulationExample example = new FieldManipulationExample(Props.get());
        example.run();

        // Test if all fields are added properly
        Map<String,Field> fieldMap = convertListToMap(example.addedFields);
        assertTrue("Field 1 was not set correctly", fieldMap.containsKey(example.field1.getName()));
        assertTrue("Field 2 was not set correctly", fieldMap.containsKey(example.field2.getName()));
        assertTrue("Field 3 was not set correctly", fieldMap.containsKey(example.field3.getName()));

        // Test if field1 is deleted properly
        fieldMap = convertListToMap(example.deletedFields);
        assertFalse("Field 1 was not deleted correctly", fieldMap.containsKey(example.field1.getName()));
        assertTrue("Field 2 was not set correctly", fieldMap.containsKey(example.field2.getName()));
        assertTrue("Field 3 was not set correctly", fieldMap.containsKey(example.field3.getName()));

        // Test if field3 is updated properly
        fieldMap = convertListToMap(example.updatedFields);
        assertTrue("Field 3 was not updated correctly", fieldMap.containsKey(example.updatedField.getName()));
    }

    private Map<String, Field> convertListToMap(Collection<Field> fields){
        Map<String,Field> fieldMap = new HashMap<String, Field>();
        for (Field field : fields){
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }
}
