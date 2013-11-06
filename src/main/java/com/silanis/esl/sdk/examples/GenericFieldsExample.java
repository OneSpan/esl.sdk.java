package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.checkBox;
import static com.silanis.esl.sdk.builder.FieldBuilder.textField;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example with a simple field and checkbox
 */
public class GenericFieldsExample extends SDKSample {

    private String email1;
    private InputStream documentInputStream1;

    public static void main( String... args ) {
        new GenericFieldsExample( Props.get() ).run();
    }

    public GenericFieldsExample( Properties properties ) {
        this( properties.getProperty( "api.key" ),
                properties.getProperty( "api.url" ),
                properties.getProperty( "1.email" ) );
    }

    public GenericFieldsExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "GenericFieldsExample Policy " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .describedAs( "This is a package created using the e-SignLive SDK" )
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "John" )
                        .withLastName( "Smith" ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature( signatureFor( email1 )
                                .onPage( 0 )
                                .atPosition( 400, 100 )
                                .withField( textField()
                                        .onPage( 0 )
                                        .atPosition( 400, 200 ) )
                                .withField( checkBox()
                                        .onPage( 0 )
                                        .withSize( 20, 20 )
                                        .atPosition( 400, 300 ) )
                                .withField( checkBox()
                                        .withValue( true )
                                        .onPage( 0 )
                                        .withSize( 20, 20 )
                                        .atPosition( 400, 350 ) ) ) )
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
    }
}