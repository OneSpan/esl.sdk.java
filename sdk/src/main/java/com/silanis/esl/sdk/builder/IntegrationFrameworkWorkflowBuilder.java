package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Connector;
import com.silanis.esl.sdk.IntegrationFrameworkWorkflow;

/**
 * Builder object used to specify connector used for external archiving.
 *
 */
public class IntegrationFrameworkWorkflowBuilder {
    private String name = null;
    private Connector connector = null;

    /**
     * Creates a new integration-framework workflow builder.
     *
     * @return This
     */
    public static IntegrationFrameworkWorkflowBuilder newIntegrationFrameworkWorkflow() {
        return new IntegrationFrameworkWorkflowBuilder();
    }

    private IntegrationFrameworkWorkflowBuilder() {}

    /**
     * Set the name of integration-framework workflow.
     *
     * @return This
     */
    public IntegrationFrameworkWorkflowBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set the connector used in the integration-framework workflow.
     *
     * @return This
     */
    public IntegrationFrameworkWorkflowBuilder withConnector(Connector connector) {
        this.connector = connector;
        return this;
    }

    /**
     * Builds the actual integration-framework workflow.
     *
     * @return the integration-framework workflow
     */
    public IntegrationFrameworkWorkflow build() {
        IntegrationFrameworkWorkflow result = new IntegrationFrameworkWorkflow();
        result.setName(name);
        result.setConnector(connector);
        return result;
    }
}
