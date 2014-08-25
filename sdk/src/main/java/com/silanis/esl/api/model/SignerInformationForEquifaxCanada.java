package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.esl.api.util.JsonDateDeserializer;
import com.silanis.esl.api.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class SignerInformationForEquifaxCanada extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CITY = "city";
    @JsonIgnore
    public static final String FIELD_DATEOFBIRTH = "dateOfBirth";
    @JsonIgnore
    public static final String FIELD_DRIVERSLICENSEINDICATOR = "driversLicenseIndicator";
    @JsonIgnore
    public static final String FIELD_FIRSTNAME = "firstName";
    @JsonIgnore
    public static final String FIELD_HOMEPHONENUMBER = "homePhoneNumber";
    @JsonIgnore
    public static final String FIELD_LASTNAME = "lastName";
    @JsonIgnore
    public static final String FIELD_SOCIALINSURANCENUMBER = "socialInsuranceNumber";
    @JsonIgnore
    public static final String FIELD_STATE = "state";
    @JsonIgnore
    public static final String FIELD_STREETADDRESS = "streetAddress";
    @JsonIgnore
    public static final String FIELD_TIMEATADDRESS = "timeAtAddress";
    @JsonIgnore
    public static final String FIELD_ZIP = "zip";
    
    // Empty Constructor
    public SignerInformationForEquifaxCanada ( ) {}
    
    // Fields
    protected String _city = "";
    protected java.util.Date _dateOfBirth;
    protected String _driversLicenseIndicator = "";
    protected String _firstName = "";
    protected String _homePhoneNumber = "";
    protected String _lastName = "";
    protected String _socialInsuranceNumber = "";
    protected String _state = "";
    protected String _streetAddress = "";
    protected String _timeAtAddress = "";
    protected String _zip = "";
    
    // Accessors
        
    
    public SignerInformationForEquifaxCanada setCity( String value ){
        SchemaSanitizer.throwOnNull(FIELD_CITY,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._city == value ) return this;
        this._city = value;
        setDirty(FIELD_CITY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxCanada safeSetCity( String value ){
        if ( value != null ) { this.setCity( value ); }
        return this;
    }
    public String getCity(){
        return _city;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public SignerInformationForEquifaxCanada setDateOfBirth( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_DATEOFBIRTH,value);
        // TODO With proper compare
        // if ( this._dateOfBirth == value ) return this;
        this._dateOfBirth = value;
        setDirty(FIELD_DATEOFBIRTH);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxCanada safeSetDateOfBirth( java.util.Date value ){
        if ( value != null ) { this.setDateOfBirth( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getDateOfBirth(){
        return _dateOfBirth;
    }
    
        
    
    public SignerInformationForEquifaxCanada setDriversLicenseIndicator( String value ){
        SchemaSanitizer.throwOnNull(FIELD_DRIVERSLICENSEINDICATOR,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._driversLicenseIndicator == value ) return this;
        this._driversLicenseIndicator = value;
        setDirty(FIELD_DRIVERSLICENSEINDICATOR);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxCanada safeSetDriversLicenseIndicator( String value ){
        if ( value != null ) { this.setDriversLicenseIndicator( value ); }
        return this;
    }
    public String getDriversLicenseIndicator(){
        return _driversLicenseIndicator;
    }
    
        
    
    public SignerInformationForEquifaxCanada setFirstName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_FIRSTNAME,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._firstName == value ) return this;
        this._firstName = value;
        setDirty(FIELD_FIRSTNAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxCanada safeSetFirstName( String value ){
        if ( value != null ) { this.setFirstName( value ); }
        return this;
    }
    public String getFirstName(){
        return _firstName;
    }
    
        
    
    public SignerInformationForEquifaxCanada setHomePhoneNumber( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._homePhoneNumber == value ) return this;
        this._homePhoneNumber = value;
        setDirty(FIELD_HOMEPHONENUMBER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxCanada safeSetHomePhoneNumber( String value ){
        if ( value != null ) { this.setHomePhoneNumber( value ); }
        return this;
    }
    public String getHomePhoneNumber(){
        return _homePhoneNumber;
    }
    
        
    
    public SignerInformationForEquifaxCanada setLastName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_LASTNAME,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._lastName == value ) return this;
        this._lastName = value;
        setDirty(FIELD_LASTNAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxCanada safeSetLastName( String value ){
        if ( value != null ) { this.setLastName( value ); }
        return this;
    }
    public String getLastName(){
        return _lastName;
    }
    
        
    
    public SignerInformationForEquifaxCanada setSocialInsuranceNumber( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._socialInsuranceNumber == value ) return this;
        this._socialInsuranceNumber = value;
        setDirty(FIELD_SOCIALINSURANCENUMBER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxCanada safeSetSocialInsuranceNumber( String value ){
        if ( value != null ) { this.setSocialInsuranceNumber( value ); }
        return this;
    }
    public String getSocialInsuranceNumber(){
        return _socialInsuranceNumber;
    }
    
        
    
    public SignerInformationForEquifaxCanada setState( String value ){
        SchemaSanitizer.throwOnNull(FIELD_STATE,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._state == value ) return this;
        this._state = value;
        setDirty(FIELD_STATE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxCanada safeSetState( String value ){
        if ( value != null ) { this.setState( value ); }
        return this;
    }
    public String getState(){
        return _state;
    }
    
        
    
    public SignerInformationForEquifaxCanada setStreetAddress( String value ){
        SchemaSanitizer.throwOnNull(FIELD_STREETADDRESS,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._streetAddress == value ) return this;
        this._streetAddress = value;
        setDirty(FIELD_STREETADDRESS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxCanada safeSetStreetAddress( String value ){
        if ( value != null ) { this.setStreetAddress( value ); }
        return this;
    }
    public String getStreetAddress(){
        return _streetAddress;
    }
    
        
    
    public SignerInformationForEquifaxCanada setTimeAtAddress( String value ){
        SchemaSanitizer.throwOnNull(FIELD_TIMEATADDRESS,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._timeAtAddress == value ) return this;
        this._timeAtAddress = value;
        setDirty(FIELD_TIMEATADDRESS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxCanada safeSetTimeAtAddress( String value ){
        if ( value != null ) { this.setTimeAtAddress( value ); }
        return this;
    }
    public String getTimeAtAddress(){
        return _timeAtAddress;
    }
    
        
    
    public SignerInformationForEquifaxCanada setZip( String value ){
        SchemaSanitizer.throwOnNull(FIELD_ZIP,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._zip == value ) return this;
        this._zip = value;
        setDirty(FIELD_ZIP);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxCanada safeSetZip( String value ){
        if ( value != null ) { this.setZip( value ); }
        return this;
    }
    public String getZip(){
        return _zip;
    }
    
    
}