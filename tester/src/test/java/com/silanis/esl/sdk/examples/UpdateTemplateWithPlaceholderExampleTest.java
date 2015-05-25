package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by schoi on 5/25/15.
 */
public class UpdateTemplateWithPlaceholderExampleTest {

    @Test
    public void verifyResult() {

        UpdateTemplateWithPlaceholderExample example = new UpdateTemplateWithPlaceholderExample( Props.get() );
        example.run();

        assertThat("Template is not created correctly.", example.retrievedTemplate.getName(), is(example.TEMPLATE1_NAME));
        assertThat("Template is not created correctly.", example.retrievedTemplate.getSigners().size(), is(2));
        assertThat("Template is not created correctly.", example.retrievedTemplate.getPlaceholders().size(), is(1));
        assertThat("Template is not created correctly.", example.retrievedTemplate.getPlaceholders().get(example.PLACEHOLDER_ID), is(not(nullValue())));

        assertThat("Template is not updated correctly.", example.updatedTemplate.getName(), is(example.TEMPLATE2_NAME));
        assertThat("Template is not updated correctly.", example.updatedTemplate.getSigners().size(), is(2));
        assertThat("Template is not updated correctly.", example.updatedTemplate.getPlaceholders().size(), is(2));
        assertThat("Template is not updated correctly.", example.updatedTemplate.getPlaceholders().get(example.PLACEHOLDER_ID), is(not(nullValue())));
        assertThat("Template is not updated correctly.", example.updatedTemplate.getPlaceholders().get(example.PLACEHOLDER2_ID), is(not(nullValue())));
    }
}
