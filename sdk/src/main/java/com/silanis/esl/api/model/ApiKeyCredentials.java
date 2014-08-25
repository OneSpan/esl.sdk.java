package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class ApiKeyCredentials extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_APIKEY = "apiKey";
    
    // Empty Constructor
    public ApiKeyCredentials ( ) {}
    
    // Fields
    protected String _apiKey = "";
    
    // Accessors
        
    
    public ApiKeyCredentials setApiKey( String value ){
        SchemaSanitizer.throwOnNull(FIELD_APIKEY,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._apiKey == value ) return this;
        this._apiKey = value;
        setDirty(FIELD_APIKEY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ApiKeyCredentials safeSetApiKey( String value ){
        if ( value != null ) { this.setApiKey( value ); }
        return this;
    }
    public String getApiKey(){
        return _apiKey;
    }
    
    
}