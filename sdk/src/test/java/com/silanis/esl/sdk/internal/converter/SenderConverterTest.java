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
    private com.silanis.esl.sdk.SenderInfo sdkSender1 = null;
    private com.silanis.esl.sdk.SenderInfo sdkSender2 = null;
    private com.silanis.esl.api.model.Sender apiSender1 = null;
    private com.silanis.esl.api.model.Sender apiSender2 = null;
    private SenderConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkSender1 = null;
        converter = new SenderConverter(sdkSender1);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPISender(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiSender1 = null;
        converter = new SenderConverter(apiSender1);
        assertThat( "Converter didn't return a null sdk object for a null api object", converter.toSDKSender(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkSender1 = null;
        converter = new SenderConverter(sdkSender1);
        assertThat( "Converter didn't return a null sdk object for a null sdk object", converter.toSDKSender(), is( nullValue() ) );
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

        sdkSender1 = createTypicalSDKSender();
        sdkSender2 = new SenderConverter(sdkSender1).toSDKSender();
        assertThat( "Converter returned a null sdk object for a non null sdk object", sdkSender2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", sdkSender2, is( equalTo( sdkSender1 ) ) );
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
        sdkSender1 = new SenderConverter(apiSender1).toSDKSender();

        assertThat("Converter returned a null api object for a non null sdk object", apiSender1, is( notNullValue() ) );
        assertThat("first name was not properly set or retrieved", apiSender1.getFirstName(), Matchers.is(Matchers.equalTo(sdkSender1.getFirstName())));
        assertThat( "last name was not properly set or retrieved", apiSender1.getLastName(), Matchers.is(Matchers.equalTo(sdkSender1.getLastName())));
        assertThat( "company was not properly set or retrieved", apiSender1.getCompany(), Matchers.is(Matchers.equalTo(sdkSender1.getCompany())));
        assertThat( "title was not properly set or retrieved", apiSender1.getTitle(), Matchers.is(Matchers.equalTo(sdkSender1.getTitle())));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkSender1 = createTypicalSDKSender();
        apiSender1 = new SenderConverter(sdkSender1).toAPISender();

        assertThat("Converter returned a null api object for a non null sdk object", apiSender1, is( notNullValue() ) );
        assertThat("first name was not properly set or retrieved", apiSender1.getFirstName(), Matchers.is(Matchers.equalTo(sdkSender1.getFirstName())));
        assertThat( "last name was not properly set or retrieved", apiSender1.getLastName(), Matchers.is(Matchers.equalTo(sdkSender1.getLastName())));
        assertThat( "company was not properly set or retrieved", apiSender1.getCompany(), Matchers.is(Matchers.equalTo(sdkSender1.getCompany())));
        assertThat( "title was not properly set or retrieved", apiSender1.getTitle(), Matchers.is(Matchers.equalTo(sdkSender1.getTitle())));
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
