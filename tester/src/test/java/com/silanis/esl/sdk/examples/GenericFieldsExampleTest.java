package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import static com.silanis.esl.sdk.FieldStyle.DATEPICKER;
import static com.silanis.esl.sdk.FieldStyle.DROP_LIST;
import static com.silanis.esl.sdk.FieldStyle.LABEL;
import static com.silanis.esl.sdk.FieldStyle.TEXT_AREA;
import static com.silanis.esl.sdk.FieldStyle.UNBOUND_CHECK_BOX;
import static com.silanis.esl.sdk.FieldStyle.UNBOUND_RADIO_BUTTON;
import static com.silanis.esl.sdk.FieldStyle.UNBOUND_TEXT_FIELD;
import static com.silanis.esl.sdk.builder.FieldBuilder.CHECKBOX_CHECKED;
import static com.silanis.esl.sdk.builder.FieldBuilder.RADIO_SELECTED;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.DATEPICKER_ERROR_MESSAGE;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.CHECKBOX_1_ID;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.CHECKBOX_1_PAGE;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.CHECKBOX_2_ID;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.DATEPICKER_FORMAT;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.DATEPICKER_ID;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.DATEPICKER_PAGE;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.DATEPICKER_VALUE;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.DOCUMENT_NAME;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.DROP_LIST_ID;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.DROP_LIST_OPTION1;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.DROP_LIST_OPTION2;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.DROP_LIST_OPTION3;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.DROP_LIST_PAGE;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.LABEL_ID;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.LABEL_PAGE;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.LABEL_VALUE;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.RADIO_1_GROUP;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.RADIO_1_ID;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.RADIO_1_PAGE;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.RADIO_2_GROUP;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.RADIO_2_ID;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.RADIO_2_PAGE;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.TEXTFIELD_ID;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.TEXTFIELD_PAGE;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.TEXT_AREA_ID;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.TEXT_AREA_PAGE;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.TEXT_AREA_VALUE;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 21/01/14
 * Time: 3:26 PM
 * <p/>
 *
 * Test 
 */
public class GenericFieldsExampleTest {
    @Test
    public void verifyResult() {
        GenericFieldsExample example = new GenericFieldsExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        for (Signature signature : documentPackage.getDocument(DOCUMENT_NAME).getSignatures()) {
            for (Field field : signature.getFields()) {
                if(field.getId().toString().equals(TEXTFIELD_ID)){
                    assertThat(field.getPage(), is(TEXTFIELD_PAGE));
                    assertThat(field.getStyle(), is(UNBOUND_TEXT_FIELD));
                }
                if (field.getId().toString().equals(CHECKBOX_1_ID)) {
                    assertThat(field.getPage(), is(CHECKBOX_1_PAGE));
                    assertThat(field.getStyle(), is(UNBOUND_CHECK_BOX));
                    assertThat(field.getValue(), nullValue());
                }
                if (field.getId().toString().equals(CHECKBOX_2_ID)) {
                    assertThat(field.getPage(), is(CHECKBOX_1_PAGE));
                    assertThat(field.getStyle(), is(UNBOUND_CHECK_BOX));
                    assertThat(field.getValue(), is(CHECKBOX_CHECKED));
                }
                if (field.getId().toString().equals(RADIO_1_ID)) {
                    assertThat(field.getPage(), is(RADIO_1_PAGE));
                    assertThat(field.getStyle(), is(UNBOUND_RADIO_BUTTON));
                    assertThat(field.getFieldValidator().getOptions().get(0), is(RADIO_1_GROUP));
                    assertThat(field.getValue(), nullValue());
                }
                if (field.getId().toString().equals(RADIO_2_ID)) {
                    assertThat(field.getPage(), is(RADIO_2_PAGE));
                    assertThat(field.getStyle(), is(UNBOUND_RADIO_BUTTON));
                    assertThat(field.getFieldValidator().getOptions().get(0), is(RADIO_2_GROUP));
                    assertThat(field.getValue(), is(RADIO_SELECTED));
                }
                if (field.getId().toString().equals(DROP_LIST_ID)) {
                    assertThat(field.getPage(), is(DROP_LIST_PAGE));
                    assertThat(field.getStyle(), is(DROP_LIST));
                    assertThat(field.getFieldValidator().getOptions().get(0), is(DROP_LIST_OPTION1));
                    assertThat(field.getFieldValidator().getOptions().get(1), is(DROP_LIST_OPTION2));
                    assertThat(field.getFieldValidator().getOptions().get(2), is(DROP_LIST_OPTION3));
                    assertThat(field.getValue(), is(DROP_LIST_OPTION2));
                }
                if (field.getId().toString().equals(TEXT_AREA_ID)) {
                    assertThat(field.getPage(), is(TEXT_AREA_PAGE));
                    assertThat(field.getStyle(), is(TEXT_AREA));
                    assertThat(field.getValue(), is(TEXT_AREA_VALUE));
                }
                if (field.getId().toString().equals(LABEL_ID)) {
                    assertThat(field.getPage(), is(LABEL_PAGE));
                    assertThat(field.getStyle(), is(LABEL));
                    assertThat(field.getValue(), is(LABEL_VALUE));
                }
                if (field.getId().toString().equals(DATEPICKER_ID)) {
                    assertThat(field.getPage(), is(DATEPICKER_PAGE));
                    assertThat(field.getStyle(), is(DATEPICKER));
                    assertThat(field.getValue(), is(DATEPICKER_VALUE));
                    assertTrue(field.getFieldValidator().isRequired());
                    assertThat(field.getFieldValidator().getRegex(), is(DATEPICKER_FORMAT));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(DATEPICKER_ERROR_MESSAGE));
                }
            }
        }
    }
}
