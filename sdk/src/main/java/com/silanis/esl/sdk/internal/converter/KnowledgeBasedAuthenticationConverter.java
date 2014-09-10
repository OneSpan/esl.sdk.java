package com.silanis.esl.sdk.internal.converter;

/**
 * Created by schoi on 9/8/14.
 */
public class KnowledgeBasedAuthenticationConverter {
    private com.silanis.esl.sdk.KnowledgeBasedAuthentication sdkKBA = null;
    private com.silanis.esl.api.model.KnowledgeBasedAuthentication apiKBA = null;

    /**
     * Construct with API KnowledgeBasedAuthentication object involved in conversion.
     *
     * @param apiKBA
     */
    public KnowledgeBasedAuthenticationConverter(com.silanis.esl.api.model.KnowledgeBasedAuthentication apiKBA) {
        this.apiKBA = apiKBA;
    }

    /**
     * Construct with SDK KnowledgeBasedAuthentication object involved in conversion.
     *
     * @param sdkKBA
     */
    public KnowledgeBasedAuthenticationConverter(com.silanis.esl.sdk.KnowledgeBasedAuthentication sdkKBA) {
        this.sdkKBA = sdkKBA;
    }

    /**
     * Convert from SDK KBA to API KBA.
     *
     * @return an API KBA object.
     */

    public com.silanis.esl.api.model.KnowledgeBasedAuthentication toAPIKnowledgeBasedAuthentication() {
        if (sdkKBA == null) {
            return apiKBA;
        }
        com.silanis.esl.api.model.KnowledgeBasedAuthentication result = new com.silanis.esl.api.model.KnowledgeBasedAuthentication();

        com.silanis.esl.sdk.SignerInformationForEquifaxCanada canada = sdkKBA.getSignerInformationForEquifaxCanada();
        com.silanis.esl.sdk.SignerInformationForEquifaxUSA usa = sdkKBA.getSignerInformationForEquifaxUSA();


        result.setKnowledgeBasedAuthenticationStatus(sdkKBA.getKnowledgeBasedAuthenticationStatus())
                .setSignerInformationForEquifaxCanada(new SignerInformationForEquifaxCanadaConverter(canada).toAPISignerInformationForEquifaxCanada())
                .setSignerInformationForEquifaxUSA(new SignerInformationForEquifaxUSAConverter(usa).toAPISignerInformationForEquifaxUSA());

        return result;
    }

    /**
     * Convert from API KBA to SDK KBA.
     *
     * @return an SDK KBA object.
     */

    public com.silanis.esl.sdk.KnowledgeBasedAuthentication toSDKKnowledgeBasedAuthentication() {
        if (apiKBA == null) {
            return sdkKBA;
        }
        com.silanis.esl.sdk.KnowledgeBasedAuthentication result = new com.silanis.esl.sdk.KnowledgeBasedAuthentication();

        com.silanis.esl.api.model.SignerInformationForEquifaxCanada canada = apiKBA.getSignerInformationForEquifaxCanada();
        com.silanis.esl.api.model.SignerInformationForEquifaxUSA usa = apiKBA.getSignerInformationForEquifaxUSA();


        result.setKnowledgeBasedAuthenticationStatus(apiKBA.getKnowledgeBasedAuthenticationStatus());
        result.setSignerInformationForEquifaxCanada(new SignerInformationForEquifaxCanadaConverter(canada).toSDKSignerInformationForEquifaxCanada());
        result.setSignerInformationForEquifaxUSA(new SignerInformationForEquifaxUSAConverter(usa).toSDKSignerInformationForEquifaxUSA());

        return result;
    }
}
