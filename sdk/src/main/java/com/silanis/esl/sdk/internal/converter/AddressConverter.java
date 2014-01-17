package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.Address;

public class AddressConverter {

    private Optional<Address> optionalApiAddress;
    private Optional<com.silanis.esl.sdk.Address> optionalSdkAddress;

    public AddressConverter( Address apiAddress ) {
        this.optionalApiAddress = Optional.of( apiAddress );
        this.optionalSdkAddress = Optional.absent();
    }

    public AddressConverter( com.silanis.esl.sdk.Address sdkAddress ) {
        optionalApiAddress = Optional.absent();
        optionalSdkAddress = Optional.of( sdkAddress );
    }

    public Address toAPIAddress() {
        if ( optionalSdkAddress.isPresent() ) {
            Address result = new Address();

            result.setAddress1( optionalSdkAddress.get().getAddress1() );
            result.setAddress2( optionalSdkAddress.get().getAddress2() );
            result.setCity( optionalSdkAddress.get().getCity() );
            result.setCountry( optionalSdkAddress.get().getCountry() );
            result.setState( optionalSdkAddress.get().getState() );
            result.setZipcode( optionalSdkAddress.get().getZipCode() );

            return result;
        } else {
            return optionalApiAddress.get();
        }
    }

    public com.silanis.esl.sdk.Address toSDKAddress() {
        if ( optionalApiAddress.isPresent() ) {
            com.silanis.esl.sdk.Address result = new com.silanis.esl.sdk.Address();

            result.setAddress1( optionalApiAddress.get().getAddress1() );
            result.setAddress2( optionalApiAddress.get().getAddress2() );
            result.setState( optionalApiAddress.get().getState() );
            result.setCountry( optionalApiAddress.get().getCountry() );
            result.setCity( optionalApiAddress.get().getCity() );
            result.setZipCode( optionalApiAddress.get().getZipcode() );

            return result;
        } else {
            return optionalSdkAddress.get();
        }
    }
}
