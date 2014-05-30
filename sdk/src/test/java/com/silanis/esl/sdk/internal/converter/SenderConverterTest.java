package com.silanis.esl.sdk.internal.converter;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * User: jessica
 * Date: 03/12/13
 * Time: 9:39 AM
 * 
 * Test SenderConverter.
 */
public class SenderConverterTest implements ConverterTest{
    private com.silanis.esl.sdk.SenderInfo sdkSenderInfo1 = null;
    private com.silanis.esl.sdk.SenderInfo sdkSenderInfo2 = null;
    private com.silanis.esl.sdk.Sender sdkSender = null;
    private com.silanis.esl.api.model.Sender apiSender1 = null;
    private com.silanis.esl.api.model.Sender apiSender2 = null;
    private SenderConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkSenderInfo1 = null;
        converter = new SenderConverter(sdkSenderInfo1);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPISender(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiSender1 = null;
        converter = new SenderConverter(apiSender1);
        assertThat( "Converter didn't return a null sdk object for a null api object", converter.toSDKSenderInfo(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkSenderInfo1 = null;
        converter = new SenderConverter(sdkSenderInfo1);
        assertThat( "Converter didn't return a null sdk object for a null sdk object", converter.toSDKSenderInfo(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiSender1 = null;
        converter = new SenderConverter(apiSender1);
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPISender(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkSenderInfo1 = createTypicalSDKSender();
        sdkSenderInfo2 = new SenderConverter(sdkSenderInfo1).toSDKSenderInfo();
        assertThat( "Converter returned a null sdk object for a non null sdk object", sdkSenderInfo2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", sdkSenderInfo2, is( equalTo(sdkSenderInfo1) ) );
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiSender1 = createTypicalAPISender();
        apiSender2 = new SenderConverter(apiSender1).toAPISender();

        assertThat( "Converter returned a null api object for a non null api object", apiSender2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", apiSender2, is( equalTo( apiSender1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiSender1 = createTypicalAPISender();
        sdkSenderInfo1 = new SenderConverter(apiSender1).toSDKSenderInfo();

        assertThat("Converter returned a null api object for a non null sdk object", apiSender1, is(notNullValue()));
        assertThat("first name was not properly set or retrieved", apiSender1.getFirstName(), Matchers.is(Matchers.equalTo(sdkSenderInfo1.getFirstName())));
        assertThat( "last name was not properly set or retrieved", apiSender1.getLastName(), Matchers.is(Matchers.equalTo(sdkSenderInfo1.getLastName())));
        assertThat( "company was not properly set or retrieved", apiSender1.getCompany(), Matchers.is(Matchers.equalTo(sdkSenderInfo1.getCompany())));
        assertThat( "title was not properly set or retrieved", apiSender1.getTitle(), Matchers.is(Matchers.equalTo(sdkSenderInfo1.getTitle())));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkSenderInfo1 = createTypicalSDKSender();
        apiSender1 = new SenderConverter(sdkSenderInfo1).toAPISender();

        assertThat("Converter returned a null api object for a non null sdk object", apiSender1, is( notNullValue() ) );
        assertThat("first name was not properly set or retrieved", apiSender1.getFirstName(), Matchers.is(Matchers.equalTo(sdkSenderInfo1.getFirstName())));
        assertThat( "last name was not properly set or retrieved", apiSender1.getLastName(), Matchers.is(Matchers.equalTo(sdkSenderInfo1.getLastName())));
        assertThat( "company was not properly set or retrieved", apiSender1.getCompany(), Matchers.is(Matchers.equalTo(sdkSenderInfo1.getCompany())));
        assertThat( "title was not properly set or retrieved", apiSender1.getTitle(), Matchers.is(Matchers.equalTo(sdkSenderInfo1.getTitle())));
    }

    @Test
    public void convertAPIToSDKSender() {
        apiSender1 = createTypicalAPISender();
        sdkSender = new SenderConverter(apiSender1).toSDKSender();

        assertThat("Sender status is incorrect.", sdkSender.getStatus().toString(), is(apiSender1.getStatus().toString()));
        assertThat("Sender last name is incorrect.", sdkSender.getLastName(), is(apiSender1.getLastName()));
        assertThat("Sender first name is incorrect.", sdkSender.getFirstName(), is(apiSender1.getFirstName()));
        assertThat("Sender company is incorrect.", sdkSender.getCompany(), is(apiSender1.getCompany()));
        assertThat("Sender created date is incorrect.", sdkSender.getCreated(), is(apiSender1.getCreated()));
        assertThat("Sender email is incorrect.", sdkSender.getEmail(), is(apiSender1.getEmail()));
        assertThat("Sender language is incorrect.", sdkSender.getLanguage(), is(apiSender1.getLanguage()));
        assertThat("Sender phone number is incorrect.", sdkSender.getPhone(), is(apiSender1.getPhone()));
        assertThat("Sender name is incorrect.", sdkSender.getName(), is(apiSender1.getName()));
        assertThat("Sender title is incorrect.", sdkSender.getTitle(), is(apiSender1.getTitle()));
        assertThat("Sender type is incorrect.", sdkSender.getType().toString(), is(apiSender1.getType().toString()));
        assertThat("Sender updated date is incorrect.", sdkSender.getUpdated(), is(apiSender1.getUpdated()));
        assertThat("Sender signer type is incorrect.", sdkSender.getSignerType(), is(apiSender1.getSignerType()));
        assertThat("Sender id is incorrect.", sdkSender.getId(), is(apiSender1.getId()));
    }

    /**
     * Create an SDK Sender.
     *
     * @return SDK Sender.
     */
    private com.silanis.esl.sdk.SenderInfo createTypicalSDKSender() {

        com.silanis.esl.sdk.SenderInfo sdkSender = new com.silanis.esl.sdk.SenderInfo();
        sdkSender.setTitle( "title" );
        sdkSender.setCompany( "company" );
        sdkSender.setFirstName( "firstName" );
        sdkSender.setLastName( "lastName" );

        return sdkSender;
    }

    /**
     * Create an API Sender.
     *
     * @return API Sender.
     */
    private com.silanis.esl.api.model.Sender createTypicalAPISender() {
        com.silanis.esl.api.model.Sender apiSender = new com.silanis.esl.api.model.Sender();

        apiSender.setTitle("sender title");
        apiSender.setCompany("Sender company");
        apiSender.setFirstName("Sender first name");
        apiSender.setLastName("Sender last name");

        return apiSender;
    }    
}
