package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;

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
    private InputStream documentInputStream;
    public static final String documentName = "First Document";
    public static final String extractForRole = "Signer1";

    public static void main( String... args ) {
        new DocumentExtractionExample( Props.get() ).run();
    }

    public DocumentExtractionExample( Properties properties ) {
        this(properties.getProperty( "api.key" ),
                properties.getProperty( "api.url" ),
                properties.getProperty( "1.email" ) );
    }

    public DocumentExtractionExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "document-with-fields.pdf" );
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "Sample Insurance policy" )
                .withSigner( newSignerWithEmail( email1 )
                        .withRoleId( extractForRole )
                        .withFirstName( "John" )
                        .withLastName( "Smith" ) )
                .withDocument( newDocumentWithName( documentName )
                        .fromStream( documentInputStream, DocumentType.PDF )
                        .enableExtraction() )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );

        DocumentPackage sentPackage = eslClient.getPackage( packageId );
    }
}
