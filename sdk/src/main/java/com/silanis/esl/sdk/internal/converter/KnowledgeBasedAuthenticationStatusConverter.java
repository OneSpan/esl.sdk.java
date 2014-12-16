package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus;

/**
 * Created by schoi on 9/11/14.
 */
public class KnowledgeBasedAuthenticationStatusConverter {

    private String apiKnowledgeBasedAuthenticationStatus = null;
    private KnowledgeBasedAuthenticationStatus sdkKnowledgeBasedAuthenticationStatus = null;

    /**
     * Construct with API KnowledgeBasedAuthenticationStatus object involved in conversion.
     *
     * @param apiKnowledgeBasedAuthenticationStatus
     */
    public KnowledgeBasedAuthenticationStatusConverter(String apiKnowledgeBasedAuthenticationStatus) {
        this.apiKnowledgeBasedAuthenticationStatus = apiKnowledgeBasedAuthenticationStatus;
    }

    /**
     * Construct with SDK KnowledgeBasedAuthenticationStatus object involved in conversion.
     *
     * @param sdkKnowledgeBasedAuthenticationStatus
     */
    public KnowledgeBasedAuthenticationStatusConverter(KnowledgeBasedAuthenticationStatus sdkKnowledgeBasedAuthenticationStatus) {
        this.sdkKnowledgeBasedAuthenticationStatus = sdkKnowledgeBasedAuthenticationStatus;
    }

    /**
     * Convert from API KnowledgeBasedAuthenticationStatus to SDK KnowledgeBasedAuthenticationStatus.
     *
     * @return the SDK KnowledgeBasedAuthenticationStatus
     */
    public KnowledgeBasedAuthenticationStatus toSDKKnowledgeBasedAuthenticationStatus() {
        if (apiKnowledgeBasedAuthenticationStatus == null) {
            return sdkKnowledgeBasedAuthenticationStatus;
        }

        KnowledgeBasedAuthenticationStatus[] values = KnowledgeBasedAuthenticationStatus.values();
        for (KnowledgeBasedAuthenticationStatus value : values) {
            if(apiKnowledgeBasedAuthenticationStatus.equals(value.getApiValue())){
                return value;
            }
        }
        return KnowledgeBasedAuthenticationStatus.UNRECOGNIZED(apiKnowledgeBasedAuthenticationStatus);
    }

    /**
     * Convert from SDK KnowledgeBasedAuthenticationStatus to API KnowledgeBasedAuthenticationStatus.
     *
     * @return the API KnowledgeBasedAuthenticationStatus
     */
    public String toAPIKnowledgeBasedAuthenticationStatus() {
        if (sdkKnowledgeBasedAuthenticationStatus == null) {
            return apiKnowledgeBasedAuthenticationStatus;
        }
        return sdkKnowledgeBasedAuthenticationStatus.getApiValue();
    }
}
