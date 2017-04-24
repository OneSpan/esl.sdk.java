package com.silanis.esl.sdk;

public class ExternalSigningAuth implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String identityInfo;
    private String providerKey;

    public ExternalSigningAuth(String providerKey){
        this.providerKey = providerKey;
    }

    public String getIdentityInfo() {
        return identityInfo;
    }

    public void setIdentityInfo(String identityInfo) {
        this.identityInfo = identityInfo;
    }

    public String getProviderKey() {
        return providerKey;
    }

    public void setProviderKey(String providerKey) {
        this.providerKey = providerKey;
    }
}
