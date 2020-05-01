package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.sdk.AccountRole;


@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class AccountRoleConverter {
    private final Optional<AccountRole> sdkAccountRoleOptional;
    private final Optional<com.silanis.esl.api.model.AccountRole> apiAccountRoleOptional;

    public AccountRoleConverter(AccountRole sdkAccountRoleOptional) {
        this.sdkAccountRoleOptional = Optional.fromNullable(sdkAccountRoleOptional);
        this.apiAccountRoleOptional = Optional.absent();
    }

    public AccountRoleConverter(com.silanis.esl.api.model.AccountRole apiAccountRoleOptional) {
        this.apiAccountRoleOptional = Optional.fromNullable(apiAccountRoleOptional);
        this.sdkAccountRoleOptional = Optional.absent();
    }

    public AccountRole toSDKAccountRole() {
        if (apiAccountRoleOptional.isPresent()) {
            com.silanis.esl.api.model.AccountRole role = apiAccountRoleOptional.get();
            return new AccountRole(role.getId(), role.getName(), role.getPermissions(), role.getDescription(), role.isEnabled());
        }
        return sdkAccountRoleOptional.isPresent() ? sdkAccountRoleOptional.get() : null;
    }

    public com.silanis.esl.api.model.AccountRole toAPIAccountRole() {
        if (sdkAccountRoleOptional.isPresent()) {
            AccountRole role = sdkAccountRoleOptional.get();
            return new com.silanis.esl.api.model.AccountRole(role.getId(), role.getName(), role.getPermissions(), role.getDescription(), role.isEnabled());
        }
        return apiAccountRoleOptional.isPresent() ? apiAccountRoleOptional.get() : null;
    }
}
