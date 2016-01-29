package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example of how to define the order in which the signers can participate in the signing ceremony
 */
public class SignerOrderingExample extends SDKSample {

    public static final int SIGNING_ORDER_FOR_EMAIL1 = 2;
    public static final int SIGNING_ORDER_FOR_EMAIL2 = 1;
    public DocumentPackage initialOrder, afterReorder;

    public static void main( String... args ) {
        new SignerOrderingExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .signingOrder( SIGNING_ORDER_FOR_EMAIL1 ) )
                .withSigner( newSignerWithEmail( email2 )
                        .withFirstName( "Patty" )
                        .withLastName( "Galant" )
                        .signingOrder( SIGNING_ORDER_FOR_EMAIL2 ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature( signatureFor( email1 )
                                .onPage( 0 )
                                .atPosition( 500, 100 ) )
                        .withSignature( signatureFor( email2 )
                                .onPage( 0 )
                                .atPosition( 500, 500 ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );

        initialOrder = eslClient.getPackage(packageId);

        // Reorder signers
        afterReorder = eslClient.getPackage(packageId);
        afterReorder.getSigner(email1).setSigningOrder(1);
        afterReorder.getSigner(email2).setSigningOrder(2);
        eslClient.getPackageService().orderSigners(afterReorder);

        afterReorder = eslClient.getPackage(packageId);

        eslClient.sendPackage( packageId );
    }
}
