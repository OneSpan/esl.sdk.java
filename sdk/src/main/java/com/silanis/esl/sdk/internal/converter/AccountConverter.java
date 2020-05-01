package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.Account;
import com.silanis.esl.api.model.CustomField;
import com.silanis.esl.api.model.License;

public class AccountConverter {

    private Optional<Account> apiAccountOptional;
    private Optional<com.silanis.esl.sdk.Account> sdkAccountOptional;

    public AccountConverter(Account apiAccount ) {
        apiAccountOptional = Optional.fromNullable( apiAccount );
        sdkAccountOptional = Optional.absent();
    }

    public AccountConverter(com.silanis.esl.sdk.Account sdkAccount ) {
        apiAccountOptional = Optional.absent();
        sdkAccountOptional = Optional.fromNullable( sdkAccount );
    }

    public Account toAPIAccount() {
        if ( sdkAccountOptional.isPresent() ) {
            Account apiAccount = new Account();
            com.silanis.esl.sdk.Account sdkAccount = sdkAccountOptional.get();
            apiAccount.setId(sdkAccount.getId());
            apiAccount.setName(sdkAccount.getName());
            apiAccount.setOwner(sdkAccount.getOwner());
            apiAccount.setCreated(sdkAccount.getCreated());
            apiAccount.setUpdated(sdkAccount.getUpdated());
            apiAccount.setLogoUrl(sdkAccount.getLogoUrl());
            apiAccount.setData(sdkAccount.getData());
            if (sdkAccount.getCompany() != null){
                apiAccount.setCompany(new CompanyConverter(sdkAccount.getCompany()).toAPICompany());
            }
            if (sdkAccount.getProviders() != null){
                apiAccount.setProviders(new AccountProvidersConverter(sdkAccount.getProviders()).toAPIAccountProviders());
            }
            for (com.silanis.esl.sdk.CustomField customField : sdkAccount.getCustomFields()){
                apiAccount.addCustomField(new CustomFieldConverter(customField).toAPICustomField());
            }
            for (com.silanis.esl.sdk.License license : sdkAccount.getLicenses()){
                apiAccount.addLicense(new LicenseConverter(license).toAPILicense());
            }
            return apiAccount;
        } else {
            return apiAccountOptional.orNull();
        }
    }

    public com.silanis.esl.sdk.Account toSDKAccount() {
        if ( apiAccountOptional.isPresent() ) {
            com.silanis.esl.sdk.Account sdkAccount = new com.silanis.esl.sdk.Account();
            Account apiAccount = apiAccountOptional.get();
            sdkAccount.setId(apiAccount.getId());
            sdkAccount.setName(apiAccount.getName());
            sdkAccount.setOwner(apiAccount.getOwner());
            sdkAccount.setData(apiAccount.getData());
            sdkAccount.setLogoUrl(apiAccount.getLogoUrl());
            sdkAccount.setCreated(apiAccount.getCreated());
            sdkAccount.setUpdated(apiAccount.getUpdated());
            if (apiAccount.getCompany() != null) {
                sdkAccount.setCompany(new CompanyConverter(apiAccount.getCompany()).toSDKCompany());
            }
            if (apiAccount.getProviders() != null){
                sdkAccount.setProviders(new AccountProvidersConverter(apiAccount.getProviders()).toSDKAccountProviders());
            }
            for (CustomField customField : apiAccount.getCustomFields()){
                sdkAccount.addCustomField(new CustomFieldConverter(customField).toSDKCustomField());
            }
            for (License license : apiAccount.getLicenses()){
                sdkAccount.addLicense(new LicenseConverter(license).toSDKLicense());
            }

            return sdkAccount;
        } else {
            return sdkAccountOptional.orNull();
        }
    }
}
