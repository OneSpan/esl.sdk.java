package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.FieldId;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.textField;
import static com.silanis.esl.sdk.builder.FieldBuilder.checkBox;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.EMAIL_REGEX;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.alphabetic;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.alphanumeric;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.basic;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.email;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.numeric;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.regex;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.url;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example of various field validators
 */
public class FieldValidatorsExample extends SDKSample {

    public static final String DOCUMENT_NAME = "First Document";
    public static final FieldId FIELD_NUMERIC_ID = new FieldId("numeric");
    public static final int     FIELD_NUMERIC_MAX_LENGTH = 10;
    public static final FieldId FIELD_ALPHABETIC_ID = new FieldId("alphabetic");
    public static final int     FIELD_ALPHABETIC_MIN_LENGTH = 3;
    public static final int     FIELD_ALPHABETIC_MAX_LENGTH = 10;
    public static final FieldId FIELD_ALPHANUMERIC_ID = new FieldId("alphanumeric");
    public static final int     FIELD_ALPHANUMERIC_MIN_LENGTH = 5;
    public static final FieldId FIELD_URL_ID = new FieldId("url");
    public static final String  FIELD_URL_ERROR_MESSAGE = "This is not a URL";
    public static final FieldId FIELD_EMAIL_ID = new FieldId("email");
    public static final FieldId FIELD_BASIC_ID = new FieldId("basic");
    public static final String  FIELD_BASIC_OPTION_1 = "one";
    public static final String  FIELD_BASIC_OPTION_2 = "two";
    public static final FieldId FIELD_REGEX_ID = new FieldId("regex");
    public static final String  FIELD_REGEX_ERROR_MESSAGE = "This is not a valid email";
    public static final String  FIELD_GROUP = "group";
    public static final FieldId  FIELD_CHECKBOX_ID_1 = new FieldId("checkbox_one");
    public static final FieldId  FIELD_CHECKBOX_ID_2 = new FieldId("checkbox_two");

    public static void main( String... args ) {
        new FieldValidatorsExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "John" )
                        .withLastName( "Smith" ) )
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature( signatureFor( email1 )
                                .onPage( 0 )
                                .atPosition( 500, 100 )
                                .withField( textField()
                                        .withId(FIELD_NUMERIC_ID)
                                        .atPosition( 500, 200 )
                                        .onPage( 0 )
                                        .withValidation(numeric().maxLength(FIELD_NUMERIC_MAX_LENGTH)))
                                .withField( textField()
                                        .withId(FIELD_ALPHABETIC_ID)
                                        .atPosition( 500, 300 )
                                        .onPage( 0 )
                                        .withValidation( alphabetic()
                                                .minLength(FIELD_ALPHABETIC_MIN_LENGTH)
                                                .maxLength(FIELD_ALPHABETIC_MAX_LENGTH)
                                                .required() ) )
                                .withField( textField()
                                        .withId(FIELD_ALPHANUMERIC_ID)
                                        .atPosition( 500, 400 )
                                        .onPage( 0 )
                                        .withValidation(alphanumeric().minLength(FIELD_ALPHANUMERIC_MIN_LENGTH)))
                                .withField( textField()
                                        .withId(FIELD_URL_ID)
                                        .atPosition( 500, 500 )
                                        .onPage( 0 )
                                        .withValidation(url().withErrorMessage(FIELD_URL_ERROR_MESSAGE)))
                                .withField( textField()
                                        .withId(FIELD_EMAIL_ID)
                                        .atPosition( 500, 600 )
                                        .onPage( 0 )
                                        .withValidation( email() ) )
                                .withField( textField()
                                        .withId(FIELD_BASIC_ID)
                                        .atPosition( 500, 700 )
                                        .onPage( 0 )
                                        .withValidation( basic()
                                                .withOption(FIELD_BASIC_OPTION_1)
                                                .withOption(FIELD_BASIC_OPTION_2)
                                                .disabled()))
                                .withField( textField()
                                        .withId(FIELD_REGEX_ID)
                                        .atPosition( 500, 700 )
                                        .onPage( 0 )
                                        .withValidation(regex(EMAIL_REGEX).withErrorMessage(FIELD_REGEX_ERROR_MESSAGE)))
                                .withField( checkBox()
                                        .withId(FIELD_CHECKBOX_ID_1)
                                        .atPosition( 500, 800 )
                                        .onPage( 0 )
                                        .withValidation(basic().setGroup(FIELD_GROUP).setMinimumRequired(1)))
                                .withField( checkBox()
                                        .withId(FIELD_CHECKBOX_ID_2)
                                        .atPosition( 550, 800 )
                                        .onPage( 0 )
                                        .withValidation(basic().setGroup(FIELD_GROUP).setMinimumRequired(1)))
                        ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
