package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.AccountRole;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class AccountRoleBuilderTest {

    @Test
    public void testBuild() {
        List<String> permissions = Collections.singletonList("P1");
        AccountRole accountRole = AccountRoleBuilder.newAccountRole()
            .withId("ID")
            .withName("A")
            .withPermissions(permissions)
            .withDescription("D")
            .withEnabled(true)
            .build();
        Assert.assertEquals("ID", accountRole.getId());
        Assert.assertEquals("A", accountRole.getName());
        Assert.assertEquals(permissions, accountRole.getPermissions());
        Assert.assertEquals("D", accountRole.getDescription());
        Assert.assertTrue(accountRole.isEnabled());
    }
}