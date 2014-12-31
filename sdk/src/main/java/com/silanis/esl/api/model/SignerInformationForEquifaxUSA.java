package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.esl.api.util.JsonDateDeserializer;
import com.silanis.esl.api.util.JsonDateSerializer;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class SignerInformationForEquifaxUSA extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CITY = "city";
    @JsonIgnore
    public static final String FIELD_DATEOFBIRTH = "dateOfBirth";
    @JsonIgnore
    public static final String FIELD_DRIVERSLICENSENUMBER = "driversLicenseNumber";
    @JsonIgnore
    public static final String FIELD_FIRSTNAME = "firstName";
    @JsonIgnore
    public static final String FIELD_HOMEPHONENUMBER = "homePhoneNumber";
    @JsonIgnore
    public static final String FIELD_LASTNAME = "lastName";
    @JsonIgnore
    public static final String FIELD_SOCIALSECURITYNUMBER = "socialSecurityNumber";
    @JsonIgnore
    public static final String FIELD_STATE = "state";
    @JsonIgnore
    public static final String FIELD_STREETADDRESS = "streetAddress";
    @JsonIgnore
    public static final String FIELD_TIMEATADDRESS = "timeAtAddress";
    @JsonIgnore
    public static final String FIELD_ZIP = "zip";
    
    // Empty Constructor
    public SignerInformationForEquifaxUSA ( ) {}
    
    // Fields
    protected String _city = "";
    protected java.util.Date _dateOfBirth;
    protected String _driversLicenseNumber = "";
    protected String _firstName = "";
    protected String _homePhoneNumber = "";
    protected String _lastName = "";
    protected String _socialSecurityNumber = "";
    protected String _state = "";
    protected String _streetAddress = "";
    protected Integer _timeAtAddress = null;
    protected String _zip = "";
    
    // Accessors
        
    
    public SignerInformationForEquifaxUSA setCity( String value ){
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
    public SignerInformationForEquifaxUSA safeSetCity( String value ){
        if ( value != null ) { this.setCity( value ); }
        return this;
    }
    public String getCity(){
        return _city;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public SignerInformationForEquifaxUSA setDateOfBirth( java.util.Date value ){
        // TODO With proper compare
        // if ( this._dateOfBirth == value ) return this;
        this._dateOfBirth = value;
        setDirty(FIELD_DATEOFBIRTH);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxUSA safeSetDateOfBirth( java.util.Date value ){
        if ( value != null ) { this.setDateOfBirth( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getDateOfBirth(){
        return _dateOfBirth;
    }



    public SignerInformationForEquifaxUSA setDriversLicenseNumber( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._driversLicenseNumber == value ) return this;
        this._driversLicenseNumber = value;
        setDirty(FIELD_DRIVERSLICENSENUMBER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxUSA safeSetDriversLicenseNumber( String value ){
        if ( value != null ) { this.setDriversLicenseNumber( value ); }
        return this;
    }
    public String getDriversLicenseNumber(){
        return _driversLicenseNumber;
    }


    
    public SignerInformationForEquifaxUSA setFirstName( String value ){
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
    public SignerInformationForEquifaxUSA safeSetFirstName( String value ){
        if ( value != null ) { this.setFirstName( value ); }
        return this;
    }
    public String getFirstName(){
        return _firstName;
    }
    
        
    
    public SignerInformationForEquifaxUSA setHomePhoneNumber( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._homePhoneNumber == value ) return this;
        this._homePhoneNumber = value;
        setDirty(FIELD_HOMEPHONENUMBER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxUSA safeSetHomePhoneNumber( String value ){
        if ( value != null ) { this.setHomePhoneNumber( value ); }
        return this;
    }
    public String getHomePhoneNumber(){
        return _homePhoneNumber;
    }
    
        
    
    public SignerInformationForEquifaxUSA setLastName( String value ){
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
    public SignerInformationForEquifaxUSA safeSetLastName( String value ){
        if ( value != null ) { this.setLastName( value ); }
        return this;
    }
    public String getLastName(){
        return _lastName;
    }
    
        
    
    public SignerInformationForEquifaxUSA setSocialSecurityNumber( String value ){
        SchemaSanitizer.throwOnNull(FIELD_SOCIALSECURITYNUMBER,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._socialSecurityNumber == value ) return this;
        this._socialSecurityNumber = value;
        setDirty(FIELD_SOCIALSECURITYNUMBER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxUSA safeSetSocialSecurityNumber( String value ){
        if ( value != null ) { this.setSocialSecurityNumber( value ); }
        return this;
    }
    public String getSocialSecurityNumber(){
        return _socialSecurityNumber;
    }
    
        
    
    public SignerInformationForEquifaxUSA setState( String value ){
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
    public SignerInformationForEquifaxUSA safeSetState( String value ){
        if ( value != null ) { this.setState( value ); }
        return this;
    }
    public String getState(){
        return _state;
    }
    
        
    
    public SignerInformationForEquifaxUSA setStreetAddress( String value ){
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
    public SignerInformationForEquifaxUSA safeSetStreetAddress( String value ){
        if ( value != null ) { this.setStreetAddress( value ); }
        return this;
    }
    public String getStreetAddress(){
        return _streetAddress;
    }



    public SignerInformationForEquifaxUSA setTimeAtAddress( Integer value ){
        // TODO With proper compare
        // if ( this._timeAtAddress == value ) return this;
        this._timeAtAddress = value;
        setDirty(FIELD_TIMEATADDRESS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForEquifaxUSA safeSetTimeAtAddress( Integer value ){
        if ( value != null ) { this.setTimeAtAddress( value ); }
        return this;
    }
    public Integer getTimeAtAddress(){
        return _timeAtAddress;
    }

        
    
    public SignerInformationForEquifaxUSA setZip( String value ){
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
    public SignerInformationForEquifaxUSA safeSetZip( String value ){
        if ( value != null ) { this.setZip( value ); }
        return this;
    }
    public String getZip(){
        return _zip;
    }
    
    
}