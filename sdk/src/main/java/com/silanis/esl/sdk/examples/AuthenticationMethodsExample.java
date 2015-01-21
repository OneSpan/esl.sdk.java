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
import static com.silanis.esl.sdk.builder.SignerBuilder.ChallengeBuilder.firstQuestion;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Email, Q&A, and SMS authentication example.
 */
public class AuthenticationMethodsExample extends SDKSample {

    public final String email1;
    public final String email2;
    public final String email3;
    private String sms3;
    private InputStream documentInputStream;

    public static void main( String... args ) {
        new AuthenticationMethodsExample( Props.get() ).run();
    }

    public AuthenticationMethodsExample( Properties props ) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ),
                props.getProperty( "2.email" ),
                props.getProperty( "3.email" ),
                props.getProperty( "3.sms" ) );
    }

    public AuthenticationMethodsExample( String apiKey, String apiUrl, String email1, String email2, String email3, String sms3 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        this.sms3 = sms3;
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "Policy " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .describedAs( "This is a package created using the e-SignLive SDK to demonstrate the authentication methods." )
                .expiresAt( now().plusMonths( 1 ).toDate() )
                .withEmailMessage( "This message should be delivered to all signers" )
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "Anna" )
                        .withLastName( "Bel" ) )
                .withSigner( newSignerWithEmail( email2 )
                        .withFirstName( "Bobby" )
                        .withLastName( "Sue" )
                        .challengedWithQuestions( firstQuestion( "What's 1+1?" )
                                .answer( "2" )
                                .secondQuestion( "What color's the sky?" )
                                .answer( "blue" ) ) )
                .withSigner( newSignerWithEmail( email3 )
                        .withFirstName( "Charlie" )
                        .withLastName( "Brown" )
                        .withSmsSentTo( sms3 ) )
                .withDocument( newDocumentWithName( "dave.silanis@gmail.com's Document" )
                        .fromStream( documentInputStream, DocumentType.PDF )
                        .withSignature( signatureFor( email1 )
                                .onPage( 0 )
                                .atPosition( 100, 100 ) )
                        .withSignature( signatureFor( email2 )
                                .onPage( 0 )
                                .atPosition( 100, 200 ) )
                        .withSignature( signatureFor( email3 )
                                .onPage( 0 )
                                .atPosition( 100, 300 ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        retrievedPackage = eslClient.getPackage( packageId );
    }

}
