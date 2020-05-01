package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.SubAccount;

public class SubAccountConverter {

    private Optional<SubAccount> apiSubAccountOptional;
    private Optional<com.silanis.esl.sdk.SubAccount> sdkSubAccountOptional;

    public SubAccountConverter(SubAccount apiSubAccount ) {
        apiSubAccountOptional = Optional.fromNullable( apiSubAccount );
        sdkSubAccountOptional = Optional.absent();
    }

    public SubAccountConverter(com.silanis.esl.sdk.SubAccount sdkSubAccount ) {
        apiSubAccountOptional = Optional.absent();
        sdkSubAccountOptional = Optional.fromNullable( sdkSubAccount );
    }

    public SubAccount toAPISubAccount() {
        if ( sdkSubAccountOptional.isPresent() ) {
            SubAccount apiSubAccount = new SubAccount();
            com.silanis.esl.sdk.SubAccount sdkSubAccount = sdkSubAccountOptional.get();
            apiSubAccount.safeSetName(sdkSubAccount.getName());
            apiSubAccount.safeSetTimezoneId(sdkSubAccount.getTimezoneId());
            apiSubAccount.safeSetLanguage(sdkSubAccount.getLanguage());
            apiSubAccount.safeSetParentAccountId(sdkSubAccount.getParentAccountId());

            return apiSubAccount;
        } else {
            return apiSubAccountOptional.orNull();
        }
    }

    public com.silanis.esl.sdk.SubAccount toSDKSubAccount() {
        if ( apiSubAccountOptional.isPresent() ) {
            com.silanis.esl.sdk.SubAccount sdkSubAccount = new com.silanis.esl.sdk.SubAccount();
            SubAccount apiSubAccount = apiSubAccountOptional.get();
            sdkSubAccount.setName(apiSubAccount.getName());
            sdkSubAccount.setParentAccountId(apiSubAccount.getParentAccountId());
            sdkSubAccount.setLanguage(apiSubAccount.getLanguage());
            sdkSubAccount.setTimezoneId(apiSubAccount.getTimezoneId());

            return sdkSubAccount;
        } else {
            return sdkSubAccountOptional.orNull();
        }
    }
}
