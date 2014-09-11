package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.BuilderException;

/**
 * Created by schoi on 9/11/14.
 */
public class KnowledgeBasedAuthenticationStatusConverter {

    private com.silanis.esl.api.model.KnowledgeBasedAuthenticationStatus apiKnowledgeBasedAuthenticationStatus = null;
    private com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus sdkKnowledgeBasedAuthenticationStatus = null;

    /**
     * Construct with API KnowledgeBasedAuthenticationStatus object involved in conversion.
     *
     * @param apiKnowledgeBasedAuthenticationStatus
     */
    public KnowledgeBasedAuthenticationStatusConverter(com.silanis.esl.api.model.KnowledgeBasedAuthenticationStatus apiKnowledgeBasedAuthenticationStatus) {
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

        switch (apiKnowledgeBasedAuthenticationStatus) {
            case NOT_YET_ATTEMPTED:
                return sdkKnowledgeBasedAuthenticationStatus.NOT_YET_ATTEMPTED;
            case FAILED:
                return sdkKnowledgeBasedAuthenticationStatus.FAILED;
            case PASSED:
                return sdkKnowledgeBasedAuthenticationStatus.PASSED;
            default:
                throw new BuilderException("Unrecognized message status type.");
        }
    }

    /**
     * Convert from SDK KnowledgeBasedAuthenticationStatus to API KnowledgeBasedAuthenticationStatus.
     *
     * @return the API KnowledgeBasedAuthenticationStatus
     */
    public com.silanis.esl.api.model.KnowledgeBasedAuthenticationStatus toAPIKnowledgeBasedAuthenticationStatus() {
        if (sdkKnowledgeBasedAuthenticationStatus == null) {
            return apiKnowledgeBasedAuthenticationStatus;
        }

        switch (sdkKnowledgeBasedAuthenticationStatus) {
            case NOT_YET_ATTEMPTED:
                return apiKnowledgeBasedAuthenticationStatus.NOT_YET_ATTEMPTED;
            case FAILED:
                return apiKnowledgeBasedAuthenticationStatus.FAILED;
            case PASSED:
                return apiKnowledgeBasedAuthenticationStatus.PASSED;
            default:
                throw new BuilderException("Unrecognized message status type.");
        }
    }
}
