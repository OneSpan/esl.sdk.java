package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import java.io.InputStream;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Document-level field extraction
 */
public class DocumentExtractionExample extends SDKSample {

    private String email1;
    private String email2;
    private String email3;
    private InputStream documentInputStream;
    public static final String DOCUMENT_NAME = "First Document";

    public static void main( String... args ) {
        new DocumentExtractionExample( Props.get() ).run();
    }

    public DocumentExtractionExample( Properties properties ) {
        this(properties.getProperty( "api.key" ),
                properties.getProperty( "api.url" ),
                properties.getProperty( "1.email" ),
                properties.getProperty( "2.email" ),
                properties.getProperty( "3.email" ) );
    }

    public DocumentExtractionExample( String apiKey, String apiUrl, String email1, String email2, String email3 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "extract_document.pdf" );
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "Sample Insurance policy" )
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "John1" )
                        .withLastName( "Smith1" ) )
                .withSigner( newSignerWithEmail( email2 )
                        .withFirstName( "John2" )
                        .withLastName( "Smith2" ) )
                .withSigner( newSignerWithEmail( email3 )
                        .withFirstName( "John3" )
                        .withLastName( "Smith3" ) )
                .withDocument( newDocumentWithName(DOCUMENT_NAME)
                        .fromStream( documentInputStream, DocumentType.PDF )
                        .enableExtraction() )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
