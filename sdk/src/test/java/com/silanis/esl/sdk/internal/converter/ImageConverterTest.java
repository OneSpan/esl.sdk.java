package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.Image;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by schoi on 7/14/16.
 */
public class ImageConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.Image sdkImage = null;
    private com.silanis.esl.api.model.Image apiImage = null;
    private ImageConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkImage = null;
        converter = new ImageConverter(sdkImage);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIImage(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiImage = null;
        converter = new ImageConverter(apiImage);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKImage(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkImage = null;
        converter = new ImageConverter(sdkImage);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKImage(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiImage = null;
        converter = new ImageConverter(apiImage);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIImage(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkImage = createTypicalSDKImage();
        sdkImage = new ImageConverter(sdkImage).toSDKImage();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkImage, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkImage, is(equalTo(sdkImage)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiImage = createTypicalAPIAuthImage();
        apiImage = new ImageConverter(apiImage).toAPIImage();

        assertThat("Converter returned a null api object for a non null api object", apiImage, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiImage, is(equalTo(apiImage)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiImage = createTypicalAPIAuthImage();
        sdkImage = new ImageConverter(apiImage).toSDKImage();

        assertThat("Converter returned a null sdk object for a non null api object", sdkImage, is(notNullValue()));
        assertThat("Link was not correctly set", sdkImage.getLink(), is(apiImage.getLink()));
        assertThat("Src was not correctly set", sdkImage.getSrc(), is(apiImage.getSrc()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkImage = createTypicalSDKImage();
        apiImage = new ImageConverter(sdkImage).toAPIImage();

        assertThat("Converter returned a null api object for a non null sdk object", apiImage, is(notNullValue()));
        assertThat("Link was not correctly set", apiImage.getLink(), is(sdkImage.getLink()));
        assertThat("Src was not correctly set", apiImage.getSrc(), is(sdkImage.getSrc()));
    }

    private com.silanis.esl.sdk.Image createTypicalSDKImage() {
        Image sdkImage = new Image();
        sdkImage.setLink("sdkLink");
        sdkImage.setSrc("sdkSrc");
        return sdkImage;
    }

    private com.silanis.esl.api.model.Image createTypicalAPIAuthImage() {
        com.silanis.esl.api.model.Image apiImage = new com.silanis.esl.api.model.Image();
        apiImage.setLink("apiLink");
        apiImage.setSrc("apiSrc");
        return apiImage;
    }
}
