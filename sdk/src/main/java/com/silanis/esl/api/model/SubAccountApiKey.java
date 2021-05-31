package com.silanis.esl.api.model;

import com.silanis.esl.api.util.SchemaSanitizer;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SubAccountApiKey extends Model  {
    @JsonIgnore
    public static final String FIELD_ACCOUNT_ID = "accountUid";
    @JsonIgnore
    public static final String FIELD_ACCOUNT_NAME = "accountName";
    @JsonIgnore
    public static final String FIELD_API_KEY = "apiKey";

    protected String accountUid;
    protected String accountName;
    protected String apiKey;

    public SubAccountApiKey() {
    }

    public SubAccountApiKey(String accountUid, String accountName, String apiKey) {
        setAccountUid(accountUid);
        setAccountName(accountName);
        setApiKey(apiKey);
    }

    public SubAccountApiKey setAccountUid(String value) {
        SchemaSanitizer.throwOnNull(FIELD_ACCOUNT_ID, value);

        if (value.equals(this.accountUid)) return this;

        this.accountUid = value;
        setDirty(FIELD_ACCOUNT_ID);
        return this;
    }

    public SubAccountApiKey safeSetAccountUid( String value ){
        if ( StringUtils.isNotEmpty(value) ) { this.setAccountUid( value ); }
        return this;
    }

    public String getAccountUid() {
        return accountUid;
    }

    public SubAccountApiKey setAccountName(String value) {
        SchemaSanitizer.throwOnNull(FIELD_ACCOUNT_NAME, value);

        if (value.equals(this.accountName)) return this;

        this.accountName = value;
        setDirty(FIELD_ACCOUNT_NAME);
        return this;
    }

    public SubAccountApiKey safeSetAccountName( String value ){
        if ( StringUtils.isNotEmpty(value) ) { this.setAccountName( value ); }
        return this;
    }

    public String getAccountName() {
        return accountName;
    }

    public SubAccountApiKey setApiKey(String value) {
        SchemaSanitizer.throwOnNull(FIELD_API_KEY, value);

        if (value.equals(this.apiKey)) return this;

        this.apiKey = value;
        setDirty(FIELD_API_KEY);
        return this;
    }

    public SubAccountApiKey safeSetApiKey( String value ){
        if ( StringUtils.isNotEmpty(value) ) { this.setApiKey( value ); }
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }
}
