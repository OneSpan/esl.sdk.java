package com.silanis.esl.sdk.internal.converter;

/**
 * Created by schoi on 9/8/14.
 */
public class KnowledgeBasedAuthenticationConverter {

    private com.silanis.esl.sdk.KnowledgeBasedAuthentication sdkKnowledgeBasedAuthentication = null;
    private com.silanis.esl.api.model.KnowledgeBasedAuthentication apiKnowledgeBasedAuthentication = null;

    /**
     * Construct with API KnowledgeBasedAuthentication object involved in conversion.
     *
     * @param apiKnowledgeBasedAuthentication
     */
    public KnowledgeBasedAuthenticationConverter(com.silanis.esl.api.model.KnowledgeBasedAuthentication apiKnowledgeBasedAuthentication) {
        this.apiKnowledgeBasedAuthentication = apiKnowledgeBasedAuthentication;
    }

    /**
     * Construct with SDK KnowledgeBasedAuthentication object involved in conversion.
     *
     * @param sdkKnowledgeBasedAuthentication
     */
    public KnowledgeBasedAuthenticationConverter(com.silanis.esl.sdk.KnowledgeBasedAuthentication sdkKnowledgeBasedAuthentication) {
        this.sdkKnowledgeBasedAuthentication = sdkKnowledgeBasedAuthentication;
    }

    /**
     * Convert from SDK KBA to API KBA.
     *
     * @return an API KBA object.
     */

    public com.silanis.esl.api.model.KnowledgeBasedAuthentication toAPIKnowledgeBasedAuthentication() {
        if (sdkKnowledgeBasedAuthentication == null) {
            return apiKnowledgeBasedAuthentication;
        }
        com.silanis.esl.api.model.KnowledgeBasedAuthentication result = new com.silanis.esl.api.model.KnowledgeBasedAuthentication();

        com.silanis.esl.sdk.SignerInformationForLexisNexis lexisNexis = sdkKnowledgeBasedAuthentication.getSignerInformationForLexisNexis();

        result.setKnowledgeBasedAuthenticationStatus(new KnowledgeBasedAuthenticationStatusConverter(sdkKnowledgeBasedAuthentication.getKnowledgeBasedAuthenticationStatus()).toAPIKnowledgeBasedAuthenticationStatus())
                .setSignerInformationForLexisNexis(new SignerInformationForLexisNexisConverter(lexisNexis).toAPISignerInformationForLexisNexis());
        return result;
    }

    /**
     * Convert from API KBA to SDK KBA.
     *
     * @return an SDK KBA object.
     */

    public com.silanis.esl.sdk.KnowledgeBasedAuthentication toSDKKnowledgeBasedAuthentication() {
        if (apiKnowledgeBasedAuthentication == null) {
            return sdkKnowledgeBasedAuthentication;
        }
        com.silanis.esl.sdk.KnowledgeBasedAuthentication result = new com.silanis.esl.sdk.KnowledgeBasedAuthentication();

        com.silanis.esl.api.model.SignerInformationForLexisNexis lexisNexis = apiKnowledgeBasedAuthentication.getSignerInformationForLexisNexis();

        result.setKnowledgeBasedAuthenticationStatus(new KnowledgeBasedAuthenticationStatusConverter(apiKnowledgeBasedAuthentication.getKnowledgeBasedAuthenticationStatus()).toSDKKnowledgeBasedAuthenticationStatus());
        result.setSignerInformationForLexisNexis(new SignerInformationForLexisNexisConverter(lexisNexis).toSDKSignerInformationForLexisNexis());
        return result;
        //haha
    }
}
