package com.silanis.esl.sdk.internal.converter;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class AccountConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.Account sdkAccount = null;
    private com.silanis.esl.api.model.Account apiAccount = null;
    private AccountConverter converter;

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
    public void convertNullSDKToAPI() {
        sdkAccount = null;
        converter = new AccountConverter(sdkAccount);

        assertThat(converter.toAPIAccount(), nullValue());
    }


    @Test
    public void convertNullAPIToSDK() {
        apiAccount = null;
        converter = new AccountConverter(apiAccount);

        assertThat( converter.toSDKAccount(), nullValue());
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkAccount = null;
        converter = new AccountConverter(sdkAccount);

        assertThat( converter.toSDKAccount(), nullValue());
    }


    @Test
    public void convertNullAPIToAPI() {
        apiAccount = null;
        converter = new AccountConverter(apiAccount);

        assertThat(converter.toAPIAccount(), nullValue());
    }

    @Test
    public void convertSDKToSDK() {
        sdkAccount = createTypicalSDKAccount();
        converter = new AccountConverter( sdkAccount );

        com.silanis.esl.sdk.Account account = converter.toSDKAccount();

        assertThat(account, is( notNullValue() ) );
        assertThat(account, is( equalTo( account ) ) );
    }

    @Test
    public void convertAPIToAPI() {
        apiAccount = createTypicalAPIAccount();
        converter = new AccountConverter( apiAccount );

        com.silanis.esl.api.model.Account account = converter.toAPIAccount();

        assertThat( account, is( notNullValue() ) );
        assertThat( account, is( equalTo( account ) ) );
    }

    @Test
    public void convertAPIToSDK() {
        apiAccount = createTypicalAPIAccount();
        converter = new AccountConverter( apiAccount );

        com.silanis.esl.sdk.Account account = converter.toSDKAccount();

        assertThat(account, is( notNullValue() ) );
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

    @Test
    public void convertSDKToAPI() {
        sdkAccount = createTypicalSDKAccount();
        converter = new AccountConverter( sdkAccount );
        com.silanis.esl.api.model.Account account = converter.toAPIAccount();

        assertThat(account, is( notNullValue() ) );
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

    private com.silanis.esl.sdk.Account createTypicalSDKAccount() {
        com.silanis.esl.sdk.Account account = new com.silanis.esl.sdk.Account();

        account.setName(ACC_NAME);
        account.setId(ACC_ID);
        account.setOwner(ACC_OWNER);
        account.setLogoUrl(ACC_LOGOURL);
        account.setData(ACC_DATA);
        account.setCreated(ACC_CREATED);
        account.setUpdated(ACC_UPDATED);

        com.silanis.esl.sdk.Company company = new com.silanis.esl.sdk.Company();
        company.setId(ACC_CO_ID);
        company.setName(ACC_CO_NAME);
        company.setData(ACC_CO_DATA);
        com.silanis.esl.sdk.Address address = new com.silanis.esl.sdk.Address();
        address.setAddress1(ACC_CO_ADDR_ADDR1);
        address.setAddress2(ACC_CO_ADDR_ADDR2);
        address.setCity(ACC_CO_ADDR_CITY);
        address.setCountry(ACC_CO_ADDR_COUNTRY);
        address.setState(ACC_CO_ADDR_STATE);
        address.setZipCode(ACC_CO_ADDR_ZIP);
        company.setAddress(address);
        account.setCompany(company);

        com.silanis.esl.sdk.CustomField customField = new com.silanis.esl.sdk.CustomField();
        customField.setId(ACC_FIELD_ID);
        customField.setRequired(ACC_FIELD_IS_REQUIRED);
        customField.setValue(ACC_FIELD_DEF_VLE);
        com.silanis.esl.sdk.Translation translation = new com.silanis.esl.sdk.Translation();
        translation.setLanguage(ACC_FIELD_TRANSL_LANG);
        customField.setTranslations(Arrays.asList(translation));
        account.addCustomField(customField);

        com.silanis.esl.sdk.License license = new com.silanis.esl.sdk.License();
        license.setCreated(ACC_LIC_CREATED);
        license.setStatus(ACC_LIC_STATUS);
        license.setPaidUntil(ACC_LIC_PAIDUNTIL);
        com.silanis.esl.sdk.Transaction transaction = new com.silanis.esl.sdk.Transaction();
        transaction.setCreated(ACC_LIC_TRANS_CREATED);
        com.silanis.esl.sdk.CreditCard creditCard = new com.silanis.esl.sdk.CreditCard();
        creditCard.setCvv(ACC_LIC_TRANS_CC_CVV);
        creditCard.setType(ACC_LIC_TRANS_CC_TYPE);
        creditCard.setName(ACC_LIC_TRANS_CC_NAME);
        creditCard.setNumber(ACC_LIC_TRANS_CC_NUM);
        com.silanis.esl.sdk.CcExpiration ccExpiration = new com.silanis.esl.sdk.CcExpiration();
        ccExpiration.setMonth(ACC_LIC_TRANS_CC_EXP_MONTH);
        ccExpiration.setYear(ACC_LIC_TRANS_CC_EXP_YEAR);
        creditCard.setExpiration(ccExpiration);
        transaction.setCreditCard(creditCard);
        com.silanis.esl.sdk.Price price = new com.silanis.esl.sdk.Price();
        price.setAmount(ACC_LIC_TRANS_PRICE_AMOUNT);
        com.silanis.esl.sdk.Currency currency = new com.silanis.esl.sdk.Currency();
        currency.setData(ACC_LIC_TRANS_PRICE_CURR_DATA);
        currency.setName(ACC_LIC_TRANS_PRICE_CURR_NAME);
        currency.setId(ACC_LIC_TRANS_PRICE_CURR_ID);
        price.setCurrency(currency);
        transaction.setPrice(price);
        license.addTransaction(transaction);
        com.silanis.esl.sdk.Plan plan = new com.silanis.esl.sdk.Plan();
        plan.setContract(ACC_LIC_PLAN_CONTRACT);
        plan.setGroup(ACC_LIC_PLAN_GRP);
        plan.setOriginal(ACC_LIC_PLAN_ORI);
        plan.setDescription(ACC_LIC_PLAN_DES);
        plan.setData(ACC_LIC_PLAN_DATA);
        plan.setCycle(ACC_LIC_PLAN_CYC);
        plan.setId(ACC_LIC_PLAN_ID);
        plan.setFeatures(ACC_LIC_PLAN_FEAT);
        plan.setName(ACC_LIC_PLAN_NAME);
        com.silanis.esl.sdk.CycleCount cycleCount = new com.silanis.esl.sdk.CycleCount();
        cycleCount.setCycle(ACC_LIC_PLAN_CYC_CYCLE);
        cycleCount.setCount(ACC_LIC_PLAN_CYC_COUNT);
        plan.setFreeCycles(cycleCount);
        com.silanis.esl.sdk.Quota quota = new com.silanis.esl.sdk.Quota();
        quota.setTarget(ACC_LIC_PLAN_QUOTA_TARGET);
        quota.setLimit(ACC_LIC_PLAN_QUOTA_LIMIT);
        quota.setCycle(ACC_LIC_PLAN_QUOTA_CYCLE);
        quota.setScope(ACC_LIC_PLAN_QUOTA_SCOPE);
        plan.addQuota(quota);
        com.silanis.esl.sdk.Price price1 = new com.silanis.esl.sdk.Price();
        price1.setAmount(ACC_LIC_PLAN_PRICE_AMOUNT);
        com.silanis.esl.sdk.Currency currency1 = new com.silanis.esl.sdk.Currency();
        currency1.setId(ACC_LIC_PLAN_PRICE_CURR_ID);
        currency1.setName(ACC_LIC_PLAN_PRICE_CURR_NAME);
        currency1.setData(ACC_LIC_PLAN_PRICE_CURR_DATA);
        price1.setCurrency(currency1);
        plan.setPrice(price1);
        license.setPlan(plan);
        account.addLicense(license);

        com.silanis.esl.sdk.AccountProviders accountProviders = new com.silanis.esl.sdk.AccountProviders();
        com.silanis.esl.sdk.Provider provider = new com.silanis.esl.sdk.Provider();
        provider.setId(ACC_PROV_DOC_ID);
        provider.setName(ACC_PROV_DOC_NAME);
        provider.setData(ACC_PROV_DOC_DATA);
        provider.setProvides(ACC_PROV_DOC_PROVIDES);
        accountProviders.addDocument(provider);
        com.silanis.esl.sdk.Provider provider1 = new com.silanis.esl.sdk.Provider();
        provider1.setId(ACC_PROV_USR_ID);
        provider1.setName(ACC_PROV_USR_NAME);
        provider1.setData(ACC_PROV_USR_DATA);
        provider1.setProvides(ACC_PROV_USR_PROVIDES);
        accountProviders.addUser(provider1);
        account.setProviders(accountProviders);

        return account;
    }

    private com.silanis.esl.api.model.Account createTypicalAPIAccount() {
        com.silanis.esl.api.model.Account account = new com.silanis.esl.api.model.Account();

        account.setName(ACC_NAME);
        account.setId(ACC_ID);
        account.setOwner(ACC_OWNER);
        account.setLogoUrl(ACC_LOGOURL);
        account.setData(ACC_DATA);
        account.setCreated(ACC_CREATED);
        account.setUpdated(ACC_UPDATED);

        com.silanis.esl.api.model.Company company = new com.silanis.esl.api.model.Company();
        company.setId(ACC_CO_ID);
        company.setName(ACC_CO_NAME);
        company.setData(ACC_CO_DATA);
        com.silanis.esl.api.model.Address address = new com.silanis.esl.api.model.Address();
        address.setAddress1(ACC_CO_ADDR_ADDR1);
        address.setAddress2(ACC_CO_ADDR_ADDR2);
        address.setCity(ACC_CO_ADDR_CITY);
        address.setCountry(ACC_CO_ADDR_COUNTRY);
        address.setState(ACC_CO_ADDR_STATE);
        address.setZipcode(ACC_CO_ADDR_ZIP);
        company.setAddress(address);
        account.setCompany(company);

        com.silanis.esl.api.model.CustomField customField = new com.silanis.esl.api.model.CustomField();
        customField.setId(ACC_FIELD_ID);
        customField.setRequired(ACC_FIELD_IS_REQUIRED);
        customField.setValue(ACC_FIELD_DEF_VLE);
        com.silanis.esl.api.model.Translation translation = new com.silanis.esl.api.model.Translation();
        translation.setLanguage(ACC_FIELD_TRANSL_LANG);
        customField.setTranslations(Arrays.asList(translation));
        account.addCustomField(customField);

        com.silanis.esl.api.model.License license = new com.silanis.esl.api.model.License();
        license.setCreated(ACC_LIC_CREATED);
        license.setStatus(ACC_LIC_STATUS);
        license.setPaidUntil(ACC_LIC_PAIDUNTIL);
        com.silanis.esl.api.model.Transaction transaction = new com.silanis.esl.api.model.Transaction();
        transaction.setCreated(ACC_LIC_TRANS_CREATED);
        com.silanis.esl.api.model.CreditCard creditCard = new com.silanis.esl.api.model.CreditCard();
        creditCard.setCvv(ACC_LIC_TRANS_CC_CVV);
        creditCard.setType(ACC_LIC_TRANS_CC_TYPE);
        creditCard.setName(ACC_LIC_TRANS_CC_NAME);
        creditCard.setNumber(ACC_LIC_TRANS_CC_NUM);
        com.silanis.esl.api.model.CcExpiration ccExpiration = new com.silanis.esl.api.model.CcExpiration();
        ccExpiration.setMonth(ACC_LIC_TRANS_CC_EXP_MONTH);
        ccExpiration.setYear(ACC_LIC_TRANS_CC_EXP_YEAR);
        creditCard.setExpiration(ccExpiration);
        transaction.setCreditCard(creditCard);
        com.silanis.esl.api.model.Price price = new com.silanis.esl.api.model.Price();
        price.setAmount(ACC_LIC_TRANS_PRICE_AMOUNT);
        com.silanis.esl.api.model.Currency currency = new com.silanis.esl.api.model.Currency();
        currency.setData(ACC_LIC_TRANS_PRICE_CURR_DATA);
        currency.setName(ACC_LIC_TRANS_PRICE_CURR_NAME);
        currency.setId(ACC_LIC_TRANS_PRICE_CURR_ID);
        price.setCurrency(currency);
        transaction.setPrice(price);
        license.addTransaction(transaction);
        com.silanis.esl.api.model.Plan plan = new com.silanis.esl.api.model.Plan();
        plan.setContract(ACC_LIC_PLAN_CONTRACT);
        plan.setGroup(ACC_LIC_PLAN_GRP);
        plan.setOriginal(ACC_LIC_PLAN_ORI);
        plan.setDescription(ACC_LIC_PLAN_DES);
        plan.setData(ACC_LIC_PLAN_DATA);
        plan.setCycle(ACC_LIC_PLAN_CYC);
        plan.setId(ACC_LIC_PLAN_ID);
        plan.setFeatures(ACC_LIC_PLAN_FEAT);
        plan.setName(ACC_LIC_PLAN_NAME);
        com.silanis.esl.api.model.CycleCount cycleCount = new com.silanis.esl.api.model.CycleCount();
        cycleCount.setCycle(ACC_LIC_PLAN_CYC_CYCLE);
        cycleCount.setCount(ACC_LIC_PLAN_CYC_COUNT);
        plan.setFreeCycles(cycleCount);
        com.silanis.esl.api.model.Quota quota = new com.silanis.esl.api.model.Quota();
        quota.setTarget(ACC_LIC_PLAN_QUOTA_TARGET);
        quota.setLimit(ACC_LIC_PLAN_QUOTA_LIMIT);
        quota.setCycle(ACC_LIC_PLAN_QUOTA_CYCLE);
        quota.setScope(ACC_LIC_PLAN_QUOTA_SCOPE);
        plan.addQuota(quota);
        com.silanis.esl.api.model.Price price1 = new com.silanis.esl.api.model.Price();
        price1.setAmount(ACC_LIC_PLAN_PRICE_AMOUNT);
        com.silanis.esl.api.model.Currency currency1 = new com.silanis.esl.api.model.Currency();
        currency1.setId(ACC_LIC_PLAN_PRICE_CURR_ID);
        currency1.setName(ACC_LIC_PLAN_PRICE_CURR_NAME);
        currency1.setData(ACC_LIC_PLAN_PRICE_CURR_DATA);
        price1.setCurrency(currency1);
        plan.setPrice(price1);
        license.setPlan(plan);
        account.addLicense(license);

        com.silanis.esl.api.model.AccountProviders accountProviders = new com.silanis.esl.api.model.AccountProviders();
        com.silanis.esl.api.model.Provider provider = new com.silanis.esl.api.model.Provider();
        provider.setId(ACC_PROV_DOC_ID);
        provider.setName(ACC_PROV_DOC_NAME);
        provider.setData(ACC_PROV_DOC_DATA);
        provider.setProvides(ACC_PROV_DOC_PROVIDES);
        accountProviders.addDocument(provider);
        com.silanis.esl.api.model.Provider provider1 = new com.silanis.esl.api.model.Provider();
        provider1.setId(ACC_PROV_USR_ID);
        provider1.setName(ACC_PROV_USR_NAME);
        provider1.setData(ACC_PROV_USR_DATA);
        provider1.setProvides(ACC_PROV_USR_PROVIDES);
        accountProviders.addUser(provider1);
        account.setProviders(accountProviders);

        return account;
    }
}
