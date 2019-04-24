package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.FieldCondition;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.FieldStyle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ConditionalFieldConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.ConditionalField sdkField1 = null;
    private com.silanis.esl.sdk.ConditionalField sdkField2 = null;
    private com.silanis.esl.api.model.ConditionalField apiField1 = null;
    private com.silanis.esl.api.model.ConditionalField apiField2 = null;
    private ConditionalFieldConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkField1 = null;
        converter = new ConditionalFieldConverter(sdkField1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIConditionalField(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiField1 = null;
        converter = new ConditionalFieldConverter(apiField1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKConditionalField(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkField1 = null;
        converter = new ConditionalFieldConverter(sdkField1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKConditionalField(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiField1 = null;
        converter = new ConditionalFieldConverter(apiField1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIConditionalField(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkField1 = createTypicalSDKConditionalField();
        sdkField2 = new ConditionalFieldConverter(sdkField1).toSDKConditionalField();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkField2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkField2, is(equalTo(sdkField1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiField1 = createTypicalAPIConditionalField();
        apiField2 = new ConditionalFieldConverter(apiField1).toAPIConditionalField();

        assertThat("Converter returned a null api object for a non null api object", apiField2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiField2, is(equalTo(apiField1)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiField1 = createTypicalAPIConditionalField();
        sdkField1 = new ConditionalFieldConverter(apiField1).toSDKConditionalField();

        assertThat("Converter returned a null api object for a non null sdk object", apiField1, is(notNullValue()));
        assertThat("Left coordinate was not correctly set", apiField1.getLeft(), is(equalTo(sdkField1.getX())));
        assertThat("Top coordinate was not correctly set", apiField1.getTop(), is(sdkField1.getY()));
        assertThat("Width was not correctly set", apiField1.getWidth(), is(sdkField1.getWidth()));
        assertThat("Height was not correctly set", apiField1.getHeight(), is(sdkField1.getHeight()));
        assertThat("Page was not correctly set", apiField1.getPage(), is(sdkField1.getPage()));
        assertThat("Extract was not correctly set", apiField1.getExtract(), is(sdkField1.isExtraction()));
        assertThat("Value was not correctly set", apiField1.getValue(), is(sdkField1.getValue()));
        assertThat("ID was not correctly set", apiField1.getId(), is(sdkField1.getId().toString()));
        assertThat("Name was not correctly set", apiField1.getName(), is(sdkField1.getName()));
        assertThat("Conditions was not correctly set", apiField1.getConditions().size(), is(sdkField1.getConditions().size()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkField1 = createTypicalSDKConditionalField();
        apiField1 = new ConditionalFieldConverter(sdkField1).toAPIConditionalField();

        assertThat("Converter returned a null api object for a non null sdk object", apiField1, is(notNullValue()));
        assertThat("Left coordinate was not correctly set", apiField1.getLeft(), is(equalTo(sdkField1.getX())));
        assertThat("Top coordinate was not correctly set", apiField1.getTop(), is(sdkField1.getY()));
        assertThat("Width was not correctly set", apiField1.getWidth(), is(sdkField1.getWidth()));
        assertThat("Height was not correctly set", apiField1.getHeight(), is(sdkField1.getHeight()));
        assertThat("Page was not correctly set", apiField1.getPage(), is(sdkField1.getPage()));
        assertThat("Extract was not correctly set", apiField1.getExtract(), is(sdkField1.isExtraction()));
        assertThat("Value was not correctly set", apiField1.getValue(), is(sdkField1.getValue()));
        assertThat("ID was not correctly set", apiField1.getId(), is(sdkField1.getId().toString()));
        assertThat("Name was not correctly set", apiField1.getName(), is(sdkField1.getName()));
        assertThat("Conditions was not correctly set", apiField1.getConditions().size(), is(sdkField1.getConditions().size()));
        assertThat("Conditions was not correctly set", apiField1.getConditions().size(), is(sdkField1.getConditions().size()));
    }

    /**
     * Create an SDK Field.
     *
     * @return SDK Field.
     */
    private com.silanis.esl.sdk.ConditionalField createTypicalSDKConditionalField() {
        com.silanis.esl.sdk.ConditionalField sdkField = new com.silanis.esl.sdk.ConditionalField();

        sdkField.setExtraction(false);
        sdkField.setHeight(100.0);
        sdkField.setX(10.0);
        sdkField.setId(new FieldId("99"));
        sdkField.setName("Field name");
        sdkField.setPage(1);
        sdkField.setStyle(FieldStyle.BOUND_DATE);
        sdkField.setY(101.0);
        sdkField.setValue("field value");
        sdkField.setWidth(102.0);
        com.silanis.esl.sdk.FieldCondition condition = new com.silanis.esl.sdk.FieldCondition();
        condition.setId("ConditionId");
        condition.setCondition("document['DocumentId'].field['fieldId2'].value == 'X'");
        condition.setAction("document['DocumentId'].field['fieldId1'].enabled = false");
        List<com.silanis.esl.sdk.FieldCondition> conditions = new ArrayList<com.silanis.esl.sdk.FieldCondition>();
        conditions.add(condition);
        sdkField.setConditions(conditions);

        return sdkField;
    }

    /**
     * Create an API Field.
     *
     * @return API Field.
     */
    private com.silanis.esl.api.model.ConditionalField createTypicalAPIConditionalField() {
        com.silanis.esl.api.model.ConditionalField apiField = new com.silanis.esl.api.model.ConditionalField();

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
        com.silanis.esl.api.model.FieldCondition condition = new com.silanis.esl.api.model.FieldCondition();
        condition.setId("ConditionId");
        condition.setCondition("document['DocumentId'].field['fieldId2'].value == 'X'");
        condition.setAction("document['DocumentId'].field['fieldId1'].enabled = false");
        List<FieldCondition> conditions = new ArrayList<FieldCondition>();
        conditions.add(condition);
        apiField.setConditions(conditions);

        return apiField;
    }
}
