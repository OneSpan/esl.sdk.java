package com.silanis.esl.sdk.examples.guide;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Signer;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.ChallengeBuilder.firstQuestion;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateTime.now;

public class SignerAuthentication {
    private static class Email {
        public static void main( String... args ) {
            Signer signer = newSignerWithEmail( "bob@email.com" )
                    .withFirstName( "Bob" )
                    .withLastName( "Smith" )
                    .build();
        }
    }

    private static class QuestionAndAnswer {
        public static void main( String... args ) {
            Signer signer = newSignerWithEmail( "bob@email.com" )
                    .withFirstName( "Bob" )
                    .withLastName( "Smith" )
                    .challengedWithQuestions( firstQuestion( "What's your favorite sport?" )
                            .answer( "golf" )
                            .secondQuestion( "What musical instrument do you play?" )
                            .answer( "drums" ) )
                    .build();
        }
    }

    private static class SMS {
        public static void main( String... args ) {
            Signer signer = newSignerWithEmail( "bob@email.com" )
                    .withFirstName( "Bob" )
                    .withLastName( "Smith" )
                    .withSmsSentTo( "1112223333" )
                    .build();
        }
    }

    private static class Sample {
        public static final String API_KEY = "API KEY";
        public static final String API_URL = "https://stage-api.e-signlive.com:8080";

        public static void main( String... args ) {
            EslClient eslClient = new EslClient( API_KEY, API_URL );
            DocumentPackage documentPackage = newPackageNamed( "Authentication Methods Package" )
                    .describedAs( "This is a package created using the e-SignLive SDK to demonstrate the authentication methods." )
                    .expiresAt( now().plusMonths( 1 ).toDate() )
                    .withEmailMessage( "This message should be delivered to all signers" )
                    .withSigner( newSignerWithEmail( "anna@email.com" )
                            .withFirstName( "Anna" )
                            .withLastName( "Bel" ) )
                    .withSigner( newSignerWithEmail( "bobby@email.com" )
                            .withFirstName( "Bobby" )
                            .withLastName( "Sue" )
                            .challengedWithQuestions( firstQuestion( "What's your favorite sport?" )
                                    .answer( "golf" )
                                    .secondQuestion( "What music instrument do you play?" )
                                    .answer( "drums" ) ) )
                    .withSigner( newSignerWithEmail( "charlie@email.com" )
                            .withFirstName( "Charlie" )
                            .withLastName( "Brown" )
                            .withSmsSentTo( "4508002987" ) )
                    .withDocument( newDocumentWithName( "dave.silanis@gmail.com's Document" )
                            .fromFile( "src/main/resources/document.pdf" )
                            .withSignature( signatureFor( "anna@email.com" )
                                    .onPage( 0 )
                                    .atPosition( 100, 100 ) )
                            .withSignature( signatureFor( "bobby@email.com" )
                                    .onPage( 0 )
                                    .atPosition( 100, 200 ) )
                            .withSignature( signatureFor( "charlie@email.com" )
                                    .onPage( 0 )
                                    .atPosition( 100, 300 ) ) )
                    .build();

            PackageId packageId = eslClient.createPackage( documentPackage );
        }
    }
}
