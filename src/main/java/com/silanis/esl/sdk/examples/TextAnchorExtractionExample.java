package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.FieldBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.TextAnchorBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class TextAnchorExtractionExample extends SDKSample {

    private String email1;
    private InputStream documentInputStream1;

    public static void main( String... args ) {
        new TextAnchorExtractionExample( Props.get() ).run();
    }

    public TextAnchorExtractionExample( Properties properties ) {
        this( properties.getProperty( "api.key" ),
                properties.getProperty( "api.url" ),
                properties.getProperty( "1.email" ) );
    }

    public TextAnchorExtractionExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document-for-anchor-extraction.pdf" );
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "TextAnchorExtractionExample: " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .withSigner(newSignerWithEmail(email1)
                        .withRoleId( "Signer1" )
                        .withFirstName( "John" )
                        .withLastName( "Smith" ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .enableExtraction()
                        .withSignature(signatureFor( email1 )
                                .withPositionAnchor( TextAnchorBuilder.newTextAnchor( "Nondisclosure" )
                                        .atPosition( TextAnchorPosition.BOTTOMRIGHT )
                                        .withSize( 150, 40 )
                                        .withOffset( 0, 0 )
                                        .withCharacter( 9 )
                                        .withOccurence( 0 ) ) )
                        .withSignature(signatureFor( email1 )
                                .withPositionAnchor( TextAnchorBuilder.newTextAnchor( "Receiving" )
                                        .atPosition( TextAnchorPosition.TOPLEFT )
                                        .withSize( 150, 40 )
                                        .withOffset( 0, 0 )
                                        .withCharacter( 0 )
                                        .withOccurence( 0 ) )
                                .withField( FieldBuilder.textField()
                                        .withPositionAnchor( TextAnchorBuilder.newTextAnchor("Definition")
                                                .atPosition( TextAnchorPosition.TOPLEFT )
                                                .withSize( 150, 40 )
                                                .withOffset( 0, 0 )
                                                .withCharacter( 0 )
                                                .withOccurence( 0 ) ) )
                                .withField( FieldBuilder.textField()
                                        .withPositionAnchor( TextAnchorBuilder.newTextAnchor("through legitimate means")
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
