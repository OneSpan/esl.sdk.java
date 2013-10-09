package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class PackageArtifactsLimits extends Model
      implements java.io.Serializable, IPackageArtifactsLimits
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DOCUMENTS = "documents";
    @JsonIgnore
    public static final String FIELD_ROLES = "roles";
    
    // Empty Constructor
    public PackageArtifactsLimits ( ) {}
    
    // Fields
    protected Integer _documents = null;
    protected Integer _roles = null;
    
    // Accessors
        
    
    public PackageArtifactsLimits setDocuments( Integer value ){
        // TODO With proper compare
        // if ( this._documents == value ) return this;
        this._documents = value;
        setDirty(FIELD_DOCUMENTS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageArtifactsLimits safeSetDocuments( Integer value ){
        if ( value != null ) { this.setDocuments( value ); }
        return this;
    }
    public Integer getDocuments(){
        return _documents;
    }
    
        
    
    public PackageArtifactsLimits setRoles( Integer value ){
        // TODO With proper compare
        // if ( this._roles == value ) return this;
        this._roles = value;
        setDirty(FIELD_ROLES);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageArtifactsLimits safeSetRoles( Integer value ){
        if ( value != null ) { this.setRoles( value ); }
        return this;
    }
    public Integer getRoles(){
        return _roles;
    }
    
    
}