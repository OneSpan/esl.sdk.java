package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreditCard extends Model
      implements java.io.Serializable, ICreditCard
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CVV = "cvv";
    @JsonIgnore
    public static final String FIELD_EXPIRATION = "expiration";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_NUMBER = "number";
    @JsonIgnore
    public static final String FIELD_TYPE = "type";
    
    // Empty Constructor
    public CreditCard ( ) {}
    
    // Fields
    protected String _cvv = "";
    protected CcExpiration _expiration;
    protected String _name = "";
    protected String _number = "";
    protected String _type = "";
    
    // Accessors
        
    
    public CreditCard setCvv( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._cvv == value ) return this;
        this._cvv = value;
        setDirty(FIELD_CVV);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CreditCard safeSetCvv( String value ){
        if ( value != null ) { this.setCvv( value ); }
        return this;
    }
    public String getCvv(){
        return _cvv;
    }
    
        
    
    public CreditCard setExpiration( CcExpiration value ){
        SchemaSanitizer.throwOnNull(FIELD_EXPIRATION,value);
        // TODO With proper compare
        // if ( this._expiration == value ) return this;
        this._expiration = value;
        setDirty(FIELD_EXPIRATION);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CreditCard safeSetExpiration( CcExpiration value ){
        if ( value != null ) { this.setExpiration( value ); }
        return this;
    }
    public CcExpiration getExpiration(){
        return _expiration;
    }
    
        
    
    public CreditCard setName( String value ){
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
    public CreditCard safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    public String getName(){
        return _name;
    }
    
        
    
    public CreditCard setNumber( String value ){
        SchemaSanitizer.throwOnNull(FIELD_NUMBER,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._number == value ) return this;
        this._number = value;
        setDirty(FIELD_NUMBER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CreditCard safeSetNumber( String value ){
        if ( value != null ) { this.setNumber( value ); }
        return this;
    }
    public String getNumber(){
        return _number;
    }
    
        
    
    public CreditCard setType( String value ){
        SchemaSanitizer.throwOnNull(FIELD_TYPE,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._type == value ) return this;
        this._type = value;
        setDirty(FIELD_TYPE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CreditCard safeSetType( String value ){
        if ( value != null ) { this.setType( value ); }
        return this;
    }
    public String getType(){
        return _type;
    }
    
    
}