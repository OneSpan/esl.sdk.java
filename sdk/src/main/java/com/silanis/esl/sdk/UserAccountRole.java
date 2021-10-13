package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserAccountRole implements Serializable {
    protected String userId;
    protected String accountId;
    protected List<AccountRole> accountRoles;

    public UserAccountRole(String userId, String accountId, List<AccountRole> accountRoles) {
        this.userId =userId;
        this.accountId = accountId;
        this.accountRoles = accountRoles;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public List<AccountRole> getAccountRoles() {
        return accountRoles;
    }

    public void setPermissions(List<AccountRole> accountRoles) {
        this.accountRoles = accountRoles;
    }

    @Override
    public String toString() {
        return "UserAccountRole{" +
                "accountId='" + accountId + '\'' +
                ", userId='" + userId + '\'' +
                ", accountRoles=" + accountRoles +
                '}';
    }
}
