package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Credentials extends Model
      implements java.io.Serializable, ICredentials
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_EMAIL = "email";
    @JsonIgnore
    public static final String FIELD_NEWPASSWORD = "newPassword";
    @JsonIgnore
    public static final String FIELD_PASSWORD = "password";
    
    // Empty Constructor
    public Credentials ( ) {}
    
    // Fields
    protected String _email = "";
    protected String _newPassword = null;
    protected String _password = "";
    
    // Accessors
        
    
    public Credentials setEmail( String value ){
        SchemaSanitizer.throwOnNull(FIELD_EMAIL,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._email == value ) return this;
        this._email = value;
        setDirty(FIELD_EMAIL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Credentials safeSetEmail( String value ){
        if ( value != null ) { this.setEmail( value ); }
        return this;
    }
    public String getEmail(){
        return _email;
    }
    
        
    
    public Credentials setNewPassword( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._newPassword == value ) return this;
        this._newPassword = value;
        setDirty(FIELD_NEWPASSWORD);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Credentials safeSetNewPassword( String value ){
        if ( value != null ) { this.setNewPassword( value ); }
        return this;
    }
    public String getNewPassword(){
        return _newPassword;
    }
    
        
    
    public Credentials setPassword( String value ){
        SchemaSanitizer.throwOnNull(FIELD_PASSWORD,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._password == value ) return this;
        this._password = value;
        setDirty(FIELD_PASSWORD);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Credentials safeSetPassword( String value ){
        if ( value != null ) { this.setPassword( value ); }
        return this;
    }
    public String getPassword(){
        return _password;
    }
    
    
}