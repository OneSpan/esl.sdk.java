package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.CustomFieldValueBuilder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * User: jessica
 * Date: 26/11/13
 * Time: 3:16 PM
 *
 * Test CustomFieldValueConverter.
 *
 */
public class CustomFieldValueConverterTest implements ConverterTest {

    private com.silanis.esl.api.model.UserCustomField apiUserCustomField1 = null;
    private com.silanis.esl.api.model.UserCustomField apiUserCustomField2 = null;
    private com.silanis.esl.sdk.CustomFieldValue sdkCustomFieldValue1 = null;
    private com.silanis.esl.sdk.CustomFieldValue sdkCustomFieldValue2 = null;
    private CustomFieldValueConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkCustomFieldValue1 = null;
        converter = new CustomFieldValueConverter(sdkCustomFieldValue1);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPIUserCustomField(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiUserCustomField1 = null;
        converter = new CustomFieldValueConverter(apiUserCustomField1);
        assertThat( "Converter didn't return a null sdk object for a null api object", converter.toSDKCustomFieldValue(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkCustomFieldValue1 = null;
        converter = new CustomFieldValueConverter(sdkCustomFieldValue1);
        assertThat( "Converter didn't return a null sdk object for a null sdk object", converter.toSDKCustomFieldValue(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiUserCustomField1 = null;
        converter = new CustomFieldValueConverter(apiUserCustomField1);
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPIUserCustomField(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkCustomFieldValue1 = createTypicalSDKCustomFieldValue();
        sdkCustomFieldValue2 = new CustomFieldValueConverter(sdkCustomFieldValue1).toSDKCustomFieldValue();
        assertThat( "Converter returned a null sdk object for a non null sdk object", sdkCustomFieldValue2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", sdkCustomFieldValue2, is( equalTo( sdkCustomFieldValue1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiUserCustomField1 = createTypicalAPICustomFieldValue();
        apiUserCustomField2 = new CustomFieldValueConverter(apiUserCustomField1).toAPIUserCustomField();

        assertThat( "Converter returned a null api object for a non null api object", apiUserCustomField2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", apiUserCustomField2, is( equalTo( apiUserCustomField1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiUserCustomField1 = createTypicalAPICustomFieldValue();
        sdkCustomFieldValue1 = new CustomFieldValueConverter(apiUserCustomField1).toSDKCustomFieldValue();

        assertThat("Converter returned a null api object for a non null sdk object", apiUserCustomField1, is( notNullValue() ) );
        assertThat("ID was not correctly set", apiUserCustomField1.getId(), is( equalTo(sdkCustomFieldValue1.getId() ) ));
        assertThat("Value was not correctly set", apiUserCustomField1.getValue(), is( equalTo(sdkCustomFieldValue1.getValue() ) ));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkCustomFieldValue1 = createTypicalSDKCustomFieldValue();
        apiUserCustomField1 = new CustomFieldValueConverter(sdkCustomFieldValue1).toAPIUserCustomField();

        assertThat("Converter returned a null api object for a non null sdk object", apiUserCustomField1, is( notNullValue() ) );
        assertThat("ID was not correctly set", apiUserCustomField1.getId(), is( equalTo(sdkCustomFieldValue1.getId() ) ));
        assertThat("Value was not correctly set", apiUserCustomField1.getValue(), is( equalTo(sdkCustomFieldValue1.getValue() ) ));
    }

    /**
     * Create an SDK CustomFieldValue.
     *
     * @return SDK CustomFieldValue.
     */
    private com.silanis.esl.sdk.CustomFieldValue createTypicalSDKCustomFieldValue() {
        com.silanis.esl.sdk.CustomFieldValue sdkCustomFieldValue = new CustomFieldValueBuilder()
                                                                      .withValue("Custom field value")
                                                                      .withId("1")
                                                                      .build();
        return sdkCustomFieldValue;
    }

    /**
     * Create an API CustomFieldValue.
     *
     * @return API CustomFieldValue.
     */
    private com.silanis.esl.api.model.UserCustomField createTypicalAPICustomFieldValue() {
        com.silanis.esl.api.model.UserCustomField apiCustomFieldValue = new com.silanis.esl.api.model.UserCustomField();
        apiCustomFieldValue.setValue("API custom field value");
        apiCustomFieldValue.setId("1");

        return apiCustomFieldValue;
    }
    
}
