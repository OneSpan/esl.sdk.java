package com.silanis.esl.sdk.internal.converter;

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

        if (apiKnowledgeBasedAuthenticationStatus.equals("NOT_YET_ATTEMPTED"))
            return sdkKnowledgeBasedAuthenticationStatus.NOT_YET_ATTEMPTED;
        else if (apiKnowledgeBasedAuthenticationStatus.equals("FAILED"))
            return sdkKnowledgeBasedAuthenticationStatus.FAILED;
        else if (apiKnowledgeBasedAuthenticationStatus.equals("PASSED"))
            return sdkKnowledgeBasedAuthenticationStatus.PASSED;
        else
            return sdkKnowledgeBasedAuthenticationStatus.UNRECOGNIZED(apiKnowledgeBasedAuthenticationStatus);
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

        if(sdkKnowledgeBasedAuthenticationStatus.getValue().equals("NOT_YET_ATTEMPTED"))
            return "NOT_YET_ATTEMPTED";
        else if (sdkKnowledgeBasedAuthenticationStatus.getValue().equals("FAILED"))
            return "FAILED";
        else if (sdkKnowledgeBasedAuthenticationStatus.getValue().equals("PASSED"))
            return "PASSED";
        else
            return "";
    }
}
