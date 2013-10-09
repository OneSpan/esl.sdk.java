package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class GuestAuth extends Auth
      implements java.io.Serializable, IGuestAuth
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CHALLENGES = "challenges";
    @JsonIgnore
    public static final String FIELD_LOGINTOKEN = "loginToken";
    @JsonIgnore
    public static final String FIELD_PACKAGE = "package";
    @JsonIgnore
    public static final String FIELD_SCHEME = "scheme";
    @JsonIgnore
    public static final String FIELD_USER = "user";
    
    // Empty Constructor
    public GuestAuth ( ) {}
    
    // Fields
    protected String _loginToken = "";
    protected Package _package;
    protected User _user;
    
    // Accessors
        
    
    @Override
    public GuestAuth setChallenges( List<AuthChallenge> value ){
        super.setChallenges(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GuestAuth safeSetChallenges( List<AuthChallenge> value ){
        if ( value != null ) { this.setChallenges( value ); }
        return this;
    }
    // List adder
    @Override
    public GuestAuth addChallenge( AuthChallenge value ){
        super.addChallenge(value);
        return this;
    }
    
        
    
    public GuestAuth setLoginToken( String value ){
        SchemaSanitizer.throwOnNull(FIELD_LOGINTOKEN,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._loginToken == value ) return this;
        this._loginToken = value;
        setDirty(FIELD_LOGINTOKEN);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GuestAuth safeSetLoginToken( String value ){
        if ( value != null ) { this.setLoginToken( value ); }
        return this;
    }
    public String getLoginToken(){
        return _loginToken;
    }
    
        
    
    public GuestAuth setPackage( Package value ){
        SchemaSanitizer.throwOnNull(FIELD_PACKAGE,value);
        // TODO With proper compare
        // if ( this._package == value ) return this;
        this._package = value;
        setDirty(FIELD_PACKAGE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GuestAuth safeSetPackage( Package value ){
        if ( value != null ) { this.setPackage( value ); }
        return this;
    }
    public Package getPackage(){
        return _package;
    }
    
        
    
    @Override
    public GuestAuth setScheme( AuthScheme value ){
        super.setScheme(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GuestAuth safeSetScheme( AuthScheme value ){
        if ( value != null ) { this.setScheme( value ); }
        return this;
    }
    
        
    
    public GuestAuth setUser( User value ){
        SchemaSanitizer.throwOnNull(FIELD_USER,value);
        // TODO With proper compare
        // if ( this._user == value ) return this;
        this._user = value;
        setDirty(FIELD_USER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GuestAuth safeSetUser( User value ){
        if ( value != null ) { this.setUser( value ); }
        return this;
    }
    public User getUser(){
        return _user;
    }
    
    
}