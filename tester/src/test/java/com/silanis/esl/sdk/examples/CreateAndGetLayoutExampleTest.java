package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.examples.DocumentLayoutExample.LAYOUT_DOCUMENT_NAME;
import static com.silanis.esl.sdk.examples.DocumentLayoutExample.LAYOUT_PACKAGE_DESCRIPTION;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 08/01/18.
 */
public class CreateAndGetLayoutExampleTest {

    @Test
    public void verifyResult() {
        CreateAndGetLayoutExample example = new CreateAndGetLayoutExample();
        example.run();

        assertThat("Layout desciption was not set correctly", example.newLayout.getDescription(), is(LAYOUT_PACKAGE_DESCRIPTION));
        assertThat("Layout should only have one document", example.newLayout.getDocuments(), hasSize(1));
        assertThat("Layout should have two signers", example.newLayout.getSigners(), hasSize(2));
        assertThat("Layout name was not set correctly", example.newLayout.getDocuments().get(0).getName(), is(LAYOUT_DOCUMENT_NAME));
        assertThat("Layout should only have one signature", example.newLayout.getDocuments().get(0).getSignatures(), hasSize(1));
    }
}