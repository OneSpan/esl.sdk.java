package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.*;
import static com.silanis.esl.sdk.examples.FieldValidatorsExample.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * User: jessica
 * Date: 21/01/14
 * Time: 9:37 AM
 * <p/>
 * Test FieldValidatorsExample.
 */
public class FieldValidatorsExampleTest {
    @Test
    public void verifyResult() {
        FieldValidatorsExample example = new FieldValidatorsExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        Document document = documentPackage.getDocument(DOCUMENT_NAME);

        for (Signature signature : document.getSignatures()) {
            if (!signature.getSignerEmail().equals(example.email1)) {
                break;
            }
            for (Field field : signature.getFields()) {
                FieldId fieldId = field.getId();
                if (fieldId.getId().equals(FIELD_ALPHABETIC_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(ALPHABETIC_REGEX));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(ALPHABETIC_ERROR_MESSAGE));
                    assertThat(field.getFieldValidator().getMinLength(), is(FIELD_ALPHABETIC_MIN_LENGTH));
                    assertThat(field.getFieldValidator().getMaxLength(), is(FIELD_ALPHABETIC_MAX_LENGTH));
                }
                if (fieldId.getId().equals(FIELD_ALPHANUMERIC_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(ALPHANUMERIC_REGEX));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(ALPHANUMERIC_ERROR_MESSAGE));
                    assertThat(field.getFieldValidator().getMinLength(), is(FIELD_ALPHANUMERIC_MIN_LENGTH));
                }
                if (fieldId.getId().equals(FIELD_BASIC_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(DEFAULT_REGEX));
                    assertTrue(field.getFieldValidator().getOptions().contains(FIELD_BASIC_OPTION_1));
                    assertTrue(field.getFieldValidator().getOptions().contains(FIELD_BASIC_OPTION_2));
                    assertTrue(field.getFieldValidator().isDisabled());
                }
                if (fieldId.getId().equals(FIELD_EMAIL_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(EMAIL_REGEX));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(EMAIL_ERROR_MESSAGE));
                }
                if (fieldId.getId().equals(FIELD_NUMERIC_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(NUMERIC_REGEX));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(NUMERIC_ERROR_MESSAGE));
                    assertThat(field.getFieldValidator().getMaxLength(), is(FIELD_NUMERIC_MAX_LENGTH));
                }
                if (fieldId.getId().equals(FIELD_REGEX_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(EMAIL_REGEX));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(FIELD_REGEX_ERROR_MESSAGE));
                }
                if (fieldId.getId().equals(FIELD_URL_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(URL_REGEX));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(FIELD_URL_ERROR_MESSAGE));
                }
                if (fieldId.getId().equals(FIELD_CHECKBOX_ID_1.getId())) {
                    assertThat(field.getFieldValidator().getGroup(), is(FIELD_GROUP));
                    assertThat(field.getFieldValidator().getGroupTooltip(), is(FIELD_GROUP_TOOLTIP));
                }
                if (fieldId.getId().equals(FIELD_CHECKBOX_ID_2.getId())) {
                    assertThat(field.getFieldValidator().getGroup(), is(FIELD_GROUP));
                    assertThat(field.getFieldValidator().getGroupTooltip(), is(FIELD_GROUP_TOOLTIP));
                }
                if (fieldId.getId().equals(FIELD_RADIO_ID_1.getId())) {
                    assertThat(field.getFieldValidator().getGroup(), is(FIELD_RADIO_GROUP));
                    assertThat(field.getFieldValidator().getGroupTooltip(), is(FIELD_RADIO_GROUP_TOOLTIP));
                }
                if (fieldId.getId().equals(FIELD_RADIO_ID_2.getId())) {
                    assertThat(field.getFieldValidator().getGroup(), is(FIELD_RADIO_GROUP));
                    assertThat(field.getFieldValidator().getGroupTooltip(), is(FIELD_RADIO_GROUP_TOOLTIP));
                }
            }
        }
    }
}
