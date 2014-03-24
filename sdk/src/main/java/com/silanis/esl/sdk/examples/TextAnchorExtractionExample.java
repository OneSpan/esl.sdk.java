package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.TextAnchorPosition;
import com.silanis.esl.sdk.builder.FieldBuilder;
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
    private DocumentPackage retrievedPackage;

    public static final String DOCUMENT_NAME = "Document With Anchors";
    public static final int FIELD_WIDTH = 150;
    public static final int FIELD_HEIGHT = 40;

    public DocumentPackage getRetrievedPackage() {
        return retrievedPackage;
    }

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
                .withDocument( newDocumentWithName( DOCUMENT_NAME )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .enableExtraction()
                        .withSignature(signatureFor( email1 )
                                .withPositionAnchor( TextAnchorBuilder.newTextAnchor( "Nondisclosure" )
                                        .atPosition( TextAnchorPosition.BOTTOMRIGHT )
                                        .withSize( FIELD_WIDTH, FIELD_HEIGHT )
                                        .withOffset( 0, 0 )
                                        .withCharacter( 9 )
                                        .withOccurence( 0 ) ) )
                        .withSignature(signatureFor( email1 )
                                .withPositionAnchor( TextAnchorBuilder.newTextAnchor( "Receiving" )
                                        .atPosition( TextAnchorPosition.TOPLEFT )
                                        .withSize( FIELD_WIDTH, FIELD_HEIGHT )
                                        .withOffset( 0, 0 )
                                        .withCharacter( 0 )
                                        .withOccurence( 0 ) )
                                .withField( FieldBuilder.textField()
                                        .withPositionAnchor( TextAnchorBuilder.newTextAnchor("Definition")
                                                .atPosition( TextAnchorPosition.TOPLEFT )
                                                .withSize( FIELD_WIDTH, FIELD_HEIGHT )
                                                .withOffset( 0, 0 )
                                                .withCharacter( 0 )
                                                .withOccurence( 0 ) ) )
                                .withField( FieldBuilder.textField()
                                        .withPositionAnchor( TextAnchorBuilder.newTextAnchor("through legitimate means")
                                                .atPosition( TextAnchorPosition.TOPLEFT )
                                                .withSize( FIELD_WIDTH, FIELD_HEIGHT )
                                                .withOffset( 100, 100 )
                                                .withCharacter( 0 )
                                                .withOccurence( 1 ) ) )
                                .withField( FieldBuilder.textField()
                                        .atPosition( 10, 10 )
                                        .withSize( FIELD_WIDTH, FIELD_HEIGHT )
                                        .onPage(1)
                                )
                        )
                )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );

        retrievedPackage = eslClient.getPackage( packageId );
    }
}
