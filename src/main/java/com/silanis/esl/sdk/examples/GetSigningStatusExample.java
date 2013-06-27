package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.SigningStatus;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * User: dave
 */
public class GetSigningStatusExample {

    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage superDuperPackage = newPackageNamed( "GetSigningStatus example: " + format.format( new Date() ) )
                .describedAs( "This is a package created using the e-SignLive SDK" )
                .expiresAt( now().plusMonths( 1 ).toDate() )
                .withEmailMessage( "This message should be delivered to all signers" )
                .inPerson( true )
                .withSigner( newSignerWithEmail( props.getProperty("1.email") )
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .withTitle( "Managing Director" )
                        .withCompany( "Acme Inc." ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromFile( "src/main/resources/document.pdf" )
                        .withSignature( signatureFor( props.getProperty("1.email") )
                                .onPage( 0 )
                                .atPosition( 100, 100 ) ) )
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );

        SigningStatus draftSigningStatus = eslClient.getSigningStatus( packageId, null, null );
        eslClient.sendPackage( packageId );
        SigningStatus sentSigningStatus = eslClient.getSigningStatus( packageId, null, null );
    }
}
