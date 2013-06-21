package com.silanis.esl.sdk.examples.guide;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class SigningWorkflow {
    private static class SignerWorkflow {
        public static final String API_KEY = "<YOUR API KEY HERE>";
        public static final String API_URL = "https://stage-api.e-signlive.com";

        private static final String DATA_FORMAT = "EEE, d MMM yyyy HH:mm:ss Z";

        public static void main( String[] args ) {
            SimpleDateFormat format = new SimpleDateFormat( DATA_FORMAT );
            EslClient eslClient = new EslClient( API_KEY, API_URL );
            // Build the DocumentPackage object
            DocumentPackage documentPackage = newPackageNamed( "Policy " + format.format( new Date() ) )
                    .withSigner( newSignerWithEmail( "signer1@xyz.com" )
                            .withFirstName( "FirstNameSigner1" )
                            .withLastName( "LastNameSigner1" )
                            .signingOrder( 1 ) )
                    .withSigner( newSignerWithEmail( "signer2@xyz.com" )
                            .withFirstName( "FirstNameSigner2" )
                            .withLastName( "LastNameSigner2" )
                            .signingOrder( 2 ) )
                    .withSigner( newSignerWithEmail( "signer3@xyz.com" )
                            .withFirstName( "FirstNameSigner3" )
                            .withLastName( "LastNameSigner3" )
                            .signingOrder( 2 ) )
                    .withSigner( newSignerWithEmail( "signer4@xyz.com" )
                            .withFirstName( "FirstNameSigner4" )
                            .withLastName( "LastNameSigner4" )
                            .signingOrder( 3 ) )
                    .withDocument( newDocumentWithName( "First Document" )
                            .fromFile( "src/main/resources/document.pdf" )
                            .withSignature( signatureFor( "signer1@xyz.com" )
                                    .onPage( 0 )
                                    .atPosition( 100, 200 ) )
                            .withSignature( signatureFor( "signer2@xyz.com" )
                                    .onPage( 0 )
                                    .atPosition( 100, 400 ) )
                            .withSignature( signatureFor( "signer3@xyz.com" )
                                    .onPage( 0 )
                                    .atPosition( 100, 600 ) )
                            .withSignature( signatureFor( "signer4@xyz.com" )
                                    .onPage( 0 )
                                    .atPosition( 100, 800 ) ) )
                    .build();

            // Issue the request to the ESL server to create the DocumentPackage
            PackageId packageId = eslClient.createPackage( documentPackage );

            // Send the package to be signed by the participants
            eslClient.sendPackage( packageId );
        }
    }

    private static class DocumentWorkflow {
        public static final String API_KEY = "<YOUR API KEY>";
        public static final String API_URL = "https://stage-api.e-signlive.com:8080";

        private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );

        public static void main( String... args ) {
            EslClient eslClient = new EslClient( API_KEY, API_URL );

            DocumentPackage superDuperPackage = newPackageNamed( "Policy " + format.format( new Date() ) )
                    .describedAs( "This is a package with a document workflow created using the e-SignLive SDK" )
                    .withSigner( newSignerWithEmail( "john@email.com" )
                            .withFirstName( "John" )
                            .withLastName( "Smith" ) )
                    .withDocument( newDocumentWithName( "Second Document" )
                            .fromFile( "src/main/resources/document.pdf" )
                            .atIndex( 2 )
                            .withSignature( signatureFor( "john@email.com" )
                                    .onPage( 0 )
                                    .atPosition( 100, 100 ) ) )
                    .withDocument( newDocumentWithName( "First Document" )
                            .fromFile( "src/main/resources/document.pdf" )
                            .atIndex( 1 )
                            .withSignature( signatureFor( "john@email.com" )
                                    .onPage( 0 )
                                    .atPosition( 100, 100 ) ) )
                    .build();

            PackageId packageId = eslClient.createPackage( superDuperPackage );

            eslClient.sendPackage( packageId );
        }
    }

    private static class CombinedWorkflow {
        public static final String API_KEY = "<YOUR API KEY>";
        public static final String API_URL = "https://stage-api.e-signlive.com:8080";

        private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );

        public static void main( String... args ) {
            EslClient eslClient = new EslClient( API_KEY, API_URL );

            DocumentPackage superDuperPackage = newPackageNamed( "Policy " + format.format( new Date() ) )
                    .describedAs( "This is a package with a document workflow created using the e-SignLive SDK" )
                    .withSigner( newSignerWithEmail( "signer1@xyz.com" )
                            .withFirstName( "FirstNameSigner1" )
                            .withLastName( "LastNameSigner1" )
                            .signingOrder( 1 ) )
                    .withSigner( newSignerWithEmail( "signer2@xyz.com" )
                            .withFirstName( "FirstNameSigner2" )
                            .withLastName( "LastNameSigner2" )
                            .signingOrder( 2 ) )
                    .withDocument( newDocumentWithName( "Second Document" )
                            .fromFile( "src/main/resources/document.pdf" )
                            .atIndex( 2 )
                            .withSignature( signatureFor( "signer1@xyz.com" )
                                    .onPage( 0 )
                                    .atPosition( 100, 100 ) )
                            .withSignature( signatureFor( "signer2@xyz.com" )
                                    .onPage( 0 )
                                    .atPosition( 100, 200 ) ) )
                    .withDocument( newDocumentWithName( "First Document" )
                            .fromFile( "src/main/resources/document.pdf" )
                            .atIndex( 1 )
                            .withSignature( signatureFor( "signer1@xyz.com" )
                                    .onPage( 0 )
                                    .atPosition( 100, 100 ) )
                            .withSignature( signatureFor( "signer2@xyz.com" )
                                    .onPage( 0 )
                                    .atPosition( 100, 200 ) ) )
                    .build();

            PackageId packageId = eslClient.createPackage( superDuperPackage );

            eslClient.sendPackage( packageId );
        }
    }
}
