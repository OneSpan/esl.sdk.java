package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.AccountRole;
import com.silanis.esl.api.model.Result;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class AccountRolesExample extends SDKSample {
    Result<AccountRole> result = null;
    Result<AccountRole> result2 = null;
    Result<AccountRole> result3 = null;
    String newAccountRoleId = null;
    AccountRole newAccountRole = null;
    List<String> newAccountUsers = null;

    public static void main(String... args) {
        new AccountRolesExample().run();
    }

    public void execute() {
        result = eslClient.getAccountService().getAccountRoles();
        String newAccountRoleName = UUID.randomUUID().toString();
        AccountRole accountRole = new AccountRole().setName(newAccountRoleName)
            .setDescription("DESCRIPTION").setEnabled(true)
            .setPermissions(Collections.singletonList(UUID.randomUUID().toString()));

        eslClient.getAccountService().addAccountRole(accountRole);

        for (AccountRole forAccountRole : result2.getResults()) {
            if (forAccountRole.getName().equals(newAccountRoleName)) {
                newAccountRoleId = forAccountRole.getId();
            }
        }

        accountRole.setDescription("NEW - DESCRIPTION");
        eslClient.getAccountService().updateAccountRole(newAccountRoleId, accountRole);

        result2 = eslClient.getAccountService().getAccountRoles();

        newAccountRole = eslClient.getAccountService().getAccountRole(newAccountRoleId);

        newAccountUsers = eslClient.getAccountService().getAccountRoleUsers(newAccountRoleId);

        eslClient.getAccountService().deleteAccountRole(newAccountRoleId);
        result3 = eslClient.getAccountService().getAccountRoles();
    }
}