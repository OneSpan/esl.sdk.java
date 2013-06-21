package com.silanis.esl.sdk.examples.guide;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.FieldBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;

import java.util.List;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.newField;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class FieldsAndSignatures {
    private static class SignaturesSampleA {
        public static void main( String ... args ) {
            SignatureBuilder.signatureFor( "john.smith@acme.com" )
                    .onPage( 0 )
                    .atPosition( 100, 100 );
        }
    }

    private static class SignaturesSampleB {
        public static void main( String ... args ) {
            SignatureBuilder.signatureFor( "john.smith@acme.com" )
                    .withStyle( SignatureStyle.HAND_DRAWN )
                    .onPage( 0 )
                    .atPosition( 100, 100 )
                    .withSize( 300, 75 );
        }
    }

    private static class FieldsSampleA {
        public static void main( String ... args ) {
            FieldBuilder.newField()
                    .onPage( 2 )
                    .atPosition( 100, 500 );
        }
    }

    private static class FieldsSampleB {
        public static void main( String ... args ) {
            FieldBuilder.newField()
                    .onPage( 2 )
                    .atPosition( 100, 500 )
                    .withStyle( FieldStyle.BOUND_DATE )
                    .withSize( 400, 50 );
        }
    }

    private static class FieldsSampleC {
        // TODO: Not yet written!
    }

    private static class RequiredValueSampleA {
        public static void main( String ... args ) {
            // TODO: .isRequired() needs implementing
            FieldBuilder.newField()
                    //.isRequired()
                    .onPage( 2 )
                    .atPosition( 100, 500 );
        }
    }

    private static class FullSampleA {
        public static final String API_KEY = "API KEY";
        public static final String API_URL = "https://stage-api.e-signlive.com:8080";

        public static void main( String... args ) {
            EslClient eslClient = new EslClient( API_KEY, API_URL );

            DocumentPackage documentPackage = newPackageNamed( "Sample Insurance policy" )
                    .withSigner( newSignerWithEmail( "john.smith@email.com" )
                            .withFirstName( "John" )
                            .withLastName( "Smith" ) )
                    .withSigner( newSignerWithEmail( "patty.galant@email.com" )
                            .withFirstName( "Patty" )
                            .withLastName( "Galant" ) )
                    .withDocument( newDocumentWithName( "First Document" )
                            .fromFile( "src/main/resources/document.pdf" )
                            .withSignature( signatureFor( "john.smith@email.com" )
                                    .onPage( 0 )
                                    .atPosition( 500, 100 )
                                    .withSize( 200, 50 )
                                    .withField( newField()
                                            .onPage( 1 )
                                            .atPosition( 100, 500 )
                                            .withStyle( FieldStyle.BOUND_DATE )
                                            .withSize( 200, 50 ) ) )
                            .withSignature( signatureFor( "john.smith@email.com" )
                                    .onPage( 0 )
                                    .atPosition( 500, 300 )
                                    .withStyle( SignatureStyle.INITIALS )
                                    .withSize( 200, 50 )
                                    .withField( newField()
                                            .onPage( 1 )
                                            .atPosition( 200, 500 )
                                            .withStyle( FieldStyle.UNBOUND_TEXT_FIELD )
                                            .withSize( 200, 50 ) ) ) )
                    .build();

            PackageId packageId = eslClient.createPackage( documentPackage );
        }
    }

    private static class RetrievingValuesSampleA {
        public static final String API_KEY = "YOUR API KEY";
        public static final String API_URL = "https://stage-api.e-signlive.com:8080";
        public static final String PACKAGE_ID = "YOUR PACKAGE ID";

        public static void main( String... args ) {
            EslClient eslClient = new EslClient( API_KEY, API_URL );
            List<FieldSummary> fieldSummaries = eslClient.getFieldValues( new PackageId( PACKAGE_ID ) );

            System.out.println( "SignerId, DocumentId, FieldId: Value" );
            for ( FieldSummary fieldSummary : fieldSummaries ) {
                System.out.print( fieldSummary.getSignerId() + ", " + fieldSummary.getDocumentId() + ", " +
                        fieldSummary.getFieldId() + ": " + fieldSummary.getFieldValue() );
            }
        }
    }
}
