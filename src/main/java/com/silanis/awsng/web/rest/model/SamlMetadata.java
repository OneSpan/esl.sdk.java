package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class SamlMetadata extends Model
      implements java.io.Serializable, ISamlMetadata
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ACCOUNTUID = "accountUid";
    @JsonIgnore
    public static final String FIELD_ACTIVE = "active";
    @JsonIgnore
    public static final String FIELD_ENTITYID = "entityId";
    @JsonIgnore
    public static final String FIELD_METADATA = "metadata";
    @JsonIgnore
    public static final String FIELD_PUBLICKEY = "publicKey";
    @JsonIgnore
    public static final String FIELD_UID = "uid";
    @JsonIgnore
    public static final String FIELD_URL = "url";
    
    // Empty Constructor
    public SamlMetadata ( ) {}
    
    // Fields
    protected String _accountUid = "";
    protected Boolean _active = true;
    protected String _entityId = "";
    protected String _metadata = "";
    protected String _publicKey = "";
    protected String _uid = "";
    protected String _url = "";
    
    // Accessors
        
    
    public SamlMetadata setAccountUid( String value ){
        SchemaSanitizer.throwOnNull(FIELD_ACCOUNTUID,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._accountUid == value ) return this;
        this._accountUid = value;
        setDirty(FIELD_ACCOUNTUID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SamlMetadata safeSetAccountUid( String value ){
        if ( value != null ) { this.setAccountUid( value ); }
        return this;
    }
    public String getAccountUid(){
        return _accountUid;
    }
    
        
    
    public SamlMetadata setActive( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_ACTIVE,value);
        // TODO With proper compare
        // if ( this._active == value ) return this;
        this._active = value;
        setDirty(FIELD_ACTIVE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SamlMetadata safeSetActive( Boolean value ){
        if ( value != null ) { this.setActive( value ); }
        return this;
    }
    public Boolean getActive(){
        return _active;
    }
    @JsonIgnore
    public boolean evalActive(){
        return _active == null ? false : _active.booleanValue();
    }
    
        
    
    public SamlMetadata setEntityId( String value ){
        SchemaSanitizer.throwOnNull(FIELD_ENTITYID,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._entityId == value ) return this;
        this._entityId = value;
        setDirty(FIELD_ENTITYID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SamlMetadata safeSetEntityId( String value ){
        if ( value != null ) { this.setEntityId( value ); }
        return this;
    }
    public String getEntityId(){
        return _entityId;
    }
    
        
    
    public SamlMetadata setMetadata( String value ){
        SchemaSanitizer.throwOnNull(FIELD_METADATA,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._metadata == value ) return this;
        this._metadata = value;
        setDirty(FIELD_METADATA);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SamlMetadata safeSetMetadata( String value ){
        if ( value != null ) { this.setMetadata( value ); }
        return this;
    }
    public String getMetadata(){
        return _metadata;
    }
    
        
    
    public SamlMetadata setPublicKey( String value ){
        SchemaSanitizer.throwOnNull(FIELD_PUBLICKEY,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._publicKey == value ) return this;
        this._publicKey = value;
        setDirty(FIELD_PUBLICKEY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SamlMetadata safeSetPublicKey( String value ){
        if ( value != null ) { this.setPublicKey( value ); }
        return this;
    }
    public String getPublicKey(){
        return _publicKey;
    }
    
        
    
    public SamlMetadata setUid( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._uid == value ) return this;
        this._uid = value;
        setDirty(FIELD_UID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SamlMetadata safeSetUid( String value ){
        if ( value != null ) { this.setUid( value ); }
        return this;
    }
    public String getUid(){
        return _uid;
    }
    
        
    
    public SamlMetadata setUrl( String value ){
        SchemaSanitizer.throwOnNull(FIELD_URL,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._url == value ) return this;
        this._url = value;
        setDirty(FIELD_URL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SamlMetadata safeSetUrl( String value ){
        if ( value != null ) { this.setUrl( value ); }
        return this;
    }
    public String getUrl(){
        return _url;
    }
    
    
}