package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;

import java.util.Map;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class AttachmentRequirement extends Entity
      implements java.io.Serializable, IAttachmentRequirement
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ATTACHMENTBIN = "attachmentBin";
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_DESCRIPTION = "description";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_REQUIRED = "required";
    @JsonIgnore
    public static final String FIELD_STATUS = "status";
    
    // Empty Constructor
    public AttachmentRequirement ( ) {}
    
    // Fields
    protected AttachmentBin _attachmentBin = null;
    protected String _description = "";
    protected Boolean _required = true;
    protected RequirementStatus _status = RequirementStatus.INCOMPLETE;
    
    // Accessors
        
    
    public AttachmentRequirement setAttachmentBin( AttachmentBin value ){
        // TODO With proper compare
        // if ( this._attachmentBin == value ) return this;
        this._attachmentBin = value;
        setDirty(FIELD_ATTACHMENTBIN);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AttachmentRequirement safeSetAttachmentBin( AttachmentBin value ){
        if ( value != null ) { this.setAttachmentBin( value ); }
        return this;
    }
    public AttachmentBin getAttachmentBin(){
        return _attachmentBin;
    }
    
        
    
    @Override
    public AttachmentRequirement setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AttachmentRequirement safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    public AttachmentRequirement setDescription( String value ){
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._description == value ) return this;
        this._description = value;
        setDirty(FIELD_DESCRIPTION);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AttachmentRequirement safeSetDescription( String value ){
        if ( value != null ) { this.setDescription( value ); }
        return this;
    }
    public String getDescription(){
        return _description;
    }
    
        
    
    @Override
    public AttachmentRequirement setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AttachmentRequirement safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    @Override
    public AttachmentRequirement setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AttachmentRequirement safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    public AttachmentRequirement setRequired( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_REQUIRED,value);
        // TODO With proper compare
        // if ( this._required == value ) return this;
        this._required = value;
        setDirty(FIELD_REQUIRED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AttachmentRequirement safeSetRequired( Boolean value ){
        if ( value != null ) { this.setRequired( value ); }
        return this;
    }
    public Boolean getRequired(){
        return _required;
    }
    @JsonIgnore
    public boolean evalRequired(){
        return _required == null ? false : _required.booleanValue();
    }
    
        
    
    public AttachmentRequirement setStatus( RequirementStatus value ){
        SchemaSanitizer.throwOnNull(FIELD_STATUS,value);
        // TODO With proper compare
        // if ( this._status == value ) return this;
        this._status = value;
        setDirty(FIELD_STATUS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AttachmentRequirement safeSetStatus( RequirementStatus value ){
        if ( value != null ) { this.setStatus( value ); }
        return this;
    }
    public RequirementStatus getStatus(){
        return _status;
    }
    
    
}