package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.*;
import static com.silanis.esl.sdk.examples.FieldValidatorsExample.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
        FieldValidatorsExample example = new FieldValidatorsExample(Props.get());
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
                    assertThat(field.getFieldValidator().getRegex(), is(equalTo(ALPHABETIC_REGEX)));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(equalTo(ALPHABETIC_ERROR_MESSAGE)));
                    assertThat(field.getFieldValidator().getMinLength(), is(equalTo(FIELD_ALPHABETIC_MIN_LENGTH)));
                    assertThat(field.getFieldValidator().getMaxLength(), is(equalTo(FIELD_ALPHABETIC_MAX_LENGTH)));
                }
                if (fieldId.getId().equals(FIELD_ALPHANUMERIC_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(equalTo(ALPHANUMERIC_REGEX)));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(equalTo(ALPHANUMERIC_ERROR_MESSAGE)));
                    assertThat(field.getFieldValidator().getMinLength(), is(equalTo(FIELD_ALPHANUMERIC_MIN_LENGTH)));
                }
                if (fieldId.getId().equals(FIELD_BASIC_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(equalTo(DEFAULT_REGEX)));
                    assertThat(field.getFieldValidator().getOptions().contains(FIELD_BASIC_OPTION_1), is(equalTo(true)));
                    assertThat(field.getFieldValidator().getOptions().contains(FIELD_BASIC_OPTION_2), is(equalTo(true)));
                }
                if (fieldId.getId().equals(FIELD_EMAIL_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(equalTo(EMAIL_REGEX)));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(equalTo(EMAIL_ERROR_MESSAGE)));
                }
                if (fieldId.getId().equals(FIELD_NUMERIC_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(equalTo(NUMERIC_REGEX)));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(equalTo(NUMERIC_ERROR_MESSAGE)));
                    assertThat(field.getFieldValidator().getMaxLength(), is(equalTo(FIELD_NUMERIC_MAX_LENGTH)));
                }
                if (fieldId.getId().equals(FIELD_REGEX_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(equalTo(EMAIL_REGEX)));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(equalTo(FIELD_REGEX_ERROR_MESSAGE)));
                }
                if (fieldId.getId().equals(FIELD_URL_ID)) {
                    assertThat(field.getFieldValidator().getRegex(), is(equalTo(URL_REGEX)));
                    assertThat(field.getFieldValidator().getErrorMessage(), is(equalTo(FIELD_URL_ERROR_MESSAGE)));
                }
            }
        }
    }
}
