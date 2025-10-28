package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    public static final String FIELD_SESSION_FIELDS = "sessionFields";
    @JsonIgnore
    public static final String FIELD_DELEGATEEID = "delegateeId";

    @JsonIgnore
    public static final String FIELD_VALUE = "value";
    
    // Empty Constructor
    public SignerAuthenticationToken ( ) {}
    
    // Fields
    protected String _packageId = "";
    protected String _signerId = "";
    protected String _delegateeId = null;
    protected SessionFields _sessionFields = null;
    
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
        if ( value != null ) { this.setSignerId(value); }
        return this;
    }
    public String getSignerId(){
        return _signerId;
    }



    public SignerAuthenticationToken setSessionFields( SessionFields value ){
        // if ( this._sessionFields == value ) return this;
        this._sessionFields = value;
        setDirty(FIELD_SESSION_FIELDS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerAuthenticationToken safeSetSessionFields( SessionFields value ){
        if ( value != null ) { this.setSessionFields(value); }
        return this;
    }
    public SessionFields getSessionFields(){
        return _sessionFields;
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

    public SignerAuthenticationToken setDelegateeId( String value ){
        value = SchemaSanitizer.trim(value);
        this._delegateeId = value;
        setDirty(FIELD_DELEGATEEID);
        return this;
    }

    // Used internally by aws. Invokes a corresponding setter if the value is not null
    @JsonIgnore
    public SignerAuthenticationToken safeSetDelegateeId( String value ){
        if ( value != null ) { this.setDelegateeId( value ); }
        return this;
    }

    public String getDelegateeId(){
        return _delegateeId;
    }

}