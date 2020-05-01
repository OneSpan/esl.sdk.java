package com.silanis.esl.sdk.internal.converter;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class SubAccountConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.SubAccount sdkSubAccount = null;
    private com.silanis.esl.api.model.SubAccount apiSubAccount = null;
    private SubAccountConverter converter;

    private static final String SUBACCOUNT_NAME = "subAccount_name";
    private static final String SUBACCOUNT_LANGUAGE = "en";
    private static final String SUBACCOUNT_TIMEZONE_ID = "GMT";
    private static final String SUBACCOUNT_PARENT_ACCOUNT_ID = "dummy_account_ID";


    @Test
    public void convertNullSDKToAPI() {
        sdkSubAccount = null;
        converter = new SubAccountConverter(sdkSubAccount);

        assertThat(converter.toAPISubAccount(), nullValue());
    }


    @Test
    public void convertNullAPIToSDK() {
        apiSubAccount = null;
        converter = new SubAccountConverter(apiSubAccount);

        assertThat( converter.toSDKSubAccount(), nullValue());
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkSubAccount = null;
        converter = new SubAccountConverter(sdkSubAccount);

        assertThat( converter.toSDKSubAccount(), nullValue());
    }


    @Test
    public void convertNullAPIToAPI() {
        apiSubAccount = null;
        converter = new SubAccountConverter(apiSubAccount);

        assertThat(converter.toAPISubAccount(), nullValue());
    }

    @Test
    public void convertSDKToSDK() {
        sdkSubAccount = createTypicalSDKSubAccount();
        converter = new SubAccountConverter( sdkSubAccount );

        com.silanis.esl.sdk.SubAccount subAccount = converter.toSDKSubAccount();
        assertThat(subAccount, is( notNullValue() ) );
        assertThat(subAccount, is( equalTo( subAccount ) ) );
    }

    @Test
    public void convertAPIToAPI() {
        apiSubAccount = createTypicalAPISubAccount();
        converter = new SubAccountConverter( apiSubAccount );

        com.silanis.esl.api.model.SubAccount subAccount = converter.toAPISubAccount();

        assertThat( subAccount, is( notNullValue() ) );
        assertThat( subAccount, is( equalTo( subAccount ) ) );
    }

    @Test
    public void convertAPIToSDK() {
        apiSubAccount = createTypicalAPISubAccount();
        converter = new SubAccountConverter( apiSubAccount );

        com.silanis.esl.sdk.SubAccount subAccount = converter.toSDKSubAccount();

        assertThat(subAccount, is( notNullValue() ) );
        assertThat(subAccount.getLanguage(), is(equalTo(SUBACCOUNT_LANGUAGE)));
        assertThat(subAccount.getParentAccountId(), is(equalTo(SUBACCOUNT_PARENT_ACCOUNT_ID)));
        assertThat(subAccount.getTimezoneId(), is(equalTo(SUBACCOUNT_TIMEZONE_ID)));
        assertThat(subAccount.getName(), is(equalTo(SUBACCOUNT_NAME)));

    }

    @Test
    public void convertSDKToAPI() {
        sdkSubAccount = createTypicalSDKSubAccount();
        converter = new SubAccountConverter( sdkSubAccount );
        com.silanis.esl.api.model.SubAccount subAccount = converter.toAPISubAccount();

        assertThat(subAccount, is( notNullValue() ) );
        assertThat(subAccount.getLanguage(), is(equalTo(SUBACCOUNT_LANGUAGE)));
        assertThat(subAccount.getParentAccountId(), is(equalTo(SUBACCOUNT_PARENT_ACCOUNT_ID)));
        assertThat(subAccount.getTimezoneId(), is(equalTo(SUBACCOUNT_TIMEZONE_ID)));
        assertThat(subAccount.getName(), is(equalTo(SUBACCOUNT_NAME)));
    }

    private com.silanis.esl.sdk.SubAccount createTypicalSDKSubAccount() {

        com.silanis.esl.sdk.SubAccount subAccount = new com.silanis.esl.sdk.SubAccount();

        subAccount.setTimezoneId(SUBACCOUNT_TIMEZONE_ID);
        subAccount.setLanguage(SUBACCOUNT_LANGUAGE);
        subAccount.setParentAccountId(SUBACCOUNT_PARENT_ACCOUNT_ID);
        subAccount.setName(SUBACCOUNT_NAME);

        return subAccount;
    }

    private com.silanis.esl.api.model.SubAccount createTypicalAPISubAccount() {

        com.silanis.esl.api.model.SubAccount subAccount = new com.silanis.esl.api.model.SubAccount();

        subAccount.setTimezoneId(SUBACCOUNT_TIMEZONE_ID);
        subAccount.setLanguage(SUBACCOUNT_LANGUAGE);
        subAccount.setParentAccountId(SUBACCOUNT_PARENT_ACCOUNT_ID);
        subAccount.setName(SUBACCOUNT_NAME);

        return subAccount;
    }
}
