package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class PackageSettings extends Settings
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CEREMONY = "ceremony";
    @JsonIgnore
    public static final String FIELD_INTEGRATION_FRAMEWORK_WORKFLOWS = "integrationFrameworkWorkflows";
    
    // Empty Constructor
    public PackageSettings ( ) {}
    
    // Fields
    protected CeremonySettings _ceremony;
    protected List<IntegrationFrameworkWorkflow> integrationFrameworkWorkflows = new ArrayList<>();
    
    // Accessors
        
    
    public PackageSettings setCeremony( CeremonySettings value ){
        SchemaSanitizer.throwOnNull(FIELD_CEREMONY,value);
        // TODO With proper compare
        // if ( this._ceremony == value ) return this;
        this._ceremony = value;
        setDirty(FIELD_CEREMONY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageSettings safeSetCeremony( CeremonySettings value ){
        if ( value != null ) { this.setCeremony( value ); }
        return this;
    }
    public CeremonySettings getCeremony(){
        return _ceremony;
    }

    public PackageSettings setIntegrationFrameworkWorkflows(List<IntegrationFrameworkWorkflow> value) {
        SchemaSanitizer.throwOnNull(FIELD_INTEGRATION_FRAMEWORK_WORKFLOWS, value);

        this.integrationFrameworkWorkflows = value;
        setDirty(FIELD_INTEGRATION_FRAMEWORK_WORKFLOWS);
        return this;
    }

    @JsonIgnore
    public PackageSettings safeSetIntegrationFrameworkWorkflows(List<IntegrationFrameworkWorkflow> value) {
        if (value != null) {
            this.setIntegrationFrameworkWorkflows(value);
        }
        return this;
    }

    public List<IntegrationFrameworkWorkflow> getIntegrationFrameworkWorkflows() {
        return integrationFrameworkWorkflows;
    }

    public PackageSettings addIntegrationFrameworkWorkflow(IntegrationFrameworkWorkflow value) {
        if (value == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        this.integrationFrameworkWorkflows.add(value);
        setDirty(FIELD_INTEGRATION_FRAMEWORK_WORKFLOWS);
        return this;
    }
}