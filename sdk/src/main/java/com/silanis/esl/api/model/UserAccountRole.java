package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserAccountRole extends Model implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public static final String FIELD_USERUID = "userId";
    @JsonIgnore
    public static final String FIELD_ACCOUNTUID = "accountId";
    @JsonIgnore
    public static final String FIELD_ACCOUNTROLES = "accountRoles";

    protected String userId;
    protected String accountId;
    protected List<AccountRole> accountRoles;

    public UserAccountRole() {
    }

    public UserAccountRole(String userId, String accountId, List<AccountRole> accountRoles) {
        setUserId(userId);
        setAccountId(accountId);
        setAccountRoles(accountRoles);
    }


    public String getAccountId() {
        return accountId;
    }

    public UserAccountRole setAccountId(String accountId) {
        SchemaSanitizer.throwOnNull(FIELD_ACCOUNTUID, accountId);

        if (accountId.equals(this.accountId)) return this;

        this.accountId = accountId;
        setDirty(FIELD_ACCOUNTUID);
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UserAccountRole setUserId(String userId) {
        SchemaSanitizer.throwOnNull(FIELD_USERUID, userId);

        if (userId.equals(this.userId)) return this;

        this.userId = userId;
        setDirty(FIELD_USERUID);
        return this;
    }

    public List<AccountRole> getAccountRoles() {
        return accountRoles;
    }

    public UserAccountRole setAccountRoles(List<AccountRole> accountRoles) {
        SchemaSanitizer.throwOnNull(FIELD_ACCOUNTROLES, accountRoles);

        if (accountRoles.equals(this.accountRoles)) return this;

        this.accountRoles = accountRoles;
        setDirty(FIELD_ACCOUNTROLES);
        return this;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UserAccountRole that = (UserAccountRole) o;

        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null)
            return false;
        if (accountRoles != null ? !accountRoles.equals(that.accountRoles) : that.accountRoles != null)
            return false;
        return true;
    }

    @Override public int hashCode() {
        int result = accountId != null ? accountId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (accountRoles != null ? accountRoles.hashCode() : 0);
        return result;
    }
}
