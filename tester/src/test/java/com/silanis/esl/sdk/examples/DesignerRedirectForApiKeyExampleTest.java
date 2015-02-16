package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.internal.HttpRequestUtil;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mpoitras on 22/04/14.
 */
public class DesignerRedirectForApiKeyExampleTest {
    @Test
    public void verifyResult() {
        DesignerRedirectForApiKeyExample example = new DesignerRedirectForApiKeyExample( Props.get() );
        example.run();

        assertThat(example.generatedLinkToDesignerForApiKey, is(notNullValue()));

        String stringResponse = HttpRequestUtil.getUrlContent(example.generatedLinkToDesignerForApiKey);
        assertThat(stringResponse, containsString("Electronic Disclosures and Signatures Consent"));
    }

}
