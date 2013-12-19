package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Session extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ACCOUNT = "account";
    @JsonIgnore
    public static final String FIELD_FEATURES = "features";
    @JsonIgnore
    public static final String FIELD_INPERSON = "inPerson";
    @JsonIgnore
    public static final String FIELD_PACKAGES = "packages";
    @JsonIgnore
    public static final String FIELD_USER = "user";
    
    // Empty Constructor
    public Session ( ) {}
    
    // Fields
    protected Account _account = null;
    protected Features _features = null;
    protected Boolean _inPerson = false;
    protected List<String> _packages = new ArrayList<String>();
    protected User _user;
    
    // Accessors
        
    
    public Session setAccount( Account value ){
        // TODO With proper compare
        // if ( this._account == value ) return this;
        this._account = value;
        setDirty(FIELD_ACCOUNT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Session safeSetAccount( Account value ){
        if ( value != null ) { this.setAccount( value ); }
        return this;
    }
    public Account getAccount(){
        return _account;
    }
    
        
    
    public Session setFeatures( Features value ){
        // TODO With proper compare
        // if ( this._features == value ) return this;
        this._features = value;
        setDirty(FIELD_FEATURES);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Session safeSetFeatures( Features value ){
        if ( value != null ) { this.setFeatures( value ); }
        return this;
    }
    public Features getFeatures(){
        return _features;
    }
    
        
    
    public Session setInPerson( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_INPERSON,value);
        // TODO With proper compare
        // if ( this._inPerson == value ) return this;
        this._inPerson = value;
        setDirty(FIELD_INPERSON);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Session safeSetInPerson( Boolean value ){
        if ( value != null ) { this.setInPerson( value ); }
        return this;
    }
    public Boolean getInPerson(){
        return _inPerson;
    }
    @JsonIgnore
    public boolean evalInPerson(){
        return _inPerson == null ? false : _inPerson.booleanValue();
    }
    
        
    
    public Session setPackages( List<String> value ){
        SchemaSanitizer.throwOnNull(FIELD_PACKAGES,value);
        // TODO With proper compare
        // if ( this._packages == value ) return this;
        this._packages = value;
        setDirty(FIELD_PACKAGES);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Session safeSetPackages( List<String> value ){
        if ( value != null ) { this.setPackages( value ); }
        return this;
    }
    public List<String> getPackages(){
        return _packages;
    }
    // List adder
    public Session addPackage( String value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._packages.add(value);
        setDirty(FIELD_PACKAGES);
        return this;
    }
    
        
    
    public Session setUser( User value ){
        SchemaSanitizer.throwOnNull(FIELD_USER,value);
        // TODO With proper compare
        // if ( this._user == value ) return this;
        this._user = value;
        setDirty(FIELD_USER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Session safeSetUser( User value ){
        if ( value != null ) { this.setUser( value ); }
        return this;
    }
    public User getUser(){
        return _user;
    }
    
    
}