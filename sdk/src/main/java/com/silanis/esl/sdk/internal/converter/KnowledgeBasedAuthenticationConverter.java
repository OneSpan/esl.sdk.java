package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.SignerInformationForEquifaxCanada;
import com.silanis.esl.sdk.SignerInformationForEquifaxUSA;

/**
 * Created by schoi on 9/8/14.
 */
public class KnowledgeBasedAuthenticationConverter {
    private com.silanis.esl.sdk.KnowledgeBasedAuthentication sdkKBA = null;
    private com.silanis.esl.api.model.KnowledgeBasedAuthentication apiKBA = null;

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

        SignerInformationForEquifaxCanada canada = sdkKBA.getSignerInformationForEquifaxCanada();
        SignerInformationForEquifaxUSA usa = sdkKBA.getSignerInformationForEquifaxUSA();


        result.setKnowledgeBasedAuthenticationStatus(sdkKBA.getKnowledgeBasedAuthenticationStatus())
                .setSignerInformationForEquifaxCanada(new SignerInformationForEquifaxCanadaConverter(canada).toAPISignerInformationForEquifaxCanada())
                .setSignerInformationForEquifaxUSA(new SignerInformationForEquifaxUSAConverter(usa).toAPISignerInformationForEquifaxUSA());

        return result;
    }
}
