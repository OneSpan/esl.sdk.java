package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Features extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ATTACHMENTS = "attachments";
    @JsonIgnore
    public static final String FIELD_FASTTRACK = "fasttrack";
    @JsonIgnore
    public static final String FIELD_GROUPS = "groups";
    
    // Empty Constructor
    public Features ( ) {}
    
    // Fields
    protected Boolean _attachments = false;
    protected Boolean _fasttrack = false;
    protected Boolean _groups = false;
    
    // Accessors
        
    
    public Features setAttachments( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_ATTACHMENTS,value);
        // TODO With proper compare
        // if ( this._attachments == value ) return this;
        this._attachments = value;
        setDirty(FIELD_ATTACHMENTS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Features safeSetAttachments( Boolean value ){
        if ( value != null ) { this.setAttachments( value ); }
        return this;
    }
    public Boolean getAttachments(){
        return _attachments;
    }
    @JsonIgnore
    public boolean evalAttachments(){
        return _attachments == null ? false : _attachments.booleanValue();
    }
    
        
    
    public Features setFasttrack( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_FASTTRACK,value);
        // TODO With proper compare
        // if ( this._fasttrack == value ) return this;
        this._fasttrack = value;
        setDirty(FIELD_FASTTRACK);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Features safeSetFasttrack( Boolean value ){
        if ( value != null ) { this.setFasttrack( value ); }
        return this;
    }
    public Boolean getFasttrack(){
        return _fasttrack;
    }
    @JsonIgnore
    public boolean evalFasttrack(){
        return _fasttrack == null ? false : _fasttrack.booleanValue();
    }
    
        
    
    public Features setGroups( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_GROUPS,value);
        // TODO With proper compare
        // if ( this._groups == value ) return this;
        this._groups = value;
        setDirty(FIELD_GROUPS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Features safeSetGroups( Boolean value ){
        if ( value != null ) { this.setGroups( value ); }
        return this;
    }
    public Boolean getGroups(){
        return _groups;
    }
    @JsonIgnore
    public boolean evalGroups(){
        return _groups == null ? false : _groups.booleanValue();
    }
    
    
}