package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Address;

/**
 * Helper class to help define user addresses.
 *
 */
public class AddressBuilder {
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String state;
    private String zipCode;

    private AddressBuilder() {}

    /**
     * Set user's address
     * @param address1 Civic number and Street Name ( max 255, any characters )
     * @return This
     */
    public AddressBuilder withAddress1( String address1 ) {
        this.address1 = address1;
        return this;
    }

    /**
     * Set user's address
     * @param address2 Apt # / Suite # / etc (max 255, any characters )
     * @return This
     */
    public AddressBuilder withAddress2( String address2 ) {
        this.address2 = address2;
        return this;
    }

    /**
     * Set the address' city
     * @param city (max 255, any characters)
     * @return This
     */
    public AddressBuilder withCity( String city ) {
        this.city = city;
        return this;
    }

    /**
     * Set the address' country
     *
     * @param country (max 255, any characters)
     * @return This
     */
    public AddressBuilder withCountry( String country ) {
        this.country = country;
        return this;
    }

    /**
     * Set the address' state
     * @param state (max 32, any characters)
     * @return This
     */
    public AddressBuilder withState( String state ) {
        this.state = state;
        return this;
    }

    /**
     * Set the address's zip code / postal code
     * @param zipCode (max 32, any characters)
     * @return This
     */
    public AddressBuilder withZipCode( String zipCode ) {
        this.zipCode = zipCode;
        return this;
    }

    /**
     * Builds the actual Address with the specified values
     *
     * @return the Address object
     */
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

    /**
     * Creates a new Address object
     * @return This
     */
    public static AddressBuilder newAddress() {
        return new AddressBuilder();
    }
}
