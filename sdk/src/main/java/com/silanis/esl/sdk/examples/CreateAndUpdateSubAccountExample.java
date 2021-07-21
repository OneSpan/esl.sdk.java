package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AccessibleAccountResponse;
import com.silanis.esl.sdk.Account;
import com.silanis.esl.sdk.SubAccount;
import com.silanis.esl.sdk.SubAccountApiKey;
import com.silanis.esl.sdk.builder.SubAccountBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class CreateAndUpdateSubAccountExample extends SDKSample {

    public List<Account> subAccounts;
    public List<SubAccountApiKey> subAccountApiKeys;
    public List<AccessibleAccountResponse> accessibleAccounts;

    private static final String PARENT_ACCOUNT_ID = "dummyAccountId";
    public static final String NAME = "SubAccount_" + new SimpleDateFormat("HH:mm:ss").format(new Date());
    private static final String TIMEZONE_ID = "GMT";
    private static final String LANGUAGE = "en";
    private static final String UPDATE_TIMEZONE_ID = "Europe/Prague";
    private static final String UPDATE_LANGUAGE = "it";

    public static void main(String... args) {
        new CreateAndUpdateSubAccountExample().run();
    }

    public void execute() {
        SubAccount subAccount = SubAccountBuilder.newSubAccount(NAME)
                .withParentAccountId(PARENT_ACCOUNT_ID)
                .withLanguage(LANGUAGE)
                .withTimezoneId(TIMEZONE_ID).build();

        //Creates subAccount
        Account account = eslClient.getAccountService().createSubAccount(subAccount);

        SubAccount updateSubAccount = SubAccountBuilder.newSubAccount()
                .withLanguage(UPDATE_LANGUAGE)
                .withTimezoneId(UPDATE_TIMEZONE_ID).build();

        //Updates subAccount
        eslClient.getAccountService().updateSubAccount(updateSubAccount, account.getId());

        //Lists accessibleAccounts
        accessibleAccounts = eslClient.getAccountService().getAccessibleAccounts();

        //Lists subAccounts
        subAccounts = eslClient.getAccountService().getSubAccounts();

        //Lists subAccounts Api Key
        subAccountApiKeys = eslClient.getAccountService().getSubAccountApiKeys();
    }
}