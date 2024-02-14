package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AccountRole;
import org.junit.Assert;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.AccountRoleWithLayoutPermissionsExample.APPLY_LAYOUTS_PERMISSION;
import static com.silanis.esl.sdk.examples.AccountRoleWithLayoutPermissionsExample.SAVE_LAYOUTS_PERMISSION;
import static com.silanis.esl.sdk.examples.AccountRoleWithLayoutPermissionsExample.SHARE_LAYOUTS_PERMISSION;

public class AccountRoleWithLayoutPermissionsExampleTest {

    @Test
    public void verifyResult() {
        AccountRoleWithLayoutPermissionsExample example = new AccountRoleWithLayoutPermissionsExample();
        example.run();

        // result has initial Account Roles
        Assert.assertNotNull(example.result);

        // result2 has the new Account Role with Layout Permissions
        Assert.assertNotNull(example.result2);
        Assert.assertEquals(example.result.size() + 1, example.result2.size());

        AccountRole layoutPermissionsAccountRole = null;
        for (AccountRole forAccountRole : example.result2) {
            if (forAccountRole.getId().equals(example.layoutPermissionsRoleUid)) {
                layoutPermissionsAccountRole = forAccountRole;
            }
        }

        Assert.assertNotNull(layoutPermissionsAccountRole);
        Assert.assertNotNull(example.layoutPermissionsAccountRole);
        Assert.assertEquals("Account Role with Layout Permissions", layoutPermissionsAccountRole.getDescription());

        Assert.assertTrue(layoutPermissionsAccountRole.getPermissions().contains(SAVE_LAYOUTS_PERMISSION));
        Assert.assertTrue(layoutPermissionsAccountRole.getPermissions().contains(APPLY_LAYOUTS_PERMISSION));
        Assert.assertTrue(layoutPermissionsAccountRole.getPermissions().contains(SHARE_LAYOUTS_PERMISSION));

        // the newly created Account Role is deleted, result3 is updated
        layoutPermissionsAccountRole = null;
        for (AccountRole forAccountRole : example.result3) {
            if (forAccountRole.getId().equals(example.layoutPermissionsRoleUid)) {
                layoutPermissionsAccountRole = forAccountRole;
            }
        }
        Assert.assertNull(layoutPermissionsAccountRole);

    }
}