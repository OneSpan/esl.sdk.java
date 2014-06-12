package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class SupportConfiguration extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_EMAIL = "email";
    @JsonIgnore
    public static final String FIELD_PHONE = "phone";
    
    // Empty Constructor
    public SupportConfiguration ( ) {}
    
    // Fields
    protected String _email = "";
    protected String _phone = "";
    
    // Accessors
        
    
    public SupportConfiguration setEmail( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._email == value ) return this;
        this._email = value;
        setDirty(FIELD_EMAIL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SupportConfiguration safeSetEmail( String value ){
        if ( value != null ) { this.setEmail( value ); }
        return this;
    }
    public String getEmail(){
        return _email;
    }
    
        
    
    public SupportConfiguration setPhone( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._phone == value ) return this;
        this._phone = value;
        setDirty(FIELD_PHONE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SupportConfiguration safeSetPhone( String value ){
        if ( value != null ) { this.setPhone( value ); }
        return this;
    }
    public String getPhone(){
        return _phone;
    }
    
    
}