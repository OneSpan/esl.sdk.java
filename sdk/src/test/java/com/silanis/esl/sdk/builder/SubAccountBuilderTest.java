package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Address;
import com.silanis.esl.sdk.SubAccount;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class SubAccountBuilderTest {

    private static final String SUBACCOUNT_NAME = "subAccount_name";
    private static final String SUBACCOUNT_LANGUAGE = "en";
    private static final String SUBACCOUNT_TIMEZONE_ID = "GMT";
    private static final String SUBACCOUNT_PARENT_ACCOUNT_ID = "dummy_account_ID";

    @Test
    public void buildWithSpecifiedValues() {
        SubAccountBuilder subAccountBuilder = SubAccountBuilder.newSubAccount(SUBACCOUNT_NAME)
                .withParentAccountId(SUBACCOUNT_PARENT_ACCOUNT_ID)
                .withTimezoneId(SUBACCOUNT_TIMEZONE_ID)
                .withLanguage(SUBACCOUNT_LANGUAGE);

        SubAccount subAccount = subAccountBuilder.build();

        assertThat(subAccount.getLanguage(), is(equalTo(SUBACCOUNT_LANGUAGE)));
        assertThat(subAccount.getName(), is(equalTo(SUBACCOUNT_NAME)));
        assertThat(subAccount.getParentAccountId(), is(equalTo(SUBACCOUNT_PARENT_ACCOUNT_ID)));
        assertThat(subAccount.getTimezoneId(), is(equalTo(SUBACCOUNT_TIMEZONE_ID)));
    }
}
