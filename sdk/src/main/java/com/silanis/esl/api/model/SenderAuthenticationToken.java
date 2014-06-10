package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class SenderAuthenticationToken extends AuthenticationToken
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_PACKAGEID = "packageId";
    @JsonIgnore
    public static final String FIELD_VALUE = "value";
    
    // Empty Constructor
    public SenderAuthenticationToken ( ) {}
    
    // Fields
    protected String _packageId = "";
    
    // Accessors
        
    
    public SenderAuthenticationToken setPackageId( String value ){
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
    public SenderAuthenticationToken safeSetPackageId( String value ){
        if ( value != null ) { this.setPackageId( value ); }
        return this;
    }
    public String getPackageId(){
        return _packageId;
    }
    
        
    
    @Override
    public SenderAuthenticationToken setValue( String value ){
        super.setValue(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SenderAuthenticationToken safeSetValue( String value ){
        if ( value != null ) { this.setValue( value ); }
        return this;
    }
    
    
}