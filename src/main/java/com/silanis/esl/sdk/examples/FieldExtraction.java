package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * User: dave
 */
public class FieldExtraction {
    public static final String API_KEY = "ZDQ2MzczNmUtMDMyNC00OTkxLTkzNjYtODc3YTNlOWFmYzNjOkJzYnAyeXNJQURnSA==";
    public static final String API_URL = "http://localhost:8080";

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage superDuperPackage = newPackageNamed( "Sample Insurance policy" )
                .withSigner( newSignerWithEmail( "dlawson@silanis.com" )
                        .withCustomId( "Signer1" )
                        .withFirstName( "John" )
                        .withLastName( "Smith" ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromFile( "src/main/resources/document-with-fields.pdf" )
                        .enableExtraction() )
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
    }
}
