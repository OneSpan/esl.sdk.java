package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountSystemSettingProperties extends Model implements java.io.Serializable{
    @JsonIgnore
    public static final String FIELD_SENDER_LOGIN_MAX_FAILED_ATTEMPTS = "senderLoginMaxFailedAttempts";
    @JsonIgnore
    public static final String FIELD_LOGIN_SESSION_TIMEOUT = "loginSessionTimeout";
    @JsonIgnore
    public static final String FIELD_SESSION_TIMEOUT_WARNING = "sessionTimeoutWarning";

    protected Integer _senderLoginMaxFailedAttempts;
    protected Integer _loginSessionTimeout;
    protected Integer _sessionTimeoutWarning;


    //Empty Constructor
    public AccountSystemSettingProperties() {
        //do nothing
    }

    public AccountSystemSettingProperties setSenderLoginMaxFailedAttempts(Integer value) {
        SchemaSanitizer.throwOnNull(FIELD_SENDER_LOGIN_MAX_FAILED_ATTEMPTS, value);
        this._senderLoginMaxFailedAttempts = value;
        setDirty(FIELD_SENDER_LOGIN_MAX_FAILED_ATTEMPTS);
        return this;
    }

    @JsonIgnore
    public AccountSystemSettingProperties safeSetSenderLoginMaxFailedAttempts(Integer value ){
        if ( value != null ) {
            this.setSenderLoginMaxFailedAttempts(value);
        }
        return this;
    }
    public int getSenderLoginMaxFailedAttempts() {
        return _senderLoginMaxFailedAttempts;
    }

    @JsonIgnore
    public Integer evalSenderLoginMaxFailedAttempts(){
        return _senderLoginMaxFailedAttempts == null ? 0 : _senderLoginMaxFailedAttempts.intValue();
    }

    public AccountSystemSettingProperties setLoginSessionTimeout(int value) {
        SchemaSanitizer.throwOnNull(FIELD_LOGIN_SESSION_TIMEOUT, value);
        this._loginSessionTimeout = value;
        setDirty(FIELD_LOGIN_SESSION_TIMEOUT);
        return this;
    }

    @JsonIgnore
    public AccountSystemSettingProperties safeSetLoginSessionTimeout(Integer value ){
        if ( value != null ) {
            this.setLoginSessionTimeout(value);
        }
        return this;
    }

    public int getLoginSessionTimeout() {
        return _loginSessionTimeout;
    }

    @JsonIgnore
    public Integer evalLoginSessionTimeout(){
        return _loginSessionTimeout == null ? 0 : _loginSessionTimeout.intValue();
    }

    public AccountSystemSettingProperties setSessionTimeoutWarning(int value) {
        SchemaSanitizer.throwOnNull(FIELD_SESSION_TIMEOUT_WARNING, value);
        this._sessionTimeoutWarning = value;
        setDirty(FIELD_SESSION_TIMEOUT_WARNING);
        return this;
    }

    @JsonIgnore
    public AccountSystemSettingProperties safeSetSessionTimeoutWarning(Integer value ){
        if ( value != null ) {
            this.setSessionTimeoutWarning(value);
        }
        return this;
    }

    public int getSessionTimeoutWarning() {
        return _sessionTimeoutWarning;
    }

    @JsonIgnore
    public Integer evalSessionTimeoutWarning(){
        return _sessionTimeoutWarning == null ? 0 : _sessionTimeoutWarning.intValue();
    }




}


