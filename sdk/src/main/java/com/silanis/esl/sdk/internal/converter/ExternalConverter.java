package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.External;

/**
 * Created by chi-wing on 7/3/14.
 */
public class ExternalConverter {
    private com.silanis.esl.api.model.External apiExternal;
    private External sdkExternal;

    /**
     * Construct with SDK object
     *
     * @param sdkExternal
     */
    public ExternalConverter(External sdkExternal){
        this.sdkExternal = sdkExternal;
    }

    /**
     * Construct with API object
     *
     * @param apiExternal
     */
    public ExternalConverter(com.silanis.esl.api.model.External apiExternal){
        this.apiExternal = apiExternal;
    }

    /**
     * Convert API External to SDK External
     *
     * @return
     */
    public External toSDKExternal(){
        if(apiExternal == null){
            return sdkExternal;
        }
        return new External(apiExternal.getProvider(), apiExternal.getId(), apiExternal.getProviderName());
    }


    /**
     * Convert SDK External to API External
     *
     * @return
     */
    public com.silanis.esl.api.model.External toAPIExternal(){
        if(sdkExternal == null){
            return apiExternal;
        }

        apiExternal = new com.silanis.esl.api.model.External();
        apiExternal.setProvider(sdkExternal.getProvider());
        apiExternal.setId(sdkExternal.getId());
        apiExternal.setProviderName(sdkExternal.getProviderName());

        return apiExternal;
    }
}
