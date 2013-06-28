package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.FieldValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>FieldValidatorBuilder is a convenient class used to create field validators.</p>
 */
public class FieldValidatorBuilder {

    public static final String EMAIL_REGEX = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
    public static final String URL_REGEX = "^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";

    private static final String ALPHABETIC_REGEX = "^[\\sa-zA-Z]+$";
    private static final String ALPHANUMERIC_REGEX = "^[\\s0-9a-zA-Z]+$";
    private static final String NUMERIC_REGEX = "^[-+]?[0-9]*\\.?[0-9]*$";
    private static final String DEFAULT_REGEX = null;

    private static final String ALPHABETIC_ERROR_MESSAGE = "Value entered must by alphabetic only.";
    private static final String ALPHANUMERIC_ERROR_MESSAGE = "Value entered must be alphanumeric only.";
    private static final String NUMERIC_ERROR_MESSAGE = "Value entered must be numeric only.";
    private static final String EMAIL_ERROR_MESSAGE = "Value entered must be an email.";
    private static final String URL_ERROR_MESSAGE = "Value entered must be a URL.";

    private static final int DEFAULT_MAX_LENGTH = Integer.MAX_VALUE;

    private List<String> options = new ArrayList<String>();
    private int minLength = 0;
    private int maxLength = DEFAULT_MAX_LENGTH;
    private String regex;
    private boolean required;
    private String errorMessage;

    private FieldValidatorBuilder( String regex ) {
        this.regex = regex;
    }

    /**
     * Creates an URL based field validator. The allowed values should match an URL address.
     * 
     * @return	an URL address field validator builder
     */
    public static FieldValidatorBuilder url() {
        return new FieldValidatorBuilder( URL_REGEX )
                .withErrorMessage( URL_ERROR_MESSAGE );
    }
    
    /**
     * Creates an Email based field validator. The allowed values should match an Email address.
     * 
     * @return	an Email address field validator builder
     */
    public static FieldValidatorBuilder email() {
        return new FieldValidatorBuilder( EMAIL_REGEX )
                .withErrorMessage( EMAIL_ERROR_MESSAGE );
    }
    
    /**
     * Creates an Alphabetic based field validator. The allowed values should match an Alphabetic value.
     * 
     * @return	an Alphabetic value field validator builder
     */
    public static FieldValidatorBuilder alphabetic() {
        return new FieldValidatorBuilder( ALPHABETIC_REGEX )
                .withErrorMessage( ALPHABETIC_ERROR_MESSAGE );
    }
    /**
     * Creates an Alphanumeric based field validator. The allowed values should match an Alphanumeric value.
     * 
     * @return	an Alphanumeric value field validator builder
     */
    public static FieldValidatorBuilder alphanumeric() {
        return new FieldValidatorBuilder( ALPHANUMERIC_REGEX )
                .withErrorMessage( ALPHANUMERIC_ERROR_MESSAGE );
    }
    /**
     * Creates a Numeric based field validator. The allowed values should match a Numeric value.
     * 
     * @return	a Numeric value field validator builder
     */
    public static FieldValidatorBuilder numeric() {
        return new FieldValidatorBuilder( NUMERIC_REGEX )
                .withErrorMessage( NUMERIC_ERROR_MESSAGE );
    }
    /**
     * Creates a Regex based field validator. The allowed values should match the Regex expression.
     * 
     * @return	a Regex expression field validator builder
     */
    public static FieldValidatorBuilder regex(String regex) {
        return new FieldValidatorBuilder( regex );
    }
    
    /**
     * There would be no field valitator.
     * 
     * @return	a field validator builder that does no validation
     */
    public static FieldValidatorBuilder basic() {
        return new FieldValidatorBuilder( DEFAULT_REGEX );
    }

    public FieldValidatorBuilder withErrorMessage( String errorMessage ) {
        this.errorMessage = errorMessage;
        return this;
    }

    public FieldValidatorBuilder minLength( int minLength ) {
        this.minLength = minLength;
        return this;
    }

    public FieldValidatorBuilder maxLength( int maxLength ) {
        this.maxLength = maxLength;
        return this;
    }

    public FieldValidatorBuilder required() {
        this.required = true;
        return this;
    }

    public FieldValidator build() {
        FieldValidator result = new FieldValidator();
        if ( minLength < 0 ) {
            throw new IllegalArgumentException("minLength can not be less than 0");
        }
        result.setMinLength( minLength );
        if ( maxLength < 0 ) {
            throw new IllegalArgumentException( "maxLength can not be less than 0" );
        }
        result.setMaxLength( maxLength );
        if ( minLength > maxLength ) {
            throw new IllegalArgumentException( "maxLength can not be less than minLength" );
        }
        result.setRegex( regex );
        result.getOptions().addAll( options );
        result.setRequired(required);
        result.setErrorMessage( errorMessage );
        return result;
    }

    public FieldValidatorBuilder withOption( String option ) {
        options.add( option );
        return this;
    }
}