package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;

import java.io.InputStream;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.captureFor;
import static com.silanis.esl.sdk.builder.SignatureBuilder.initialsFor;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
/**
 * Demonstrating the 3 options a signer can have when signing a document
 */
public class SignatureStylesExample extends SDKSample {

    private String email1;
    private InputStream documentInputStream1;
    public static final String DOCUMENT_NAME = "First Document";
    public static final int FULL_NAME_SIGNATURE_PAGE = 0;
    public static final double FULL_NAME_SIGNATURE_POSITION_X = 500;
    public static final double FULL_NAME_SIGNATURE_POSITION_Y = 100;
    public static final int INITIAL_SIGNATURE_PAGE = 0;
    public static final double INITIAL_SIGNATURE_POSITION_X = 500;
    public static final double INITIAL_SIGNATURE_POSITION_Y = 300;
    public static final int HAND_DRAWN_SIGNATURE_PAGE = 0;
    public static final double HAND_DRAWN_SIGNATURE_POSITION_X = 500;
    public static final double HAND_DRAWN_SIGNATURE_POSITION_Y = 500;

    public static void main( String... args ) {
        new SignatureStylesExample( Props.get() ).run();
    }

    public SignatureStylesExample( Properties properties ) {
        this( properties.getProperty( "api.key" ),
                properties.getProperty( "api.url" ),
                properties.getProperty( "1.email" ) );
    }

    public SignatureStylesExample( String apiKey, String apiUrl, String email1 ) {
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
                .withDocument( newDocumentWithName( DOCUMENT_NAME )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature( signatureFor( email1 )
                                .onPage( FULL_NAME_SIGNATURE_PAGE )
                                .atPosition( FULL_NAME_SIGNATURE_POSITION_X, FULL_NAME_SIGNATURE_POSITION_Y ) )
                        .withSignature( initialsFor( email1 )
                                .onPage( INITIAL_SIGNATURE_PAGE )
                                .atPosition( INITIAL_SIGNATURE_POSITION_X, INITIAL_SIGNATURE_POSITION_Y ) )
                        .withSignature( captureFor( email1 )
                                .onPage( HAND_DRAWN_SIGNATURE_PAGE )
                                .atPosition( HAND_DRAWN_SIGNATURE_POSITION_X, HAND_DRAWN_SIGNATURE_POSITION_Y ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
    }
}
