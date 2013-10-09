package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Delivery extends Model
      implements java.io.Serializable, IDelivery
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DOWNLOAD = "download";
    @JsonIgnore
    public static final String FIELD_EMAIL = "email";
    @JsonIgnore
    public static final String FIELD_PROVIDER = "provider";
    
    // Empty Constructor
    public Delivery ( ) {}
    
    // Fields
    protected Boolean _download = false;
    protected Boolean _email = true;
    protected Boolean _provider = false;
    
    // Accessors
        
    
    public Delivery setDownload( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DOWNLOAD,value);
        // TODO With proper compare
        // if ( this._download == value ) return this;
        this._download = value;
        setDirty(FIELD_DOWNLOAD);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Delivery safeSetDownload( Boolean value ){
        if ( value != null ) { this.setDownload( value ); }
        return this;
    }
    public Boolean getDownload(){
        return _download;
    }
    @JsonIgnore
    public boolean evalDownload(){
        return _download == null ? false : _download.booleanValue();
    }
    
        
    
    public Delivery setEmail( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_EMAIL,value);
        // TODO With proper compare
        // if ( this._email == value ) return this;
        this._email = value;
        setDirty(FIELD_EMAIL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Delivery safeSetEmail( Boolean value ){
        if ( value != null ) { this.setEmail( value ); }
        return this;
    }
    public Boolean getEmail(){
        return _email;
    }
    @JsonIgnore
    public boolean evalEmail(){
        return _email == null ? false : _email.booleanValue();
    }
    
        
    
    public Delivery setProvider( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_PROVIDER,value);
        // TODO With proper compare
        // if ( this._provider == value ) return this;
        this._provider = value;
        setDirty(FIELD_PROVIDER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Delivery safeSetProvider( Boolean value ){
        if ( value != null ) { this.setProvider( value ); }
        return this;
    }
    public Boolean getProvider(){
        return _provider;
    }
    @JsonIgnore
    public boolean evalProvider(){
        return _provider == null ? false : _provider.booleanValue();
    }
    
    
}