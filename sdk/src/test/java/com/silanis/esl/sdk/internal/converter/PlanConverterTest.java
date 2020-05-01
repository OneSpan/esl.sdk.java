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

public class PlanConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.Plan sdkPlan = null;
    private com.silanis.esl.api.model.Plan apiPlan = null;
    private PlanConverter converter;

   
    private static final String PLAN_NAME = "plan_name";
    private static final String PLAN_ID = "plan_id";
    private static final String PLAN_CONTRACT = "plan_contract";
    private static final String PLAN_DES = "plan_description";
    private static final String PLAN_GRP = "plan_group";
    private static final String PLAN_CYC = "plan_cycle";
    private static final String PLAN_ORI = "plan_original";
    private static final Integer PLAN_CYC_COUNT = 1;
    private static final String PLAN_CYC_CYCLE = "plan_cycle_freeCycle";
    private static final Map<String, Object> PLAN_DATA = ImmutableMap.<String, Object>of("plan_data_0_key", "plan_data_0_value");
    private static final Map<String, Object> PLAN_FEAT = ImmutableMap.<String, Object>of("plan_feature_0_key", "plan_feature_0_value");
    private static final String PLAN_QUOTA_CYCLE = "quota_cycle";
    private static final Integer PLAN_QUOTA_LIMIT = 1;
    private static final String PLAN_QUOTA_SCOPE = "quota_scope";
    private static final String PLAN_QUOTA_TARGET = "quota_target";
    private static final Integer PLAN_PRICE_AMOUNT = 2000;
    private static final String PLAN_PRICE_CURR_ID = "plan_price_currency_id";
    private static final String PLAN_PRICE_CURR_NAME = "plan_price_currency_name";
    private static final Map<String, Object> PLAN_PRICE_CURR_DATA = ImmutableMap.<String, Object>of("plan_price_data_0_key", "plan_price_data_0_value");



    @Test
    public void convertNullSDKToAPI() {
        sdkPlan = null;
        converter = new PlanConverter(sdkPlan);

        assertThat(converter.toAPIPlan(), nullValue());
    }


    @Test
    public void convertNullAPIToSDK() {
        apiPlan = null;
        converter = new PlanConverter(apiPlan);

        assertThat( converter.toSDKPlan(), nullValue());
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkPlan = null;
        converter = new PlanConverter(sdkPlan);

        assertThat( converter.toSDKPlan(), nullValue());
    }


    @Test
    public void convertNullAPIToAPI() {
        apiPlan = null;
        converter = new PlanConverter(apiPlan);

        assertThat(converter.toAPIPlan(), nullValue());
    }

    @Test
    public void convertSDKToSDK() {
        sdkPlan = createTypicalSDKPlan();
        converter = new PlanConverter( sdkPlan );

        com.silanis.esl.sdk.Plan plan = converter.toSDKPlan();

        assertThat(plan, is( notNullValue() ) );
        assertThat(plan, is( equalTo( plan ) ) );
    }

    @Test
    public void convertAPIToAPI() {
        apiPlan = createTypicalAPIPlan();
        converter = new PlanConverter( apiPlan );

        com.silanis.esl.api.model.Plan plan = converter.toAPIPlan();

        assertThat( plan, is( notNullValue() ) );
        assertThat( plan, is( equalTo( plan ) ) );
    }

    @Test
    public void convertAPIToSDK() {
        apiPlan = createTypicalAPIPlan();
        converter = new PlanConverter( apiPlan );

        com.silanis.esl.sdk.Plan plan = converter.toSDKPlan();


        assertThat(plan.getContract(), is(equalTo(PLAN_CONTRACT)));
        assertThat(plan.getPrice().getAmount(), is(equalTo(PLAN_PRICE_AMOUNT)));
    }

    @Test
    public void convertSDKToAPI() {
        sdkPlan = createTypicalSDKPlan();
        converter = new PlanConverter( sdkPlan );
        com.silanis.esl.api.model.Plan plan = converter.toAPIPlan();


        assertThat(plan.getContract(), is(equalTo(PLAN_CONTRACT)));
        assertThat(plan.getPrice().getAmount(), is(equalTo(PLAN_PRICE_AMOUNT)));
    }

    private com.silanis.esl.sdk.Plan createTypicalSDKPlan() {

        com.silanis.esl.sdk.Plan plan = new com.silanis.esl.sdk.Plan();
        plan.setContract(PLAN_CONTRACT);
        plan.setGroup(PLAN_GRP);
        plan.setOriginal(PLAN_ORI);
        plan.setDescription(PLAN_DES);
        plan.setData(PLAN_DATA);
        plan.setCycle(PLAN_CYC);
        plan.setId(PLAN_ID);
        plan.setFeatures(PLAN_FEAT);
        plan.setName(PLAN_NAME);
        com.silanis.esl.sdk.CycleCount cycleCount = new com.silanis.esl.sdk.CycleCount();
        cycleCount.setCycle(PLAN_CYC_CYCLE);
        cycleCount.setCount(PLAN_CYC_COUNT);
        plan.setFreeCycles(cycleCount);
        com.silanis.esl.sdk.Quota quota = new com.silanis.esl.sdk.Quota();
        quota.setTarget(PLAN_QUOTA_TARGET);
        quota.setLimit(PLAN_QUOTA_LIMIT);
        quota.setCycle(PLAN_QUOTA_CYCLE);
        quota.setScope(PLAN_QUOTA_SCOPE);
        plan.addQuota(quota);
        com.silanis.esl.sdk.Price price1 = new com.silanis.esl.sdk.Price();
        price1.setAmount(PLAN_PRICE_AMOUNT);
        com.silanis.esl.sdk.Currency currency1 = new com.silanis.esl.sdk.Currency();
        currency1.setId(PLAN_PRICE_CURR_ID);
        currency1.setName(PLAN_PRICE_CURR_NAME);
        currency1.setData(PLAN_PRICE_CURR_DATA);
        price1.setCurrency(currency1);
        plan.setPrice(price1);

        return plan;
    }

    private com.silanis.esl.api.model.Plan createTypicalAPIPlan() {

        com.silanis.esl.api.model.Plan plan = new com.silanis.esl.api.model.Plan();
        plan.setContract(PLAN_CONTRACT);
        plan.setGroup(PLAN_GRP);
        plan.setOriginal(PLAN_ORI);
        plan.setDescription(PLAN_DES);
        plan.setData(PLAN_DATA);
        plan.setCycle(PLAN_CYC);
        plan.setId(PLAN_ID);
        plan.setFeatures(PLAN_FEAT);
        plan.setName(PLAN_NAME);
        com.silanis.esl.api.model.CycleCount cycleCount = new com.silanis.esl.api.model.CycleCount();
        cycleCount.setCycle(PLAN_CYC_CYCLE);
        cycleCount.setCount(PLAN_CYC_COUNT);
        plan.setFreeCycles(cycleCount);
        com.silanis.esl.api.model.Quota quota = new com.silanis.esl.api.model.Quota();
        quota.setTarget(PLAN_QUOTA_TARGET);
        quota.setLimit(PLAN_QUOTA_LIMIT);
        quota.setCycle(PLAN_QUOTA_CYCLE);
        quota.setScope(PLAN_QUOTA_SCOPE);
        plan.addQuota(quota);
        com.silanis.esl.api.model.Price price1 = new com.silanis.esl.api.model.Price();
        price1.setAmount(PLAN_PRICE_AMOUNT);
        com.silanis.esl.api.model.Currency currency1 = new com.silanis.esl.api.model.Currency();
        currency1.setId(PLAN_PRICE_CURR_ID);
        currency1.setName(PLAN_PRICE_CURR_NAME);
        currency1.setData(PLAN_PRICE_CURR_DATA);
        price1.setCurrency(currency1);
        plan.setPrice(price1);

        return plan;
    }
}
