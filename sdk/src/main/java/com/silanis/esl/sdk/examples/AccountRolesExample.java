package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.builder.AccountRoleBuilder;
import com.silanis.esl.sdk.EslClient;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class AccountRolesExample extends SDKSampleWithRolesAndPermission {
    List<com.silanis.esl.sdk.AccountRole> result = null;
    List<com.silanis.esl.sdk.AccountRole> result2 = null;
    List<com.silanis.esl.sdk.AccountRole> result3 = null;
    String newAccountRoleId = null;
    com.silanis.esl.sdk.AccountRole newAccountRole = null;
    List<String> newAccountUsers = null;
    private EslClient client = eslClientForRolesAndPermission;

    public static void main(String... args) {
        new AccountRolesExample().run();
    }

    public void execute() {
        result = client.getAccountService().getAccountRoles();
        String newAccountRoleName = UUID.randomUUID().toString();
        com.silanis.esl.sdk.AccountRole accountRole = AccountRoleBuilder.newAccountRole()
            .withId("")
            .withName(newAccountRoleName)
            .withPermissions(Collections.singletonList("sender_admin.users"))
            .withDescription("DESCRIPTION")
            .withEnabled(true)
            .build();

        client.getAccountService().addAccountRole(accountRole);

        result2 = client.getAccountService().getAccountRoles();

        for (com.silanis.esl.sdk.AccountRole forAccountRole : result2) {
            if (forAccountRole.getName().equals(newAccountRoleName)) {
                newAccountRoleId = forAccountRole.getId();
            }
        }

        accountRole.setDescription("NEW - DESCRIPTION");
        client.getAccountService().updateAccountRole(newAccountRoleId, accountRole);

        result2 = client.getAccountService().getAccountRoles();

        newAccountRole = client.getAccountService().getAccountRole(newAccountRoleId);

        newAccountUsers = client.getAccountService().getAccountRoleUsers(newAccountRoleId);

        client.getAccountService().deleteAccountRole(newAccountRoleId);
        result3 = client.getAccountService().getAccountRoles();
    }
}