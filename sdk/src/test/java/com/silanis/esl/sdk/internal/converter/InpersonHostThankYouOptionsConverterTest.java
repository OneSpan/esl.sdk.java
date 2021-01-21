package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.InpersonHostThankYouOptions;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.InpersonHostThankYouOptionsBuilder.newInpersonHostThankYouOptions;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class InpersonHostThankYouOptionsConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.InpersonHostThankYouOptions sdkInpersonHostThankYouOptions = null;
    private InpersonHostThankYouOptions apiInpersonHostThankYouOptions = null;
    private InpersonHostThankYouOptionsConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkInpersonHostThankYouOptions = null;
        converter = new InpersonHostThankYouOptionsConverter(sdkInpersonHostThankYouOptions);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIInpersonHostThankYouOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiInpersonHostThankYouOptions = null;
        converter = new InpersonHostThankYouOptionsConverter(apiInpersonHostThankYouOptions);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKInpersonHostThankYouOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkInpersonHostThankYouOptions = null;
        converter = new InpersonHostThankYouOptionsConverter(sdkInpersonHostThankYouOptions);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKInpersonHostThankYouOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiInpersonHostThankYouOptions = null;
        converter = new InpersonHostThankYouOptionsConverter(apiInpersonHostThankYouOptions);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIInpersonHostThankYouOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkInpersonHostThankYouOptions = createTypicalSDKInpersonHostThankYouOptions();
        com.silanis.esl.sdk.InpersonHostThankYouOptions sdkInpersonHostThankYouOptions2 = new InpersonHostThankYouOptionsConverter(sdkInpersonHostThankYouOptions).toSDKInpersonHostThankYouOptions();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkInpersonHostThankYouOptions2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkInpersonHostThankYouOptions2, is(sdkInpersonHostThankYouOptions));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiInpersonHostThankYouOptions = createTypicalAPIInpersonHostThankYouOptions();
        InpersonHostThankYouOptions apiInpersonHostThankYouOptions2 = new InpersonHostThankYouOptionsConverter(apiInpersonHostThankYouOptions).toAPIInpersonHostThankYouOptions();

        assertThat("Converter returned a null api object for a non null api object", apiInpersonHostThankYouOptions2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiInpersonHostThankYouOptions2, is(apiInpersonHostThankYouOptions));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiInpersonHostThankYouOptions = createTypicalAPIInpersonHostThankYouOptions();
        sdkInpersonHostThankYouOptions = new InpersonHostThankYouOptionsConverter(apiInpersonHostThankYouOptions).toSDKInpersonHostThankYouOptions();

        assertThat("Converter returned a null api object for a non null api object", apiInpersonHostThankYouOptions, notNullValue());
        assertThat("'title' was not correctly set", apiInpersonHostThankYouOptions.getTitle(), is(sdkInpersonHostThankYouOptions.getTitle()));
        assertThat("'body' was not correctly set", apiInpersonHostThankYouOptions.getBody(), is(sdkInpersonHostThankYouOptions.getBody()));
        assertThat("'recipientName' was not correctly set", apiInpersonHostThankYouOptions.getRecipientName(), is(sdkInpersonHostThankYouOptions.getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiInpersonHostThankYouOptions.getRecipientEmail(), is(sdkInpersonHostThankYouOptions.getRecipientEmail()));
        assertThat("'recipientRole' was not correctly set", apiInpersonHostThankYouOptions.getRecipientRole(), is(sdkInpersonHostThankYouOptions.getRecipientRole()));
        assertThat("'recipientStatus' was not correctly set", apiInpersonHostThankYouOptions.getRecipientStatus(), is(sdkInpersonHostThankYouOptions.getRecipientStatus()));
        assertThat("'downloadButton' was not correctly set", apiInpersonHostThankYouOptions.getDownloadButton(), is(sdkInpersonHostThankYouOptions.getDownloadButton()));
        assertThat("'reviewDocumentsButton' was not correctly set", apiInpersonHostThankYouOptions.getReviewDocumentsButton(), is(sdkInpersonHostThankYouOptions.getReviewDocumentsButton()));


    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkInpersonHostThankYouOptions = createTypicalSDKInpersonHostThankYouOptions();
        apiInpersonHostThankYouOptions = new InpersonHostThankYouOptionsConverter(sdkInpersonHostThankYouOptions).toAPIInpersonHostThankYouOptions();

        assertThat("Converter returned a null api object for a non null sdk object", apiInpersonHostThankYouOptions, is(notNullValue()));
        assertThat("'title' was not correctly set", apiInpersonHostThankYouOptions.getTitle(), is(sdkInpersonHostThankYouOptions.getTitle()));
        assertThat("'body' was not correctly set", apiInpersonHostThankYouOptions.getBody(), is(sdkInpersonHostThankYouOptions.getBody()));
        assertThat("'recipientName' was not correctly set", apiInpersonHostThankYouOptions.getRecipientName(), is(sdkInpersonHostThankYouOptions.getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiInpersonHostThankYouOptions.getRecipientEmail(), is(sdkInpersonHostThankYouOptions.getRecipientEmail()));
        assertThat("'recipientRole' was not correctly set", apiInpersonHostThankYouOptions.getRecipientRole(), is(sdkInpersonHostThankYouOptions.getRecipientRole()));
        assertThat("'recipientStatus' was not correctly set", apiInpersonHostThankYouOptions.getRecipientStatus(), is(sdkInpersonHostThankYouOptions.getRecipientStatus()));
        assertThat("'downloadButton' was not correctly set", apiInpersonHostThankYouOptions.getDownloadButton(), is(sdkInpersonHostThankYouOptions.getDownloadButton()));
        assertThat("'reviewDocumentsButton' was not correctly set", apiInpersonHostThankYouOptions.getReviewDocumentsButton(), is(sdkInpersonHostThankYouOptions.getReviewDocumentsButton()));

    }

    /**
     * Create an SDK InpersonHostThankYouOptions.
     *
     * @return SDK InpersonHostThankYouOptions.
     */
    private com.silanis.esl.sdk.InpersonHostThankYouOptions createTypicalSDKInpersonHostThankYouOptions() {
        return newInpersonHostThankYouOptions()
                .withTitle()
                .withBody()
                .withRecipientName()
                .withRecipientEmail()
                .withRecipientRole()
                .withRecipientStatus()
                .withDownloadButton()
                .withReviewDocumentsButton()
                .build();
    }

    /**
     * Create an API InpersonHostThankYouOptions.
     *
     * @return API InpersonHostThankYouOptions.
     */
    private InpersonHostThankYouOptions createTypicalAPIInpersonHostThankYouOptions() {

        InpersonHostThankYouOptions apiInpersonHostThankYouOptions = new InpersonHostThankYouOptions();

        apiInpersonHostThankYouOptions.setTitle(true);
        apiInpersonHostThankYouOptions.setBody(true);
        apiInpersonHostThankYouOptions.setRecipientName(true);
        apiInpersonHostThankYouOptions.setRecipientEmail(true);
        apiInpersonHostThankYouOptions.setRecipientRole(true);
        apiInpersonHostThankYouOptions.setRecipientStatus(true);
        apiInpersonHostThankYouOptions.setDownloadButton(true);
        apiInpersonHostThankYouOptions.setReviewDocumentsButton(true);

        return apiInpersonHostThankYouOptions;
    }
}
