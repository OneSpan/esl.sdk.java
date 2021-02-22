package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

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
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIKnowledgeBasedAuthenticationStatus(), nullValue());
    }

    @Test
    public void convertNullAPIToSDK() {
        apiKnowledgeBasedAuthenticationStatus = null;
        converter = new KnowledgeBasedAuthenticationStatusConverter(apiKnowledgeBasedAuthenticationStatus);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKKnowledgeBasedAuthenticationStatus(), nullValue());
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkKnowledgeBasedAuthenticationStatus = null;
        converter = new KnowledgeBasedAuthenticationStatusConverter(sdkKnowledgeBasedAuthenticationStatus);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKKnowledgeBasedAuthenticationStatus(), nullValue());
    }

    @Test
    public void convertNullAPIToAPI() {
        apiKnowledgeBasedAuthenticationStatus = null;
        converter = new KnowledgeBasedAuthenticationStatusConverter(apiKnowledgeBasedAuthenticationStatus);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIKnowledgeBasedAuthenticationStatus(), nullValue());
    }

    @Test
    public void convertSDKToSDK() {
        sdkKnowledgeBasedAuthenticationStatus = com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus.NOT_YET_ATTEMPTED;
        converter = new KnowledgeBasedAuthenticationStatusConverter(sdkKnowledgeBasedAuthenticationStatus);
        com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus result = converter.toSDKKnowledgeBasedAuthenticationStatus();

        assertThat("Converter returned a null sdk object for a non null sdk object", result, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", result, is(sdkKnowledgeBasedAuthenticationStatus));
    }

    @Test
    public void convertAPIToAPI() {
        apiKnowledgeBasedAuthenticationStatus = "PASSED";
        converter = new KnowledgeBasedAuthenticationStatusConverter(apiKnowledgeBasedAuthenticationStatus);
        String result = converter.toAPIKnowledgeBasedAuthenticationStatus();

        assertThat("Converter returned a null api object for a non null api object", result, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", result, is(apiKnowledgeBasedAuthenticationStatus));
    }

    @Test
    public void convertAPIToSDK() {
        apiKnowledgeBasedAuthenticationStatus = "NOT_YET_ATTEMPTED";
        converter = new KnowledgeBasedAuthenticationStatusConverter(apiKnowledgeBasedAuthenticationStatus);
        sdkKnowledgeBasedAuthenticationStatus = converter.toSDKKnowledgeBasedAuthenticationStatus();
        assertThat("KnowledgeBasedAuthentication status was not set correctly", sdkKnowledgeBasedAuthenticationStatus, is(KnowledgeBasedAuthenticationStatus.NOT_YET_ATTEMPTED));

        apiKnowledgeBasedAuthenticationStatus = "PASSED";
        converter = new KnowledgeBasedAuthenticationStatusConverter(apiKnowledgeBasedAuthenticationStatus);
        sdkKnowledgeBasedAuthenticationStatus = converter.toSDKKnowledgeBasedAuthenticationStatus();
        assertThat("KnowledgeBasedAuthentication status was not set correctly", sdkKnowledgeBasedAuthenticationStatus, is(KnowledgeBasedAuthenticationStatus.PASSED));

        apiKnowledgeBasedAuthenticationStatus = "FAILED";
        converter = new KnowledgeBasedAuthenticationStatusConverter(apiKnowledgeBasedAuthenticationStatus);
        sdkKnowledgeBasedAuthenticationStatus = converter.toSDKKnowledgeBasedAuthenticationStatus();
        assertThat("KnowledgeBasedAuthentication status was not set correctly", sdkKnowledgeBasedAuthenticationStatus, is(KnowledgeBasedAuthenticationStatus.FAILED));

        apiKnowledgeBasedAuthenticationStatus = "INVALID_SIGNER";
        converter = new KnowledgeBasedAuthenticationStatusConverter(apiKnowledgeBasedAuthenticationStatus);
        sdkKnowledgeBasedAuthenticationStatus = converter.toSDKKnowledgeBasedAuthenticationStatus();
        assertThat("KnowledgeBasedAuthentication status was not set correctly", sdkKnowledgeBasedAuthenticationStatus, is(KnowledgeBasedAuthenticationStatus.INVALID_SIGNER));

        apiKnowledgeBasedAuthenticationStatus = "UPDATED";
        converter = new KnowledgeBasedAuthenticationStatusConverter(apiKnowledgeBasedAuthenticationStatus);
        sdkKnowledgeBasedAuthenticationStatus = converter.toSDKKnowledgeBasedAuthenticationStatus();
        assertThat("KnowledgeBasedAuthentication status was not set correctly", sdkKnowledgeBasedAuthenticationStatus, is(KnowledgeBasedAuthenticationStatus.UPDATED));

        apiKnowledgeBasedAuthenticationStatus = "LOCKED";
        converter = new KnowledgeBasedAuthenticationStatusConverter(apiKnowledgeBasedAuthenticationStatus);
        sdkKnowledgeBasedAuthenticationStatus = converter.toSDKKnowledgeBasedAuthenticationStatus();
        assertThat("KnowledgeBasedAuthentication status was not set correctly", sdkKnowledgeBasedAuthenticationStatus, is(KnowledgeBasedAuthenticationStatus.LOCKED));

        apiKnowledgeBasedAuthenticationStatus = "UNKNOWN";
        converter = new KnowledgeBasedAuthenticationStatusConverter(apiKnowledgeBasedAuthenticationStatus);
        sdkKnowledgeBasedAuthenticationStatus = converter.toSDKKnowledgeBasedAuthenticationStatus();
        assertThat("KnowledgeBasedAuthentication status was not set correctly", sdkKnowledgeBasedAuthenticationStatus.toString(), is(KnowledgeBasedAuthenticationStatus.UNRECOGNIZED("UNKNOWN").toString()));
    }

    @Test
    public void convertSDKToAPI() {
        sdkKnowledgeBasedAuthenticationStatus = com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus.NOT_YET_ATTEMPTED;
        converter = new KnowledgeBasedAuthenticationStatusConverter(sdkKnowledgeBasedAuthenticationStatus);
        apiKnowledgeBasedAuthenticationStatus = converter.toAPIKnowledgeBasedAuthenticationStatus();
        assertThat("KnowledgeBasedAuthentication status was not set correctly", apiKnowledgeBasedAuthenticationStatus, is("NOT_YET_ATTEMPTED"));

        sdkKnowledgeBasedAuthenticationStatus = com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus.PASSED;
        converter = new KnowledgeBasedAuthenticationStatusConverter(sdkKnowledgeBasedAuthenticationStatus);
        apiKnowledgeBasedAuthenticationStatus = converter.toAPIKnowledgeBasedAuthenticationStatus();
        assertThat("KnowledgeBasedAuthentication status was not set correctly", apiKnowledgeBasedAuthenticationStatus, is("PASSED"));

        sdkKnowledgeBasedAuthenticationStatus = com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus.FAILED;
        converter = new KnowledgeBasedAuthenticationStatusConverter(sdkKnowledgeBasedAuthenticationStatus);
        apiKnowledgeBasedAuthenticationStatus = converter.toAPIKnowledgeBasedAuthenticationStatus();
        assertThat("KnowledgeBasedAuthentication status was not set correctly", apiKnowledgeBasedAuthenticationStatus, is("FAILED"));

        sdkKnowledgeBasedAuthenticationStatus = com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus.INVALID_SIGNER;
        converter = new KnowledgeBasedAuthenticationStatusConverter(sdkKnowledgeBasedAuthenticationStatus);
        apiKnowledgeBasedAuthenticationStatus = converter.toAPIKnowledgeBasedAuthenticationStatus();
        assertThat("KnowledgeBasedAuthentication status was not set correctly", apiKnowledgeBasedAuthenticationStatus, is("INVALID_SIGNER"));

        sdkKnowledgeBasedAuthenticationStatus = com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus.UPDATED;
        converter = new KnowledgeBasedAuthenticationStatusConverter(sdkKnowledgeBasedAuthenticationStatus);
        apiKnowledgeBasedAuthenticationStatus = converter.toAPIKnowledgeBasedAuthenticationStatus();
        assertThat("KnowledgeBasedAuthentication status was not set correctly", apiKnowledgeBasedAuthenticationStatus, is("UPDATED"));

        sdkKnowledgeBasedAuthenticationStatus = com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus.LOCKED;
        converter = new KnowledgeBasedAuthenticationStatusConverter(sdkKnowledgeBasedAuthenticationStatus);
        apiKnowledgeBasedAuthenticationStatus = converter.toAPIKnowledgeBasedAuthenticationStatus();
        assertThat("KnowledgeBasedAuthentication status was not set correctly", apiKnowledgeBasedAuthenticationStatus, is("LOCKED"));

        sdkKnowledgeBasedAuthenticationStatus = com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus.UNRECOGNIZED("UNKNOWN");
        converter = new KnowledgeBasedAuthenticationStatusConverter(sdkKnowledgeBasedAuthenticationStatus);
        apiKnowledgeBasedAuthenticationStatus = converter.toAPIKnowledgeBasedAuthenticationStatus();
        assertThat("KnowledgeBasedAuthentication status was not set correctly", apiKnowledgeBasedAuthenticationStatus, is("UNKNOWN"));
    }
}
