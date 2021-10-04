package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.AccountRole;
import com.silanis.esl.api.model.UserAccountRole;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class UserAccountRoleConverter {
    private final Optional<com.silanis.esl.sdk.UserAccountRole> sdkUserAccountRoleOptional;
    private final Optional<UserAccountRole> apiUserAccountRoleOptional;

    public UserAccountRoleConverter(com.silanis.esl.sdk.UserAccountRole sdkAccountRoleOptional) {
        this.sdkUserAccountRoleOptional = Optional.fromNullable(sdkAccountRoleOptional);
        this.apiUserAccountRoleOptional = Optional.absent();
    }

    public UserAccountRoleConverter(UserAccountRole apiUserAccountRoleOptional) {
        this.apiUserAccountRoleOptional = Optional.fromNullable(apiUserAccountRoleOptional);
        this.sdkUserAccountRoleOptional = Optional.absent();
    }

    public com.silanis.esl.sdk.UserAccountRole toSDKUserAccountRole() {
        if (apiUserAccountRoleOptional.isPresent()) {
            UserAccountRole role = apiUserAccountRoleOptional.get();
            return new com.silanis.esl.sdk.UserAccountRole(role.getUserId(), role.getAccountId(), toSDKAccountRoles(role.getAccountRoles()));
        }
        return sdkUserAccountRoleOptional.isPresent() ? sdkUserAccountRoleOptional.get() : null;
    }

    public UserAccountRole toAPIUserAccountRole() {
        if (sdkUserAccountRoleOptional.isPresent()) {
            com.silanis.esl.sdk.UserAccountRole role = sdkUserAccountRoleOptional.get();
            return new com.silanis.esl.api.model.UserAccountRole(role.getUserId(), role.getAccountId(), toAPIAccountRoles(role.getAccountRoles()));
        }
        return apiUserAccountRoleOptional.isPresent() ? apiUserAccountRoleOptional.get() : null;
    }

    private List<AccountRole> toAPIAccountRoles(List<com.silanis.esl.sdk.AccountRole> sdkAccountRoles){
        List<AccountRole> accountRoles = new ArrayList<AccountRole>();
        for(com.silanis.esl.sdk.AccountRole sdkAccountRole: sdkAccountRoles){
            accountRoles.add(new AccountRoleConverter(sdkAccountRole).toAPIAccountRole());
        }
        return accountRoles;
    }

    private List<com.silanis.esl.sdk.AccountRole> toSDKAccountRoles(List<AccountRole> apiAccountRoles){
        List<com.silanis.esl.sdk.AccountRole> sdkAccountRoles = new ArrayList<com.silanis.esl.sdk.AccountRole>();
        for(AccountRole apiAccountRole: apiAccountRoles){
            sdkAccountRoles.add(new AccountRoleConverter(apiAccountRole).toSDKAccountRole());
        }
        return sdkAccountRoles;
    }


}
