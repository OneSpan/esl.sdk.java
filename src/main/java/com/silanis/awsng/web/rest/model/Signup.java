package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Signup extends Credentials
      implements java.io.Serializable, ISignup
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_BILL = "bill";
    @JsonIgnore
    public static final String FIELD_EMAIL = "email";
    @JsonIgnore
    public static final String FIELD_EMAILVERIFIED = "emailVerified";
    @JsonIgnore
    public static final String FIELD_FIRSTNAME = "firstName";
    @JsonIgnore
    public static final String FIELD_LASTNAME = "lastName";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_NEWPASSWORD = "newPassword";
    @JsonIgnore
    public static final String FIELD_PASSWORD = "password";
    @JsonIgnore
    public static final String FIELD_PHONE = "phone";
    
    // Empty Constructor
    public Signup ( ) {}
    
    // Fields
    protected Bill _bill = null;
    protected Boolean _emailVerified = false;
    protected String _firstName = "";
    protected String _lastName = "";
    protected String _name = "";
    protected String _phone = "";
    
    // Accessors
        
    
    public Signup setBill( Bill value ){
        // TODO With proper compare
        // if ( this._bill == value ) return this;
        this._bill = value;
        setDirty(FIELD_BILL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signup safeSetBill( Bill value ){
        if ( value != null ) { this.setBill( value ); }
        return this;
    }
    public Bill getBill(){
        return _bill;
    }
    
        
    
    @Override
    public Signup setEmail( String value ){
        super.setEmail(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signup safeSetEmail( String value ){
        if ( value != null ) { this.setEmail( value ); }
        return this;
    }
    
        
    
    public Signup setEmailVerified( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_EMAILVERIFIED,value);
        // TODO With proper compare
        // if ( this._emailVerified == value ) return this;
        this._emailVerified = value;
        setDirty(FIELD_EMAILVERIFIED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signup safeSetEmailVerified( Boolean value ){
        if ( value != null ) { this.setEmailVerified( value ); }
        return this;
    }
    public Boolean getEmailVerified(){
        return _emailVerified;
    }
    @JsonIgnore
    public boolean evalEmailVerified(){
        return _emailVerified == null ? false : _emailVerified.booleanValue();
    }
    
        
    
    public Signup setFirstName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_FIRSTNAME,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._firstName == value ) return this;
        this._firstName = value;
        setDirty(FIELD_FIRSTNAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signup safeSetFirstName( String value ){
        if ( value != null ) { this.setFirstName( value ); }
        return this;
    }
    public String getFirstName(){
        return _firstName;
    }
    
        
    
    public Signup setLastName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_LASTNAME,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._lastName == value ) return this;
        this._lastName = value;
        setDirty(FIELD_LASTNAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signup safeSetLastName( String value ){
        if ( value != null ) { this.setLastName( value ); }
        return this;
    }
    public String getLastName(){
        return _lastName;
    }
    
        
    
    public Signup setName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_NAME,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._name == value ) return this;
        this._name = value;
        setDirty(FIELD_NAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signup safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    public String getName(){
        return _name;
    }
    
        
    
    @Override
    public Signup setNewPassword( String value ){
        super.setNewPassword(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signup safeSetNewPassword( String value ){
        if ( value != null ) { this.setNewPassword( value ); }
        return this;
    }
    
        
    
    @Override
    public Signup setPassword( String value ){
        super.setPassword(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signup safeSetPassword( String value ){
        if ( value != null ) { this.setPassword( value ); }
        return this;
    }
    
        
    
    public Signup setPhone( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._phone == value ) return this;
        this._phone = value;
        setDirty(FIELD_PHONE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signup safeSetPhone( String value ){
        if ( value != null ) { this.setPhone( value ); }
        return this;
    }
    public String getPhone(){
        return _phone;
    }
    
    
}