package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class SignerOrderingExample {

    public static final String API_KEY = "c0Y5ZnZRZ1ppN2liOnNlY3JldA==";
    public static final String API_URL = "http://localhost:8080";

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage superDuperPackage = newPackageNamed( "Sample Insurance policy" )
                .withSigner(newSignerWithEmail("etienne_hardy@silanis.com")
                        .withFirstName("John")
                        .withLastName("Smith")
                        .signingOrder(1))
                .withSigner( newSignerWithEmail( "patty.galant@email.com" )
                        .withFirstName( "Patty" )
                        .withLastName( "Galant" )
                        .signingOrder( 2 ) )
                .withDocument(newDocumentWithName("First Document")
                        .fromFile("src/main/resources/document.pdf")
                        .withSignature(signatureFor("etienne_hardy@silanis.com")
                                .onPage(0)
                                .atPosition(500, 100))
                        .withSignature(signatureFor("patty.galant@email.com")
                                .onPage(0)
                                .atPosition(500, 500)))
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage(packageId);
    }
}
