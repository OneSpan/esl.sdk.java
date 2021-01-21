package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.CompleteSummaryOptions;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.CompleteSummaryOptionsBuilder.newCompleteSummaryOptions;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class CompleteSummaryOptionsConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.CompleteSummaryOptions sdkCompleteSummaryOptions = null;
    private com.silanis.esl.api.model.CompleteSummaryOptions apiCompleteSummaryOptions = null;
    private CompleteSummaryOptionsConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkCompleteSummaryOptions = null;
        converter = new CompleteSummaryOptionsConverter(sdkCompleteSummaryOptions);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPICompleteSummaryOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiCompleteSummaryOptions = null;
        converter = new CompleteSummaryOptionsConverter(apiCompleteSummaryOptions);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKCompleteSummaryOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkCompleteSummaryOptions = null;
        converter = new CompleteSummaryOptionsConverter(sdkCompleteSummaryOptions);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKCompleteSummaryOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiCompleteSummaryOptions = null;
        converter = new CompleteSummaryOptionsConverter(apiCompleteSummaryOptions);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPICompleteSummaryOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkCompleteSummaryOptions = createTypicalSDKCompleteSummaryOptions();
        com.silanis.esl.sdk.CompleteSummaryOptions sdkCompleteSummaryOptions2 = new CompleteSummaryOptionsConverter(sdkCompleteSummaryOptions).toSDKCompleteSummaryOptions();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkCompleteSummaryOptions2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkCompleteSummaryOptions2, is(sdkCompleteSummaryOptions));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiCompleteSummaryOptions = createTypicalAPICompleteSummaryOptions();
        com.silanis.esl.api.model.CompleteSummaryOptions apiCompleteSummaryOptions2 = new CompleteSummaryOptionsConverter(apiCompleteSummaryOptions).toAPICompleteSummaryOptions();

        assertThat("Converter returned a null api object for a non null api object", apiCompleteSummaryOptions2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiCompleteSummaryOptions2, is(apiCompleteSummaryOptions));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiCompleteSummaryOptions = createTypicalAPICompleteSummaryOptions();
        sdkCompleteSummaryOptions = new CompleteSummaryOptionsConverter(apiCompleteSummaryOptions).toSDKCompleteSummaryOptions();

        assertThat("Converter returned a null api object for a non null api object", apiCompleteSummaryOptions, notNullValue());
        assertThat("'from' was not correctly set", apiCompleteSummaryOptions.getFrom(), is(sdkCompleteSummaryOptions.getFrom()));
        assertThat("'title' was not correctly set", apiCompleteSummaryOptions.getTitle(), is(sdkCompleteSummaryOptions.getTitle()));
        assertThat("'message' was not correctly set", apiCompleteSummaryOptions.getMessage(), is(sdkCompleteSummaryOptions.getMessage()));
        assertThat("'download' was not correctly set", apiCompleteSummaryOptions.getDownload(), is(sdkCompleteSummaryOptions.getDownload()));
        assertThat("'review' was not correctly set", apiCompleteSummaryOptions.getReview(), is(sdkCompleteSummaryOptions.getReview()));
        assertThat("'continue' was not correctly set", apiCompleteSummaryOptions.getContinue(), is(sdkCompleteSummaryOptions.getContinue()));

    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkCompleteSummaryOptions = createTypicalSDKCompleteSummaryOptions();
        apiCompleteSummaryOptions = new CompleteSummaryOptionsConverter(sdkCompleteSummaryOptions).toAPICompleteSummaryOptions();

        assertThat("Converter returned a null api object for a non null sdk object", apiCompleteSummaryOptions, is(notNullValue()));
        assertThat("'from' was not correctly set", apiCompleteSummaryOptions.getFrom(), is(sdkCompleteSummaryOptions.getFrom()));
        assertThat("'title' was not correctly set", apiCompleteSummaryOptions.getTitle(), is(sdkCompleteSummaryOptions.getTitle()));
        assertThat("'message' was not correctly set", apiCompleteSummaryOptions.getMessage(), is(sdkCompleteSummaryOptions.getMessage()));
        assertThat("'download' was not correctly set", apiCompleteSummaryOptions.getDownload(), is(sdkCompleteSummaryOptions.getDownload()));
        assertThat("'review' was not correctly set", apiCompleteSummaryOptions.getReview(), is(sdkCompleteSummaryOptions.getReview()));
        assertThat("'continue' was not correctly set", apiCompleteSummaryOptions.getContinue(), is(sdkCompleteSummaryOptions.getContinue()));
    }

    /**
     * Create an SDK CompleteSummaryOptions.
     *
     * @return SDK CompleteSummaryOptions.
     */
    private com.silanis.esl.sdk.CompleteSummaryOptions createTypicalSDKCompleteSummaryOptions() {
        return newCompleteSummaryOptions()
                .withFrom()
                .withTitle()
                .withMessage()
                .withDownload()
                .withReview()
                .withContinue()
                .build();
    }

    /**
     * Create an API CompleteSummaryOptions.
     *
     * @return API CompleteSummaryOptions.
     */
    private com.silanis.esl.api.model.CompleteSummaryOptions createTypicalAPICompleteSummaryOptions() {

        com.silanis.esl.api.model.CompleteSummaryOptions apiCompleteSummaryOptions = new CompleteSummaryOptions();

        apiCompleteSummaryOptions.setFrom(true);
        apiCompleteSummaryOptions.setTitle(true);
        apiCompleteSummaryOptions.setMessage(true);
        apiCompleteSummaryOptions.setDownload(true);
        apiCompleteSummaryOptions.setReview(true);
        apiCompleteSummaryOptions.setContinue(true);

        return apiCompleteSummaryOptions;
    }
}
