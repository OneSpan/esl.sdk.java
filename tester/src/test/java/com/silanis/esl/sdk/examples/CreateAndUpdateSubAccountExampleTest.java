package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AccessibleAccountResponse;
import com.silanis.esl.sdk.Account;
import com.silanis.esl.sdk.SubAccountApiKey;
import com.silanis.esl.sdk.internal.EslServerException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static com.silanis.esl.sdk.examples.CreateAndUpdateSubAccountExample.NAME;
import static org.junit.Assert.assertTrue;

/*
 * Since a fake AccountID is used in example class, a try catch statement is used to cover the "accountNotFound" exception. once a real account ID is used, the test will skip catch statement and assert response.
 */
public class CreateAndUpdateSubAccountExampleTest {

    @Test
    public void verifyResult() {
        CreateAndUpdateSubAccountExample example = new CreateAndUpdateSubAccountExample();
        List<Account> subAccountList;
        List<SubAccountApiKey> subAccountApiKeyList;
        List<AccessibleAccountResponse> accessibleAccountList;

        try {
            example.run();
        } catch (EslServerException e) {

            String msg = e.getMessage();
            assertTrue(msg.contains("error.notFound.accountNotFound") || msg.contains("error.forbidden.noPermission"));
            return;
        } finally {
            subAccountList = example.subAccounts;
            accessibleAccountList = example.accessibleAccounts;
            subAccountApiKeyList = example.subAccountApiKeys;
        }

        assertThat(subAccountList.size(), greaterThanOrEqualTo(1));

        List<String> accountNames = new ArrayList<String>();
        for (Account account : subAccountList) {
            accountNames.add(account.getName());
        }
        assertThat(accountNames, hasItem(NAME));

        assertThat(accessibleAccountList.size(), greaterThanOrEqualTo(1));

        assertThat(subAccountApiKeyList.size(), greaterThanOrEqualTo(1));
    }
}
