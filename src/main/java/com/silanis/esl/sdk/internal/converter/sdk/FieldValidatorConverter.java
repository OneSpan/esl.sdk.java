package com.silanis.esl.sdk.internal.converter.sdk;

import com.silanis.awsng.web.rest.model.FieldValidation;
import com.silanis.esl.sdk.FieldValidator;

import java.util.ArrayList;

public class FieldValidatorConverter {
    private FieldValidator fieldValidator;

    public FieldValidatorConverter( FieldValidator fieldValidator ) {
        this.fieldValidator = fieldValidator;
    }

    public FieldValidation getESLFieldValidation() {
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
}
