package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;

import java.io.InputStream;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.textField;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.*;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example of various field validators
 */
public class FieldValidatorsExample extends SDKSample {

    private String email1;
    private InputStream documentInputStream1;

    public static void main( String... args ) {
        new FieldValidatorsExample( Props.get() ).run();
    }

    public FieldValidatorsExample( Properties properties ) {
        this( properties.getProperty( "api.key" ),
                properties.getProperty( "api.url" ),
                properties.getProperty( "1.email" ) );
    }

    public FieldValidatorsExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "Sample Insurance policy" )
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "John" )
                        .withLastName( "Smith" ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature( signatureFor( email1 )
                                .onPage( 0 )
                                .atPosition( 500, 100 )
                                .withField( textField()
                                        .atPosition( 500, 200 )
                                        .onPage( 0 )
                                        .withValidation( numeric().maxLength( 10 ) ) )
                                .withField( textField()
                                        .atPosition( 500, 300 )
                                        .onPage( 0 )
                                        .withValidation( alphabetic()
                                                .minLength( 3 )
                                                .maxLength( 10 )
                                                .required() ) )
                                .withField( textField()
                                        .atPosition( 500, 400 )
                                        .onPage( 0 )
                                        .withValidation( alphanumeric().minLength( 5 ) ) )
                                .withField( textField()
                                        .atPosition( 500, 500 )
                                        .onPage( 0 )
                                        .withValidation( url().withErrorMessage( "This is not a URL" ) ) )
                                .withField( textField()
                                        .atPosition( 500, 600 )
                                        .onPage( 0 )
                                        .withValidation( email() ) )
                                .withField( textField()
                                        .atPosition( 500, 700 )
                                        .onPage( 0 )
                                        .withValidation( basic()
                                                .withOption( "one" )
                                                .withOption( "two" ) ) )
                                .withField( textField()
                                        .atPosition( 500, 700 )
                                        .onPage( 0 )
                                        .withValidation( regex( EMAIL_REGEX ).withErrorMessage( "This is not a valid email" ) ) )
                        ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );

        eslClient.sendPackage( packageId );
    }
}
