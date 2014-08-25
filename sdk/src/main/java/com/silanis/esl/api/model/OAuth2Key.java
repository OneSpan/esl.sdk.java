package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class OAuth2Key extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CLIENTID = "clientId";
    @JsonIgnore
    public static final String FIELD_CLIENTSECRET = "clientSecret";
    
    // Empty Constructor
    public OAuth2Key ( ) {}
    
    // Fields
    protected String _clientId = "";
    protected String _clientSecret = "";
    
    // Accessors
        
    
    public OAuth2Key setClientId( String value ){
        SchemaSanitizer.throwOnNull(FIELD_CLIENTID,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._clientId == value ) return this;
        this._clientId = value;
        setDirty(FIELD_CLIENTID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public OAuth2Key safeSetClientId( String value ){
        if ( value != null ) { this.setClientId( value ); }
        return this;
    }
    public String getClientId(){
        return _clientId;
    }
    
        
    
    public OAuth2Key setClientSecret( String value ){
        SchemaSanitizer.throwOnNull(FIELD_CLIENTSECRET,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._clientSecret == value ) return this;
        this._clientSecret = value;
        setDirty(FIELD_CLIENTSECRET);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public OAuth2Key safeSetClientSecret( String value ){
        if ( value != null ) { this.setClientSecret( value ); }
        return this;
    }
    public String getClientSecret(){
        return _clientSecret;
    }
    
    
}