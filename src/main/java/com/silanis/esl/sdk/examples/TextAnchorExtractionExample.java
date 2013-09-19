package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.TextAnchorPosition;
import com.silanis.esl.sdk.builder.FieldBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.TextAnchorBuilder;

import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class TextAnchorExtractionExample {
    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage superDuperPackage = newPackageNamed( "Anchor Extraction Package" )
                .withSigner( newSignerWithEmail( props.getProperty( "1.email" ) )
                        .withRoleId( "Signer1" )
                        .withFirstName( "John" )
                        .withLastName( "Smith" ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromFile( "src/main/resources/document-for-anchor-extraction.pdf" )
                        .enableExtraction()
                        .withSignature( SignatureBuilder.signatureFor( props.getProperty( "1.email" ) )
                                .withPositionAnchor( TextAnchorBuilder.newTextAnchor( "Application" )
                                        .atPosition( TextAnchorPosition.BOTTOMRIGHT )
                                        .withSize( 150, 40 )
                                        .withOffset( 0, 0 )
                                        .withCharacter( 9 )
                                        .withOccurence( 0 ) ) )
                        .withSignature( SignatureBuilder.signatureFor( props.getProperty( "1.email" ) )
                                .withPositionAnchor( TextAnchorBuilder.newTextAnchor( "who are paid in Canada" )
                                        .atPosition( TextAnchorPosition.TOPLEFT )
                                        .withSize( 150, 40 )
                                        .withOffset( 0, 0 )
                                        .withCharacter( 0 )
                                        .withOccurence( 0 ) )
                                .withField( FieldBuilder.textField()
                                        .withPositionAnchor( TextAnchorBuilder.newTextAnchor("INFORMATION")
                                                .atPosition( TextAnchorPosition.TOPLEFT )
                                                .withSize( 150, 40 )
                                                .withOffset( 0, 0 )
                                                .withCharacter( 0 )
                                                .withOccurence( 0 ) ) )
                                .withField( FieldBuilder.textField()
                                        .withPositionAnchor( TextAnchorBuilder.newTextAnchor("INFORMATION")
                                                .atPosition( TextAnchorPosition.TOPLEFT )
                                                .withSize( 150, 40 )
                                                .withOffset( 100, 100 )
                                                .withCharacter( 0 )
                                                .withOccurence( 1 ) ) )
                        )
                )
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );

        DocumentPackage sentPackage = eslClient.getPackage( packageId );
    }
}
