package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.ChallengeBuilder.firstQuestion;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Email, Q&A, and SMS authentication example.
 */
public class AuthenticationMethods {

    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );

    public static void main( String... args ) {

        EslClient eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage superDuperPackage = newPackageNamed( "Policy " + format.format( new Date() ) )
                .describedAs( "This is a package created using the e-SignLive SDK to demonstrate the authentication methods." )
                .expiresAt( now().plusMonths( 1 ).toDate() )
                .withEmailMessage( "This message should be delivered to all signers" )
                .withSigner( newSignerWithEmail( props.getProperty("1.email") )
                        .withFirstName( "Anna" )
                        .withLastName( "Bel" ) )
                .withSigner( newSignerWithEmail( props.getProperty("2.email") )
                        .withFirstName( "Bobby" )
                        .withLastName( "Sue" )
                        .challengedWithQuestions( firstQuestion( "What's 1+1?" )
                                .answer( "2" )
                                .secondQuestion( "What color's the sky?" )
                                .answer( "blue" ) ) )
                .withSigner( newSignerWithEmail( props.getProperty("3.email") )
                        .withFirstName( "Charlie" )
                        .withLastName( "Brown" )
                        .withSmsSentTo( props.getProperty( "3.sms" ) ) )
                .withDocument( newDocumentWithName( "dave.silanis@gmail.com's Document" )
                        .fromFile( "src/main/resources/document.pdf" )
                        .withSignature( signatureFor(props.getProperty("1.email"))
                                .onPage( 0 )
                                .atPosition( 100, 100 ) )
                        .withSignature( signatureFor(props.getProperty("2.email"))
                                .onPage( 0 )
                                .atPosition( 100, 200 ) )
                        .withSignature( signatureFor(props.getProperty("3.email"))
                                .onPage( 0 )
                                .atPosition( 100, 300 ) ) )
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );

        System.out.println( "PackageId: " + packageId );

        eslClient.sendPackage( packageId );
    }
}
