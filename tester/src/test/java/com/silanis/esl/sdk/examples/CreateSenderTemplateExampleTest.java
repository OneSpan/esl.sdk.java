package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mina on 18/02/15.
 */
public class CreateSenderTemplateExampleTest {

    @Test
    public void verifyResult() {
        CreateSenderTemplateExample example = new CreateSenderTemplateExample();
        example.run();

        DocumentPackage retrievedTemplate = example.eslClient.getPackage(example.templateId);

        assertThat("Template Visibility is incorrectly set.", retrievedTemplate.getVisibility(), is(example.visibility));
    }
}
