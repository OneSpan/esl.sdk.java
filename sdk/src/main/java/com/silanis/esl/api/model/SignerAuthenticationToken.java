package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import com.silanis.esl.api.model.AuthenticationToken;
import java.util.List;
import java.util.ArrayList;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class SignerAuthenticationToken extends AuthenticationToken
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_PACKAGEID = "packageId";
    @JsonIgnore
    public static final String FIELD_SIGNERID = "signerId";
    @JsonIgnore
    public static final String FIELD_VALUE = "value";
    
    // Empty Constructor
    public SignerAuthenticationToken ( ) {}
    
    // Fields
    protected String _packageId = "";
    protected String _signerId = "";
    
    // Accessors
        
    
    public SignerAuthenticationToken setPackageId( String value ){
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
    public SignerAuthenticationToken safeSetPackageId( String value ){
        if ( value != null ) { this.setPackageId( value ); }
        return this;
    }
    public String getPackageId(){
        return _packageId;
    }
    
        
    
    public SignerAuthenticationToken setSignerId( String value ){
        SchemaSanitizer.throwOnNull(FIELD_SIGNERID,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._signerId == value ) return this;
        this._signerId = value;
        setDirty(FIELD_SIGNERID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerAuthenticationToken safeSetSignerId( String value ){
        if ( value != null ) { this.setSignerId( value ); }
        return this;
    }
    public String getSignerId(){
        return _signerId;
    }
    
        
    
    @Override
    public SignerAuthenticationToken setValue( String value ){
        super.setValue(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerAuthenticationToken safeSetValue( String value ){
        if ( value != null ) { this.setValue( value ); }
        return this;
    }
    
    
}