package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.internal.HttpRequestUtil.getUrlContent;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mpoitras on 22/04/14.
 */
public class SigningRedirectForSignerExampleTest {
    @Test
    public void verifyResult() {
        SigningRedirectForSignerExample example = new SigningRedirectForSignerExample();
        example.run();

        assertThat(example.generatedLinkToSigningForSigner, notNullValue());

        String stringResponse = getUrlContent(example.generatedLinkToSigningForSigner);
        assertThat(stringResponse, containsString("Electronic Disclosures and Signatures Consent"));
    }
}
