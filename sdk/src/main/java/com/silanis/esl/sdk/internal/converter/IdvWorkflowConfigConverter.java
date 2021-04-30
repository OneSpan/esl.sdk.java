package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.IdvWorkflowConfiguration;
import com.silanis.esl.sdk.IdvWorkflowConfig;
import com.silanis.esl.sdk.builder.IdvWorkflowConfigBuilder;

/**
 * Created by schoi on 2021-05-06.
 */
public class IdvWorkflowConfigConverter {

    private IdvWorkflowConfiguration apiIdvWorkflowConfiguration;
    private IdvWorkflowConfig sdkIdvWorkflowConfig;


    /**
     * Construct with API object involved in conversion.
     *
     * @param apiIdvWorkflowConfiguration
     */
    public IdvWorkflowConfigConverter(IdvWorkflowConfiguration apiIdvWorkflowConfiguration) {
        this.apiIdvWorkflowConfiguration = apiIdvWorkflowConfiguration;
    }

    /**
     * Construct with SDK object involved in conversion.
     *
     * @param sdkIdvWorkflowConfig
     */
    public IdvWorkflowConfigConverter(IdvWorkflowConfig sdkIdvWorkflowConfig) {
        this.sdkIdvWorkflowConfig = sdkIdvWorkflowConfig;
    }

    /**
     * Convert from SDK to API.
     *
     * @return
     */
    public IdvWorkflowConfiguration toAPIIdvWorkflowConfiguration() {
        if (sdkIdvWorkflowConfig == null) {
            return apiIdvWorkflowConfiguration;
        }

        return new IdvWorkflowConfiguration()
                .setWorkflowId(sdkIdvWorkflowConfig.getId())
                .setType(sdkIdvWorkflowConfig.getType())
                .setTenant(sdkIdvWorkflowConfig.getTenant())
                .setDesc(sdkIdvWorkflowConfig.getDesc())
                .setSkipWhenAccessingSignedDocuments(sdkIdvWorkflowConfig.isSkipWhenAccessingSignedDocuments());
    }

    /**
     * Convert from API to SDK.
     *
     * @return
     */
    public IdvWorkflowConfig toSDKIdvWorkflowConfig() {
        if (apiIdvWorkflowConfiguration == null) {
            return sdkIdvWorkflowConfig;
        }

        IdvWorkflowConfig idvWorkflowConfig = IdvWorkflowConfigBuilder.newIdvWorkflowConfig(apiIdvWorkflowConfiguration.getWorkflowId())
                .withType(apiIdvWorkflowConfiguration.getType())
                .withTenant(apiIdvWorkflowConfiguration.getTenant())
                .withDesc(apiIdvWorkflowConfiguration.getDesc())
                .build();
        idvWorkflowConfig.setSkipWhenAccessingSignedDocuments(apiIdvWorkflowConfiguration.isSkipWhenAccessingSignedDocuments());
        return idvWorkflowConfig;
    }
}