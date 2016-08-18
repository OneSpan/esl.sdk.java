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

    public static final String ALPHABETIC_REGEX = "^[\\sa-zA-Z]+$";
    public static final String ALPHANUMERIC_REGEX = "^[\\s0-9a-zA-Z]+$";
    public static final String NUMERIC_REGEX = "^[-+]?[0-9]*\\.?[0-9]*$";
    public static final String DEFAULT_REGEX = null;
    public static final String DEFAULT_DATEPICKER_FORMAT = "YYYY-MM-dd";

    public static final String ALPHABETIC_ERROR_MESSAGE = "Value entered must by alphabetic only.";
    public static final String ALPHANUMERIC_ERROR_MESSAGE = "Value entered must be alphanumeric only.";
    public static final String NUMERIC_ERROR_MESSAGE = "Value entered must be numeric only.";
    public static final String EMAIL_ERROR_MESSAGE = "Value entered must be an email.";
    public static final String URL_ERROR_MESSAGE = "Value entered must be a URL.";
    public static final String DATEPICKER_ERROR_MESSAGE = "Value entered must be valid DateTimeFormat only.";

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
     * Creates a default datepicker format. The format is "YYYY-MM-dd".
     *
     * @return	a field validator builder
     */
    public static FieldValidatorBuilder datepickerFormat() {
        return new FieldValidatorBuilder( DEFAULT_DATEPICKER_FORMAT )
            .withErrorMessage(DATEPICKER_ERROR_MESSAGE);
    }
    /**
     * Creates a default datepicker format.
     * @param format string that user want to use, for example, "YYYY-dd-MM", "MM-dd-YYYY", "dd-MM-YYYY", "MMM dd, YYYY", "MMMM dd, YYYY".
     * @return	a Numeric value field validator builder
     */
    public static FieldValidatorBuilder datepickerFormat(String format) {
        return new FieldValidatorBuilder( format )
            .withErrorMessage( DATEPICKER_ERROR_MESSAGE );
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
     * There would be no field validator.
     *
     * @return	a field validator builder that does no validation
     */
    public static FieldValidatorBuilder basic() {
        return new FieldValidatorBuilder( DEFAULT_REGEX );
    }

    /**
     * Sets the error message returned to the end user should the validator not
     * validate the input.
     *
     * @param errorMessage @size(max="255")
     * @return
     */
    public FieldValidatorBuilder withErrorMessage( String errorMessage ) {
        this.errorMessage = errorMessage;
        return this;
    }

    /**
     * Set the minimum length of the input allowed
     * @param minLength
     * @return
     */
    public FieldValidatorBuilder minLength( int minLength ) {
        this.minLength = minLength;
        return this;
    }

    /**
     * Sets the maximum length of the input allowed.
     * @param maxLength
     * @return
     */
    public FieldValidatorBuilder maxLength( int maxLength ) {
        this.maxLength = maxLength;
        return this;
    }

    /**
     * Add an option to the options list
     * The first option of the list defines the group for the radio buttons
     * @param option @size(max="255")
     * @return
     */
    public FieldValidatorBuilder withOption( String option ) {
        options.add( option );
        return this;
    }

    /**
     * Set that the field is mandatory to be filled before the signer is allowed
     * to complete signing his document.
     *
     * @return
     */
    public FieldValidatorBuilder required() {
        this.required = true;
        return this;
    }

    /**
     * Builds the actual FieldValidator with the values specified.
     *
     * @return
     */
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


}