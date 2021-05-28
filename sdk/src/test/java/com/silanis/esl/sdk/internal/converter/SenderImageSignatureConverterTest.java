package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.SenderImageSignature;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class SenderImageSignatureConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.SenderImageSignature sdkSenderImageSignature1 = null;
    private com.silanis.esl.sdk.SenderImageSignature sdkSenderImageSignature2 = null;
    private com.silanis.esl.api.model.SenderImageSignature apiSenderImageSignature1 = null;
    private com.silanis.esl.api.model.SenderImageSignature apiSenderImageSignature2 = null;
    private SenderImageSignatureConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkSenderImageSignature1 = null;
        converter = new SenderImageSignatureConverter(sdkSenderImageSignature1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toAPISenderImageSignature(), is(nullValue()));

    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiSenderImageSignature1 = null;
        converter = new SenderImageSignatureConverter(apiSenderImageSignature1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toSDKSenderImageSignature(), is(nullValue()));

    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkSenderImageSignature1 = null;
        converter = new SenderImageSignatureConverter(sdkSenderImageSignature1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKSenderImageSignature(), is(nullValue()));

    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiSenderImageSignature1 = null;
        converter = new SenderImageSignatureConverter(apiSenderImageSignature1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPISenderImageSignature(), is(nullValue()));

    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkSenderImageSignature1 = new SenderImageSignature();
        sdkSenderImageSignature2 = new SenderImageSignatureConverter(sdkSenderImageSignature1).toSDKSenderImageSignature();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkSenderImageSignature2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkSenderImageSignature2, is(equalTo(sdkSenderImageSignature1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiSenderImageSignature1 = new com.silanis.esl.api.model.SenderImageSignature();
        apiSenderImageSignature2 = new SenderImageSignatureConverter(apiSenderImageSignature1).toAPISenderImageSignature();

        assertThat("Converter returned a null api object for a non null api object", apiSenderImageSignature2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiSenderImageSignature2, is(equalTo(apiSenderImageSignature1)));

    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiSenderImageSignature1 = buildApiSenderImageSignature();
        sdkSenderImageSignature1 = new SenderImageSignatureConverter(apiSenderImageSignature1).toSDKSenderImageSignature();

        assertThat("Converter returned a null api object for a non null sdk object", sdkSenderImageSignature1, is(notNullValue()));
        assertThat("Video Value was not correctly set", sdkSenderImageSignature1.getContent(), is(apiSenderImageSignature1.getContent()));
        assertThat("VideoRecording Value was not correctly set", sdkSenderImageSignature1.getFileName(), is(apiSenderImageSignature1.getFileName()));
        assertThat("HostUid Value was not correctly set", sdkSenderImageSignature1.getMediaType(), is(apiSenderImageSignature1.getMediaType()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkSenderImageSignature1 = buildSdkSenderImageSignature();
        apiSenderImageSignature1 = new SenderImageSignatureConverter(sdkSenderImageSignature1).toAPISenderImageSignature();

        assertThat("Converter returned a null api object for a non null sdk object", apiSenderImageSignature1, is(notNullValue()));
        assertThat("Video Value was not correctly set", apiSenderImageSignature1.getFileName(), is(sdkSenderImageSignature1.getFileName()));
        assertThat("VideoRecording Value was not correctly set", apiSenderImageSignature1.getMediaType(), is(sdkSenderImageSignature1.getMediaType()));
        assertThat("HostUid Value was not correctly set", apiSenderImageSignature1.getContent(), is(sdkSenderImageSignature1.getContent()));
    }

    private SenderImageSignature buildSdkSenderImageSignature() {
        SenderImageSignature result = new SenderImageSignature();
        result.setMediaType("mediaType");
        result.setFileName("fileName");
        result.setContent("content");
        return result;
    }


    private com.silanis.esl.api.model.SenderImageSignature buildApiSenderImageSignature() {
        com.silanis.esl.api.model.SenderImageSignature result = new com.silanis.esl.api.model.SenderImageSignature();
        result.setMediaType("mediaType");
        result.setFileName("fileName");
        result.setContent("content");
        return result;
    }
}