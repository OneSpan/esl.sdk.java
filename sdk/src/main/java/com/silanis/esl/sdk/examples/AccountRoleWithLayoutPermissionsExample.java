package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.builder.AccountRoleBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AccountRoleWithLayoutPermissionsExample extends SDKSampleWithRolesAndPermission {
    public static final String SAVE_LAYOUTS_PERMISSION = "templates_layouts.save_layouts";
    public static final String APPLY_LAYOUTS_PERMISSION = "templates_layouts.apply_layouts";
    public static final String SHARE_LAYOUTS_PERMISSION = "templates_layouts.share_layouts";

    List<com.silanis.esl.sdk.AccountRole> result = null;
    List<com.silanis.esl.sdk.AccountRole> result2 = null;
    List<com.silanis.esl.sdk.AccountRole> result3 = null;

    String layoutPermissionsRoleUid = null;
    com.silanis.esl.sdk.AccountRole layoutPermissionsAccountRole = null;

    private final EslClient client = eslClientForRolesAndPermission;

    public static void main(String... args) {
        new AccountRoleWithLayoutPermissionsExample().run();
    }

    public void execute() {

        result = client.getAccountService().getAccountRoles();

        String layoutPermissionsRoleName = UUID.randomUUID().toString();

        com.silanis.esl.sdk.AccountRole accountRole = AccountRoleBuilder.newAccountRole()
                .withId("")
                .withName(layoutPermissionsRoleName)
                .withPermissions(Arrays.asList(SAVE_LAYOUTS_PERMISSION, APPLY_LAYOUTS_PERMISSION, SHARE_LAYOUTS_PERMISSION))
                .withDescription("Account Role with Layout Permissions")
                .withEnabled(true)
                .build();

        client.getAccountService().addAccountRole(accountRole);

        result2 = client.getAccountService().getAccountRoles();

        for (com.silanis.esl.sdk.AccountRole forAccountRole : result2) {
            if (forAccountRole.getName().equals(layoutPermissionsRoleName)) {
                layoutPermissionsRoleUid = forAccountRole.getId();
            }
        }

        client.getAccountService().updateAccountRole(layoutPermissionsRoleUid, accountRole);

        result2 = client.getAccountService().getAccountRoles();

        layoutPermissionsAccountRole = client.getAccountService().getAccountRole(layoutPermissionsRoleUid);

        client.getAccountService().deleteAccountRole(layoutPermissionsRoleUid);
        result3 = client.getAccountService().getAccountRoles();
    }
}