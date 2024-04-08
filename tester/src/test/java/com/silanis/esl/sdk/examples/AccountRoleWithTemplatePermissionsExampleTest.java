package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AccountRole;
import org.junit.Assert;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.AccountRoleWithTemplatePermissionsExample.TEMPLATES_PERMISSION;
import static com.silanis.esl.sdk.examples.AccountRoleWithTemplatePermissionsExample.SHARE_TEMPLATES_PERMISSION;

public class AccountRoleWithTemplatePermissionsExampleTest {

    @Test
    public void verifyResult() {
        AccountRoleWithTemplatePermissionsExample example = new AccountRoleWithTemplatePermissionsExample();
        example.run();

        // result has initial Account Roles
        Assert.assertNotNull(example.result);

        // result2 has the new Account Role with Template Permissions
        Assert.assertNotNull(example.result2);
        Assert.assertEquals(example.result.size() + 1, example.result2.size());

        AccountRole templatePermissionsAccountRole = null;
        for (AccountRole forAccountRole : example.result2) {
            if (forAccountRole.getId().equals(example.templatePermissionsRoleUid)) {
                templatePermissionsAccountRole = forAccountRole;
            }
        }

        Assert.assertNotNull(templatePermissionsAccountRole);
        Assert.assertNotNull(example.templatePermissionsAccountRole);
        Assert.assertEquals("Account Role with Template Permissions", templatePermissionsAccountRole.getDescription());

        Assert.assertTrue(templatePermissionsAccountRole.getPermissions().contains(TEMPLATES_PERMISSION));
        Assert.assertTrue(templatePermissionsAccountRole.getPermissions().contains(SHARE_TEMPLATES_PERMISSION));

        // the newly created Account Role is deleted, result3 is updated
        templatePermissionsAccountRole = null;
        for (AccountRole forAccountRole : example.result3) {
            if (forAccountRole.getId().equals(example.templatePermissionsRoleUid)) {
                templatePermissionsAccountRole = forAccountRole;
            }
        }
        Assert.assertNull(templatePermissionsAccountRole);

    }
}