package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.esl.api.util.JsonDateDeserializer;
import com.silanis.esl.api.util.JsonDateSerializer;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class SignerInformationForLexisNexis extends Model
        implements java.io.Serializable {

    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CITY = "city";
    @JsonIgnore
    public static final String FIELD_DATEOFBIRTH = "dateOfBirth";
    @JsonIgnore
    public static final String FIELD_FIRSTNAME = "firstName";
    @JsonIgnore
    public static final String FIELD_LASTNAME = "lastName";
    @JsonIgnore
    public static final String FIELD_FLATORAPARTMENTNUMBER = "flatOrApartmentNumber";
    @JsonIgnore
    public static final String FIELD_HOUSENUMBER = "houseNumber";
    @JsonIgnore
    public static final String FIELD_SOCIALSECURITYNUMBER = "socialSecurityNumber";
    @JsonIgnore
    public static final String FIELD_STATE = "state";
    @JsonIgnore
    public static final String FIELD_HOUSENAME = "houseName";
    @JsonIgnore
    public static final String FIELD_ZIP = "zip";

    // Empty Constructor
    public SignerInformationForLexisNexis() {
    }

    // Fields
    protected String _firstName = "";
    protected java.util.Date _dateOfBirth;
    protected String _lastName = "";
    protected String _flatOrApartmentNumber = "";
    protected String _houseName = "";
    protected String _houseNumber = "";
    protected String _socialSecurityNumber = "";
    protected String _city = "";
    protected String _state = "";
    protected String _zip = "";

    // Accessors


    public SignerInformationForLexisNexis setCity(String value) {
        SchemaSanitizer.throwOnNull(FIELD_CITY, value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._city == value ) return this;
        this._city = value;
        setDirty(FIELD_CITY);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForLexisNexis safeSetCity(String value) {
        if (value != null) {
            this.setCity(value);
        }
        return this;
    }

    public String getCity() {
        return _city;
    }


    @JsonDeserialize(using = JsonDateDeserializer.class)
    public SignerInformationForLexisNexis setDateOfBirth(java.util.Date value) {
        // TODO With proper compare
        // if ( this._dateOfBirth == value ) return this;
        this._dateOfBirth = value;
        setDirty(FIELD_DATEOFBIRTH);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForLexisNexis safeSetDateOfBirth(java.util.Date value) {
        if (value != null) {
            this.setDateOfBirth(value);
        }
        return this;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getDateOfBirth() {
        return _dateOfBirth;
    }


    public SignerInformationForLexisNexis setHouseName(String value) {
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._driversLicenseNumber == value ) return this;
        this._houseName = value;
        setDirty(FIELD_HOUSENAME);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForLexisNexis safeSetHouseName(String value) {
        if (value != null) {
            this.setHouseName(value);
        }
        return this;
    }

    public String getHouseName() {
        return _houseName;
    }


    public SignerInformationForLexisNexis setFirstName(String value) {
        SchemaSanitizer.throwOnNull(FIELD_FIRSTNAME, value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._firstName == value ) return this;
        this._firstName = value;
        setDirty(FIELD_FIRSTNAME);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForLexisNexis safeSetFirstName(String value) {
        if (value != null) {
            this.setFirstName(value);
        }
        return this;
    }

    public String getFirstName() {
        return _firstName;
    }


    public SignerInformationForLexisNexis setFlatOrApartmentNumber(String value) {
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._homePhoneNumber == value ) return this;
        this._flatOrApartmentNumber = value;
        setDirty(FIELD_FLATORAPARTMENTNUMBER);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForLexisNexis safeSetFlatOrApartmentNumber(String value) {
        if (value != null) {
            this.setFlatOrApartmentNumber(value);
        }
        return this;
    }

    public String getFlatOrApartmentNumber() {
        return _flatOrApartmentNumber;
    }


    public SignerInformationForLexisNexis setLastName(String value) {
        SchemaSanitizer.throwOnNull(FIELD_LASTNAME, value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._lastName == value ) return this;
        this._lastName = value;
        setDirty(FIELD_LASTNAME);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForLexisNexis safeSetLastName(String value) {
        if (value != null) {
            this.setLastName(value);
        }
        return this;
    }

    public String getLastName() {
        return _lastName;
    }
    public SignerInformationForLexisNexis setHouseNumber(String value) {
        SchemaSanitizer.throwOnNull(FIELD_HOUSENUMBER, value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._lastName == value ) return this;
        this._houseNumber = value;
        setDirty(FIELD_HOUSENUMBER);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForLexisNexis safeSetHouseNumber(String value) {
        if (value != null) {
            this.setHouseNumber(value);
        }
        return this;
    }

    public String getHouseNumber() {
        return _houseNumber;
    }
    public SignerInformationForLexisNexis setSocialSecurityNumber(String value) {
        SchemaSanitizer.throwOnNull(FIELD_SOCIALSECURITYNUMBER, value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._lastName == value ) return this;
        this._socialSecurityNumber = value;
        setDirty(FIELD_SOCIALSECURITYNUMBER);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForLexisNexis safeSetSocialSecurityNumber(String value) {
        if (value != null) {
            this.setSocialSecurityNumber(value);
        }
        return this;
    }

    public String getSocialSecurityNumber() {
        return _socialSecurityNumber;
    }
    public SignerInformationForLexisNexis setState(String value) {
        SchemaSanitizer.throwOnNull(FIELD_SOCIALSECURITYNUMBER, value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._lastName == value ) return this;
        this._state = value;
        setDirty(FIELD_STATE);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForLexisNexis safeSetState(String value) {
        if (value != null) {
            this.setState(value);
        }
        return this;
    }

    public String getState() {
        return _state;
    }
    public SignerInformationForLexisNexis setZip(String value) {
        SchemaSanitizer.throwOnNull(FIELD_SOCIALSECURITYNUMBER, value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._lastName == value ) return this;
        this._zip = value;
        setDirty(FIELD_ZIP);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignerInformationForLexisNexis safeSetZip(String value) {
        if (value != null) {
            this.setZip(value);
        }
        return this;
    }

    public String getZip() {
        return _zip;
    }
}
