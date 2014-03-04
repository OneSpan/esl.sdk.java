package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class AuthenticationToken extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_VALUE = "value";
    
    // Empty Constructor
    public AuthenticationToken ( ) {}
    
    // Fields
    protected String _value = "";
    
    // Accessors
        
    
    public AuthenticationToken setValue( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._value == value ) return this;
        this._value = value;
        setDirty(FIELD_VALUE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AuthenticationToken safeSetValue( String value ){
        if ( value != null ) { this.setValue( value ); }
        return this;
    }
    public String getValue(){
        return _value;
    }
    
    
}