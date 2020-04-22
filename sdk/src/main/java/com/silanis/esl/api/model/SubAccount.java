package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubAccount extends Account implements Serializable {

    @JsonIgnore
    private static final String FIELD_PARENT_ACCOUNT_ID = "parentAccountId";
    @JsonIgnore
    public static final String FIELD_LANGUAGE = "language";
    @JsonIgnore
    public static final String FIELD_TIMEZONE_ID = "timezoneId";

    private String _parentAccountId;
    private String _language;
    private String _timezoneId;

    public SubAccount(){
        this._timezoneId = "GMT";
        this._language = "en";
    }

    @JsonIgnore
    public SubAccount safeSetName( String value ){
        if (StringUtils.isNotEmpty(value)) { this.setName( value ); }
        return this;
    }

    public SubAccount setParentAccountId(String value) {
        SchemaSanitizer.throwOnNull(FIELD_PARENT_ACCOUNT_ID, value);
        this._parentAccountId = value;
        setDirty(FIELD_PARENT_ACCOUNT_ID);
        return this;
    }

    @JsonIgnore
    public Account safeSetParentAccountId( String value ){
        if ( StringUtils.isNotEmpty(value) ) { this.setParentAccountId( value ); }
        return this;
    }

    public String getParentAccountId() {
        return _parentAccountId;
    }

    public SubAccount setLanguage(String value) {
        SchemaSanitizer.throwOnNull(FIELD_LANGUAGE, value);
        this._language = value;
        setDirty(FIELD_LANGUAGE);
        return this;
    }

    @JsonIgnore
    public Account safeSetLanguage( String value ){
        if ( StringUtils.isNotEmpty(value) ) { this.setLanguage( value ); }
        return this;
    }

    public String getLanguage() {
        return _language;
    }

    public Account setTimezoneId(String value) {
        SchemaSanitizer.throwOnNull(FIELD_TIMEZONE_ID, value);
        this._timezoneId = value;
        setDirty(FIELD_TIMEZONE_ID);
        return this;
    }

    @JsonIgnore
    public Account safeSetTimezoneId( String value ){
        if ( StringUtils.isNotEmpty(value) ) { this.setTimezoneId( value ); }
        return this;
    }

    public String getTimezoneId() {
        return _timezoneId;
    }
}
