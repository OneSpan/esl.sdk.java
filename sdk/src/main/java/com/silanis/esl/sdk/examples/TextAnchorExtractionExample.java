package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.TextAnchorPosition;
import com.silanis.esl.sdk.builder.FieldBuilder;
import com.silanis.esl.sdk.builder.TextAnchorBuilder;

import java.io.InputStream;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class TextAnchorExtractionExample extends SDKSample {

    private InputStream documentInputStream1;

    public static final String DOCUMENT_NAME = "Document With Anchors";
    public static final int FIELD_WIDTH = 151;
    public static final int FIELD_HEIGHT = 40;

    public static void main( String... args ) {
        new TextAnchorExtractionExample().run();
    }

    public TextAnchorExtractionExample() {
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document-for-anchor-extraction.pdf" );
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
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
                                        .withCharacter( 6 )
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
