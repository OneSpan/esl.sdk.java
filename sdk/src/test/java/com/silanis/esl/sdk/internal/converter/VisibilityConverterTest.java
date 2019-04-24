package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.Visibility;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 2/20/15.
 */
public class VisibilityConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.Visibility sdkVisibility1 = null;
    private com.silanis.esl.sdk.Visibility sdkVisibility2 = null;
    private String apiVisibility1 = null;
    private String apiVisibility2 = null;
    private VisibilityConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkVisibility1 = null;
        converter = new VisibilityConverter(sdkVisibility1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIVisibility(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiVisibility1 = null;
        converter = new VisibilityConverter(apiVisibility1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKVisibility(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkVisibility1 = null;
        converter = new VisibilityConverter(sdkVisibility1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKVisibility(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiVisibility1 = null;
        converter = new VisibilityConverter(apiVisibility1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIVisibility(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkVisibility1 = com.silanis.esl.sdk.Visibility.ACCOUNT;
        sdkVisibility2 = new VisibilityConverter(sdkVisibility1).toSDKVisibility();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkVisibility2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkVisibility2, is(sdkVisibility1));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiVisibility1 = "REGULAR";
        apiVisibility2 = new VisibilityConverter(apiVisibility1).toAPIVisibility();

        assertThat("Converter returned a null api object for a non null api object", apiVisibility2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiVisibility2, is(apiVisibility1));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiVisibility1 = "ACCOUNT";
        sdkVisibility1 = new VisibilityConverter(apiVisibility1).toSDKVisibility();
        assertThat("Sender type was not set correctly", sdkVisibility1, is(Visibility.ACCOUNT));

        apiVisibility1 = "SENDER";
        sdkVisibility1 = new VisibilityConverter(apiVisibility1).toSDKVisibility();
        assertThat("Sender type was not set correctly", sdkVisibility1, is(Visibility.SENDER));

        apiVisibility1 = "UNKNOWN";
        sdkVisibility1 = new VisibilityConverter(apiVisibility1).toSDKVisibility();
        assertThat("Sender type was not set correctly", sdkVisibility1.toString(), is(Visibility.UNRECOGNIZED("UNKNOWN").toString()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkVisibility1 = com.silanis.esl.sdk.Visibility.ACCOUNT;
        apiVisibility1 = new VisibilityConverter(sdkVisibility1).toAPIVisibility();
        assertThat("Sender type was not set correctly", apiVisibility1, is("ACCOUNT"));

        sdkVisibility1 = com.silanis.esl.sdk.Visibility.SENDER;
        apiVisibility1 = new VisibilityConverter(sdkVisibility1).toAPIVisibility();
        assertThat("Sender type was not set correctly", apiVisibility1, is("SENDER"));

        sdkVisibility1 = com.silanis.esl.sdk.Visibility.UNRECOGNIZED("UNKNOWN");
        apiVisibility1 = new VisibilityConverter(sdkVisibility1).toAPIVisibility();
        assertThat("Sender type was not set correctly", apiVisibility1, is("UNKNOWN"));
    }

}
