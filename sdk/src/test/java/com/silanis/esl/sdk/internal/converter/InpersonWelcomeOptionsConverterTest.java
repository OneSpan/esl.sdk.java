package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.InpersonWelcomeOptions;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.InpersonWelcomeOptionsBuilder.newInpersonWelcomeOptions;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class InpersonWelcomeOptionsConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.InpersonWelcomeOptions sdkInpersonWelcomeOptions = null;
    private InpersonWelcomeOptions apiInpersonWelcomeOptions = null;
    private InpersonWelcomeOptionsConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkInpersonWelcomeOptions = null;
        converter = new InpersonWelcomeOptionsConverter(sdkInpersonWelcomeOptions);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIInpersonWelcomeOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiInpersonWelcomeOptions = null;
        converter = new InpersonWelcomeOptionsConverter(apiInpersonWelcomeOptions);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKInpersonWelcomeOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkInpersonWelcomeOptions = null;
        converter = new InpersonWelcomeOptionsConverter(sdkInpersonWelcomeOptions);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKInpersonWelcomeOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiInpersonWelcomeOptions = null;
        converter = new InpersonWelcomeOptionsConverter(apiInpersonWelcomeOptions);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIInpersonWelcomeOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkInpersonWelcomeOptions = createTypicalSDKInpersonWelcomeOptions();
        com.silanis.esl.sdk.InpersonWelcomeOptions sdkInpersonWelcomeOptions2 = new InpersonWelcomeOptionsConverter(sdkInpersonWelcomeOptions).toSDKInpersonWelcomeOptions();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkInpersonWelcomeOptions2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkInpersonWelcomeOptions2, is(sdkInpersonWelcomeOptions));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiInpersonWelcomeOptions = createTypicalAPIInpersonWelcomeOptions();
        InpersonWelcomeOptions apiInpersonWelcomeOptions2 = new InpersonWelcomeOptionsConverter(apiInpersonWelcomeOptions).toAPIInpersonWelcomeOptions();

        assertThat("Converter returned a null api object for a non null api object", apiInpersonWelcomeOptions2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiInpersonWelcomeOptions2, is(apiInpersonWelcomeOptions));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiInpersonWelcomeOptions = createTypicalAPIInpersonWelcomeOptions();
        sdkInpersonWelcomeOptions = new InpersonWelcomeOptionsConverter(apiInpersonWelcomeOptions).toSDKInpersonWelcomeOptions();

        assertThat("Converter returned a null api object for a non null api object", apiInpersonWelcomeOptions, notNullValue());
        assertThat("'title' was not correctly set", apiInpersonWelcomeOptions.getTitle(), is(sdkInpersonWelcomeOptions.getTitle()));
        assertThat("'body' was not correctly set", apiInpersonWelcomeOptions.getBody(), is(sdkInpersonWelcomeOptions.getBody()));
        assertThat("'recipientName' was not correctly set", apiInpersonWelcomeOptions.getRecipientName(), is(sdkInpersonWelcomeOptions.getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiInpersonWelcomeOptions.getRecipientEmail(), is(sdkInpersonWelcomeOptions.getRecipientEmail()));
        assertThat("'recipientActionRequired' was not correctly set", apiInpersonWelcomeOptions.getRecipientActionRequired(), is(sdkInpersonWelcomeOptions.getRecipientActionRequired()));
        assertThat("'recipientRole' was not correctly set", apiInpersonWelcomeOptions.getRecipientRole(), is(sdkInpersonWelcomeOptions.getRecipientRole()));
        assertThat("'recipientStatus' was not correctly set", apiInpersonWelcomeOptions.getRecipientStatus(), is(sdkInpersonWelcomeOptions.getRecipientStatus()));

    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkInpersonWelcomeOptions = createTypicalSDKInpersonWelcomeOptions();
        apiInpersonWelcomeOptions = new InpersonWelcomeOptionsConverter(sdkInpersonWelcomeOptions).toAPIInpersonWelcomeOptions();

        assertThat("Converter returned a null api object for a non null sdk object", apiInpersonWelcomeOptions, is(notNullValue()));
        assertThat("'title' was not correctly set", apiInpersonWelcomeOptions.getTitle(), is(sdkInpersonWelcomeOptions.getTitle()));
        assertThat("'body' was not correctly set", apiInpersonWelcomeOptions.getBody(), is(sdkInpersonWelcomeOptions.getBody()));
        assertThat("'recipientName' was not correctly set", apiInpersonWelcomeOptions.getRecipientName(), is(sdkInpersonWelcomeOptions.getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiInpersonWelcomeOptions.getRecipientEmail(), is(sdkInpersonWelcomeOptions.getRecipientEmail()));
        assertThat("'recipientActionRequired' was not correctly set", apiInpersonWelcomeOptions.getRecipientActionRequired(), is(sdkInpersonWelcomeOptions.getRecipientActionRequired()));
        assertThat("'recipientRole' was not correctly set", apiInpersonWelcomeOptions.getRecipientRole(), is(sdkInpersonWelcomeOptions.getRecipientRole()));
        assertThat("'recipientStatus' was not correctly set", apiInpersonWelcomeOptions.getRecipientStatus(), is(sdkInpersonWelcomeOptions.getRecipientStatus()));

    }

    /**
     * Create an SDK InpersonWelcomeOptions.
     *
     * @return SDK InpersonWelcomeOptions.
     */
    private com.silanis.esl.sdk.InpersonWelcomeOptions createTypicalSDKInpersonWelcomeOptions() {
        return newInpersonWelcomeOptions()
                .withTitle()
                .withBody()
                .withRecipientName()
                .withRecipientEmail()
                .withRecipientActionRequired()
                .withRecipientRole()
                .withRecipientStatus()
                .build();
    }

    /**
     * Create an API InpersonWelcomeOptions.
     *
     * @return API InpersonWelcomeOptions.
     */
    private InpersonWelcomeOptions createTypicalAPIInpersonWelcomeOptions() {

        InpersonWelcomeOptions apiInpersonWelcomeOptions = new InpersonWelcomeOptions();

        apiInpersonWelcomeOptions.setTitle(true);
        apiInpersonWelcomeOptions.setBody(true);
        apiInpersonWelcomeOptions.setRecipientName(true);
        apiInpersonWelcomeOptions.setRecipientEmail(true);
        apiInpersonWelcomeOptions.setRecipientActionRequired(true);
        apiInpersonWelcomeOptions.setRecipientRole(true);
        apiInpersonWelcomeOptions.setRecipientStatus(true);


        return apiInpersonWelcomeOptions;
    }
}
