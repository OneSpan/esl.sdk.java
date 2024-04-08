package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.builder.AccountRoleBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AccountRoleWithTemplatePermissionsExample extends SDKSampleWithRolesAndPermission {
    public static final String TEMPLATES_PERMISSION = "templates_layouts.templates";
    public static final String SHARE_TEMPLATES_PERMISSION = "templates_layouts.share_templates";

    List<com.silanis.esl.sdk.AccountRole> result = null;
    List<com.silanis.esl.sdk.AccountRole> result2 = null;
    List<com.silanis.esl.sdk.AccountRole> result3 = null;

    String templatePermissionsRoleUid = null;
    com.silanis.esl.sdk.AccountRole templatePermissionsAccountRole = null;

    private final EslClient client = eslClientForRolesAndPermission;

    public static void main(String... args) {
        new AccountRoleWithTemplatePermissionsExample().run();
    }

    public void execute() {

        result = client.getAccountService().getAccountRoles();

        String templatePermissionsRoleName = UUID.randomUUID().toString();

        com.silanis.esl.sdk.AccountRole accountRole = AccountRoleBuilder.newAccountRole()
                .withId("")
                .withName(templatePermissionsRoleName)
                .withPermissions(Arrays.asList(TEMPLATES_PERMISSION, SHARE_TEMPLATES_PERMISSION))
                .withDescription("Account Role with Template Permissions")
                .withEnabled(true)
                .build();

        client.getAccountService().addAccountRole(accountRole);

        result2 = client.getAccountService().getAccountRoles();

        for (com.silanis.esl.sdk.AccountRole forAccountRole : result2) {
            if (forAccountRole.getName().equals(templatePermissionsRoleName)) {
                templatePermissionsRoleUid = forAccountRole.getId();
            }
        }

        client.getAccountService().updateAccountRole(templatePermissionsRoleUid, accountRole);

        result2 = client.getAccountService().getAccountRoles();

        templatePermissionsAccountRole = client.getAccountService().getAccountRole(templatePermissionsRoleUid);

        client.getAccountService().deleteAccountRole(templatePermissionsRoleUid);
        result3 = client.getAccountService().getAccountRoles();
    }
}