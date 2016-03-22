package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.examples.UpdateTemplateWithPlaceholderExample.DOCUMENT_NAME;
import static com.silanis.esl.sdk.examples.UpdateTemplateWithPlaceholderExample.TEMPLATE_NAME;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by schoi on 5/25/15.
 */
public class UpdateTemplateWithPlaceholderExampleTest {

    @Test
    public void verifyResult() {

        UpdateTemplateWithPlaceholderExample example = new UpdateTemplateWithPlaceholderExample();
        example.run();

        assertThat("Template is not created correctly.", example.retrievedTemplate.getName(), is(TEMPLATE_NAME));
        assertThat("Template is not created correctly.", example.retrievedTemplate.getSigners().size(), is(2));
        assertThat("Template is not created correctly.", example.retrievedTemplate.getPlaceholders().size(), is(1));
        assertThat("Template is not created correctly.", example.retrievedTemplate.getPlaceholders().get(0), notNullValue());
        assertThat("Template is not created correctly.", example.retrievedTemplate.getDocument(DOCUMENT_NAME).getSignatures().size(), is(2));

        assertThat("Template is not updated correctly.", example.updatedTemplate.getSigners().size(), is(2));
        assertThat("Template is not updated correctly.", example.updatedTemplate.getPlaceholders().size(), is(2));
        assertThat("Template is not updated correctly.", example.updatedTemplate.getPlaceholders().get(0), notNullValue());
        assertThat("Template is not updated correctly.", example.updatedTemplate.getPlaceholders().get(1), notNullValue());
        assertThat("Template is not updated correctly.", example.updatedTemplate.getDocument(DOCUMENT_NAME).getSignatures().size(), is(3));
    }
}
