package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.DocumentVisibilityConfiguration;

import static com.silanis.esl.sdk.builder.DocumentVisibilityConfigurationBuilder.newDocumentVisibilityConfiguration;

/**
 * Created by schoi on 11/23/16.
 */
public class DocumentVisibilityConfigurationConverter {

    private com.silanis.esl.api.model.DocumentVisibilityConfiguration apiConfiguration;
    private DocumentVisibilityConfiguration sdkConfiguration;

    /**
     * Construct with API object involved in conversion.
     *
     * @param apiConfiguration
     */
    public DocumentVisibilityConfigurationConverter(com.silanis.esl.api.model.DocumentVisibilityConfiguration apiConfiguration) {
        this.apiConfiguration = apiConfiguration;
    }

    /**
     * Construct with SDK object involved in conversion.
     *
     * @param sdkConfiguration
     */
    public DocumentVisibilityConfigurationConverter(DocumentVisibilityConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
    }

    /**
     * Convert from SDK to API.
     *
     * @return
     */
    public com.silanis.esl.api.model.DocumentVisibilityConfiguration toAPIVisibilityConfiguration() {
        if (sdkConfiguration == null) {
            return apiConfiguration;
        }

        return new com.silanis.esl.api.model.DocumentVisibilityConfiguration()
            .safeSetDocumentUid(sdkConfiguration.getDocumentUid())
            .safeSetRoleUids(sdkConfiguration.getSignerIds());
    }

    /**
     * Convert from API to SDK.
     *
     * @return
     */
    public DocumentVisibilityConfiguration toSDKVisibilityConfiguration() {

        if (apiConfiguration == null) {
            return sdkConfiguration;
        }

        return newDocumentVisibilityConfiguration(apiConfiguration.getDocumentUid())
            .withSignerIds(apiConfiguration.getRoleUids()).build();
    }
}
