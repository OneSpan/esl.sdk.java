package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ExternalSigningAuth extends Model implements java.io.Serializable{

    @JsonIgnore
    public static final String FIELD_IDENTITY_INFO = "identityInfo";

    @JsonIgnore
    public static final String FIELD_PROVIDER_KEY = "providerKey";

    private String identityInfo;
    private String providerKey;

    public String getIdentityInfo() {
        return identityInfo;
    }

    public void setIdentityInfo(String identityInfo) {
        this.identityInfo = identityInfo;
        setDirty(FIELD_IDENTITY_INFO);
    }

    public String getProviderKey() {
        return providerKey;
    }

    public void setProviderKey(String providerKey) {
        this.providerKey = providerKey;
        setDirty(FIELD_PROVIDER_KEY);
    }
}
