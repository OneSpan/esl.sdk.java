package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.UserAccountRole;
import com.silanis.esl.sdk.internal.EslServerException;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/*
 * Since a fake userID is used in example class, a try catch statement is used to cover the "userNotFound" exception. once a real user ID is used, the test will skip catch statement and assert response.
 */
public class GetAndAssignUserAccountRoleExampleTest {

    @Test
    public void verifyResult() {
        List<UserAccountRole> userAccountRoleList;
        GetAndAssignUserAccountRoleExample example = new GetAndAssignUserAccountRoleExample();
        try {
            example.run();
        } catch (EslServerException e) {
            String msg = e.getMessage();
            assertTrue(msg.contains("error.notFound.userNotFound") || msg.contains("error.forbidden.noPermission"));
            return;
        } finally {
           userAccountRoleList = example.userAccountRoleList;
        }
        assertThat(userAccountRoleList.size(), greaterThanOrEqualTo(1));
    }
}
