package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import java.util.Map;

import static com.silanis.esl.sdk.examples.DocumentAttributesExample.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by schoi on 28/06/17.
 */
public class DocumentAttributesExampleTest {

    @Test
    public void verifyResult() {
        DocumentAttributesExample example = new DocumentAttributesExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();
        Map<String, Object> documentAttributes = documentPackage.getDocument(DOCUMENT_NAME).getData();

        assertThat("Attribute key 1 is not setup correctly.", documentAttributes.containsKey(ATTRIBUTE_KEY_1));
        assertThat("Attribute key 2 is not setup correctly.", documentAttributes.containsKey(ATTRIBUTE_KEY_2));
        assertThat("Attribute key 3 is not setup correctly.", documentAttributes.containsKey(ATTRIBUTE_KEY_3));

        assertThat("Attribute 1 is not setup correctly.", documentAttributes.get(ATTRIBUTE_KEY_1).toString().equals(ATTRIBUTE_1));
        assertThat("Attribute 2 is not setup correctly.", documentAttributes.get(ATTRIBUTE_KEY_2).toString().equals(ATTRIBUTE_2));
        assertThat("Attribute 3 is not setup correctly.", documentAttributes.get(ATTRIBUTE_KEY_3).toString().equals(ATTRIBUTE_3));
    }
}
