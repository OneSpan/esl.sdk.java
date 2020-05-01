package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.Company;

public class CompanyConverter {

    private Optional<Company> apiCompanyOptional;
    private Optional<com.silanis.esl.sdk.Company> sdkCompanyOptional;

    public CompanyConverter(Company apiCompany ) {
        apiCompanyOptional = Optional.fromNullable( apiCompany );
        sdkCompanyOptional = Optional.absent();
    }

    public CompanyConverter(com.silanis.esl.sdk.Company sdkCompany ) {
        apiCompanyOptional = Optional.absent();
        sdkCompanyOptional = Optional.fromNullable( sdkCompany );
    }

    public Company toAPICompany() {
        if ( sdkCompanyOptional.isPresent() ) {
            Company apiCompany = new Company();
            com.silanis.esl.sdk.Company sdkCompany = sdkCompanyOptional.get();
            if (sdkCompany.getAddress() != null){
                apiCompany.setAddress(new AddressConverter(sdkCompany.getAddress()).toAPIAddress());
            }
            apiCompany.setId(sdkCompany.getId());
            apiCompany.setName(sdkCompany.getName());
            apiCompany.setData(sdkCompany.getData());

            return apiCompany;
        } else {
            return apiCompanyOptional.orNull();
        }
    }

    public com.silanis.esl.sdk.Company toSDKCompany() {
        if ( apiCompanyOptional.isPresent() ) {
            com.silanis.esl.sdk.Company sdkCompany = new com.silanis.esl.sdk.Company();
            Company apiCompany = apiCompanyOptional.get();
            if (apiCompany.getAddress() != null) {
                sdkCompany.setAddress(new AddressConverter(apiCompany.getAddress()).toSDKAddress());
            }
            sdkCompany.setId(apiCompany.getId());
            sdkCompany.setName(apiCompany.getName());
            sdkCompany.setData(apiCompany.getData());

            return sdkCompany;
        } else {
            return sdkCompanyOptional.orNull();
        }
    }
}
