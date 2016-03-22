package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.examples.ChangePlaceholderNameExample.PLACEHOLDER_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 8/17/15.
 */
public class ChangePlaceholderNameExampleTest {

    @Test
    public void verifyResult() {
        ChangePlaceholderNameExample example = new ChangePlaceholderNameExample();
        example.run();

        assertThat("Package Status is not set correctly.", example.retrievedPackage.getPlaceholder(PLACEHOLDER_ID), notNullValue());
        assertThat("Package Status is not set correctly.", example.retrievedPackage.getPlaceholder(PLACEHOLDER_ID).getPlaceholderName(), is(example.newPlaceholder.getName()));
        assertThat("Package Status is not set correctly.", example.updatedTemplate.getPlaceholder(PLACEHOLDER_ID), notNullValue());
        assertThat("Package Status is not set correctly.", example.updatedTemplate.getPlaceholder(PLACEHOLDER_ID).getPlaceholderName(), is(example.updatedPlaceholder.getName()));
    }
}
