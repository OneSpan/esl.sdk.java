package com.silanis.esl.sdk.internal.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertTrue;

import com.silanis.esl.sdk.FieldCondition;
import com.silanis.esl.sdk.ReferencedFieldConditions;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class ReferencedFieldConditionsConverterTest extends ReferencedConditionsConverterTest {

    @Override
    @Test
    public void convertNullSDKToAPI() {
        com.silanis.esl.api.model.ReferencedFieldConditions api = ReferencedFieldConditionsConverter.toAPI(null);
        assertThat("Converter didn't return a null api object for a null sdk object", api, is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        ReferencedFieldConditions sdk = ReferencedFieldConditionsConverter.toSDK(null);
        assertThat("Converter didn't return a null sdk object for a null api object", sdk, is(nullValue()));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        List<com.silanis.esl.api.model.FieldCondition> apiFieldConditionsReferencedInCondition = createApiFieldConditionsForTest(CONDITION_1_ID, FIELD_1_ID, FIELD_2_ID);
        List<com.silanis.esl.api.model.FieldCondition> apiFieldConditionsReferencedInAction = new ArrayList<com.silanis.esl.api.model.FieldCondition>();

        com.silanis.esl.api.model.ReferencedFieldConditions api = new com.silanis.esl.api.model.ReferencedFieldConditions();
        api.setReferencedInCondition(apiFieldConditionsReferencedInCondition);
        api.setReferencedInAction(apiFieldConditionsReferencedInAction);

        ReferencedFieldConditions sdk = ReferencedFieldConditionsConverter.toSDK(api);

        assertTrue("Field Conditions referenced in Condition was not correctly converted",
            compareFieldConditions(apiFieldConditionsReferencedInCondition, sdk.getReferencedInCondition()));
        assertTrue("Field Conditions referenced in Condition was not correctly converted",
            compareFieldConditions(apiFieldConditionsReferencedInAction, sdk.getReferencedInAction()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        List<FieldCondition> sdkFieldConditionsReferencedInCondition = createSdkFieldConditionsForTest(CONDITION_1_ID, FIELD_1_ID, FIELD_2_ID);
        List<FieldCondition> sdkFieldConditionsReferencedInAction = new ArrayList<FieldCondition>();

        ReferencedFieldConditions sdk = new ReferencedFieldConditions();
        sdk.setReferencedInCondition(sdkFieldConditionsReferencedInCondition);
        sdk.setReferencedInAction(sdkFieldConditionsReferencedInAction);

        com.silanis.esl.api.model.ReferencedFieldConditions api = ReferencedFieldConditionsConverter.toAPI(sdk);

        assertTrue("Field Conditions referenced in Condition was not correctly converted",
            compareFieldConditions(api.getReferencedInCondition(), sdkFieldConditionsReferencedInCondition));
        assertTrue("Field Conditions referenced in Condition was not correctly converted",
            compareFieldConditions(api.getReferencedInAction(), sdkFieldConditionsReferencedInAction));
    }
}
