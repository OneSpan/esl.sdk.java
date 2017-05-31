package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import java.util.Map;

import static com.silanis.esl.sdk.ExtractionType.FORM_FIELDS_WITH_TEXT_TAGS;
import static com.silanis.esl.sdk.builder.DocumentBuilder.ESL_DOC_EXTRACT_TYPE;
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
        assertThat("TextTags Attribute key is not set correctly.", documentAttributes.containsKey(ESL_DOC_EXTRACT_TYPE));

        assertThat("Attribute 1 is not setup correctly.", documentAttributes.get(ATTRIBUTE_KEY_1).toString().equals(ATTRIBUTE_1));
        assertThat("Attribute 2 is not setup correctly.", documentAttributes.get(ATTRIBUTE_KEY_2).toString().equals(ATTRIBUTE_2));
        assertThat("Attribute 3 is not setup correctly.", documentAttributes.get(ATTRIBUTE_KEY_3).toString().equals(ATTRIBUTE_3));
        assertThat("TextTags Attribute value is not setup correctly.", documentAttributes.get(ESL_DOC_EXTRACT_TYPE).toString().equals(FORM_FIELDS_WITH_TEXT_TAGS.getValue()));
    }
}
