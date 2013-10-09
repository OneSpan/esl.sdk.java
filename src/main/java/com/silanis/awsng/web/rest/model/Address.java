package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Address extends Model
      implements java.io.Serializable, IAddress
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ADDRESS1 = "address1";
    @JsonIgnore
    public static final String FIELD_ADDRESS2 = "address2";
    @JsonIgnore
    public static final String FIELD_CITY = "city";
    @JsonIgnore
    public static final String FIELD_COUNTRY = "country";
    @JsonIgnore
    public static final String FIELD_STATE = "state";
    @JsonIgnore
    public static final String FIELD_ZIPCODE = "zipcode";
    
    // Empty Constructor
    public Address ( ) {}
    
    // Fields
    protected String _address1 = "";
    protected String _address2 = "";
    protected String _city = "";
    protected String _country = "";
    protected String _state = "";
    protected String _zipcode = "";
    
    // Accessors
        
    
    public Address setAddress1( String value ){
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._address1 == value ) return this;
        this._address1 = value;
        setDirty(FIELD_ADDRESS1);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Address safeSetAddress1( String value ){
        if ( value != null ) { this.setAddress1( value ); }
        return this;
    }
    public String getAddress1(){
        return _address1;
    }
    
        
    
    public Address setAddress2( String value ){
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._address2 == value ) return this;
        this._address2 = value;
        setDirty(FIELD_ADDRESS2);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Address safeSetAddress2( String value ){
        if ( value != null ) { this.setAddress2( value ); }
        return this;
    }
    public String getAddress2(){
        return _address2;
    }
    
        
    
    public Address setCity( String value ){
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._city == value ) return this;
        this._city = value;
        setDirty(FIELD_CITY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Address safeSetCity( String value ){
        if ( value != null ) { this.setCity( value ); }
        return this;
    }
    public String getCity(){
        return _city;
    }
    
        
    
    public Address setCountry( String value ){
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._country == value ) return this;
        this._country = value;
        setDirty(FIELD_COUNTRY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Address safeSetCountry( String value ){
        if ( value != null ) { this.setCountry( value ); }
        return this;
    }
    public String getCountry(){
        return _country;
    }
    
        
    
    public Address setState( String value ){
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._state == value ) return this;
        this._state = value;
        setDirty(FIELD_STATE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Address safeSetState( String value ){
        if ( value != null ) { this.setState( value ); }
        return this;
    }
    public String getState(){
        return _state;
    }
    
        
    
    public Address setZipcode( String value ){
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._zipcode == value ) return this;
        this._zipcode = value;
        setDirty(FIELD_ZIPCODE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Address safeSetZipcode( String value ){
        if ( value != null ) { this.setZipcode( value ); }
        return this;
    }
    public String getZipcode(){
        return _zipcode;
    }
    
    
}