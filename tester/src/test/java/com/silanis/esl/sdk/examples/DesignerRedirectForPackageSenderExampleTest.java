package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.internal.HttpRequestUtil;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mpoitras on 22/04/14.
 */
public class DesignerRedirectForPackageSenderExampleTest {
    @Test
    public void verifyResult() {
        DesignerRedirectForPackageSenderExample example = new DesignerRedirectForPackageSenderExample();
        example.run();

        assertThat(example.generatedLinkToDesignerForSender, notNullValue());

        String stringResponse = HttpRequestUtil.getUrlContent(example.generatedLinkToDesignerForSender);
        assertThat(stringResponse, containsString("Electronic Disclosures and Signatures Consent"));
    }
}
