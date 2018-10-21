package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Sender;
import com.silanis.esl.sdk.AccountMember;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class AccountMemberConverterTest implements ConverterTest{
    @Test
    public void convertNullSDKToAPI() {
    }

    @Test
    public void convertNullAPIToSDK() {
    }

    @Test
    public void convertNullSDKToSDK() {
    }

    @Test
    public void convertNullAPIToAPI() {
    }

    @Test
    public void convertSDKToSDK() {
        AccountMember member = createTypicalSDKAccountMember();
        AccountMemberConverter converter = new AccountMemberConverter( member );
        AccountMember result = converter.toSDKAccountMember();
        assertThat( "Converter returned a null sdk object for a non null sdk object", result, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", result, is( equalTo( member ) ) );
    }

    @Test
    public void convertAPIToAPI() {
        com.silanis.esl.api.model.Sender sender = createTypicalAPISender();
        AccountMemberConverter converter = new AccountMemberConverter( sender );
        com.silanis.esl.api.model.Sender result = converter.toAPISender();
        assertThat( "Converter returned a null api object for a non null api object", result, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", result, is( equalTo( sender ) ) );
    }

    @Test
    public void convertAPIToSDK() {
        com.silanis.esl.api.model.Sender sender = createTypicalAPISender();
        AccountMemberConverter converter = new AccountMemberConverter( sender );
        AccountMember result = converter.toSDKAccountMember();
        assertThat( "Converter returned a null sdk object for a non null api object", result, is( notNullValue() ) );
        assertThat( "company was not correctly set", result.getCompany(), is( equalTo( sender.getCompany() ) ) );
        assertThat( "language was not correctly set", result.getLanguage(), is( equalTo( sender.getLanguage() ) ) );
        assertThat( "email was not correctly set", result.getEmail(), is( equalTo( sender.getEmail() ) ) );
        assertThat( "phone number was not correctly set", result.getPhoneNumber(), is( equalTo( sender.getPhone() ) ) );
        assertThat( "first name was not correctly set", result.getFirstName(), is( equalTo( sender.getFirstName() ) ) );
        assertThat( "last name was not correctly set", result.getLastName(), is( equalTo( sender.getLastName() ) ) );
        assertThat( "was not correctly set", result.getTitle(), is( equalTo( sender.getTitle() ) ) );
        assertThat( "status was not set", result.getStatus().isPresent(), is(true) );
        assertThat( "timezone id was not correctly set", result.getTimezoneId(), is( equalTo( sender.getTimezoneId() ) ) );
        assertThat( "status was not correclty set", result.getStatus().get(), is (equalTo( com.silanis.esl.sdk.SenderStatus.ACTIVE )));
    }

    @Test
    public void convertSDKToAPI() {
        AccountMember member = createTypicalSDKAccountMember();
        AccountMemberConverter converter = new AccountMemberConverter( member );
        com.silanis.esl.api.model.Sender result = converter.toAPISender();
        assertThat( "Converter returned a null api object for a non null sdk object", result, is( notNullValue() ) );
        assertThat( "company was not correctly set", result.getCompany(), is( equalTo( member.getCompany() ) ) );
        assertThat( "language was not correctly set", result.getLanguage(), is( equalTo( member.getLanguage() ) ) );
        assertThat( "email was not correctly set", result.getEmail(), is( equalTo( member.getEmail() ) ) );
        assertThat( "phone number was not correctly set", result.getPhone(), is( equalTo( member.getPhoneNumber() ) ) );
        assertThat( "first name was not correctly set", result.getFirstName(), is( equalTo( member.getFirstName() ) ) );
        assertThat( "last name was not correctly set", result.getLastName(), is( equalTo( member.getLastName() ) ) );
        assertThat( "title was not correctly set", result.getTitle(), is( equalTo( member.getTitle() ) ) );
        assertThat( "timezone id was not correctly set", result.getTimezoneId(), is( equalTo( member.getTimezoneId() ) ) );
        assertThat( "status was not correctly set", result.getStatus(), is( equalTo("ACTIVE")));
    }

    private AccountMember createTypicalSDKAccountMember() {
        AccountMember result = new AccountMember();
        result.setCompany( "company" );
        result.setLanguage( "language" );
        result.setEmail( "email" );
        result.setPhoneNumber( "phone" );
        result.setFirstName( "first" );
        result.setLastName( "last" );
        result.setTitle( "title" );
        result.setTimezoneId( "Canada/Mountain" );
        result.setStatus(com.silanis.esl.sdk.SenderStatus.ACTIVE);
        return result;
    }

    private Sender createTypicalAPISender() {
        com.silanis.esl.api.model.Sender result = new com.silanis.esl.api.model.Sender();
        result.setCompany( "company" );
        result.setLanguage( "language" );
        result.setEmail( "email" );
        result.setPhone( "phone" );
        result.setFirstName( "first" );
        result.setLastName( "last" );
        result.setTitle( "title" );
        result.setTimezoneId( "Canada/Mountain" );
        result.setStatus("ACTIVE");
        return result;
    }
}
