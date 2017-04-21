package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.ExternalProviderKey;
import com.silanis.esl.sdk.builder.ExternalSigningBuilder;

public class ExternalSigningConverter {

    private com.silanis.esl.api.model.ExternalSigning apiExternalSigning = null;
    private com.silanis.esl.sdk.ExternalSigning sdkExternalSigning = null;


    public ExternalSigningConverter(com.silanis.esl.api.model.ExternalSigning apiExternalSigning) {
        this.apiExternalSigning = apiExternalSigning;
    }

    public ExternalSigningConverter(com.silanis.esl.sdk.ExternalSigning sdkExternalSigning) {
        this.sdkExternalSigning = sdkExternalSigning;
    }

    public com.silanis.esl.api.model.ExternalSigning toApiExternalSigning(){
        if(sdkExternalSigning == null) {
            return apiExternalSigning;
        }

        com.silanis.esl.api.model.ExternalSigning result = new com.silanis.esl.api.model.ExternalSigning();
        result.setIdentityInfo(sdkExternalSigning.getIdentityInfo());
        result.setProviderKey(sdkExternalSigning.getProviderKey().getApiValue());

        return result;

    }

    public com.silanis.esl.sdk.ExternalSigning toSdkExternalSigning(){
        if(apiExternalSigning == null) {
            return sdkExternalSigning;
        }

        com.silanis.esl.sdk.ExternalSigning result = ExternalSigningBuilder.newExternalSigning(
                ExternalProviderKey.fromAPIExternalProviderKey(apiExternalSigning.getProviderKey()))
                .withIdentityInfo(apiExternalSigning.getIdentityInfo()).build();

        return result;
    }
}
