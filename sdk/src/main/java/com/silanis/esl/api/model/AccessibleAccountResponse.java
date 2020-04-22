package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessibleAccountResponse extends Entity implements java.io.Serializable{

    @JsonIgnore
    public static final String FIELD_ACCOUNT_UID = "accountUid";
    @JsonIgnore
    public static final String FIELD_ACCOUNT_NAME = "accountName";

    private String _accountUid;
    private String _accountName;

    public AccessibleAccountResponse() {}

    public String getAccountUid() {
        return _accountUid;
    }

    public AccessibleAccountResponse setAccountUid(String value) {
        SchemaSanitizer.throwOnNull(FIELD_ACCOUNT_UID, value);
        this._accountUid = value;
        setDirty(FIELD_ACCOUNT_UID);
        return this;
    }

    public String getAccountName() {
        return _accountName;
    }

    public AccessibleAccountResponse setAccountName(String value) {
        SchemaSanitizer.throwOnNull(FIELD_ACCOUNT_UID, value);
        this._accountName = value;
        setDirty(FIELD_ACCOUNT_NAME);
        return this;
    }
}
