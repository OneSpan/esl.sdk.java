package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.Provider;

public class ProviderConverter {

    private Optional<Provider> apiProviderOptional;
    private Optional<com.silanis.esl.sdk.Provider> sdkProviderOptional;

    public ProviderConverter(Provider apiProvider ) {
        apiProviderOptional = Optional.of( apiProvider );
        sdkProviderOptional = Optional.absent();
    }

    public ProviderConverter(com.silanis.esl.sdk.Provider sdkProvider ) {
        apiProviderOptional = Optional.absent();
        sdkProviderOptional = Optional.of( sdkProvider );
    }

    public Provider toAPIProvider() {
        if ( sdkProviderOptional.isPresent() ) {
            Provider apiProvider = new Provider();
            com.silanis.esl.sdk.Provider sdkProvider = sdkProviderOptional.get();
            apiProvider.setProvides(sdkProvider.getProvides());
            apiProvider.setId(sdkProvider.getId());
            apiProvider.setName(sdkProvider.getName());
            apiProvider.setData(sdkProvider.getData());

            return apiProvider;
        } else {
            return apiProviderOptional.get();
        }
    }

    public com.silanis.esl.sdk.Provider toSDKProvider() {
        if ( apiProviderOptional.isPresent() ) {
            com.silanis.esl.sdk.Provider sdkProvider = new com.silanis.esl.sdk.Provider();
            Provider apiProvider = apiProviderOptional.get();
            sdkProvider.setProvides(apiProvider.getProvides());
            sdkProvider.setId(apiProvider.getId());
            sdkProvider.setName(apiProvider.getName());
            sdkProvider.setData(apiProvider.getData());

            return sdkProvider;
        } else {
            return sdkProviderOptional.get();
        }
    }
}
