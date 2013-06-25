package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * User: dave
 */
public class GetPackageExample {
    public static final String API_KEY = "ZDQ2MzczNmUtMDMyNC00OTkxLTkzNjYtODc3YTNlOWFmYzNjOkJzYnAyeXNJQURnSA==";
    public static final String API_URL = "http://localhost:8080";

    private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );


    public static void main( String... args ) {
//        getCompletedPackage();
        getNewlyCreatedPackage();
    }


    public static void getNewlyCreatedPackage() {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage superDuperPackage = newPackageNamed( "Policy " + format.format( new Date() ) )
                .describedAs( "This is a package created using the e-SignLive SDK" )
                .expiresAt( now().plusMonths( 1 ).toDate() )
                .withEmailMessage( "This message should be delivered to all signers" )
                .inPerson( true )
                .withSigner( newSignerWithEmail( "dlawson@silanis.com" )
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .withTitle( "Managing Director" )
                        .withCompany( "Acme Inc." ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromFile( "src/main/resources/document.pdf" )
                        .withSignature( signatureFor( "dlawson@silanis.com" )
                                .onPage( 0 )
                                .atPosition( 100, 100 )
                                .withField( FieldBuilder.textField()
                                        .onPage( 0 )
                                        .atPosition( 400, 100 )
                                        .withSize( 200, 50 ) )
                                .withField( FieldBuilder.signatureDate()
                                        .onPage( 0 )
                                        .atPosition( 400, 200 )
                                        .withSize( 200, 50 ) )
                                .withField( FieldBuilder.checkBox()
                                        .onPage( 0 )
                                        .atPosition( 400, 300 )
                                        .withSize( 200, 50 ) )
                                .withField( FieldBuilder.signerCompany()
                                        .onPage( 0 )
                                        .atPosition( 400, 400 )
                                        .withSize( 200, 50 ) )
                                .withField( FieldBuilder.signerName()
                                        .onPage( 0 )
                                        .atPosition( 400, 500 )
                                        .withSize( 200, 50 ) )
                                .withField( FieldBuilder.signerTitle()
                                        .onPage( 0 )
                                        .atPosition( 400, 600 )
                                        .withSize( 200, 50 ) ) ) )
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );
        DocumentPackage unsentPackage = eslClient.getPackage( packageId );

        eslClient.sendPackage( packageId );
        DocumentPackage sentPackage = eslClient.getPackage( packageId );

        System.out.println( "Done" );
    }

    public static void getCompletedPackage() {
        EslClient eslClient = new EslClient( API_KEY, API_URL );
        PackageId packageId = new PackageId( "jyvSSesuYZBadsRdCLxQAzr3KlsS" );
        DocumentPackage retrievedPackage = eslClient.getPackage( packageId );

        System.out.println( retrievedPackage.getName() );
    }
}
