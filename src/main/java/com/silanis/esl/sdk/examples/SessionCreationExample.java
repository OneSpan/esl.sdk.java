package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.SessionToken;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class SessionCreationExample {
    public static final String API_KEY = "YjA0ODY5MDItZjM4NC00MTA2LTk0OTgtYWVhNmZkZGQ4YjJlOkJzYnAyeXNJQURnSA==";
    public static final String API_URL = "http://localhost:8080";

    private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );


    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );
        String signerId = "myCustomSignerId";

        DocumentPackage superDuperPackage = newPackageNamed( "Policy " + format.format( new Date() ) )
                .withSigner( newSignerWithEmail( "etienne_hardy@silanis.com" )
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .withCustomId( signerId ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromFile( "src/main/resources/document.pdf" )
                        .withSignature( signatureFor( "etienne_hardy@silanis.com" )
                                .onPage( 0 )
                                .atPosition( 100, 100 ) ) )
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        SessionToken signerSessionToken = eslClient.createSessionToken( packageId, signerId );
        System.out.println( ">>>" + signerSessionToken.getSessionToken() + "<<<" );
    }
}
