package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.FieldValidatorBuilder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * User: jessica
 * Date: 19/11/13
 * Time: 4:17 PM
 *
 * Test FieldValidatorConverter.
 */
public class FieldValidatorConverterTest implements ConverterTest {

    private com.silanis.esl.api.model.FieldValidation apiFieldValidation1 = null;
    private com.silanis.esl.api.model.FieldValidation apiFieldValidation2 = null;
    private com.silanis.esl.sdk.FieldValidator sdkFieldValidator1 = null;
    private com.silanis.esl.sdk.FieldValidator sdkFieldValidator2 = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkFieldValidator1 = null;
        FieldValidatorConverter converter = new FieldValidatorConverter(sdkFieldValidator1);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPIFieldValidation(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiFieldValidation1 = null;
        FieldValidatorConverter converter = new FieldValidatorConverter(apiFieldValidation1);
        assertThat( "Converter didn't return a null sdk object for a null api object", new FieldValidatorConverter(apiFieldValidation1).toAPIFieldValidation(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkFieldValidator1 = null;
        FieldValidatorConverter converter = new FieldValidatorConverter(sdkFieldValidator1);
        assertThat( "Converter didn't return a null sdk object for a null sdk object", converter.toSDKFieldValidator(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiFieldValidation1 = null;
        FieldValidatorConverter converter = new FieldValidatorConverter(apiFieldValidation1);
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPIFieldValidation(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkFieldValidator1 = createTypicalSDKFieldValidator();
        FieldValidatorConverter converter = new FieldValidatorConverter(sdkFieldValidator1);
        sdkFieldValidator2 = converter.toSDKFieldValidator();
        assertThat( "Converter returned a null sdk object for a non null sdk object", sdkFieldValidator2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", sdkFieldValidator2, is( equalTo( sdkFieldValidator1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiFieldValidation1 = createTypicalAPIFieldValidation();
        FieldValidatorConverter converter = new FieldValidatorConverter(apiFieldValidation1);
        apiFieldValidation2 = converter.toAPIFieldValidation();
        assertThat( "Converter returned a null api object for a non null api object", apiFieldValidation2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", apiFieldValidation2, is( equalTo( apiFieldValidation1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiFieldValidation1 = createTypicalAPIFieldValidation();
        sdkFieldValidator1 = new FieldValidatorConverter(apiFieldValidation1).toSDKFieldValidator();

        assertThat( "Error message was not set correctly",apiFieldValidation1.getErrorMessage(), is( equalTo( sdkFieldValidator1.getErrorMessage()) ) );
        assertThat( "Max length was not set correctly",apiFieldValidation1.getMaxLength(), is( equalTo( sdkFieldValidator1.getMaxLength()) ) );
        assertThat( "Min length was not set correctly", apiFieldValidation1.getMinLength(), is( equalTo( sdkFieldValidator1.getMinLength()) ) );
        assertThat( "Required was not set correctly",apiFieldValidation1.getRequired(), is( equalTo( sdkFieldValidator1.isRequired()) ) );
        assertThat( "Disabled was not set correctly",apiFieldValidation1.getDisabled(), is( equalTo( sdkFieldValidator1.isDisabled()) ) );
        assertThat( "Group tooltip was not set correctly",apiFieldValidation1.getGroupTooltip(), is( equalTo( sdkFieldValidator1.getGroupTooltip()) ) );

    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkFieldValidator1 = createTypicalSDKFieldValidator();
        apiFieldValidation1 = new FieldValidatorConverter(sdkFieldValidator1).toAPIFieldValidation();

        assertThat( "Error code was not set correctly", apiFieldValidation1.getErrorCode(), is( nullValue() ) );
        assertThat( "Error message was not set correctly",apiFieldValidation1.getErrorMessage(), is( equalTo( sdkFieldValidator1.getErrorMessage()) ) );
        assertThat( "Max length was not set correctly",apiFieldValidation1.getMaxLength(), is( equalTo( sdkFieldValidator1.getMaxLength()) ) );
        assertThat( "Min length was not set correctly", apiFieldValidation1.getMinLength(), is( equalTo( sdkFieldValidator1.getMinLength()) ) );
        assertThat( "Required was not set correctly",apiFieldValidation1.getRequired(), is( equalTo( sdkFieldValidator1.isRequired()) ) );
        assertThat( "Disabled was not set correctly",apiFieldValidation1.getDisabled(), is( equalTo( sdkFieldValidator1.isDisabled()) ) );
        assertThat( "Pattern was not set correctly", apiFieldValidation1.getPattern(), is( equalTo( sdkFieldValidator1.getRegex()) ) );
        assertThat( "Group tooltip was not set correctly", apiFieldValidation1.getGroupTooltip(), is( equalTo( sdkFieldValidator1.getGroupTooltip()) ) );
        assertThat( "Group was not set correctly", apiFieldValidation1.getGroup(), is( equalTo( sdkFieldValidator1.getGroup()) ) );

    }

    /**
     * Create a typical API field validation.
     *
     * @return
     */
    private com.silanis.esl.api.model.FieldValidation createTypicalAPIFieldValidation() {
        com.silanis.esl.api.model.FieldValidation apiFieldValidation = new com.silanis.esl.api.model.FieldValidation();
        apiFieldValidation.setErrorCode(100);
        apiFieldValidation.setErrorMessage("Error message.");
        apiFieldValidation.setMaxLength(30);
        apiFieldValidation.setMinLength(10);
        apiFieldValidation.setPattern("*pattern*");
        apiFieldValidation.setRequired(true);
        apiFieldValidation.setDisabled(false);
        return apiFieldValidation;
    }

    /**
     * Create a typical SDK field validator.
     *
     * @return
     */
    private com.silanis.esl.sdk.FieldValidator createTypicalSDKFieldValidator() {
        com.silanis.esl.sdk.FieldValidator sdkFieldValidator  = FieldValidatorBuilder.alphabetic()
                .maxLength(15)
                .minLength(5)
                .required()
                .withErrorMessage("Error message for validation.")
                .build();
        return sdkFieldValidator;
    }

}
