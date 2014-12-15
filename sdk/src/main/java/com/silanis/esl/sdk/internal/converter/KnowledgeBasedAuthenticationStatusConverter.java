package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus;

/**
 * Created by schoi on 9/11/14.
 */
public class KnowledgeBasedAuthenticationStatusConverter {

    private String apiKnowledgeBasedAuthenticationStatus = null;
    private com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus sdkKnowledgeBasedAuthenticationStatus = null;

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
    public KnowledgeBasedAuthenticationStatusConverter(com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus sdkKnowledgeBasedAuthenticationStatus) {
        this.sdkKnowledgeBasedAuthenticationStatus = sdkKnowledgeBasedAuthenticationStatus;
    }

    /**
     * Convert from API KnowledgeBasedAuthenticationStatus to SDK KnowledgeBasedAuthenticationStatus.
     *
     * @return the SDK KnowledgeBasedAuthenticationStatus
     */
    public com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus toSDKKnowledgeBasedAuthenticationStatus() {
        if (apiKnowledgeBasedAuthenticationStatus == null) {
            return sdkKnowledgeBasedAuthenticationStatus;
        }

        sdkKnowledgeBasedAuthenticationStatus = KnowledgeBasedAuthenticationStatus.values().get(apiKnowledgeBasedAuthenticationStatus);
        if (sdkKnowledgeBasedAuthenticationStatus == null)
            return KnowledgeBasedAuthenticationStatus.UNRECOGNIZED(apiKnowledgeBasedAuthenticationStatus);
        else
            return sdkKnowledgeBasedAuthenticationStatus;
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
        return sdkKnowledgeBasedAuthenticationStatus.toString();
    }
}
