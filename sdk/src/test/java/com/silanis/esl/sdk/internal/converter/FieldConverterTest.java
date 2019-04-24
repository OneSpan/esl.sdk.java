package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.FieldStyle;
import com.silanis.esl.sdk.TextAnchorPosition;
import com.silanis.esl.sdk.builder.FieldValidatorBuilder;
import com.silanis.esl.sdk.builder.TextAnchorBuilder;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.FieldBuilder.newField;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Test FieldConverter.
 */
public class FieldConverterTest implements ConverterTest{

    private com.silanis.esl.sdk.Field sdkField1 = null;
    private com.silanis.esl.sdk.Field sdkField2 = null;
    private com.silanis.esl.api.model.Field apiField1 = null;
    private com.silanis.esl.api.model.Field apiField2 = null;
    private FieldConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkField1 = null;
        converter = new FieldConverter(sdkField1);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPIField(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiField1 = null;
        converter = new FieldConverter(apiField1);
        assertThat( "Converter didn't return a null sdk object for a null api object", converter.toSDKField(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkField1 = null;
        converter = new FieldConverter(sdkField1);
        assertThat( "Converter didn't return a null sdk object for a null sdk object", converter.toSDKField(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiField1 = null;
        converter = new FieldConverter(apiField1);
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPIField(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkField1 = createTypicalSDKField();
        sdkField2 = new FieldConverter(sdkField1).toSDKField();
        assertThat( "Converter returned a null sdk object for a non null sdk object", sdkField2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", sdkField2, is( equalTo( sdkField1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiField1 = createTypicalAPIField();
        apiField2 = new FieldConverter(apiField1).toAPIField();

        assertThat( "Converter returned a null api object for a non null api object", apiField2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", apiField2, is( equalTo( apiField1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiField1 = createTypicalAPIField();
        sdkField1 = new FieldConverter(apiField1).toSDKField();

        assertThat("Converter returned a null api object for a non null sdk object", apiField1, is( notNullValue() ) );
        assertThat("Left coordinate was not correctly set", apiField1.getLeft(), is( equalTo(sdkField1.getX()) ) );
        assertThat("Top coordinate was not correctly set", apiField1.getTop(), is(sdkField1.getY()));
        assertThat("Width was not correctly set", apiField1.getWidth(), is(sdkField1.getWidth()));
        assertThat("Height was not correctly set",apiField1.getHeight(), is(sdkField1.getHeight()));
        assertThat("Page was not correctly set",apiField1.getPage(), is(sdkField1.getPage()));
        assertThat("Extract was not correctly set",apiField1.getExtract(), is(sdkField1.isExtraction()));
        assertThat("Value was not correctly set",apiField1.getValue(), is(sdkField1.getValue()));
        assertThat("ID was not correctly set", apiField1.getId(), is(sdkField1.getId().toString()));
        assertThat("Name was not correctly set", apiField1.getName(), is(sdkField1.getName()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkField1 = createTypicalSDKField();
        apiField1 = new FieldConverter(sdkField1).toAPIField();

        assertThat("Converter returned a null api object for a non null sdk object", apiField1, is( notNullValue() ) );
        assertThat("Left coordinate was not correctly set", apiField1.getLeft(), is( equalTo(sdkField1.getX()) ) );
        assertThat("Top coordinate was not correctly set", apiField1.getTop(), is(sdkField1.getY()));
        assertThat("Width was not correctly set", apiField1.getWidth(), is(sdkField1.getWidth()));
        assertThat("Height was not correctly set",apiField1.getHeight(), is(sdkField1.getHeight()));
        assertThat("Page was not correctly set",apiField1.getPage(), is(sdkField1.getPage()));
        assertThat("Extract was not correctly set",apiField1.getExtract(), is(sdkField1.isExtraction()));
        assertThat("Value was not correctly set",apiField1.getValue(), is(sdkField1.getValue()));
        assertThat("ID was not correctly set", apiField1.getId(), is(sdkField1.getId().toString()));
        assertThat("Name was not correctly set", apiField1.getName(), is(sdkField1.getName()));
    }

    /**
     * Create an SDK Field.
     *
     * @return SDK Field.
     */
    private com.silanis.esl.sdk.Field createTypicalSDKField() {
        double x = 1;
        double y = 2;
        int page = 3;
        double width = 4;
        double height = 5;

        com.silanis.esl.sdk.Field sdkField = newField()
                .withId(new FieldId("99"))
                .atPosition(x, y)
                .onPage(page)
                .withSize(width, height)
                .withStyle(FieldStyle.BOUND_DATE)
                .withName("Field name")
                .withPositionAnchor(TextAnchorBuilder.newTextAnchor("Anchor Text")
                        .atPosition(TextAnchorPosition.BOTTOMLEFT)
                        .withCharacter(65)
                        .withOccurence(2)
                        .withOffset(101, 102) //xOffset, yOffset
                        .withSize(201, 202)   // width, height
                        .build())
                .withValidation(FieldValidatorBuilder.alphabetic()
                        .maxLength(15)
                        .minLength(5)
                        .required()
                        .withErrorMessage("Error message for validation.")
                        .build())
                .withValue("value")
                .build();
        return sdkField;
    }

    /**
     * Create an API Field.
     *
     * @return API Field.
     */
    private com.silanis.esl.api.model.Field createTypicalAPIField() {
        com.silanis.esl.api.model.Field apiField = new com.silanis.esl.api.model.Field();

        apiField.setExtract(false);
        apiField.setHeight(100.0);
        apiField.setLeft(10.0);
        apiField.setId("3");
        apiField.setName("Field name");
        apiField.setPage(1);
        apiField.setSubtype("TEXTFIELD");
        apiField.setTop(101.0);
        apiField.setType("INPUT");
        apiField.setValue("field value");
        apiField.setWidth(102.0);

        return apiField;
    }
}