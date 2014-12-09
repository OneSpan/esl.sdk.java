package com.silanis.esl.sdk.internal.converter;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by schoi on 9/11/14.
 */
public class KnowledgeBasedAuthenticationStatusConverterTest implements ConverterTest {

    private String apiKnowledgeBasedAuthenticationStatus;
    private com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus sdkKnowledgeBasedAuthenticationStatus;
    private KnowledgeBasedAuthenticationStatusConverter converter;

    @Test
    public void convertNullSDKToAPI() {
        sdkKnowledgeBasedAuthenticationStatus = null;
        converter = new KnowledgeBasedAuthenticationStatusConverter(sdkKnowledgeBasedAuthenticationStatus);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIKnowledgeBasedAuthenticationStatus(), is(nullValue()));
    }

    @Test
    public void convertNullAPIToSDK() {
        apiKnowledgeBasedAuthenticationStatus = null;
        converter = new KnowledgeBasedAuthenticationStatusConverter(apiKnowledgeBasedAuthenticationStatus);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKKnowledgeBasedAuthenticationStatus(), is(nullValue()));
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkKnowledgeBasedAuthenticationStatus = null;
        converter = new KnowledgeBasedAuthenticationStatusConverter(sdkKnowledgeBasedAuthenticationStatus);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKKnowledgeBasedAuthenticationStatus(), is(nullValue()));
    }

    @Test
    public void convertNullAPIToAPI() {
        apiKnowledgeBasedAuthenticationStatus = null;
        converter = new KnowledgeBasedAuthenticationStatusConverter(apiKnowledgeBasedAuthenticationStatus);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIKnowledgeBasedAuthenticationStatus(), is(nullValue()));
    }

    @Test
    public void convertSDKToSDK() {
        sdkKnowledgeBasedAuthenticationStatus = createTypicalSDKKnowledgeBasedAuthenticationStatus();
        converter = new KnowledgeBasedAuthenticationStatusConverter(sdkKnowledgeBasedAuthenticationStatus);
        com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus result = converter.toSDKKnowledgeBasedAuthenticationStatus();

        assertThat("Converter returned a null sdk object for a non null sdk object", result, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", result, is(equalTo(sdkKnowledgeBasedAuthenticationStatus)));
    }

    @Test
    public void convertAPIToAPI() {
        apiKnowledgeBasedAuthenticationStatus = "PASSED";
        converter = new KnowledgeBasedAuthenticationStatusConverter(apiKnowledgeBasedAuthenticationStatus);
        String result = converter.toAPIKnowledgeBasedAuthenticationStatus();

        assertThat("Converter returned a null api object for a non null api object", result, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", result, is(equalTo(apiKnowledgeBasedAuthenticationStatus)));
    }

    @Test
    public void convertAPIToSDK() {
        apiKnowledgeBasedAuthenticationStatus = "PASSED";
        converter = new KnowledgeBasedAuthenticationStatusConverter(apiKnowledgeBasedAuthenticationStatus);
        sdkKnowledgeBasedAuthenticationStatus = converter.toSDKKnowledgeBasedAuthenticationStatus();

        assertThat("KnowledgeBasedAuthentication status was not set correctly", sdkKnowledgeBasedAuthenticationStatus.getValue(), is(apiKnowledgeBasedAuthenticationStatus));
    }

    @Test
    public void convertSDKToAPI() {
        sdkKnowledgeBasedAuthenticationStatus = createTypicalSDKKnowledgeBasedAuthenticationStatus();
        converter = new KnowledgeBasedAuthenticationStatusConverter(sdkKnowledgeBasedAuthenticationStatus);
        apiKnowledgeBasedAuthenticationStatus = converter.toAPIKnowledgeBasedAuthenticationStatus();

        assertThat("KnowledgeBasedAuthentication status was not set correctly", apiKnowledgeBasedAuthenticationStatus, is(sdkKnowledgeBasedAuthenticationStatus.getValue()));
    }

    private com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus createTypicalSDKKnowledgeBasedAuthenticationStatus() {
        return com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus.NOT_YET_ATTEMPTED;
    }
}
