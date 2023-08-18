package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import static com.silanis.esl.sdk.FieldStyle.*;
import static com.silanis.esl.sdk.builder.FieldBuilder.CHECKBOX_CHECKED;
import static com.silanis.esl.sdk.builder.FieldBuilder.RADIO_SELECTED;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.DATEPICKER_ERROR_MESSAGE;
import static com.silanis.esl.sdk.examples.GenericFieldsExample.*;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 21/01/14
 * Time: 3:26 PM
 * <p/>
 * <p>
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
                if (field.getId().toString().equals(TEXTFIELD_ID)) {
                    assertThat(field.getPage(), is(TEXTFIELD_PAGE));
                    assertThat(field.getStyle(), is(UNBOUND_TEXT_FIELD));
                    assertThat(field.getFontSize(), is(TEXTFIELD_FONT_SIZE));
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
                if (field.getId().toString().equals(GROUPED_CHECKBOX_1_ID)) {
                    assertThat(field.getPage(), is(GROUPED_CHECKBOX_1_PAGE));
                    assertThat(field.getStyle(), is(UNBOUND_CHECK_BOX));
                    assertThat(field.getFieldValidator().getGroup(), is(CHECKBOX_GROUP));
                    assertThat(field.getFieldValidator().getMinimumRequired(), is(GROUPED_CHECKBOX_MINIMUM_REQUIRED));
                    assertThat(field.getFieldValidator().getMaximumRequired(), is(GROUPED_CHECKBOX_MAXIMUM_REQUIRED));
                    assertThat(field.getFieldValidator().getGroupTooltip(), is(CHECKBOX_GROUP_TOOLTIP));
                }
                if (field.getId().toString().equals(GROUPED_CHECKBOX_2_ID)) {
                    assertThat(field.getPage(), is(GROUPED_CHECKBOX_2_PAGE));
                    assertThat(field.getStyle(), is(UNBOUND_CHECK_BOX));
                    assertThat(field.getFieldValidator().getGroup(), is(CHECKBOX_GROUP));
                    assertThat(field.getFieldValidator().getMinimumRequired(), is(GROUPED_CHECKBOX_MINIMUM_REQUIRED));
                    assertThat(field.getFieldValidator().getMaximumRequired(), is(GROUPED_CHECKBOX_MAXIMUM_REQUIRED));
                    assertThat(field.getFieldValidator().getGroupTooltip(), is(CHECKBOX_GROUP_TOOLTIP));
                }
                if (field.getId().toString().equals(RADIO_1_ID)) {
                    assertThat(field.getPage(), is(RADIO_1_PAGE));
                    assertThat(field.getStyle(), is(UNBOUND_RADIO_BUTTON));
                    assertThat(field.getFieldValidator().getOptions().get(0), is(RADIO_1_GROUP));
                    assertThat(field.getValue(), nullValue());
                    assertThat(field.getFieldValidator().getGroupTooltip(), is(RADIO_FIELD_GROUP_TOOLTIP));
                }
                if (field.getId().toString().equals(RADIO_2_ID)) {
                    assertThat(field.getPage(), is(RADIO_2_PAGE));
                    assertThat(field.getStyle(), is(UNBOUND_RADIO_BUTTON));
                    assertThat(field.getFieldValidator().getOptions().get(0), is(RADIO_2_GROUP));
                    assertThat(field.getValue(), is(RADIO_SELECTED));
                    assertThat(field.getFieldValidator().getGroupTooltip(), is(RADIO_FIELD_GROUP_TOOLTIP));
                }
                if (field.getId().toString().equals(DROP_LIST_ID)) {
                    assertThat(field.getPage(), is(DROP_LIST_PAGE));
                    assertThat(field.getStyle(), is(DROP_LIST));
                    assertThat(field.getFieldValidator().getOptions().get(0), is(DROP_LIST_OPTION1));
                    assertThat(field.getFieldValidator().getOptions().get(1), is(DROP_LIST_OPTION2));
                    assertThat(field.getFieldValidator().getOptions().get(2), is(DROP_LIST_OPTION3));
                    assertThat(field.getValue(), is(DROP_LIST_OPTION2));
                    assertThat(field.getFontSize(), is(DROP_LIST_FONT_SIZE));
                }
                if (field.getId().toString().equals(TEXT_AREA_ID)) {
                    assertThat(field.getPage(), is(TEXT_AREA_PAGE));
                    assertThat(field.getStyle(), is(TEXT_AREA));
                    assertThat(field.getValue(), is(TEXT_AREA_VALUE));
                    assertThat(field.getFontSize(), is(TEXT_AREA_FONT_SIZE));
                    assertTrue(field.getFieldValidator().isDisabled());
                }
                if (field.getId().toString().equals(LABEL_ID)) {
                    assertThat(field.getPage(), is(LABEL_PAGE));
                    assertThat(field.getStyle(), is(LABEL));
                    assertThat(field.getValue(), is(LABEL_VALUE));
                    assertThat(field.getFontSize(), is(LABEL_FIELD_FONT_SIZE));
                }
                if (field.getId().toString().equals(DATEPICKER_ID)) {
                    assertThat(field.getPage(), is(DATEPICKER_PAGE));
                    assertThat(field.getStyle(), is(DATEPICKER));
                    assertThat(field.getValue(), is(DATEPICKER_VALUE));
                    assertTrue(field.getFieldValidator().isRequired());
                    assertThat(field.getFieldValidator().getRegex(), is(DATEPICKER_FORMAT));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(DATEPICKER_ERROR_MESSAGE));
                    assertThat(field.getFontSize(), is(DATEPICKER_FIELD_FONT_SIZE));
                }
                if (field.getId().toString().equals(TEXTFIELD_WITH_TOOLTIP_ID)){
                    assertThat(field.getPage(), is(TEXTFIELD_PAGE));
                    assertThat(field.getStyle(), is(UNBOUND_TEXT_FIELD));
                    assertThat(field.getFontSize(), is(TEXTFIELD_FONT_SIZE));
                    assertThat(field.getTooltip(), is(TEXT_FIELD_TOOLTIP));
                }
                if (field.getId().toString().equals(RADIO_3_ID)) {
                    assertThat(field.getPage(), is(RADIO_3_PAGE));
                    assertThat(field.getStyle(), is(UNBOUND_RADIO_BUTTON));
                    assertThat(field.getFieldValidator().getOptions().get(0), is(RADIO_3_GROUP));
                    assertThat(field.getValue(), is(RADIO_SELECTED));
                    assertThat(field.getTooltip(), is(RADIO_FIELD_TOOLTIP));
                }

                }
            }
        }
    }

