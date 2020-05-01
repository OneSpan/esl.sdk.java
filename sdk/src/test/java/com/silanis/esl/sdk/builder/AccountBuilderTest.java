package com.silanis.esl.sdk.builder;

import com.google.common.collect.ImmutableMap;
import com.silanis.esl.sdk.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class AccountBuilderTest {

    private static final String ACC_NAME = "account_name";
    private static final String ACC_ID = "account_id";
    private static final String ACC_OWNER = "account_owner";
    private static final String ACC_LOGOURL = "account_logoUrl";
    private static final Date ACC_CREATED = new Date();
    private static final Date ACC_UPDATED = new Date();
    private static final Map<String, Object> ACC_DATA = ImmutableMap.<String, Object>of("account_data_0_key", "account_data_0_value");
    private static final String ACC_CO_NAME = "company_name";
    private static final String ACC_CO_ID = "company_id";
    private static final Map<String, Object> ACC_CO_DATA = ImmutableMap.<String, Object>of("account_company_data_0_key", "account_company_data_0_value");
    private static final String ACC_CO_ADDR_ADDR1 = "company_address_address1";
    private static final String ACC_CO_ADDR_ADDR2 = "company_address_address2";
    private static final String ACC_CO_ADDR_CITY = "company_address_city";
    private static final String ACC_CO_ADDR_COUNTRY = "company_address_country";
    private static final String ACC_CO_ADDR_STATE = "company_address_state";
    private static final String ACC_CO_ADDR_ZIP = "company_address_zipcode";
    private static final String ACC_FIELD_ID = "field_0_id";
    private static final String ACC_FIELD_DEF_VLE = "field_0_default_value";
    private static final boolean ACC_FIELD_IS_REQUIRED = true;
    private static final String ACC_FIELD_TRANSL_LANG = "en";
    private static final Date ACC_LIC_CREATED = new Date();
    private static final Date ACC_LIC_PAIDUNTIL = new Date();
    private static final String ACC_LIC_STATUS = "license_0_status";
    private static final Date ACC_LIC_TRANS_CREATED = new Date();
    private static final String ACC_LIC_TRANS_CC_CVV = "license_0_transaction_0_creditCard_cvv";
    private static final String ACC_LIC_TRANS_CC_NAME = "license_0_transaction_0_creditCard_name";
    private static final String ACC_LIC_TRANS_CC_NUM = "license_0_transaction_0_creditCard_number";
    private static final String ACC_LIC_TRANS_CC_TYPE = "license_0_transaction_0_creditCard_type";
    private static final Integer ACC_LIC_TRANS_CC_EXP_MONTH = 12;
    private static final Integer ACC_LIC_TRANS_CC_EXP_YEAR = 12;
    private static final Integer ACC_LIC_TRANS_PRICE_AMOUNT = 1000;
    private static final String ACC_LIC_TRANS_PRICE_CURR_ID = "transaction_0_price_currency_id";
    private static final String ACC_LIC_TRANS_PRICE_CURR_NAME = "transaction_0_price_currency_name";
    private static final Map<String, Object> ACC_LIC_TRANS_PRICE_CURR_DATA = ImmutableMap.<String, Object>of("transaction_0_price_currency_data_0_key", "transaction_0_price_currency_data_0_value");
    private static final String ACC_LIC_PLAN_NAME = "plan_name";
    private static final String ACC_LIC_PLAN_ID = "plan_id";
    private static final String ACC_LIC_PLAN_CONTRACT = "plan_contract";
    private static final String ACC_LIC_PLAN_DES = "plan_description";
    private static final String ACC_LIC_PLAN_GRP = "plan_group";
    private static final String ACC_LIC_PLAN_CYC = "plan_cycle";
    private static final String ACC_LIC_PLAN_ORI = "plan_original";
    private static final Integer ACC_LIC_PLAN_CYC_COUNT = 1;
    private static final String ACC_LIC_PLAN_CYC_CYCLE = "plan_cycle_freeCycle";
    private static final Map<String, Object> ACC_LIC_PLAN_DATA = ImmutableMap.<String, Object>of("plan_data_0_key", "plan_data_0_value");
    private static final Map<String, Object> ACC_LIC_PLAN_FEAT = ImmutableMap.<String, Object>of("plan_feature_0_key", "plan_feature_0_value");
    private static final String ACC_LIC_PLAN_QUOTA_CYCLE = "quota_cycle";
    private static final Integer ACC_LIC_PLAN_QUOTA_LIMIT = 1;
    private static final String ACC_LIC_PLAN_QUOTA_SCOPE = "quota_scope";
    private static final String ACC_LIC_PLAN_QUOTA_TARGET = "quota_target";
    private static final Integer ACC_LIC_PLAN_PRICE_AMOUNT = 2000;
    private static final String ACC_LIC_PLAN_PRICE_CURR_ID = "plan_price_currency_id";
    private static final String ACC_LIC_PLAN_PRICE_CURR_NAME = "plan_price_currency_name";
    private static final Map<String, Object> ACC_LIC_PLAN_PRICE_CURR_DATA = ImmutableMap.<String, Object>of("plan_price_data_0_key", "plan_price_data_0_value");
    private static final String ACC_PROV_DOC_ID = "doc_provider_id";
    private static final String ACC_PROV_USR_ID = "usr_provider_id";
    private static final String ACC_PROV_DOC_NAME = "doc_provider_name";
    private static final String ACC_PROV_USR_NAME = "usr_provider_name";
    private static final String ACC_PROV_DOC_PROVIDES = "doc_provider_provides";
    private static final String ACC_PROV_USR_PROVIDES = "usr_provider_provides";
    private static final Map<String, Object> ACC_PROV_DOC_DATA = ImmutableMap.<String, Object>of("provider_doc_0_data_0_key", "provider_doc_0_data_0_value");
    private static final Map<String, Object> ACC_PROV_USR_DATA = ImmutableMap.<String, Object>of("provider_usr_0_data_0_key", "provider_usr_0_data_0_value");

    @Test
    public void buildWithSpecifiedValues() {

        AccountBuilder accountBuilder = AccountBuilder.newAccount()
                .withName(ACC_NAME)
                .withId(ACC_ID)
                .withOwner(ACC_OWNER)
                .withLogoUrl(ACC_LOGOURL)
                .withData(ACC_DATA)
                .withCreated(ACC_CREATED)
                .withUpdated(ACC_UPDATED)
                .withCompany(CompanyBuilder.newCompany().withName(ACC_CO_NAME)
                        .withAddress(AddressBuilder.newAddress()
                                .withAddress1(ACC_CO_ADDR_ADDR1)
                                .withAddress2(ACC_CO_ADDR_ADDR2)
                                .withCity(ACC_CO_ADDR_CITY)
                                .withCountry(ACC_CO_ADDR_COUNTRY)
                                .withState(ACC_CO_ADDR_STATE)
                                .withZipCode(ACC_CO_ADDR_ZIP).build())
                        .withId(ACC_CO_ID)
                        .withData(ACC_CO_DATA)
                        .build())
                .withCustomField(CustomFieldBuilder.customFieldWithId(ACC_FIELD_ID)
                        .withDefaultValue(ACC_FIELD_DEF_VLE)
                        .isRequired(ACC_FIELD_IS_REQUIRED)
                        .withTranslation(TranslationBuilder.newTranslation(ACC_FIELD_TRANSL_LANG).build())
                        .build())
                .withLicense(LicenseBuilder.newLicense()
                        .withCreated(ACC_LIC_CREATED)
                        .withPaidUntil(ACC_LIC_PAIDUNTIL)
                        .withStatus(ACC_LIC_STATUS)
                        .withTransaction(ACC_LIC_TRANS_CREATED,
                                CreditCardBuilder.newCreditCard()
                                        .withCvv(ACC_LIC_TRANS_CC_CVV)
                                        .withName(ACC_LIC_TRANS_CC_NAME)
                                        .withNumber(ACC_LIC_TRANS_CC_NUM)
                                        .withType(ACC_LIC_TRANS_CC_TYPE)
                                        .withExpiration(ACC_LIC_TRANS_CC_EXP_MONTH, ACC_LIC_TRANS_CC_EXP_YEAR)
                                        .build(),
                                PriceBuilder.newPrice()
                                        .withAmount(ACC_LIC_TRANS_PRICE_AMOUNT)
                                        .withCurrency(ACC_LIC_TRANS_PRICE_CURR_ID, ACC_LIC_TRANS_PRICE_CURR_NAME, ACC_LIC_TRANS_PRICE_CURR_DATA)
                                        .build())
                        .withPlan(PlanBuilder.newPlan(ACC_LIC_PLAN_NAME)
                                .withId(ACC_LIC_PLAN_ID)
                                .withContract(ACC_LIC_PLAN_CONTRACT)
                                .withDescription(ACC_LIC_PLAN_DES)
                                .withGroup(ACC_LIC_PLAN_GRP)
                                .withCycle(ACC_LIC_PLAN_CYC)
                                .withOriginal(ACC_LIC_PLAN_ORI)
                                .withData(ACC_LIC_PLAN_DATA)
                                .withFreeCycles(ACC_LIC_PLAN_CYC_COUNT, ACC_LIC_PLAN_CYC_CYCLE)
                                .withQuota(ACC_LIC_PLAN_QUOTA_CYCLE, ACC_LIC_PLAN_QUOTA_LIMIT, ACC_LIC_PLAN_QUOTA_SCOPE, ACC_LIC_PLAN_QUOTA_TARGET)
                                .withFeatures(ACC_LIC_PLAN_FEAT)
                                .withPrice(PriceBuilder.newPrice()
                                        .withAmount(ACC_LIC_PLAN_PRICE_AMOUNT)
                                        .withCurrency(ACC_LIC_PLAN_PRICE_CURR_ID, ACC_LIC_PLAN_PRICE_CURR_NAME, ACC_LIC_PLAN_PRICE_CURR_DATA)
                                        .build())
                                .build())
                        .build())
                .withAccountProviders(
                        Arrays.asList(ProviderBuilder.newProvider()
                                        .withId(ACC_PROV_DOC_ID)
                                        .withName(ACC_PROV_DOC_NAME)
                                        .withProvides(ACC_PROV_DOC_PROVIDES)
                                        .withData(ACC_PROV_DOC_DATA)
                                        .build()),
                        Arrays.asList(ProviderBuilder.newProvider()
                                        .withId(ACC_PROV_USR_ID)
                                        .withName(ACC_PROV_USR_NAME)
                                        .withProvides(ACC_PROV_USR_PROVIDES)
                                        .withData(ACC_PROV_USR_DATA)
                                        .build())
                        );

        Account account = accountBuilder.build();

        assertThat(account.getName(), is(equalTo(ACC_NAME)));

        assertThat(account.getCompany().getId(), is(equalTo(ACC_CO_ID)));
        assertThat(account.getCompany().getAddress().getAddress1(), is(equalTo(ACC_CO_ADDR_ADDR1)));

        assertThat(account.getCustomFields().size(), is(equalTo(1)));
        assertThat(account.getCustomFields().get(0).getValue(), is(equalTo(ACC_FIELD_DEF_VLE)));
        assertThat(account.getCustomFields().get(0).getTranslations().size(), is(equalTo(1)));
        assertThat(account.getCustomFields().get(0).getTranslations().get(0).getLanguage(), is(equalTo(ACC_FIELD_TRANSL_LANG)));

        assertThat(account.getLicenses().size(), is(equalTo(1)));
        assertThat(account.getLicenses().get(0).getStatus(), is(equalTo(ACC_LIC_STATUS)));
        assertThat(account.getLicenses().get(0).getTransactions().size(), is(equalTo(1)));
        assertThat(account.getLicenses().get(0).getTransactions().get(0).getCreditCard().getNumber(), is(equalTo(ACC_LIC_TRANS_CC_NUM)));
        assertThat(account.getLicenses().get(0).getTransactions().get(0).getPrice().getAmount(), is(equalTo(ACC_LIC_TRANS_PRICE_AMOUNT)));
        assertThat(account.getLicenses().get(0).getPlan().getContract(), is(equalTo(ACC_LIC_PLAN_CONTRACT)));
        assertThat(account.getLicenses().get(0).getPlan().getPrice().getAmount(), is(equalTo(ACC_LIC_PLAN_PRICE_AMOUNT)));

        assertThat(account.getProviders().getDocuments().size(), is(equalTo(1)));
        assertThat(account.getProviders().getDocuments().get(0).getName(), is(equalTo(ACC_PROV_DOC_NAME)));
        assertThat(account.getProviders().getUsers().size(), is(equalTo(1)));
        assertThat(account.getProviders().getUsers().get(0).getName(), is(equalTo(ACC_PROV_USR_NAME)));

    }
}
