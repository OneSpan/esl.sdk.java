package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.AuthenticationMethod;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by schoi on 12/15/14.
 */
public class AuthenticationMethodConverterTest implements ConverterTest {

    private String apiAuthenticationMethod;
    private AuthenticationMethod sdkAuthenticationMethod;
    private AuthenticationMethodConverter converter;

    @Test
    public void convertNullSDKToAPI() {
        sdkAuthenticationMethod = null;
        converter = new AuthenticationMethodConverter(sdkAuthenticationMethod);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIAuthMethod(), is(nullValue()));
    }

    @Test
    public void convertNullAPIToSDK() {
        apiAuthenticationMethod = null;
        converter = new AuthenticationMethodConverter(apiAuthenticationMethod);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKAuthMethod(), is(nullValue()));
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkAuthenticationMethod = null;
        converter = new AuthenticationMethodConverter(sdkAuthenticationMethod);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKAuthMethod(), is(nullValue()));
    }

    @Test
    public void convertNullAPIToAPI() {
        apiAuthenticationMethod = null;
        converter = new AuthenticationMethodConverter(apiAuthenticationMethod);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIAuthMethod(), is(nullValue()));
    }

    @Test
    public void convertSDKToSDK() {
        sdkAuthenticationMethod = AuthenticationMethod.CHALLENGE;
        converter = new AuthenticationMethodConverter(sdkAuthenticationMethod);
        AuthenticationMethod result = converter.toSDKAuthMethod();

        assertThat("Converter returned a null sdk object for a non null sdk object", result, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", result, is(equalTo(sdkAuthenticationMethod)));
    }

    @Test
    public void convertAPIToAPI() {
        apiAuthenticationMethod = "CHALLENGE";
        converter = new AuthenticationMethodConverter(apiAuthenticationMethod);
        String result = converter.toAPIAuthMethod();

        assertThat("Converter returned a null api object for a non null api object", result, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", result, is(equalTo(apiAuthenticationMethod)));
    }

    @Test
    public void convertAPIToSDK() {
        apiAuthenticationMethod = "NONE";
        converter = new AuthenticationMethodConverter(apiAuthenticationMethod);
        sdkAuthenticationMethod = converter.toSDKAuthMethod();
        assertThat("AuthenticationMethod was not set correctly", sdkAuthenticationMethod, is(AuthenticationMethod.EMAIL));

        apiAuthenticationMethod = "CHALLENGE";
        converter = new AuthenticationMethodConverter(apiAuthenticationMethod);
        sdkAuthenticationMethod = converter.toSDKAuthMethod();
        assertThat("AuthenticationMethod was not set correctly", sdkAuthenticationMethod, is(AuthenticationMethod.CHALLENGE));

        apiAuthenticationMethod = "SMS";
        converter = new AuthenticationMethodConverter(apiAuthenticationMethod);
        sdkAuthenticationMethod = converter.toSDKAuthMethod();
        assertThat("AuthenticationMethod was not set correctly", sdkAuthenticationMethod, is(AuthenticationMethod.SMS));

        apiAuthenticationMethod = "KBA";
        converter = new AuthenticationMethodConverter(apiAuthenticationMethod);
        sdkAuthenticationMethod = converter.toSDKAuthMethod();
        assertThat("AuthenticationMethod was not set correctly", sdkAuthenticationMethod, is(AuthenticationMethod.KBA));

        apiAuthenticationMethod = "UNKNOWN";
        converter = new AuthenticationMethodConverter(apiAuthenticationMethod);
        sdkAuthenticationMethod = converter.toSDKAuthMethod();
        assertThat("AuthenticationMethod was not set correctly", sdkAuthenticationMethod.toString(), is(AuthenticationMethod.UNRECOGNIZED("UNKNOWN").toString()));
    }

    @Test
    public void convertSDKToAPI() {
        sdkAuthenticationMethod = com.silanis.esl.sdk.AuthenticationMethod.EMAIL;
        converter = new AuthenticationMethodConverter(sdkAuthenticationMethod);
        apiAuthenticationMethod = converter.toAPIAuthMethod();
        assertThat("AuthenticationMethod was not set correctly", apiAuthenticationMethod, is("NONE"));

        sdkAuthenticationMethod = com.silanis.esl.sdk.AuthenticationMethod.CHALLENGE;
        converter = new AuthenticationMethodConverter(sdkAuthenticationMethod);
        apiAuthenticationMethod = converter.toAPIAuthMethod();
        assertThat("AuthenticationMethod was not set correctly", apiAuthenticationMethod, is("CHALLENGE"));

        sdkAuthenticationMethod = com.silanis.esl.sdk.AuthenticationMethod.SMS;
        converter = new AuthenticationMethodConverter(sdkAuthenticationMethod);
        apiAuthenticationMethod = converter.toAPIAuthMethod();
        assertThat("AuthenticationMethod was not set correctly", apiAuthenticationMethod, is("SMS"));

        sdkAuthenticationMethod = com.silanis.esl.sdk.AuthenticationMethod.KBA;
        converter = new AuthenticationMethodConverter(sdkAuthenticationMethod);
        apiAuthenticationMethod = converter.toAPIAuthMethod();
        assertThat("AuthenticationMethod was not set correctly", apiAuthenticationMethod, is("KBA"));

        sdkAuthenticationMethod = com.silanis.esl.sdk.AuthenticationMethod.UNRECOGNIZED("UNKNOWN");
        converter = new AuthenticationMethodConverter(sdkAuthenticationMethod);
        apiAuthenticationMethod = converter.toAPIAuthMethod();
        assertThat("AuthenticationMethod was not set correctly", apiAuthenticationMethod, is("UNKNOWN"));
    }
}
