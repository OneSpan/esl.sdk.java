package com.silanis.esl.sdk.examples.guide;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.examples.Props;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class SigningWorkflow {
	
    private static final Properties props = Props.get();
    private static final String API_KEY = props.getProperty( "api.key" );
    private static final String API_URL = props.getProperty( "api.url" );
    private static final String DATA_FORMAT = "EEE, d MMM yyyy HH:mm:ss Z";
    private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );
	
    private static class SignerWorkflow {
    	
        public static void main( String[] args ) {
            SimpleDateFormat format = new SimpleDateFormat( DATA_FORMAT );
            EslClient eslClient = new EslClient( API_KEY, API_URL );
            // Build the DocumentPackage object
            DocumentPackage documentPackage1 = newPackageNamed( "Signer workflow example " + format.format( new Date() ) )
                    .withSigner( newSignerWithEmail( props.getProperty("1.email") )
                            .withFirstName( "Signer 1" )
                            .withLastName( "SignerWorkflow" )
                            .signingOrder( 1 ) )
                    .withSigner( newSignerWithEmail( props.getProperty("2.email") )
                            .withFirstName( "Signer 2" )
                            .withLastName( "SignerWorkflow" )
                            .signingOrder( 2 ) )
                    .withSigner( newSignerWithEmail( props.getProperty("3.email") )
                            .withFirstName( "Signer 3" )
                            .withLastName( "SignerWorkflow" )
                            .signingOrder( 2 ) )
                    .withSigner( newSignerWithEmail( props.getProperty("4.email") )
                            .withFirstName( "Signer 4" )
                            .withLastName( "SignerWorkflow" )
                            .signingOrder( 3 ) )
                    .withDocument( newDocumentWithName( "First Document" )
                            .fromFile( "src/main/resources/document.pdf" )
                            .withSignature( signatureFor( props.getProperty("1.email") )
                                    .onPage( 0 )
                                    .atPosition( 100, 200 ) )
                            .withSignature( signatureFor( props.getProperty("2.email") )
                                    .onPage( 0 )
                                    .atPosition( 100, 400 ) )
                            .withSignature( signatureFor( props.getProperty("3.email") )
                                    .onPage( 0 )
                                    .atPosition( 100, 600 ) )
                            .withSignature( signatureFor( props.getProperty("4.email") )
                                    .onPage( 0 )
                                    .atPosition( 100, 800 ) ) )
                    .build();

            // Issue the request to the ESL server to create the DocumentPackage
            PackageId packageId = eslClient.createPackage( documentPackage1 );

            // Send the package to be signed by the participants
            eslClient.sendPackage( packageId );
        }
    }

    private static class DocumentWorkflow {

        public static void main( String... args ) {
        	
            EslClient eslClient = new EslClient( API_KEY, API_URL );

            DocumentPackage documentPackage2 = newPackageNamed( "Document workflow example " + format.format( new Date() ) )
                    .describedAs( "This is a package with a document workflow created using the e-SignLive SDK" )
                    .withSigner( newSignerWithEmail(  props.getProperty("1.email") )
                            .withFirstName( "Signer" )
                            .withLastName( "DocumentWorkflow" ) )
                    .withDocument( newDocumentWithName( "Second Document" )
                            .fromFile( "src/main/resources/document2.pdf" )
                            .atIndex( 2 )
                            .withSignature( signatureFor(  props.getProperty("1.email") )
                                    .onPage( 0 )
                                    .atPosition( 100, 100 ) ) )
                    .withDocument( newDocumentWithName( "First Document" )
                            .fromFile( "src/main/resources/document.pdf" )
                            .atIndex( 1 )
                            .withSignature( signatureFor(  props.getProperty("1.email") )
                                    .onPage( 0 )
                                    .atPosition( 100, 400 ) ) )
                    .build();

            PackageId packageId = eslClient.createPackage( documentPackage2 );

            eslClient.sendPackage( packageId );
        }
    }

    private static class CombinedWorkflow {

        public static void main( String... args ) {
        	
            EslClient eslClient = new EslClient( API_KEY, API_URL );

            DocumentPackage documentPackage3 = newPackageNamed( "Combined workflow example " + format.format( new Date() ) )
                    .describedAs( "This is a package with a document workflow created using the e-SignLive SDK" )
                    .withSigner( newSignerWithEmail(  props.getProperty("1.email") )
                            .withFirstName( "Signer 1" )
                            .withLastName( "CombinedWorkflow" )
                            .signingOrder( 1 ) )
                    .withSigner( newSignerWithEmail(  props.getProperty("2.email") )
                            .withFirstName( "Signer 2" )
                            .withLastName( "CombinedWorkflow" )
                            .signingOrder( 2 ) )
                    .withDocument( newDocumentWithName( "Second Document" )
                            .fromFile( "src/main/resources/document2.pdf" )
                            .atIndex( 2 )
                            .withSignature( signatureFor(  props.getProperty("1.email") )
                                    .onPage( 0 )
                                    .atPosition( 100, 100 ) )
                            .withSignature( signatureFor(  props.getProperty("2.email") )
                                    .onPage( 0 )
                                    .atPosition( 100, 200 ) ) )
                    .withDocument( newDocumentWithName( "First Document" )
                            .fromFile( "src/main/resources/document.pdf" )
                            .atIndex( 1 )
                            .withSignature( signatureFor(  props.getProperty("1.email") )
                                    .onPage( 0 )
                                    .atPosition( 100, 100 ) )
                            .withSignature( signatureFor(  props.getProperty("2.email") )
                                    .onPage( 0 )
                                    .atPosition( 100, 200 ) ) )
                    .build();

            PackageId packageId = eslClient.createPackage( documentPackage3 );

            eslClient.sendPackage( packageId );
        }
    }   
}
