package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.FieldValidation;
import com.silanis.esl.sdk.FieldValidator;

import java.util.ArrayList;

/**
 * Converter between SDK FieldValidator and API FieldValidation.
 */
public class FieldValidatorConverter {
    private FieldValidator fieldValidator = null;
    private FieldValidation fieldValidation = null;

    /**
     * Construct with SDK object involved in conversion.
     *
     * @param fieldValidator
     */
    public FieldValidatorConverter( FieldValidator fieldValidator ) {
        this.fieldValidator = fieldValidator;
    }

    /**
     * Construct with API object involved in conversion.
     *
     * @param fieldValidation
     */
    public FieldValidatorConverter(FieldValidation fieldValidation) {
        this.fieldValidation = fieldValidation;
    }

    /**
     * Convert from SDK to API.
     *
     * @return a FieldValidation object.
     */
    public FieldValidation toAPIFieldValidation() {
        if (fieldValidator == null) {
            return fieldValidation;
        }
        FieldValidation fieldValidation = new FieldValidation();

        if ( fieldValidator.getMaxLength() != null ) {
            fieldValidation.setMaxLength( fieldValidator.getMaxLength() );
        }

        if ( fieldValidator.getMinLength() != null ) {
            fieldValidation.setMinLength( fieldValidator.getMinLength() );
        }

        if ( !fieldValidator.getOptions().isEmpty() ) {
            fieldValidation.setEnum( new ArrayList<String>() );
            fieldValidation.getEnum().addAll( fieldValidator.getOptions() );
        }

        fieldValidation.setRequired( fieldValidator.isRequired() );
        fieldValidation.setDisabled( fieldValidator.isDisabled() );

        if ( fieldValidator.getGroup() != null ) {
            fieldValidation.setGroup( fieldValidator.getGroup() );
        }

        if ( fieldValidator.getMinimumRequired() != null ) {
            fieldValidation.setMinimumRequired( fieldValidator.getMinimumRequired() );
        }

        if ( fieldValidator.getErrorMessage() != null ) {
            fieldValidation.setErrorMessage( fieldValidator.getErrorMessage() );
        }

        if ( fieldValidator.getRegex() != null ) {
            fieldValidation.setPattern( fieldValidator.getRegex() );
        }

        return fieldValidation;
    }

    /**
     * Convert from API to SDK.
     *
     * @return a FieldValidator object.
     */
    public FieldValidator toSDKFieldValidator() {
        if (fieldValidation == null) {
            return fieldValidator;
        }
        FieldValidator fieldValidator = new FieldValidator();

        fieldValidator.setErrorMessage(fieldValidation.getErrorMessage());

        if ( fieldValidation.getMaxLength() != null) {
            fieldValidator.setMaxLength(fieldValidation.getMaxLength());
        }

        if ( fieldValidation.getMinLength() != null) {
            fieldValidator.setMinLength(fieldValidation.getMinLength());
        }

        fieldValidator.setRegex(fieldValidation.getPattern());

        if (fieldValidation.getRequired()) {
            fieldValidator.setRequired(fieldValidation.getRequired());
        }

        if (fieldValidation.getDisabled()) {
            fieldValidator.setDisabled(fieldValidation.getDisabled());
        }

        if(fieldValidation.getEnum()!=null){
            fieldValidator.getOptions().addAll(fieldValidation.getEnum());
        }

        if(fieldValidation.getGroup() != null){
            fieldValidator.setGroup(fieldValidation.getGroup());
        }

        if ( fieldValidation.getMinimumRequired() != null) {
            fieldValidator.setMinimumRequired(fieldValidation.getMinimumRequired());
        }

        return fieldValidator;
    }

}
