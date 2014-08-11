package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example of how to define the order in which the signers can participate in the signing ceremony
 */
public class SignerOrderingExample extends SDKSample {

    public final String email1;
    public static final int SIGNING_ORDER_FOR_EMAIL1 = 1;
    public final String email2;
    public static final int SIGNING_ORDER_FOR_EMAIL2 = 2;
    private InputStream documentInputStream1;
    public DocumentPackage initialOrder, afterReorder;

    public static void main( String... args ) {
        new SignerOrderingExample( Props.get() ).run();
    }

    public SignerOrderingExample( Properties properties ) {
        this( properties.getProperty( "api.key" ),
                properties.getProperty( "api.url" ),
                properties.getProperty( "1.email" ),
                properties.getProperty( "2.email" ) );
    }

    public SignerOrderingExample( String apiKey, String apiUrl, String email1, String email2 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        this.email2 = email2;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "SignerOrderingExample: " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
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
        afterReorder.getSigner(email1).setSigningOrder(2);
        afterReorder.getSigner(email2).setSigningOrder(1);
        eslClient.getPackageService().orderSigners(afterReorder);

        afterReorder = eslClient.getPackage(packageId);

        eslClient.sendPackage( packageId );
    }
}
