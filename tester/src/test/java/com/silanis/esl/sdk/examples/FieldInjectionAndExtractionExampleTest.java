package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 16/01/14
 * Time: 11:47 AM
 * 
 * Test FieldInjectionAndExtractionExample.
 * 
 */
public class FieldInjectionAndExtractionExampleTest {
    @Test
    public void verifyResult() {
        FieldInjectionAndExtractionExample fieldInjectionAndExtractionExample = new FieldInjectionAndExtractionExample( Props.get() );
        fieldInjectionAndExtractionExample.run();

        DocumentPackage documentPackage = fieldInjectionAndExtractionExample.getEslClient().getPackage(fieldInjectionAndExtractionExample.getPackageId());

        List<Field> fields = documentPackage.getDocument(FieldInjectionAndExtractionExample.DOCUMENT_NAME).getInjectedFields();
        for (Field field: fields) {
            assertThat("Injected field not extracted properly:", field.getId().equals(FieldInjectionAndExtractionExample.INJECTED_FIELD_1_ID) ||
                                                                 field.getId().equals(FieldInjectionAndExtractionExample.INJECTED_FIELD_2_ID));
            if (field.getId().equals(FieldInjectionAndExtractionExample.INJECTED_FIELD_1_ID)) {
                assertThat(field.getName(), is(equalTo(FieldInjectionAndExtractionExample.INJECTED_FIELD_1_NAME)));
                assertThat(field.getValue(), is(equalTo(FieldInjectionAndExtractionExample.INJECTED_FIELD_1_VALUE)));
            }
        }
    }
}
