package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.FieldValidation;
import com.silanis.esl.sdk.FieldValidator;

import java.util.ArrayList;

public class FieldValidatorConverter {
    private FieldValidator fieldValidator = null;
    private FieldValidation fieldValidation = null;

    public FieldValidatorConverter( FieldValidator fieldValidator ) {
        this.fieldValidator = fieldValidator;
    }

    public FieldValidatorConverter(FieldValidation fieldValidation) {
        this.fieldValidation = fieldValidation;
    }

    public FieldValidation toAPIFieldValidation() {
        if (fieldValidation != null) {
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
        if ( fieldValidator.getErrorMessage() != null ) {
            fieldValidation.setErrorMessage( fieldValidator.getErrorMessage() );
        }

        if ( fieldValidator.getRegex() != null ) {
            fieldValidation.setPattern( fieldValidator.getRegex() );
        }

        return fieldValidation;
    }

    public FieldValidator toSDKFieldValidator() {
        if (fieldValidator != null) {
            return fieldValidator;
        }
        FieldValidator fieldValidator = new FieldValidator();
        fieldValidator.setErrorMessage(fieldValidation.getErrorMessage());
        fieldValidator.setMaxLength(fieldValidation.getMaxLength());
        fieldValidator.setMinLength(fieldValidation.getMinLength());
        fieldValidator.setRegex(fieldValidation.getPattern());
        fieldValidator.setRequired(fieldValidation.getRequired());
        return fieldValidator;
    }

}
