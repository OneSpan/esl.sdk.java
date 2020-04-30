package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.builder.AccountRoleBuilder;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class AccountRolesExample extends SDKSample {
    List<com.silanis.esl.sdk.AccountRole> result = null;
    List<com.silanis.esl.sdk.AccountRole> result2 = null;
    List<com.silanis.esl.sdk.AccountRole> result3 = null;
    String newAccountRoleId = null;
    com.silanis.esl.sdk.AccountRole newAccountRole = null;
    List<String> newAccountUsers = null;

    public static void main(String... args) {
        new AccountRolesExample().run();
    }

    public void execute() {
        result = eslClient.getAccountService().getAccountRoles();
        String newAccountRoleName = UUID.randomUUID().toString();
        com.silanis.esl.sdk.AccountRole accountRole = AccountRoleBuilder.newAccountRole()
            .withName(newAccountRoleName)
            .withPermissions(Collections.singletonList(UUID.randomUUID().toString()))
            .withDescription("DESCRIPTION")
            .withEnabled(true)
            .build();

        eslClient.getAccountService().addAccountRole(accountRole);

        for (com.silanis.esl.sdk.AccountRole forAccountRole : result2) {
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