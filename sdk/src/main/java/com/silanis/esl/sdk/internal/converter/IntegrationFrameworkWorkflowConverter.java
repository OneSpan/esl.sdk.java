package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Connector;
import com.silanis.esl.api.model.IntegrationFrameworkWorkflow;

import java.util.ArrayList;
import java.util.List;

public class IntegrationFrameworkWorkflowConverter {

    public static IntegrationFrameworkWorkflow toAPI(com.silanis.esl.sdk.IntegrationFrameworkWorkflow sdkIfWorkflow) {
        if (sdkIfWorkflow == null) {
            return null;
        }
        IntegrationFrameworkWorkflow apiIfWorkflow = new IntegrationFrameworkWorkflow();
        apiIfWorkflow.setName(sdkIfWorkflow.getName());
        apiIfWorkflow.setConnector(Connector.valueOf(sdkIfWorkflow.getConnector().getValue()));

        return apiIfWorkflow;
    }

    public static com.silanis.esl.sdk.IntegrationFrameworkWorkflow toSDK(IntegrationFrameworkWorkflow apiIfWorkflow) {
        if (apiIfWorkflow == null) {
            return null;
        }
        com.silanis.esl.sdk.IntegrationFrameworkWorkflow sdkIfWorkflow = new com.silanis.esl.sdk.IntegrationFrameworkWorkflow();
        sdkIfWorkflow.setName(apiIfWorkflow.getName());
        sdkIfWorkflow.setConnector(com.silanis.esl.sdk.Connector.valueOf(apiIfWorkflow.getConnector().getValue()));

        return sdkIfWorkflow;
    }

    public static List<com.silanis.esl.sdk.IntegrationFrameworkWorkflow> toSDKList(List<IntegrationFrameworkWorkflow> apiIfWorkflows) {
        List<com.silanis.esl.sdk.IntegrationFrameworkWorkflow> sdkIfWorkflows = new ArrayList<>();
        for (IntegrationFrameworkWorkflow apiIfWorkflow : apiIfWorkflows) {
            sdkIfWorkflows.add(IntegrationFrameworkWorkflowConverter.toSDK(apiIfWorkflow));
        }
        return sdkIfWorkflows;
    }
}
