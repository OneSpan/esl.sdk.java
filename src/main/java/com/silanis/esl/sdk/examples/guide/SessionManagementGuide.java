package com.silanis.esl.sdk.examples.guide;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.SessionToken;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class SessionManagementGuide {
    private static class SessionCreation {
        public static final String API_KEY = "API KEY";
        public static final String API_URL = "https://stage-api.e-signlive.com:8080";

        public static void main( String... args ) {
            EslClient eslClient = new EslClient( API_KEY, API_URL );
            String signerId = "myCustomSignerId";

            DocumentPackage documentPackage = newPackageNamed( "Sample Insurance policy" )
                    .withSigner( newSignerWithEmail( "john.smith@email.com" )
                            .withFirstName( "John" )
                            .withLastName( "Smith" )
                            .withCustomId( signerId ) )
                    .withDocument( newDocumentWithName( "First Document" )
                            .fromFile( "src/main/resources/document.pdf" )
                            .withSignature( signatureFor( "john.smith@email.com" )
                                    .onPage( 0 )
                                    .atPosition( 500, 100 )
                                    .withSize( 200, 50 ) ) )
                    .build();

            PackageId packageId = eslClient.createPackage( documentPackage );
            SessionToken sessionToken = eslClient.createSessionToken( packageId, signerId );
        }

    }
}
