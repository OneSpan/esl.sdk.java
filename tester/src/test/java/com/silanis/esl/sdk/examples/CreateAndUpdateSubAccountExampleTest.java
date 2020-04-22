package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.Account;
import com.silanis.esl.sdk.internal.EslServerException;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static com.silanis.esl.sdk.examples.CreateAndUpdateSubAccountExample.NAME;

public class CreateAndUpdateSubAccountExampleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void verifyResult() {
        CreateAndUpdateSubAccountExample example = new CreateAndUpdateSubAccountExample();
        List<Account> subAccountList;

        try {
            example.run();
        } catch (EslServerException e) {
            thrown.expect(EslServerException.class);
            thrown.expectMessage(containsString("error.notFound.accountNotFound"));
            throw e;
        } finally {
            subAccountList = example.subAccounts;
        }

        assertThat(subAccountList.size(), greaterThanOrEqualTo(1));
        assertThat(subAccountList, hasItem(anyOf(Matchers.<Account>hasProperty("name", is(NAME)))));
    }
}
