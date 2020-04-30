package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AccessibleAccountResponse;
import com.silanis.esl.sdk.Account;
import com.silanis.esl.sdk.internal.EslServerException;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static com.silanis.esl.sdk.examples.CreateAndUpdateSubAccountExample.NAME;

public class CreateAndUpdateSubAccountExampleTest {

    @Test
    public void verifyResult() {
        CreateAndUpdateSubAccountExample example = new CreateAndUpdateSubAccountExample();
        List<Account> subAccountList;
        List<AccessibleAccountResponse> accessibleAccountList;

        try {
            example.run();
        } catch (EslServerException e) {
            assertThat(e.getMessage(), Matchers.either(containsString("error.notFound.accountNotFound")).or(containsString("error.forbidden.noPermission")));
            return;
        } finally {
            subAccountList = example.subAccounts;
            accessibleAccountList = example.accessibleAccounts;
        }

        assertThat(subAccountList.size(), greaterThanOrEqualTo(1));
        assertThat(subAccountList, hasItem(anyOf(Matchers.<Account>hasProperty("name", is(NAME)))));
        assertThat(accessibleAccountList.size(), greaterThanOrEqualTo(1));
    }
}
