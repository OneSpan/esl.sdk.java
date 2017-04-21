package com.silanis.esl.sdk;

public class ExternalSigning implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String identityInfo;
    private ExternalProviderKey providerKey;

    public ExternalSigning(ExternalProviderKey providerKey){
        this.providerKey = providerKey;
    }

    public String getIdentityInfo() {
        return identityInfo;
    }

    public void setIdentityInfo(String identityInfo) {
        this.identityInfo = identityInfo;
    }

    public ExternalProviderKey getProviderKey() {
        return providerKey;
    }

    public void setProviderKey(ExternalProviderKey providerKey) {
        this.providerKey = providerKey;
    }
}
