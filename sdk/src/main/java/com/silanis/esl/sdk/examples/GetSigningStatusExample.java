package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.SigningStatus;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Retrieves the signing status of a package before and after sending it
 */
public class GetSigningStatusExample extends SDKSample {

    public SigningStatus draftSigningStatus, sentSigningStatus, trashedSigningStatus;

    public static void main( String... args ) {
        new GetSigningStatusExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs( "This is a package created using the eSignLive SDK" )
                .expiresAt( now().plusMonths( 1 ).toDate() )
                .withEmailMessage( "This message should be delivered to all signers" )
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .withTitle( "Managing Director" )
                        .withCompany( "Acme Inc." ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature( signatureFor( email1 )
                                .onPage( 0 )
                                .atPosition( 100, 100 ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        draftSigningStatus = eslClient.getSigningStatus( packageId, null, null );

        eslClient.sendPackage(packageId);
        sentSigningStatus = eslClient.getSigningStatus( packageId, null, null );

        eslClient.getPackageService().trash(packageId);
        trashedSigningStatus = eslClient.getSigningStatus( packageId, null, null );
    }
}
