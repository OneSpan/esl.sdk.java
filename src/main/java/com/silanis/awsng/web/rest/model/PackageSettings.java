package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class PackageSettings extends Settings
      implements java.io.Serializable, IPackageSettings
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CEREMONY = "ceremony";
    
    // Empty Constructor
    public PackageSettings ( ) {}
    
    // Fields
    protected CeremonySettings _ceremony;
    
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
    
    
}