package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IntegrationFrameworkWorkflow extends Model implements java.io.Serializable {

    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_CONNECTOR = "connector";

    // Empty Constructor
    public IntegrationFrameworkWorkflow() {
    }

    // Fields

    protected String name;
    protected Connector connector;


    public String getName() {
        return name;
    }

    public IntegrationFrameworkWorkflow setName(String value) {
        SchemaSanitizer.throwOnNull(FIELD_NAME, value);

        this.name = value;
        setDirty(FIELD_NAME);
        return this;
    }

    public Connector getConnector() {
        return connector;
    }

    public IntegrationFrameworkWorkflow setConnector(Connector value) {
        SchemaSanitizer.throwOnNull(FIELD_CONNECTOR, value);

        this.connector = value;
        setDirty(FIELD_CONNECTOR);
        return this;
    }
}
