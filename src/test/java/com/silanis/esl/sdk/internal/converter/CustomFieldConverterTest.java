package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.CustomFieldBuilder;
import com.silanis.esl.sdk.builder.TranslationBuilder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * User: jessica
 * Date: 26/11/13
 * Time: 1:32 PM
 * <p/>
 * Test CustomFieldConverter.
 */
public class CustomFieldConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.CustomField sdkCustomField1 = null;
    private com.silanis.esl.sdk.CustomField sdkCustomField2 = null;
    private com.silanis.esl.api.model.CustomField apiCustomField1 = null;
    private com.silanis.esl.api.model.CustomField apiCustomField2 = null;
    private CustomFieldConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkCustomField1 = null;
        converter = new CustomFieldConverter(sdkCustomField1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPICustomField(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiCustomField1 = null;
        converter = new CustomFieldConverter(apiCustomField1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKCustomField(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkCustomField1 = null;
        converter = new CustomFieldConverter(sdkCustomField1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKCustomField(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiCustomField1 = null;
        converter = new CustomFieldConverter(apiCustomField1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPICustomField(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkCustomField1 = createTypicalSDKCustomField();
        sdkCustomField2 = new CustomFieldConverter(sdkCustomField1).toSDKCustomField();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkCustomField2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkCustomField2, is(equalTo(sdkCustomField1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiCustomField1 = createTypicalAPICustomField();
        apiCustomField2 = new CustomFieldConverter(apiCustomField1).toAPICustomField();

        assertThat("Converter returned a null api object for a non null api object", apiCustomField2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiCustomField2, is(equalTo(apiCustomField1)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiCustomField1 = createTypicalAPICustomField();
        sdkCustomField1 = new CustomFieldConverter(apiCustomField1).toSDKCustomField();

        assertThat("Converter returned a null api object for a non null sdk object", apiCustomField1, is(notNullValue()));
        assertThat("ID was not correctly set", apiCustomField1.getId(), is(equalTo(sdkCustomField1.getId())));
        assertThat("Value was not correctly set", apiCustomField1.getValue(), is(equalTo(sdkCustomField1.getValue())));
        assertThat("Required was not correctly set", apiCustomField1.getRequired(), is(equalTo(sdkCustomField1.getRequired())));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkCustomField1 = createTypicalSDKCustomField();
        apiCustomField1 = new CustomFieldConverter(sdkCustomField1).toAPICustomField();

        assertThat("Converter returned a null api object for a non null sdk object", apiCustomField1, is(notNullValue()));
        assertThat("Converter returned a null api object for a non null sdk object", apiCustomField1, is(notNullValue()));
        assertThat("ID was not correctly set", apiCustomField1.getId(), is(equalTo(sdkCustomField1.getId())));
        assertThat("Value was not correctly set", apiCustomField1.getValue(), is(equalTo(sdkCustomField1.getValue())));
        assertThat("Required was not correctly set", apiCustomField1.getRequired(), is(equalTo(sdkCustomField1.getRequired())));
    }

    /**
     * Create an SDK Custom Field.
     *
     * @return SDK Custom Field.
     */
    private com.silanis.esl.sdk.CustomField createTypicalSDKCustomField() {
        com.silanis.esl.sdk.CustomField sdkCustomField = new CustomFieldBuilder()
                                                        .withId("1")
                                                        .withDefaultValue("Default Value.")
                                                        .withTranslation(TranslationBuilder.newTranslation("en").withName("Translation Name")
                                                                .withDescription("Translation Description")
                                                                .build())
                                                        .isRequired(true)
                                                        .build();
        return sdkCustomField;
    }

    /**
     * Create an API Custom Field.
     *
     * @return API Custom Field.
     */
    private com.silanis.esl.api.model.CustomField createTypicalAPICustomField() {
        com.silanis.esl.api.model.CustomField apiCustomField = new com.silanis.esl.api.model.CustomField();
        apiCustomField.setId("1");
        apiCustomField.setValue("API custom field value");
        apiCustomField.setRequired(true);

        return apiCustomField;
    }

}
