package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.AccountRole;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class AccountRoleConverterTest implements ConverterTest {

    @Test
    public void convertNullSDKToAPI() {
        AccountRoleConverter accountRoleConverter = new AccountRoleConverter((AccountRole) null);
        Assert.assertNull(accountRoleConverter.toAPIAccountRole());
    }

    @Test
    public void convertNullAPIToSDK() {
        AccountRoleConverter accountRoleConverter = new AccountRoleConverter(
            (com.silanis.esl.api.model.AccountRole) null);
        Assert.assertNull(accountRoleConverter.toSDKAccountRole());
    }

    @Test
    public void convertNullSDKToSDK() {
        AccountRoleConverter accountRoleConverter = new AccountRoleConverter((AccountRole) null);
        Assert.assertNull(accountRoleConverter.toSDKAccountRole());
    }

    @Test
    public void convertNullAPIToAPI() {
        AccountRoleConverter accountRoleConverter = new AccountRoleConverter(
            (com.silanis.esl.api.model.AccountRole) null);
        Assert.assertNull(accountRoleConverter.toAPIAccountRole());
    }

    @Test
    public void convertSDKToSDK() {
        AccountRole sdkAccountRole = buildSdkAccountRole();
        AccountRoleConverter accountRoleConverter = new AccountRoleConverter(sdkAccountRole);
        Assert.assertEquals(sdkAccountRole, accountRoleConverter.toSDKAccountRole());
    }

    @Test
    public void convertAPIToAPI() {
        com.silanis.esl.api.model.AccountRole apiAccountRole = buildApiAccountRole();
        AccountRoleConverter accountRoleConverter = new AccountRoleConverter(apiAccountRole);
        Assert.assertEquals(apiAccountRole, accountRoleConverter.toAPIAccountRole());
    }

    @Test
    public void convertAPIToSDK() {
        com.silanis.esl.api.model.AccountRole apiAccountRole = buildApiAccountRole();
        AccountRoleConverter accountRoleConverter = new AccountRoleConverter(apiAccountRole);
        AccountRole accountRole = accountRoleConverter.toSDKAccountRole();
        Assert.assertEquals("ID2", accountRole.getId());
        Assert.assertEquals("NAME2", accountRole.getName());
        Assert.assertEquals(Collections.singletonList("P2"), accountRole.getPermissions());
        Assert.assertEquals("DESC2", accountRole.getDescription());
        Assert.assertFalse(accountRole.isEnabled());
    }

    @Test
    public void convertSDKToAPI() {
        AccountRole sdkAccountRole = buildSdkAccountRole();
        AccountRoleConverter accountRoleConverter = new AccountRoleConverter(sdkAccountRole);
        com.silanis.esl.api.model.AccountRole accountRole = accountRoleConverter.toAPIAccountRole();
        Assert.assertEquals("ID", accountRole.getId());
        Assert.assertEquals("NAME", accountRole.getName());
        Assert.assertEquals(Collections.singletonList("P1"), accountRole.getPermissions());
        Assert.assertEquals("DESC", accountRole.getDescription());
        Assert.assertTrue(accountRole.isEnabled());
    }

    private AccountRole buildSdkAccountRole() {
        return new AccountRole("ID","NAME", Collections.singletonList("P1"),"DESC", true);
    }

    private com.silanis.esl.api.model.AccountRole buildApiAccountRole() {
        return new com.silanis.esl.api.model.AccountRole("ID2","NAME2", Collections.singletonList("P2"),"DESC2", false);
    }
}