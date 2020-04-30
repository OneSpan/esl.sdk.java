package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.AccountRole;
import org.junit.Assert;
import org.junit.Test;

public class AccountRolesExampleTest {

    @Test
    public void verifyResult() {
        AccountRolesExample example = new AccountRolesExample();
        example.run();

        Assert.assertNotNull(example.result);
        Assert.assertNotNull(example.result2);
        Assert.assertEquals(example.result.getCount() + 1, example.result2.getCount().intValue());
        AccountRole newAccountRole = null;
        for (AccountRole forAccountRole : example.result2.getResults()) {
            if (forAccountRole.getId().equals(example.newAccountRoleId)) {
                newAccountRole = forAccountRole;
            }
        }
        Assert.assertNotNull(newAccountRole);
        Assert.assertEquals("NEW - DESCRIPTION", newAccountRole.getDescription());
        Assert.assertNotNull(example.newAccountRole);

        newAccountRole = null;
        for (AccountRole forAccountRole : example.result3.getResults()) {
            if (forAccountRole.getId().equals(example.newAccountRoleId)) {
                newAccountRole = forAccountRole;
            }
        }
        Assert.assertNull(newAccountRole);
        Assert.assertNotNull(example.newAccountUsers);
    }
}