package com.silanis.esl.sdk.builder;

import com.google.common.collect.ImmutableMap;
import com.silanis.esl.sdk.Account;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class AccountBuilderTest {
    @Test
    public void buildWithSpecifiedValues() {

        AccountBuilder accountBuilder = AccountBuilder.newAccount()
                .withName("account_name")
                .withId("account_id")
                .withOwner("account_owner")
                .withLogoUrl("account_logoUrl")
                .withData(ImmutableMap.<String, Object>of("account_data_key1", "account_data_value1"))
                .withCompany(CompanyBuilder.newCompany().withName("company_name")
                        .withAddress(AddressBuilder.newAddress()
                                .withAddress1("company_address_address1")
                                .withAddress2("company_address_address2")
                                .withCity("company_address_city")
                                .withCountry("company_address_country")
                                .withState("company_address_state")
                                .withZipCode("company_address_zipCode").build())
                        .withId("company_id")
                        .withData(ImmutableMap.<String, Object>of("company_data_key", "company_data_value"))
                        .build())
                .withCustomField(CustomFieldBuilder.customFieldWithId("field_id")
                        .withDefaultValue("field_default_value")
                        .isRequired(true)
                        .withTranslation(TranslationBuilder.newTranslation("en").build())
                        .build())
                .withLicense(LicenseBuilder.newLicense()
                        .withCreated(new Date())
                        .withPaidUntil(new Date())
                        .withStatus("license_status")
                        .withTransaction(new Date(),
                                CreditCardBuilder.newCreditCard()
                                        .withCvv("cvv")
                                        .withName("creditCard_name")
                                        .withNumber("12345")
                                        .withType("visa")
                                        .withExpiration(12, 12)
                                        .build(),
                                PriceBuilder.newPrice()
                                        .withAmount(1000)
                                        .withCurrency("currency_id", "currency_name",
                                                ImmutableMap.<String, Object>of("currency_data_key", "currency_data_value"))
                                        .build())
                        .withPlan(PlanBuilder.newPlan("plan_name")
                                .withId("plan_id")
                                .withContract("plan_contract")
                                .withDescription("plan_description")
                                .withGroup("plan_group")
                                .withCycle("plan_cycle")
                                .withOriginal("original")
                                .withData(ImmutableMap.<String, Object>of("plan_data_key", "plan_data_value"))
                                .withFreeCycles(1, "free_cycle")
                                .withQuota("quota_cycle", 1, "quota_scope", "quota_target")
                                .withFeatures(ImmutableMap.<String, Object>of("feature_key", "feature_value"))
                                .withPrice(PriceBuilder.newPrice()
                                        .withAmount(2000)
                                        .withCurrency("plan_price_currency_id", "plan_price_currency_name",
                                                ImmutableMap.<String, Object>of("plan_price_currency_data_key", "plan_price_currency_data_value"))
                                        .build())
                                .build())
                        .build())
                .withAccountProviders(
                        Arrays.asList(ProviderBuilder.newProvider()
                                        .withId("doc_provider_id")
                                        .withName("doc_provider_name")
                                        .withData(ImmutableMap.<String, Object>of("doc_provider_data_key", "doc_provider_data_value"))
                                        .build()),
                        Arrays.asList(ProviderBuilder.newProvider()
                                        .withId("usr_provider_id")
                                        .withName("usr_provider_name")
                                        .withData(ImmutableMap.<String, Object>of("usr_provider_data_key", "usr_provider_data_value"))
                                        .build())
                        );

        Account account = accountBuilder.build();

        assertThat(account.getName(), is(equalTo("account_name")));
        assertThat(account.getData().get("account_data_key1").toString(), is( equalTo("account_data_value1")));
        assertThat(account.getCompany().getId(), is(equalTo("company_id")));
        assertThat(account.getCustomFields().size(), is(equalTo(1)));
        assertThat(account.getLicenses().size(), is(equalTo(1)));
    }
}
