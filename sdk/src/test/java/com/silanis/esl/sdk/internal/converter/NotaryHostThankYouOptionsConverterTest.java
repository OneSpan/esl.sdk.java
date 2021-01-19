package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.NotaryHostThankYouOptions;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.NotaryHostThankYouOptionsBuilder.newNotaryHostThankYouOptions;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class NotaryHostThankYouOptionsConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.NotaryHostThankYouOptions sdkNotaryHostThankYouOptions = null;
    private NotaryHostThankYouOptions apiNotaryHostThankYouOptions = null;
    private NotaryHostThankYouOptionsConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkNotaryHostThankYouOptions = null;
        converter = new NotaryHostThankYouOptionsConverter(sdkNotaryHostThankYouOptions);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPINotaryHostThankYouOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiNotaryHostThankYouOptions = null;
        converter = new NotaryHostThankYouOptionsConverter(apiNotaryHostThankYouOptions);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKNotaryHostThankYouOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkNotaryHostThankYouOptions = null;
        converter = new NotaryHostThankYouOptionsConverter(sdkNotaryHostThankYouOptions);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKNotaryHostThankYouOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiNotaryHostThankYouOptions = null;
        converter = new NotaryHostThankYouOptionsConverter(apiNotaryHostThankYouOptions);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPINotaryHostThankYouOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkNotaryHostThankYouOptions = createTypicalSDKNotaryHostThankYouOptions();
        com.silanis.esl.sdk.NotaryHostThankYouOptions sdkNotaryHostThankYouOptions2 = new NotaryHostThankYouOptionsConverter(sdkNotaryHostThankYouOptions).toSDKNotaryHostThankYouOptions();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkNotaryHostThankYouOptions2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkNotaryHostThankYouOptions2, is(sdkNotaryHostThankYouOptions));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiNotaryHostThankYouOptions = createTypicalAPINotaryHostThankYouOptions();
        NotaryHostThankYouOptions apiNotaryHostThankYouOptions2 = new NotaryHostThankYouOptionsConverter(apiNotaryHostThankYouOptions).toAPINotaryHostThankYouOptions();

        assertThat("Converter returned a null api object for a non null api object", apiNotaryHostThankYouOptions2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiNotaryHostThankYouOptions2, is(apiNotaryHostThankYouOptions));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiNotaryHostThankYouOptions = createTypicalAPINotaryHostThankYouOptions();
        sdkNotaryHostThankYouOptions = new NotaryHostThankYouOptionsConverter(apiNotaryHostThankYouOptions).toSDKNotaryHostThankYouOptions();

        assertThat("Converter returned a null api object for a non null api object", apiNotaryHostThankYouOptions, notNullValue());
        assertThat("'title' was not correctly set", apiNotaryHostThankYouOptions.getTitle(), is(sdkNotaryHostThankYouOptions.getTitle()));
        assertThat("'body' was not correctly set", apiNotaryHostThankYouOptions.getBody(), is(sdkNotaryHostThankYouOptions.getBody()));
        assertThat("'recipientName' was not correctly set", apiNotaryHostThankYouOptions.getRecipientName(), is(sdkNotaryHostThankYouOptions.getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiNotaryHostThankYouOptions.getRecipientEmail(), is(sdkNotaryHostThankYouOptions.getRecipientEmail()));
        assertThat("'recipientRole' was not correctly set", apiNotaryHostThankYouOptions.getRecipientRole(), is(sdkNotaryHostThankYouOptions.getRecipientRole()));
        assertThat("'notaryTag' was not correctly set", apiNotaryHostThankYouOptions.getNotaryTag(), is(sdkNotaryHostThankYouOptions.getNotaryTag()));
        assertThat("'recipientStatus' was not correctly set", apiNotaryHostThankYouOptions.getRecipientStatus(), is(sdkNotaryHostThankYouOptions.getRecipientStatus()));
        assertThat("'downloadButton' was not correctly set", apiNotaryHostThankYouOptions.getDownloadButton(), is(sdkNotaryHostThankYouOptions.getDownloadButton()));
        assertThat("'reviewDocumentsButton' was not correctly set", apiNotaryHostThankYouOptions.getReviewDocumentsButton(), is(sdkNotaryHostThankYouOptions.getReviewDocumentsButton()));

    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkNotaryHostThankYouOptions = createTypicalSDKNotaryHostThankYouOptions();
        apiNotaryHostThankYouOptions = new NotaryHostThankYouOptionsConverter(sdkNotaryHostThankYouOptions).toAPINotaryHostThankYouOptions();

        assertThat("Converter returned a null api object for a non null sdk object", apiNotaryHostThankYouOptions, is(notNullValue()));
        assertThat("'title' was not correctly set", apiNotaryHostThankYouOptions.getTitle(), is(sdkNotaryHostThankYouOptions.getTitle()));
        assertThat("'body' was not correctly set", apiNotaryHostThankYouOptions.getBody(), is(sdkNotaryHostThankYouOptions.getBody()));
        assertThat("'recipientName' was not correctly set", apiNotaryHostThankYouOptions.getRecipientName(), is(sdkNotaryHostThankYouOptions.getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiNotaryHostThankYouOptions.getRecipientEmail(), is(sdkNotaryHostThankYouOptions.getRecipientEmail()));
        assertThat("'recipientRole' was not correctly set", apiNotaryHostThankYouOptions.getRecipientRole(), is(sdkNotaryHostThankYouOptions.getRecipientRole()));
        assertThat("'notaryTag' was not correctly set", apiNotaryHostThankYouOptions.getNotaryTag(), is(sdkNotaryHostThankYouOptions.getNotaryTag()));
        assertThat("'recipientStatus' was not correctly set", apiNotaryHostThankYouOptions.getRecipientStatus(), is(sdkNotaryHostThankYouOptions.getRecipientStatus()));
        assertThat("'downloadButton' was not correctly set", apiNotaryHostThankYouOptions.getDownloadButton(), is(sdkNotaryHostThankYouOptions.getDownloadButton()));
        assertThat("'reviewDocumentsButton' was not correctly set", apiNotaryHostThankYouOptions.getReviewDocumentsButton(), is(sdkNotaryHostThankYouOptions.getReviewDocumentsButton()));

    }

    /**
     * Create an SDK NotaryHostThankYouOptions.
     *
     * @return SDK NotaryHostThankYouOptions.
     */
    private com.silanis.esl.sdk.NotaryHostThankYouOptions createTypicalSDKNotaryHostThankYouOptions() {
        return newNotaryHostThankYouOptions()
                .withTitle()
                .withBody()
                .withRecipientName()
                .withRecipientEmail()
                .withRecipientRole()
                .withNotaryTag()
                .withRecipientStatus()
                .withDownloadButton()
                .withReviewDocumentsButton()
                .build();
    }

    /**
     * Create an API NotaryHostThankYouOptions.
     *
     * @return API NotaryHostThankYouOptions.
     */
    private NotaryHostThankYouOptions createTypicalAPINotaryHostThankYouOptions() {

        NotaryHostThankYouOptions apiNotaryHostThankYouOptions = new NotaryHostThankYouOptions();

        apiNotaryHostThankYouOptions.setTitle(true);
        apiNotaryHostThankYouOptions.setBody(true);
        apiNotaryHostThankYouOptions.setRecipientName(true);
        apiNotaryHostThankYouOptions.setRecipientEmail(true);
        apiNotaryHostThankYouOptions.setRecipientRole(true);
        apiNotaryHostThankYouOptions.setNotaryTag(true);
        apiNotaryHostThankYouOptions.setRecipientStatus(true);
        apiNotaryHostThankYouOptions.setDownloadButton(true);
        apiNotaryHostThankYouOptions.setReviewDocumentsButton(true);

        return apiNotaryHostThankYouOptions;
    }
}
