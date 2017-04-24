package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.ExternalSigningAuth;
import com.silanis.esl.sdk.builder.ExternalSigningAuthBuilder;

public class ExternalSigningAuthConverter {

    private ExternalSigningAuth apiExternalSigningAuth = null;
    private com.silanis.esl.sdk.ExternalSigningAuth sdkExternalSigningAuth = null;


    public ExternalSigningAuthConverter(ExternalSigningAuth apiExternalSigningAuth) {
        this.apiExternalSigningAuth = apiExternalSigningAuth;
    }

    public ExternalSigningAuthConverter(com.silanis.esl.sdk.ExternalSigningAuth sdkExternalSigningAuth) {
        this.sdkExternalSigningAuth = sdkExternalSigningAuth;
    }

    public ExternalSigningAuth toApiExternalSigningAuth(){
        if(sdkExternalSigningAuth == null) {
            return apiExternalSigningAuth;
        }

        ExternalSigningAuth result = new ExternalSigningAuth();
        result.setIdentityInfo(sdkExternalSigningAuth.getIdentityInfo());
        result.setProviderKey(sdkExternalSigningAuth.getProviderKey());

        return result;

    }

    public com.silanis.esl.sdk.ExternalSigningAuth toSdkExternalSigningAuth(){
        if(apiExternalSigningAuth == null) {
            return sdkExternalSigningAuth;
        }

        com.silanis.esl.sdk.ExternalSigningAuth result = ExternalSigningAuthBuilder.forProvider(apiExternalSigningAuth.getProviderKey())
                .withIdentityInfo(apiExternalSigningAuth.getIdentityInfo()).build();

        return result;
    }
}
