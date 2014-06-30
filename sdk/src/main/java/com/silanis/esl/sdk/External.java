package com.silanis.esl.sdk;

import java.io.Serializable;

/**
 * Created by chi-wing on 7/3/14.
 */
public class External implements Serializable {
    private static final long serialVersionUID = 1L;

    private String provider;
    private String id;
    private String providerName;

    public External(){}

    public External(String provider, String id, String providerName){
        this.provider = provider;
        this.id = id;
        this.providerName = providerName;
    }

    public String getProvider() {
        return provider;
    }

    public String getId() {
        return id;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
