package com.silanis.esl.sdk.internal.converter;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class LicenseConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.License sdkLicense = null;
    private com.silanis.esl.api.model.License apiLicense = null;
    private LicenseConverter converter;

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


    @Test
    public void convertNullSDKToAPI() {
        sdkLicense = null;
        converter = new LicenseConverter(sdkLicense);

        assertThat(converter.toAPILicense(), nullValue());
    }


    @Test
    public void convertNullAPIToSDK() {
        apiLicense = null;
        converter = new LicenseConverter(apiLicense);

        assertThat( converter.toSDKLicense(), nullValue());
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkLicense = null;
        converter = new LicenseConverter(sdkLicense);

        assertThat( converter.toSDKLicense(), nullValue());
    }


    @Test
    public void convertNullAPIToAPI() {
        apiLicense = null;
        converter = new LicenseConverter(apiLicense);

        assertThat(converter.toAPILicense(), nullValue());
    }

    @Test
    public void convertSDKToSDK() {
        sdkLicense = createTypicalSDKLicense();
        converter = new LicenseConverter( sdkLicense );

        com.silanis.esl.sdk.License License = converter.toSDKLicense();

        assertThat(License, is( notNullValue() ) );
        assertThat(License, is( equalTo( License ) ) );
    }

    @Test
    public void convertAPIToAPI() {
        apiLicense = createTypicalAPILicense();
        converter = new LicenseConverter( apiLicense );

        com.silanis.esl.api.model.License license = converter.toAPILicense();

        assertThat( license, is( notNullValue() ) );
        assertThat( license, is( equalTo( license ) ) );
    }

    @Test
    public void convertAPIToSDK() {
        apiLicense = createTypicalAPILicense();
        converter = new LicenseConverter( apiLicense );

        com.silanis.esl.sdk.License license = converter.toSDKLicense();

        assertThat(license, is( notNullValue() ) );
        assertThat(license.getStatus(), is(equalTo(ACC_LIC_STATUS)));
        assertThat(license.getTransactions().size(), is(equalTo(1)));
        assertThat(license.getTransactions().get(0).getCreditCard().getNumber(), is(equalTo(ACC_LIC_TRANS_CC_NUM)));
        assertThat(license.getTransactions().get(0).getPrice().getAmount(), is(equalTo(ACC_LIC_TRANS_PRICE_AMOUNT)));
        assertThat(license.getPlan().getContract(), is(equalTo(ACC_LIC_PLAN_CONTRACT)));
        assertThat(license.getPlan().getPrice().getAmount(), is(equalTo(ACC_LIC_PLAN_PRICE_AMOUNT)));
    }

    @Test
    public void convertSDKToAPI() {
        sdkLicense = createTypicalSDKLicense();
        converter = new LicenseConverter( sdkLicense );
        com.silanis.esl.api.model.License license = converter.toAPILicense();

        assertThat(license, is( notNullValue() ) );
        assertThat(license.getStatus(), is(equalTo(ACC_LIC_STATUS)));
        assertThat(license.getTransactions().size(), is(equalTo(1)));
        assertThat(license.getTransactions().get(0).getCreditCard().getNumber(), is(equalTo(ACC_LIC_TRANS_CC_NUM)));
        assertThat(license.getTransactions().get(0).getPrice().getAmount(), is(equalTo(ACC_LIC_TRANS_PRICE_AMOUNT)));
        assertThat(license.getPlan().getContract(), is(equalTo(ACC_LIC_PLAN_CONTRACT)));
        assertThat(license.getPlan().getPrice().getAmount(), is(equalTo(ACC_LIC_PLAN_PRICE_AMOUNT)));
    }

    private com.silanis.esl.sdk.License createTypicalSDKLicense() {

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

        return license;
    }

    private com.silanis.esl.api.model.License createTypicalAPILicense() {
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

        return license;
    }
}
