package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.IdvWorkflow;
import com.silanis.esl.sdk.builder.IdvWorkflowBuilder;

/**
 * Created by schoi on 2021-03-10.
 */
public class IdvWorkflowConverter {

    private IdvWorkflow apiIdvWorkflow;
    private com.silanis.esl.sdk.IdvWorkflow sdkIdvWorkflow;


    /**
     * Construct with API object involved in conversion.
     *
     * @param apiIdvWorkflow
     */
    public IdvWorkflowConverter(IdvWorkflow apiIdvWorkflow) {
        this.apiIdvWorkflow = apiIdvWorkflow;
    }

    /**
     * Construct with SDK object involved in conversion.
     *
     * @param sdkIdvWorkflow
     */
    public IdvWorkflowConverter(com.silanis.esl.sdk.IdvWorkflow sdkIdvWorkflow) {
        this.sdkIdvWorkflow = sdkIdvWorkflow;
    }

    /**
     * Convert from SDK to API.
     *
     * @return
     */
    public IdvWorkflow toAPIIdvWorkflow() {
        if (sdkIdvWorkflow == null) {
            return apiIdvWorkflow;
        }

        return new IdvWorkflow()
                .setId(sdkIdvWorkflow.getId())
                .setType(sdkIdvWorkflow.getType())
                .setTenant(sdkIdvWorkflow.getTenant())
                .setDesc(sdkIdvWorkflow.getDesc());
    }

    /**
     * Convert from API to SDK.
     *
     * @return
     */
    public com.silanis.esl.sdk.IdvWorkflow toSDKIdvWorkflow() {
        if (apiIdvWorkflow == null) {
            return sdkIdvWorkflow;
        }

        return IdvWorkflowBuilder
                .newIdvWorkflow(apiIdvWorkflow.getId())
                .withType(apiIdvWorkflow.getType())
                .withTenant(apiIdvWorkflow.getTenant())
                .withDesc(apiIdvWorkflow.getDesc())
                .build();
    }

}