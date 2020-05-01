package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.AccountProviders;
import com.silanis.esl.api.model.Provider;

public class AccountProvidersConverter {

    private Optional<AccountProviders> apiAccountProvidersOptional;
    private Optional<com.silanis.esl.sdk.AccountProviders> sdkAccountProvidersOptional;

    public AccountProvidersConverter(AccountProviders apiAccountProviders ) {
        apiAccountProvidersOptional = Optional.of( apiAccountProviders );
        sdkAccountProvidersOptional = Optional.absent();
    }

    public AccountProvidersConverter(com.silanis.esl.sdk.AccountProviders sdkAccountProviders ) {
        apiAccountProvidersOptional = Optional.absent();
        sdkAccountProvidersOptional = Optional.of( sdkAccountProviders );
    }

    public AccountProviders toAPIAccountProviders() {
        if ( sdkAccountProvidersOptional.isPresent() ) {
            AccountProviders apiAccountProviders = new AccountProviders();
            com.silanis.esl.sdk.AccountProviders sdkAccountProviders = sdkAccountProvidersOptional.get();
            if (sdkAccountProviders.getDocuments() != null){
                for (com.silanis.esl.sdk.Provider provider : sdkAccountProviders.getDocuments()){
                    apiAccountProviders.addDocument(new ProviderConverter(provider).toAPIProvider());
                }
            }
            if (sdkAccountProviders.getUsers() != null){
                for (com.silanis.esl.sdk.Provider provider : sdkAccountProviders.getUsers()) {
                    apiAccountProviders.addUser(new ProviderConverter(provider).toAPIProvider());
                }
            }

            return apiAccountProviders;
        } else {
            return apiAccountProvidersOptional.get();
        }
    }

    public com.silanis.esl.sdk.AccountProviders toSDKAccountProviders() {
        if ( apiAccountProvidersOptional.isPresent() ) {
            com.silanis.esl.sdk.AccountProviders sdkAccountProviders = new com.silanis.esl.sdk.AccountProviders();
            AccountProviders apiAccountProviders = apiAccountProvidersOptional.get();
            if (apiAccountProviders.getDocuments() != null){
                for (Provider provider : apiAccountProviders.getDocuments()){
                    sdkAccountProviders.addDocument(new ProviderConverter(provider).toSDKProvider());
                }
            }
            if (apiAccountProviders.getUsers() != null){
                for (Provider provider : apiAccountProviders.getUsers()){
                    sdkAccountProviders.addUser(new ProviderConverter(provider).toSDKProvider());
                }
            }

            return sdkAccountProviders;
        } else {
            return sdkAccountProvidersOptional.get();
        }
    }
}
