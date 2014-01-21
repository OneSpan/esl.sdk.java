package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.silanis.esl.sdk.builder.FieldValidatorBuilder;

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
        FieldValidatorsExample fieldValidatorsExample = new FieldValidatorsExample(Props.get());
        fieldValidatorsExample.run();

        DocumentPackage documentPackage = fieldValidatorsExample.getEslClient().getPackage(fieldValidatorsExample.getPackageId());

        Document document = documentPackage.getDocument(FieldValidatorsExample.DOCUMENT_NAME);

        for (Signature signature : document.getSignatures()) {
            if (!signature.getSignerEmail().equals(fieldValidatorsExample.email1)) {
                break;
            }
            for (Field field : signature.getFields()) {
                FieldId fieldId = field.getId();
                if (fieldId.getId().equals(FieldValidatorsExample.FIELD_ALPHABETIC_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(equalTo(FieldValidatorBuilder.ALPHABETIC_REGEX)));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(equalTo(FieldValidatorBuilder.ALPHABETIC_ERROR_MESSAGE)));
                    assertThat(field.getFieldValidator().getMinLength(), is(equalTo(FieldValidatorsExample.FIELD_ALPHABETIC_MIN_LENGTH)));
                    assertThat(field.getFieldValidator().getMaxLength(), is(equalTo(FieldValidatorsExample.FIELD_ALPHABETIC_MAX_LENGTH)));
                }
                if (fieldId.getId().equals(FieldValidatorsExample.FIELD_ALPHANUMERIC_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(equalTo(FieldValidatorBuilder.ALPHANUMERIC_REGEX)));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(equalTo(FieldValidatorBuilder.ALPHANUMERIC_ERROR_MESSAGE)));
                    assertThat(field.getFieldValidator().getMinLength(), is(equalTo(FieldValidatorsExample.FIELD_ALPHANUMERIC_MIN_LENGTH)));
                }
                if (fieldId.getId().equals(FieldValidatorsExample.FIELD_BASIC_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(equalTo(FieldValidatorBuilder.DEFAULT_REGEX)));
                    assertThat(field.getFieldValidator().getOptions().contains(FieldValidatorsExample.FIELD_BASIC_OPTION_1), is(equalTo(true)));
                    assertThat(field.getFieldValidator().getOptions().contains(FieldValidatorsExample.FIELD_BASIC_OPTION_2), is(equalTo(true)));
                }
                if (fieldId.getId().equals(FieldValidatorsExample.FIELD_EMAIL_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(equalTo(FieldValidatorBuilder.EMAIL_REGEX)));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(equalTo(FieldValidatorBuilder.EMAIL_ERROR_MESSAGE)));
                }
                if (fieldId.getId().equals(FieldValidatorsExample.FIELD_NUMERIC_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(equalTo(FieldValidatorBuilder.NUMERIC_REGEX)));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(equalTo(FieldValidatorBuilder.NUMERIC_ERROR_MESSAGE)));
                    assertThat(field.getFieldValidator().getMaxLength(), is(equalTo(FieldValidatorsExample.FIELD_NUMERIC_MAX_LENGTH)));
                }
                if (fieldId.getId().equals(FieldValidatorsExample.FIELD_REGEX_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(equalTo(FieldValidatorBuilder.EMAIL_REGEX)));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(equalTo(FieldValidatorsExample.FIELD_REGEX_ERROR_MESSAGE)));
                }
                if (fieldId.getId().equals(FieldValidatorsExample.FIELD_URL_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(equalTo(FieldValidatorBuilder.URL_REGEX)));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(equalTo(FieldValidatorsExample.FIELD_URL_ERROR_MESSAGE)));
                }
            }
        }
    }
}
