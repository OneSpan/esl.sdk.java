package com.silanis.esl.sdk.internal.converter;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class FieldConditionConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.FieldCondition sdkFieldCondition1 = null;
    private com.silanis.esl.sdk.FieldCondition sdkFieldCondition2 = null;
    private com.silanis.esl.api.model.FieldCondition apiFieldCondition1 = null;
    private com.silanis.esl.api.model.FieldCondition apiFieldCondition2 = null;
    private FieldConditionConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkFieldCondition1 = null;
        converter = new FieldConditionConverter(sdkFieldCondition1);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPIFieldCondition(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiFieldCondition1 = null;
        converter = new FieldConditionConverter(apiFieldCondition1);
        assertThat( "Converter didn't return a null sdk object for a null api object", converter.toSDKFieldCondition(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkFieldCondition1 = null;
        converter = new FieldConditionConverter(sdkFieldCondition1);
        assertThat( "Converter didn't return a null sdk object for a null sdk object", converter.toSDKFieldCondition(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiFieldCondition1 = null;
        converter = new FieldConditionConverter(apiFieldCondition1);
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPIFieldCondition(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkFieldCondition1 = createTypicalSDKFieldCondition();
        sdkFieldCondition2 = new FieldConditionConverter(sdkFieldCondition1).toSDKFieldCondition();
        assertThat( "Converter returned a null sdk object for a non null sdk object", sdkFieldCondition2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", sdkFieldCondition2, is( equalTo(sdkFieldCondition1) ) );
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiFieldCondition1 = createTypicalAPIFieldCondition();
        apiFieldCondition2 = new FieldConditionConverter(apiFieldCondition1).toAPIFieldCondition();

        assertThat( "Converter returned a null api object for a non null api object", apiFieldCondition2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", apiFieldCondition2, is( equalTo(apiFieldCondition1) ) );
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiFieldCondition1 = createTypicalAPIFieldCondition();
        sdkFieldCondition1 = new FieldConditionConverter(apiFieldCondition1).toSDKFieldCondition();

        assertThat("Converter returned a null api object for a non null sdk object", apiFieldCondition1, is( notNullValue() ) );
        assertThat("Id was not correctly set", apiFieldCondition1.getId(), is( equalTo(sdkFieldCondition1.getId()) ) );
        assertThat("Condition was not correctly set", apiFieldCondition1.getCondition(), is(equalTo(sdkFieldCondition1.getCondition())));
        assertThat("Action not correctly set", apiFieldCondition1.getAction(), is(equalTo(sdkFieldCondition1.getAction())));
   }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkFieldCondition1 = createTypicalSDKFieldCondition();
        apiFieldCondition1 = new FieldConditionConverter(sdkFieldCondition1).toAPIFieldCondition();

        assertThat("Converter returned a null api object for a non null sdk object", apiFieldCondition1, is( notNullValue() ) );
        assertThat("Id coordinate was not correctly set", apiFieldCondition1.getId(), is( equalTo(sdkFieldCondition1.getId()) ) );
        assertThat("Condition coordinate was not correctly set", apiFieldCondition1.getCondition(), is(equalTo(sdkFieldCondition1.getCondition())));
        assertThat("Action was not correctly set", apiFieldCondition1.getAction(), is(equalTo(sdkFieldCondition1.getAction())));
  }

    /**
     * Create an SDK Field.
     *
     * @return SDK Field.
     */
    private com.silanis.esl.sdk.FieldCondition createTypicalSDKFieldCondition() {
        com.silanis.esl.sdk.FieldCondition sdkFieldCondition = new com.silanis.esl.sdk.FieldCondition();

        sdkFieldCondition.setId("ConditionId");
        sdkFieldCondition.setCondition("document['DocumentId'].field['fieldId2'].value == 'X'");
        sdkFieldCondition.setAction("document['DocumentId'].field['fieldId1'].enabled = false");

        return sdkFieldCondition;
    }

    /**
     * Create an API Field.
     *
     * @return API Field.
     */
    private com.silanis.esl.api.model.FieldCondition createTypicalAPIFieldCondition() {
        com.silanis.esl.api.model.FieldCondition apiFieldCondition = new com.silanis.esl.api.model.FieldCondition();

        apiFieldCondition.setId("ConditionId");
        apiFieldCondition.setCondition("document['DocumentId'].field['fieldId2'].value == 'X'");
        apiFieldCondition.setAction("document['DocumentId'].field['fieldId1'].enabled = false");

        return apiFieldCondition;
    }
}
