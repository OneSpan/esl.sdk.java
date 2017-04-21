package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.ExternalProviderKey;
import com.silanis.esl.sdk.ExternalSigning;

public class ExternalSigningBuilder {

    private ExternalProviderKey providerKey;
    private String identityInfo;

    private ExternalSigningBuilder(ExternalProviderKey providerKey) {
        this.providerKey = providerKey;
    }

    public static ExternalSigningBuilder newExternalSigning(ExternalProviderKey providerKey) {
        return new ExternalSigningBuilder(providerKey);
    }

    public ExternalSigningBuilder withIdentityInfo(String identityInfo) {
        this.identityInfo = identityInfo;
        return this;
    }

    public ExternalSigning build() {
        ExternalSigning externalSigning = new ExternalSigning(this.providerKey);
        externalSigning.setIdentityInfo(this.identityInfo);
        return externalSigning;
    }

}
