package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.AccountRole;

import java.util.List;

public class AccountRoleBuilder {
    private AccountRole accountRole = new AccountRole();

    private AccountRoleBuilder() { }

    public static AccountRoleBuilder newAccountRole() { return new AccountRoleBuilder(); }

    public AccountRoleBuilder withId(String id) {
        accountRole.setId(id);
        return this;
    }

    public AccountRoleBuilder withName(String name) {
        accountRole.setName(name);
        return this;
    }

    public AccountRoleBuilder withPermissions(List<String> permissions) {
        accountRole.setPermissions(permissions);
        return this;
    }

    public AccountRoleBuilder withDescription(String description) {
        accountRole.setDescription(description);
        return this;
    }

    public AccountRoleBuilder withEnabled(Boolean enabled) {
        accountRole.setEnabled(enabled);
        return this;
    }

    public AccountRole build() {
        return accountRole;
    }
}
