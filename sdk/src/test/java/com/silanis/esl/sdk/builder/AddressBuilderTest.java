package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Address;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class AddressBuilderTest {
    @Test
    public void buildWithSpecifiedValues() {
        AddressBuilder builder = AddressBuilder.newAddress();
        builder.withAddress1( "address1" );
        builder.withAddress2( "address2" );
        builder.withCity( "city" );
        builder.withCountry( "country" );
        builder.withState( "state" );
        builder.withZipCode( "zipCode" );

        Address result = builder.build();
        assertThat( "build returned a null object", result, is( notNullValue() ) );
        assertThat( "address1 was not set correctly", result.getAddress1(), is( equalTo( "address1" ) ) );
        assertThat( "address2 was not set correctly", result.getAddress2(), is( equalTo( "address2" ) ) );
        assertThat( "city was not set correctly", result.getCity(), is( equalTo( "city" ) ) );
        assertThat( "country was not set correctly", result.getCountry(), is( equalTo( "country" ) ) );
        assertThat( "state was not set correctly", result.getState(), is( equalTo( "state" ) ) );
        assertThat( "zipCode was not set correctly", result.getZipCode(), is( equalTo( "zipCode" ) ) );
    }
}
