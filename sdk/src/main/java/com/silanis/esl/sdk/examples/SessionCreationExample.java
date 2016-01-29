package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.SessionToken;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
/**
 * 
 * Create a session token based on the package ID and the signer's ID
 *
 */
public class SessionCreationExample extends SDKSample {

    private String signerId = "myCustomSignerId";

    public SessionToken signerSessionToken;

    public static void main( String... args ) {
        new SessionCreationExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .withCustomId( signerId ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature(signatureFor(email1)
                                .onPage( 0 )
                                .atPosition( 100, 100 ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        signerSessionToken = eslClient.createSessionToken( packageId, signerId );
        System.out.format("%s/access?sessionToken=%s%n", webpageUrl, signerSessionToken.getSessionToken());
    }
}
