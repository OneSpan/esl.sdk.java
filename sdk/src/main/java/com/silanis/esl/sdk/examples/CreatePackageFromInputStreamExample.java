package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Uploads a document, that is a Java resource, using an InputStream
 */
public class CreatePackageFromInputStreamExample extends SDKSample {

    public static void main( String... args ) {
        new CreatePackageFromInputStreamExample( Props.get() ).run();
    }

    private String email1;

    public CreatePackageFromInputStreamExample( Properties properties ) {
        this( properties.getProperty("api.key"),
                properties.getProperty( "api.url" ),
                properties.getProperty( "1.email" ) );
    }

    public CreatePackageFromInputStreamExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
    }

    @Override
    public void execute() {
        InputStream documentStream = CreatePackageFromInputStreamExample.class.getResourceAsStream("/document.pdf");

        DocumentPackage superDuperPackage = newPackageNamed( "CreatePackageFromInputStreamExample " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "John" )
                        .withLastName( "Smith" ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentStream, DocumentType.PDF )
                        .withSignature( signatureFor(email1)
                                .onPage( 0 )
                                .atPosition( 100, 100 ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );

        eslClient.sendPackage( packageId );
    }
}