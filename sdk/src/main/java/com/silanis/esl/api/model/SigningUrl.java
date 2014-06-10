package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class SigningUrl extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_PACKAGEID = "packageId";
    @JsonIgnore
    public static final String FIELD_ROLEID = "roleId";
    @JsonIgnore
    public static final String FIELD_URL = "url";
    
    // Empty Constructor
    public SigningUrl ( ) {}
    
    // Fields
    protected String _packageId = "";
    protected String _roleId = "";
    protected String _url = "";
    
    // Accessors
        
    
    public SigningUrl setPackageId( String value ){
        SchemaSanitizer.throwOnNull(FIELD_PACKAGEID,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._packageId == value ) return this;
        this._packageId = value;
        setDirty(FIELD_PACKAGEID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SigningUrl safeSetPackageId( String value ){
        if ( value != null ) { this.setPackageId( value ); }
        return this;
    }
    public String getPackageId(){
        return _packageId;
    }
    
        
    
    public SigningUrl setRoleId( String value ){
        SchemaSanitizer.throwOnNull(FIELD_ROLEID,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._roleId == value ) return this;
        this._roleId = value;
        setDirty(FIELD_ROLEID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SigningUrl safeSetRoleId( String value ){
        if ( value != null ) { this.setRoleId( value ); }
        return this;
    }
    public String getRoleId(){
        return _roleId;
    }
    
        
    
    public SigningUrl setUrl( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._url == value ) return this;
        this._url = value;
        setDirty(FIELD_URL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SigningUrl safeSetUrl( String value ){
        if ( value != null ) { this.setUrl( value ); }
        return this;
    }
    public String getUrl(){
        return _url;
    }
    
    
}