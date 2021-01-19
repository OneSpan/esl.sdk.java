package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.OverviewOptions;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.OverviewOptionsBuilder.newOverviewOptions;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class OverviewOptionsConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.OverviewOptions sdkOverviewOptions = null;
    private OverviewOptions apiOverviewOptions = null;
    private OverviewOptionsConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkOverviewOptions = null;
        converter = new OverviewOptionsConverter(sdkOverviewOptions);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIOverviewOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiOverviewOptions = null;
        converter = new OverviewOptionsConverter(apiOverviewOptions);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKOverviewOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkOverviewOptions = null;
        converter = new OverviewOptionsConverter(sdkOverviewOptions);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKOverviewOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiOverviewOptions = null;
        converter = new OverviewOptionsConverter(apiOverviewOptions);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIOverviewOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkOverviewOptions = createTypicalSDKOverviewOptions();
        com.silanis.esl.sdk.OverviewOptions sdkOverviewOptions2 = new OverviewOptionsConverter(sdkOverviewOptions).toSDKOverviewOptions();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkOverviewOptions2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkOverviewOptions2, is(sdkOverviewOptions));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiOverviewOptions = createTypicalAPIOverviewOptions();
        OverviewOptions apiOverviewOptions2 = new OverviewOptionsConverter(apiOverviewOptions).toAPIOverviewOptions();

        assertThat("Converter returned a null api object for a non null api object", apiOverviewOptions2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiOverviewOptions2, is(apiOverviewOptions));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiOverviewOptions = createTypicalAPIOverviewOptions();
        sdkOverviewOptions = new OverviewOptionsConverter(apiOverviewOptions).toSDKOverviewOptions();

        assertThat("Converter returned a null api object for a non null api object", apiOverviewOptions, notNullValue());
        assertThat("'title' was not correctly set", apiOverviewOptions.getTitle(), is(sdkOverviewOptions.getTitle()));
        assertThat("'body' was not correctly set", apiOverviewOptions.getBody(), is(sdkOverviewOptions.getBody()));
        assertThat("'documentSection' was not correctly set", apiOverviewOptions.getDocumentSection(), is(sdkOverviewOptions.getDocumentSection()));
        assertThat("'uploadSection' was not correctly set", apiOverviewOptions.getUploadSection(), is(sdkOverviewOptions.getUploadSection()));

    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkOverviewOptions = createTypicalSDKOverviewOptions();
        apiOverviewOptions = new OverviewOptionsConverter(sdkOverviewOptions).toAPIOverviewOptions();

        assertThat("Converter returned a null api object for a non null sdk object", apiOverviewOptions, is(notNullValue()));
        assertThat("'title' was not correctly set", apiOverviewOptions.getTitle(), is(sdkOverviewOptions.getTitle()));
        assertThat("'body' was not correctly set", apiOverviewOptions.getBody(), is(sdkOverviewOptions.getBody()));
        assertThat("'documentSection' was not correctly set", apiOverviewOptions.getDocumentSection(), is(sdkOverviewOptions.getDocumentSection()));
        assertThat("'uploadSection' was not correctly set", apiOverviewOptions.getUploadSection(), is(sdkOverviewOptions.getUploadSection()));

    }

    /**
     * Create an SDK OverviewOptions.
     *
     * @return SDK OverviewOptions.
     */
    private com.silanis.esl.sdk.OverviewOptions createTypicalSDKOverviewOptions() {
        return newOverviewOptions()
                .withTitle()
                .withBody()
                .withDocumentSection()
                .withUploadSection()
                .build();
    }

    /**
     * Create an API OverviewOptions.
     *
     * @return API OverviewOptions.
     */
    private OverviewOptions createTypicalAPIOverviewOptions() {

        OverviewOptions apiOverviewOptions = new OverviewOptions();

        apiOverviewOptions.setTitle(true);
        apiOverviewOptions.setBody(true);
        apiOverviewOptions.setDocumentSection(true);
        apiOverviewOptions.setUploadSection(true);

        return apiOverviewOptions;
    }
}
