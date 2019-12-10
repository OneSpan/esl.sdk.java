package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Role extends Entity implements java.io.Serializable {

    @JsonIgnore
    public static final String LOCAL_LANGUAGE_DATA_KEY = "localLanguage";

    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ATTACHMENTREQUIREMENTS = "attachmentRequirements";
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_EMAILMESSAGE = "emailMessage";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_INDEX = "index";
    @JsonIgnore
    public static final String FIELD_LOCKED = "locked";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_REASSIGN = "reassign";
    @JsonIgnore
    public static final String FIELD_SIGNERS = "signers";
    @JsonIgnore
    public static final String FIELD_SPECIALTYPES = "specialTypes";
    @JsonIgnore
    public static final String FIELD_TYPE = "type";
    
    // Empty Constructor
    public Role ( ) {}
    
    // Fields
    protected List<AttachmentRequirement> _attachmentRequirements = new ArrayList<AttachmentRequirement>();
    protected BaseMessage _emailMessage = null;
    protected Integer _index = 0;
    protected Boolean _locked = false;
    protected Boolean _reassign = false;
    protected List<Signer> _signers = new ArrayList<Signer>();
    protected List<String> _specialTypes = new ArrayList<String>();
    protected String _type = "SIGNER";
    
    // Accessors
        
    
    public Role setAttachmentRequirements( List<AttachmentRequirement> value ){
        SchemaSanitizer.throwOnNull(FIELD_ATTACHMENTREQUIREMENTS,value);
        // TODO With proper compare
        // if ( this._attachmentRequirements == value ) return this;
        this._attachmentRequirements = value;
        setDirty(FIELD_ATTACHMENTREQUIREMENTS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Role safeSetAttachmentRequirements( List<AttachmentRequirement> value ){
        if ( value != null ) { this.setAttachmentRequirements( value ); }
        return this;
    }
    public List<AttachmentRequirement> getAttachmentRequirements(){
        return _attachmentRequirements;
    }
    // List adder
    public Role addAttachmentRequirement( AttachmentRequirement value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._attachmentRequirements.add(value);
        setDirty(FIELD_ATTACHMENTREQUIREMENTS);
        return this;
    }
    
        
    
    @Override
    public Role setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Role safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
    public Role putData(String key, Object value) {
        if (_data == null) {
            super.setData(new HashMap<String, Object>());
        }
        _data.put(key, value);
        return this;
    }
    
    public Role setEmailMessage( BaseMessage value ){
        // TODO With proper compare
        // if ( this._emailMessage == value ) return this;
        this._emailMessage = value;
        setDirty(FIELD_EMAILMESSAGE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Role safeSetEmailMessage( BaseMessage value ){
        if ( value != null ) { this.setEmailMessage( value ); }
        return this;
    }
    public BaseMessage getEmailMessage(){
        return _emailMessage;
    }
    
        
    
    @Override
    public Role setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Role safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    public Role setIndex( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_INDEX,value);
        // TODO With proper compare
        // if ( this._index == value ) return this;
        this._index = value;
        setDirty(FIELD_INDEX);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Role safeSetIndex( Integer value ){
        if ( value != null ) { this.setIndex( value ); }
        return this;
    }
    public Integer getIndex(){
        return _index;
    }
    
        
    
    public Role setLocked( Boolean value ){
        // TODO With proper compare
        // if ( this._locked == value ) return this;
        this._locked = value;
        setDirty(FIELD_LOCKED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Role safeSetLocked( Boolean value ){
        if ( value != null ) { this.setLocked( value ); }
        return this;
    }
    public Boolean getLocked(){
        return _locked;
    }
    @JsonIgnore
    public boolean evalLocked(){
        return _locked == null ? false : _locked.booleanValue();
    }
    
        
    
    @Override
    public Role setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Role safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    public Role setReassign( Boolean value ){
        // TODO With proper compare
        // if ( this._reassign == value ) return this;
        this._reassign = value;
        setDirty(FIELD_REASSIGN);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Role safeSetReassign( Boolean value ){
        if ( value != null ) { this.setReassign( value ); }
        return this;
    }
    public Boolean getReassign(){
        return _reassign;
    }
    @JsonIgnore
    public boolean evalReassign(){
        return _reassign == null ? false : _reassign.booleanValue();
    }
    
        
    
    public Role setSigners( List<Signer> value ){
        SchemaSanitizer.throwOnNull(FIELD_SIGNERS,value);
        // TODO With proper compare
        // if ( this._signers == value ) return this;
        this._signers = value;
        setDirty(FIELD_SIGNERS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Role safeSetSigners( List<Signer> value ){
        if ( value != null ) { this.setSigners( value ); }
        return this;
    }
    public List<Signer> getSigners(){
        return _signers;
    }
    // List adder
    public Role addSigner( Signer value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._signers.add(value);
        setDirty(FIELD_SIGNERS);
        return this;
    }
    
        
    
    public Role setSpecialTypes( List<String> value ){
        SchemaSanitizer.throwOnNull(FIELD_SPECIALTYPES,value);
        // TODO With proper compare
        // if ( this._specialTypes == value ) return this;
        this._specialTypes = value;
        setDirty(FIELD_SPECIALTYPES);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Role safeSetSpecialTypes( List<String> value ){
        if ( value != null ) { this.setSpecialTypes( value ); }
        return this;
    }
    public List<String> getSpecialTypes(){
        return _specialTypes;
    }
    // List adder
    public Role addSpecialType( String value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._specialTypes.add(value);
        setDirty(FIELD_SPECIALTYPES);
        return this;
    }
    
        
    
    public Role setType( String value ){
        SchemaSanitizer.throwOnNull(FIELD_TYPE,value);
        // TODO With proper compare
        // if ( this._type == value ) return this;
        this._type = value;
        setDirty(FIELD_TYPE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Role safeSetType( String value ){
        if ( value != null ) { this.setType( value ); }
        return this;
    }
    public String getType(){
        return _type;
    }
    
    
}