package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.NotaryWelcomeOptions;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.NotaryWelcomeOptionsBuilder.newNotaryWelcomeOptions;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class NotaryWelcomeOptionsConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.NotaryWelcomeOptions sdkNotaryWelcomeOptions = null;
    private NotaryWelcomeOptions apiNotaryWelcomeOptions = null;
    private NotaryWelcomeOptionsConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkNotaryWelcomeOptions = null;
        converter = new NotaryWelcomeOptionsConverter(sdkNotaryWelcomeOptions);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPINotaryWelcomeOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiNotaryWelcomeOptions = null;
        converter = new NotaryWelcomeOptionsConverter(apiNotaryWelcomeOptions);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKNotaryWelcomeOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkNotaryWelcomeOptions = null;
        converter = new NotaryWelcomeOptionsConverter(sdkNotaryWelcomeOptions);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKNotaryWelcomeOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiNotaryWelcomeOptions = null;
        converter = new NotaryWelcomeOptionsConverter(apiNotaryWelcomeOptions);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPINotaryWelcomeOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkNotaryWelcomeOptions = createTypicalSDKNotaryWelcomeOptions();
        com.silanis.esl.sdk.NotaryWelcomeOptions sdkNotaryWelcomeOptions2 = new NotaryWelcomeOptionsConverter(sdkNotaryWelcomeOptions).toSDKNotaryWelcomeOptions();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkNotaryWelcomeOptions2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkNotaryWelcomeOptions2, is(sdkNotaryWelcomeOptions));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiNotaryWelcomeOptions = createTypicalAPINotaryWelcomeOptions();
        NotaryWelcomeOptions apiNotaryWelcomeOptions2 = new NotaryWelcomeOptionsConverter(apiNotaryWelcomeOptions).toAPINotaryWelcomeOptions();

        assertThat("Converter returned a null api object for a non null api object", apiNotaryWelcomeOptions2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiNotaryWelcomeOptions2, is(apiNotaryWelcomeOptions));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiNotaryWelcomeOptions = createTypicalAPINotaryWelcomeOptions();
        sdkNotaryWelcomeOptions = new NotaryWelcomeOptionsConverter(apiNotaryWelcomeOptions).toSDKNotaryWelcomeOptions();

        assertThat("Converter returned a null api object for a non null api object", apiNotaryWelcomeOptions, notNullValue());
        assertThat("'title' was not correctly set", apiNotaryWelcomeOptions.getTitle(), is(sdkNotaryWelcomeOptions.getTitle()));
        assertThat("'body' was not correctly set", apiNotaryWelcomeOptions.getBody(), is(sdkNotaryWelcomeOptions.getBody()));
        assertThat("'recipientName' was not correctly set", apiNotaryWelcomeOptions.getRecipientName(), is(sdkNotaryWelcomeOptions.getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiNotaryWelcomeOptions.getRecipientEmail(), is(sdkNotaryWelcomeOptions.getRecipientEmail()));
        assertThat("'recipientActionRequired' was not correctly set", apiNotaryWelcomeOptions.getRecipientActionRequired(), is(sdkNotaryWelcomeOptions.getRecipientActionRequired()));
        assertThat("'notaryTag' was not correctly set", apiNotaryWelcomeOptions.getNotaryTag(), is(sdkNotaryWelcomeOptions.getNotaryTag()));
        assertThat("'recipientRole' was not correctly set", apiNotaryWelcomeOptions.getRecipientRole(), is(sdkNotaryWelcomeOptions.getRecipientRole()));
        assertThat("'recipientStatus' was not correctly set", apiNotaryWelcomeOptions.getRecipientStatus(), is(sdkNotaryWelcomeOptions.getRecipientStatus()));

    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkNotaryWelcomeOptions = createTypicalSDKNotaryWelcomeOptions();
        apiNotaryWelcomeOptions = new NotaryWelcomeOptionsConverter(sdkNotaryWelcomeOptions).toAPINotaryWelcomeOptions();

        assertThat("Converter returned a null api object for a non null sdk object", apiNotaryWelcomeOptions, is(notNullValue()));
        assertThat("'title' was not correctly set", apiNotaryWelcomeOptions.getTitle(), is(sdkNotaryWelcomeOptions.getTitle()));
        assertThat("'body' was not correctly set", apiNotaryWelcomeOptions.getBody(), is(sdkNotaryWelcomeOptions.getBody()));
        assertThat("'recipientName' was not correctly set", apiNotaryWelcomeOptions.getRecipientName(), is(sdkNotaryWelcomeOptions.getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiNotaryWelcomeOptions.getRecipientEmail(), is(sdkNotaryWelcomeOptions.getRecipientEmail()));
        assertThat("'recipientActionRequired' was not correctly set", apiNotaryWelcomeOptions.getRecipientActionRequired(), is(sdkNotaryWelcomeOptions.getRecipientActionRequired()));
        assertThat("'notaryTag' was not correctly set", apiNotaryWelcomeOptions.getNotaryTag(), is(sdkNotaryWelcomeOptions.getNotaryTag()));
        assertThat("'recipientRole' was not correctly set", apiNotaryWelcomeOptions.getRecipientRole(), is(sdkNotaryWelcomeOptions.getRecipientRole()));
        assertThat("'recipientStatus' was not correctly set", apiNotaryWelcomeOptions.getRecipientStatus(), is(sdkNotaryWelcomeOptions.getRecipientStatus()));

    }

    /**
     * Create an SDK NotaryWelcomeOptions.
     *
     * @return SDK NotaryWelcomeOptions.
     */
    private com.silanis.esl.sdk.NotaryWelcomeOptions createTypicalSDKNotaryWelcomeOptions() {
        return newNotaryWelcomeOptions()
                .withTitle()
                .withBody()
                .withRecipientName()
                .withRecipientEmail()
                .withRecipientActionRequired()
                .withNotaryTag()
                .withRecipientRole()
                .withRecipientStatus()
                .build();
    }

    /**
     * Create an API NotaryWelcomeOptions.
     *
     * @return API NotaryWelcomeOptions.
     */
    private NotaryWelcomeOptions createTypicalAPINotaryWelcomeOptions() {

        NotaryWelcomeOptions apiNotaryWelcomeOptions = new NotaryWelcomeOptions();

        apiNotaryWelcomeOptions.setTitle(true);
        apiNotaryWelcomeOptions.setBody(true);
        apiNotaryWelcomeOptions.setRecipientName(true);
        apiNotaryWelcomeOptions.setRecipientEmail(true);
        apiNotaryWelcomeOptions.setRecipientActionRequired(true);
        apiNotaryWelcomeOptions.setNotaryTag(true);
        apiNotaryWelcomeOptions.setRecipientRole(true);
        apiNotaryWelcomeOptions.setRecipientStatus(true);


        return apiNotaryWelcomeOptions;
    }
}
