package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Address;

public class AddressConverter {

    private Address apiAddress = null;
    private com.silanis.esl.sdk.Address sdkAddress = null;

    public AddressConverter( Address apiAddress ) {
        this.apiAddress = apiAddress;
    }

    public AddressConverter( com.silanis.esl.sdk.Address sdkAddress ) {
        this.sdkAddress = sdkAddress;
    }

    public Address toAPIAddress() {
        if ( sdkAddress != null ) {
            Address result = new Address();

            result.setAddress1( sdkAddress.getAddress1() );
            result.setAddress2( sdkAddress.getAddress2() );
            result.setCity( sdkAddress.getCity() );
            result.setCountry( sdkAddress.getCountry() );
            result.setState( sdkAddress.getState() );
            result.setZipcode( sdkAddress.getZipCode() );

            return result;
        } else {
            return apiAddress;
        }
    }

    public com.silanis.esl.sdk.Address toSDKAddress() {
        if ( apiAddress != null ) {
            com.silanis.esl.sdk.Address result = new com.silanis.esl.sdk.Address();

            result.setAddress1( apiAddress.getAddress1() );
            result.setAddress2( apiAddress.getAddress2() );
            result.setState( apiAddress.getState() );
            result.setCountry( apiAddress.getCountry() );
            result.setCity( apiAddress.getCity() );
            result.setZipCode( apiAddress.getZipcode() );

            return result;
        } else {
            return sdkAddress;
        }
    }
}
