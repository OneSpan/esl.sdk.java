package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Address;
import com.silanis.esl.sdk.SubAccount;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class SubAccountBuilderTest {
    @Test
    public void buildWithSpecifiedValues() {
        SubAccountBuilder subAccountBuilder = SubAccountBuilder.newSubAccount("subaccount_name")
                .withParentAccountId("parent_account_id")
                .withTimezoneId("ENT")
                .withLanguage("en");

        SubAccount subAccount = subAccountBuilder.build();

        assertThat(subAccount.getLanguage(), is(equalTo("en")));
        assertThat(subAccount.getName(), is(equalTo("subaccount_name")));
        assertThat(subAccount.getParentAccountId(), is(equalTo("parent_account_id")));
        assertThat(subAccount.getTimezoneId(), is(equalTo("ENT")));
    }
}
