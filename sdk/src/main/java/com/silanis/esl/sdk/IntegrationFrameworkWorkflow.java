package com.silanis.esl.sdk;


public class IntegrationFrameworkWorkflow {
    private String name;
    private Connector connector;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Connector getConnector() {
        return connector;
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }
}
