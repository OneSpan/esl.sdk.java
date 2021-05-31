package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.SubAccountApiKey;

public class SubAccountApiKeyConverter {

    private Optional<SubAccountApiKey> apiSubAccountApiKeyOptional;
    private Optional<com.silanis.esl.sdk.SubAccountApiKey> sdkSubAccountApiKeyOptional;

    public SubAccountApiKeyConverter(SubAccountApiKey apiSubAccount ) {
        apiSubAccountApiKeyOptional = Optional.fromNullable( apiSubAccount );
        sdkSubAccountApiKeyOptional = Optional.absent();
    }

    public SubAccountApiKeyConverter(com.silanis.esl.sdk.SubAccountApiKey sdkSubAccountApiKey ) {
        apiSubAccountApiKeyOptional = Optional.absent();
        sdkSubAccountApiKeyOptional = Optional.fromNullable( sdkSubAccountApiKey );
    }

    public SubAccountApiKey toAPISubAccountApiKey() {
        if ( sdkSubAccountApiKeyOptional.isPresent() ) {
            SubAccountApiKey apiSubAccountApiKey = new SubAccountApiKey();
            com.silanis.esl.sdk.SubAccountApiKey sdkSubAccountApiKey = sdkSubAccountApiKeyOptional.get();
            apiSubAccountApiKey.safeSetAccountUid(sdkSubAccountApiKey.getAccountUid());
            apiSubAccountApiKey.safeSetAccountName(sdkSubAccountApiKey.getAccountName());
            apiSubAccountApiKey.safeSetApiKey(sdkSubAccountApiKey.getApiKey());

            return apiSubAccountApiKey;
        } else {
            return apiSubAccountApiKeyOptional.orNull();
        }
    }

    public com.silanis.esl.sdk.SubAccountApiKey toSDKSubAccountApiKey() {
        if ( apiSubAccountApiKeyOptional.isPresent() ) {
            com.silanis.esl.sdk.SubAccountApiKey sdkSubAccountApiKey = new com.silanis.esl.sdk.SubAccountApiKey();
            SubAccountApiKey apiSubAccountApiKey = apiSubAccountApiKeyOptional.get();
            sdkSubAccountApiKey.setAccountUid(apiSubAccountApiKey.getAccountUid());
            sdkSubAccountApiKey.setAccountName(apiSubAccountApiKey.getAccountName());
            sdkSubAccountApiKey.setApiKey(apiSubAccountApiKey.getApiKey());

            return sdkSubAccountApiKey;
        } else {
            return sdkSubAccountApiKeyOptional.orNull();
        }
    }
}
