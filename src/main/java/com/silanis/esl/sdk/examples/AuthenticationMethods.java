package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.ChallengeBuilder.firstQuestion;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

public class AuthenticationMethods {
    public static final String API_KEY = "YTUwOGQ5ZDktMDZmMi00MjM5LTkwNDQtYmZiZDI2MTdmNmQxOkJzYnAyeXNJQURnSA==";
    public static final String API_URL = "http://localhost:8080";
    private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage superDuperPackage = newPackageNamed( "Policy " + format.format( new Date() ) )
                .describedAs( "This is a package created using the e-SignLive SDK to demonstrate the authentication methods." )
                .expiresAt( now().plusMonths( 1 ).toDate() )
                .withEmailMessage( "This message should be delivered to all signers" )
                .withSigner( newSignerWithEmail( "dave.silanis@gmail.com" )
                        .withFirstName( "Anna" )
                        .withLastName( "Bel" ) )
                .withSigner( newSignerWithEmail( "dlawson@silanis.com" )
                        .withFirstName( "Bobby" )
                        .withLastName( "Sue" )
                        .challengedWithQuestions( firstQuestion( "What's your favorite sport?" )
                                .answer( "golf" )
                                .secondQuestion( "What music instrument do you play?" )
                                .answer( "drums" ) ) )
                .withSigner( newSignerWithEmail( "dave.lawson.barbar@gmail.com" )
                        .withFirstName( "Charlie" )
                        .withLastName( "Brown" )
                        .withSmsSentTo( "4388377947" ) )
                .withDocument( newDocumentWithName( "dave.silanis@gmail.com's Document" )
                        .fromFile( "src/main/resources/document.pdf" )
                        .withSignature( signatureFor("dave.silanis@gmail.com")
                                .onPage( 0 )
                                .atPosition( 100, 100 ) )
                        .withSignature( signatureFor("dlawson@silanis.com")
                                .onPage( 0 )
                                .atPosition( 100, 200 ) )
                        .withSignature( signatureFor("dave.lawson.barbar@gmail.com")
                                .onPage( 0 )
                                .atPosition( 100, 300 ) ) )
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );

        System.out.println( "PackageId: " + packageId );

        eslClient.sendPackage( packageId );
    }
}
