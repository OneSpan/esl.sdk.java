package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Address;

public class AddressBuilder {
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String state;
    private String zipCode;

    private AddressBuilder() {}

    public AddressBuilder withAddress1( String address1 ) {
        this.address1 = address1;
        return this;
    }

    public AddressBuilder withAddress2( String address2 ) {
        this.address2 = address2;
        return this;
    }

    public AddressBuilder withCity( String city ) {
        this.city = city;
        return this;
    }

    public AddressBuilder withCountry( String country ) {
        this.country = country;
        return this;
    }

    public AddressBuilder withState( String state ) {
        this.state = state;
        return this;
    }

    public AddressBuilder withZipCode( String zipCode ) {
        this.zipCode = zipCode;
        return this;
    }

    public Address build() {
        Address result = new Address();
        result.setAddress1( address1 );
        result.setAddress2( address2 );
        result.setCity( city );
        result.setCountry( country );
        result.setState( state );
        result.setZipCode( zipCode );
        return result;
    }

    public static AddressBuilder newAddress() {
        return new AddressBuilder();
    }
}
