package com.silanis.esl.sdk.internal.converter.sdk;

import com.silanis.esl.api.model.FieldValidation;
import com.silanis.esl.sdk.builder.FieldValidatorBuilder;
import com.silanis.esl.sdk.internal.converter.FieldValidatorConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: jessica
 * Date: 19/11/13
 * Time: 4:17 PM
 *
 * Test FieldValidatorConverter.
 */
public class FieldValidatorConverterTest {

    public static final double DEFAULT_DOUBLE_TOLERANCE = 0.01f;

    @Test
    public void toAPIFieldValidation() {
        com.silanis.esl.sdk.FieldValidator sdkFieldValidator  = FieldValidatorBuilder.alphabetic()
                .maxLength(15)
                .minLength(5)
                .required()
                .withErrorMessage("Error message for validation.")
                .build();

        com.silanis.esl.api.model.FieldValidation apiFieldValidation = new FieldValidatorConverter(sdkFieldValidator).toAPIFieldValidation();

        assertEquals( apiFieldValidation.getErrorMessage(), sdkFieldValidator.getErrorMessage() );
        assertEquals( apiFieldValidation.getMaxLength(), sdkFieldValidator.getMaxLength());
        assertEquals( apiFieldValidation.getMinLength(), sdkFieldValidator.getMinLength());
        assertEquals( apiFieldValidation.getRequired(), sdkFieldValidator.isRequired());

    }

    @Test
    public void toSDKFieldValidator() {
        com.silanis.esl.api.model.FieldValidation apiFieldValidation = new FieldValidation();
        apiFieldValidation.setErrorCode(100);
        apiFieldValidation.setErrorMessage("Error message.");
        apiFieldValidation.setMaxLength(30);
        apiFieldValidation.setMinLength(10);
        apiFieldValidation.setPattern("*pattern*");
        apiFieldValidation.setRequired(true);

        com.silanis.esl.sdk.FieldValidator sdkFieldValidator = new FieldValidatorConverter(apiFieldValidation).toSDKFieldValidator();

        assertEquals( apiFieldValidation.getErrorCode(), 100, DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( apiFieldValidation.getErrorMessage(), sdkFieldValidator.getErrorMessage() );
        assertEquals( apiFieldValidation.getMaxLength(), sdkFieldValidator.getMaxLength());
        assertEquals( apiFieldValidation.getMinLength(), sdkFieldValidator.getMinLength());
        assertEquals( apiFieldValidation.getRequired(), sdkFieldValidator.isRequired());
        assertEquals( apiFieldValidation.getPattern(), sdkFieldValidator.getRegex());
    }

}
