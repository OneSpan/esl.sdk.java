package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.ReferencedField;
import com.silanis.esl.sdk.ReferencedFieldConditions;
import org.junit.Test;

import static com.silanis.esl.sdk.internal.converter.ReferencedFieldConverter.toAPI;
import static com.silanis.esl.sdk.internal.converter.ReferencedFieldConverter.toSDK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertTrue;

public class ReferencedFieldConverterTest extends ReferencedConditionsConverterTest {

    @Override
    @Test
    public void convertNullSDKToAPI() {
        com.silanis.esl.api.model.ReferencedField api = toAPI(null);
        assertThat("Converter didn't return a null api object for a null sdk object", api, nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        ReferencedField sdk = toSDK(null);
        assertThat("Converter didn't return a null sdk object for a null api object", sdk, nullValue());
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        com.silanis.esl.api.model.ReferencedFieldConditions apiConditions = buildApiReferencedFieldConditions();

        com.silanis.esl.api.model.ReferencedField api = new com.silanis.esl.api.model.ReferencedField();
        api.setFieldId(FIELD_1_ID);
        api.setConditions(apiConditions);

        ReferencedField sdk = toSDK(api);

        assertThat("Field ID was not correctly converted", sdk.getFieldId(), is(FIELD_1_ID));
        assertTrue("Referenced Field Conditions was not correctly converted", compareReferencedFieldConditions(apiConditions, sdk.getConditions()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        ReferencedFieldConditions sdkConditions = buildSdkReferencedFieldConditions();

        ReferencedField sdk = new ReferencedField();
        sdk.setFieldId(FIELD_1_ID);
        sdk.setConditions(sdkConditions);

        com.silanis.esl.api.model.ReferencedField api = toAPI(sdk);

        assertThat("Field ID was not correctly converted", api.getFieldId(), is(FIELD_1_ID));
        assertTrue("Referenced Field Conditions was not correctly converted", compareReferencedFieldConditions(api.getConditions(), sdkConditions));
    }
}
