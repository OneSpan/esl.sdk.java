package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Error;
import com.silanis.esl.sdk.ServerError;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by chi-wing on 5/28/14.
 */
public class ErrorConverterTest implements ConverterTest {

    private ServerError sdkError1 = null;
    private ServerError sdkError2 = null;
    private com.silanis.esl.api.model.Error apiError1 = null;
    private com.silanis.esl.api.model.Error apiError2 = null;
    private ErrorConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkError1 = null;
        converter = new ErrorConverter(sdkError1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIError(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiError1 = null;
        converter = new ErrorConverter(apiError1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKError(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkError1 = null;
        converter = new ErrorConverter(sdkError1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKError(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiError1 = null;
        converter = new ErrorConverter(apiError1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIError(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkError1 = createTypicalServerError();
        sdkError2 = new ErrorConverter(sdkError1).toSDKError();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkError2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkError2, is(equalTo(sdkError1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiError1 = createTypicalAPIError();
        apiError2 = new ErrorConverter(apiError1).toAPIError();

        assertThat("Converter returned a null api object for a non null api object", apiError2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiError2, is(equalTo(apiError1)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiError1 = createTypicalAPIError();
        sdkError1 = new ErrorConverter(apiError1).toSDKError();

        assertThat("Converter returned a null sdk object for a non null api object", sdkError1, is(notNullValue()));
        assertThat("Name was not correctly set", apiError1.getName(), is(equalTo(sdkError1.getName())));
        assertThat("Code was not correctly set", apiError1.getCode(), is(sdkError1.getCode()));
        assertThat("Message was not correctly set", apiError1.getMessage(), is(sdkError1.getMessage()));
        assertThat("Message key was not correctly set", apiError1.getMessageKey(), is(sdkError1.getMessageKey()));
        assertThat("Technical was not correctly set", apiError1.getTechnical(), is(sdkError1.getTechnical()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkError1 = createTypicalServerError();
        apiError1 = new ErrorConverter(sdkError1).toAPIError();

        assertThat("Converter returned a null api object for a non null sdk object", sdkError1, is(notNullValue()));
        assertThat("Name was not correctly set", apiError1.getName(), is(equalTo(sdkError1.getName())));
        assertThat("Code was not correctly set", apiError1.getCode(), is(sdkError1.getCode()));
        assertThat("Message was not correctly set", apiError1.getMessage(), is(sdkError1.getMessage()));
        assertThat("Message key was not correctly set", apiError1.getMessageKey(), is(sdkError1.getMessageKey()));
        assertThat("Technical was not correctly set", apiError1.getTechnical(), is(sdkError1.getTechnical()));
    }

    private ServerError createTypicalServerError() {
        ServerError sdkError = new ServerError();

        sdkError.setCode(400);
        sdkError.setTechnical("Technical");
        sdkError.setName("Error name");
        sdkError.setMessageKey("Message key");
        sdkError.setMessage("Message");

        return sdkError;
    }


    private com.silanis.esl.api.model.Error createTypicalAPIError() {
        com.silanis.esl.api.model.Error apiError = new Error();

        apiError.setCode(400);
        apiError.setTechnical("Technical");
        apiError.setName("Error name");
        apiError.setMessageKey("Message key");
        apiError.setMessage("Message");

        return apiError;
    }
}
