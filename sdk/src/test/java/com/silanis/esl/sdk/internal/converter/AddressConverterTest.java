package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.Address;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class AddressConverterTest implements ConverterTest {
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
        Address address = createTypicalSDKAddress();
        AddressConverter converter = new AddressConverter( address );
        Address result = converter.toSDKAddress();
        assertThat( "Converter returned a null sdk object for a non null sdk object", result, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", result, is( equalTo( address ) ) );
    }

    @Test
    public void convertAPIToAPI() {
        com.silanis.esl.api.model.Address address = createTypicalAPIAddress();
        AddressConverter converter = new AddressConverter( address );
        com.silanis.esl.api.model.Address result = converter.toAPIAddress();
        assertThat( "Converter returned a null api object for a non null api object", result, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", result, is( equalTo( address ) ) );
    }

    @Test
    public void convertAPIToSDK() {
        com.silanis.esl.api.model.Address address = createTypicalAPIAddress();
        AddressConverter converter = new AddressConverter( address );
        Address result = converter.toSDKAddress();
        assertThat( "Converter returned a null sdk object for a non null api object", result, is( notNullValue() ) );
        assertThat( "zipCode was not set correctly", result.getZipCode(), is( equalTo( address.getZipcode() ) ) );
        assertThat( "city was not set correctly", result.getCity(), is( equalTo( address.getCity() ) ) );
        assertThat( "country was not set correctly", result.getCountry(), is( equalTo( address.getCountry() ) ) );
        assertThat( "state was not set correctly", result.getState(), is( equalTo( address.getState() ) ) );
        assertThat( "address1 was not set correctly", result.getAddress1(), is( equalTo( address.getAddress1() ) ) );
        assertThat( "address2 was not set correctly", result.getAddress2(), is( equalTo( address.getAddress2() ) ) );
    }

    @Test
    public void convertSDKToAPI() {
        Address address = createTypicalSDKAddress();
        AddressConverter converter = new AddressConverter( address );
        com.silanis.esl.api.model.Address result = converter.toAPIAddress();
        assertThat( "Converter returned a null api object for a non null sdk object", result, is( notNullValue() ) );
        assertThat( "zipCode was not set correctly", result.getZipcode(), is( equalTo( address.getZipCode() ) ) );
        assertThat( "city was not set correctly", result.getCity(), is( equalTo( address.getCity() ) ) );
        assertThat( "country was not set correctly", result.getCountry(), is( equalTo( address.getCountry() ) ) );
        assertThat( "state was not set correctly", result.getState(), is( equalTo( address.getState() ) ) );
        assertThat( "address1 was not set correctly", result.getAddress1(), is( equalTo( address.getAddress1() ) ) );
        assertThat( "address2 was not set correctly", result.getAddress2(), is( equalTo( address.getAddress2() ) ) );
    }

    private Address createTypicalSDKAddress() {
        Address result = new Address();
        result.setZipCode( "zipCode" );
        result.setCity( "city" );
        result.setCountry( "country" );
        result.setState( "state" );
        result.setAddress1( "address1" );
        result.setAddress2( "address2" );
        return result;
    }

    private com.silanis.esl.api.model.Address createTypicalAPIAddress() {
        com.silanis.esl.api.model.Address result = new com.silanis.esl.api.model.Address();
        result.setZipcode( "zipCode" );
        result.setState( "state" );
        result.setCountry( "country" );
        result.setCity( "city" );
        result.setAddress1( "address1" );
        result.setAddress2( "address2" );
        return result;
    }
}
