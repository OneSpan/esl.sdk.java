package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import java.io.InputStream;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Document-level field extraction
 */
public class DocumentExtractionExample extends SDKSample {

    private InputStream documentInputStream;
    public static final String DOCUMENT_NAME = "First Document";

    public static void main( String... args ) {
        new DocumentExtractionExample().run();
    }

    public DocumentExtractionExample() {
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "extract_document.pdf" );
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "John1" )
                        .withLastName( "Smith1" )
                        .withCustomId("signer1"))
                .withSigner( newSignerWithEmail( email2 )
                        .withFirstName("John2")
                        .withLastName("Smith2")
                        .withCustomId("signer2"))
                .withSigner( newSignerWithEmail( email3 )
                        .withFirstName( "John3" )
                        .withLastName( "Smith3" )
                        .withCustomId("signer3"))
                .withDocument( newDocumentWithName(DOCUMENT_NAME)
                        .fromStream( documentInputStream, DocumentType.PDF )
                        .enableExtraction() )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
