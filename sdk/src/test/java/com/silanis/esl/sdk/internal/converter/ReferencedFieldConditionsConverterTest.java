package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.FieldCondition;
import com.silanis.esl.sdk.ReferencedFieldConditions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.silanis.esl.sdk.internal.converter.ReferencedFieldConditionsConverter.toAPI;
import static com.silanis.esl.sdk.internal.converter.ReferencedFieldConditionsConverter.toSDK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertTrue;

public class ReferencedFieldConditionsConverterTest extends ReferencedConditionsConverterTest {

    @Override
    @Test
    public void convertNullSDKToAPI() {
        com.silanis.esl.api.model.ReferencedFieldConditions api = toAPI(null);
        assertThat("Converter didn't return a null api object for a null sdk object", api, nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        ReferencedFieldConditions sdk = toSDK(null);
        assertThat("Converter didn't return a null sdk object for a null api object", sdk, nullValue());
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        List<com.silanis.esl.api.model.FieldCondition> apiFieldConditionsReferencedInCondition = buildApiFieldConditions(CONDITION_1_ID, FIELD_1_ID, FIELD_2_ID);
        List<com.silanis.esl.api.model.FieldCondition> apiFieldConditionsReferencedInAction = new ArrayList<com.silanis.esl.api.model.FieldCondition>();

        com.silanis.esl.api.model.ReferencedFieldConditions api = new com.silanis.esl.api.model.ReferencedFieldConditions();
        api.setReferencedInCondition(apiFieldConditionsReferencedInCondition);
        api.setReferencedInAction(apiFieldConditionsReferencedInAction);

        ReferencedFieldConditions sdk = toSDK(api);

        assertTrue("Field Conditions referenced in Condition was not correctly converted",
                compareFieldConditions(apiFieldConditionsReferencedInCondition, sdk.getReferencedInCondition()));
        assertTrue("Field Conditions referenced in Condition was not correctly converted",
                compareFieldConditions(apiFieldConditionsReferencedInAction, sdk.getReferencedInAction()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        List<FieldCondition> sdkFieldConditionsReferencedInCondition = buildSdkFieldConditions(CONDITION_1_ID, FIELD_1_ID, FIELD_2_ID);
        List<FieldCondition> sdkFieldConditionsReferencedInAction = new ArrayList<FieldCondition>();

        ReferencedFieldConditions sdk = new ReferencedFieldConditions();
        sdk.setReferencedInCondition(sdkFieldConditionsReferencedInCondition);
        sdk.setReferencedInAction(sdkFieldConditionsReferencedInAction);

        com.silanis.esl.api.model.ReferencedFieldConditions api = toAPI(sdk);

        assertTrue("Field Conditions referenced in Condition was not correctly converted",
                compareFieldConditions(api.getReferencedInCondition(), sdkFieldConditionsReferencedInCondition));
        assertTrue("Field Conditions referenced in Condition was not correctly converted",
                compareFieldConditions(api.getReferencedInAction(), sdkFieldConditionsReferencedInAction));
    }
}
