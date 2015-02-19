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
public class SigningRedirectForSignerExampleTest {
    @Test
    public void verifyResult() {
        SigningRedirectForSignerExample example = new SigningRedirectForSignerExample( Props.get() );
        example.run();

        assertThat(example.generatedLinkToSigningForSigner, is(notNullValue()));

        String stringResponse = HttpRequestUtil.getUrlContent(example.generatedLinkToSigningForSigner);
        assertThat(stringResponse, containsString("Electronic Disclosures and Signatures Consent"));
    }
}
