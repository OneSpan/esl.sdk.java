package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by mpoitras on 22/04/14.
 */
public class DesignerRedirectForApiKeyExampleTest {

    @Test
    public void verifyResult() {
        DesignerRedirectForApiKeyExample example = new DesignerRedirectForApiKeyExample();
        example.run();

        assertThat(example.generatedLinkToDesignerForApiKey, not(isEmptyOrNullString()));
    }
}
