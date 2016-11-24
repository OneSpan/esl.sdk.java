package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Function;
import com.silanis.esl.sdk.DocumentVisibility;

import static com.google.common.collect.Lists.transform;
import static com.silanis.esl.sdk.builder.DocumentVisibilityBuilder.newDocumentVisibility;

/**
 * Created by schoi on 11/23/16.
 */
public class DocumentVisibilityConverter {

    private com.silanis.esl.api.model.DocumentVisibility apiVisibility;
    private DocumentVisibility sdkVisibility;

    /**
     * Construct with API object involved in conversion.
     *
     * @param apiVisibility
     */
    public DocumentVisibilityConverter(com.silanis.esl.api.model.DocumentVisibility apiVisibility) {
        this.apiVisibility = apiVisibility;
    }

    /**
     * Construct with SDK object involved in conversion.
     *
     * @param sdkVisibility
     */
    public DocumentVisibilityConverter(DocumentVisibility sdkVisibility) {
        this.sdkVisibility = sdkVisibility;
    }

    /**
     * Convert from SDK to API.
     *
     * @return
     */
    public com.silanis.esl.api.model.DocumentVisibility toAPIDocumentVisibility() {
        if (sdkVisibility == null) {
            return apiVisibility;
        }

        return new com.silanis.esl.api.model.DocumentVisibility()
            .setConfigurations(transform(sdkVisibility.getConfigurations(), new Function<com.silanis.esl.sdk.DocumentVisibilityConfiguration, com.silanis.esl.api.model.DocumentVisibilityConfiguration>() {
                @Override
                public com.silanis.esl.api.model.DocumentVisibilityConfiguration apply(com.silanis.esl.sdk.DocumentVisibilityConfiguration configuration) {
                    return new DocumentVisibilityConfigurationConverter(configuration).toAPIVisibilityConfiguration();
                }
            }));
    }

    /**
     * Convert from API to SDK.
     *
     * @return
     */
    public DocumentVisibility toSDKDocumentVisibility() {

        if (apiVisibility == null) {
            return sdkVisibility;
        }

        return newDocumentVisibility()
            .withConfigurations(transform(apiVisibility.getConfigurations(), new Function<com.silanis.esl.api.model.DocumentVisibilityConfiguration, com.silanis.esl.sdk.DocumentVisibilityConfiguration>() {
                @Override
                public com.silanis.esl.sdk.DocumentVisibilityConfiguration apply(com.silanis.esl.api.model.DocumentVisibilityConfiguration configuration) {
                    return new DocumentVisibilityConfigurationConverter(configuration).toSDKVisibilityConfiguration();
                }
            })).build();
    }
}
