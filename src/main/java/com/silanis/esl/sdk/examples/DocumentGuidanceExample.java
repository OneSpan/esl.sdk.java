package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Just a simple package that lets us test whether a signer, upon clicking on their invitation to sign link, is brought to the first package they can participate in.
 */
public class DocumentGuidanceExample {

    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage superDuperPackage = newPackageNamed( "Policy " + format.format( new Date() ) )
                .describedAs( "This is a package created using the e-SignLive SDK" )
                .withEmailMessage( "This message should be delivered to all signers" )
                .withSigner( newSignerWithEmail( props.getProperty("1.email") )
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .withTitle( "Managing Director" )
                        .withCompany( "Acme Inc." ) )
                .withSigner( newSignerWithEmail( props.getProperty("2.email") )
                        .withFirstName( "Patty" )
                        .withLastName( "Galant" ) )
                .withDocument( newDocumentWithName( props.getProperty("1.email") )
                        .fromFile( "src/main/resources/document.pdf" )
                        .withSignature( signatureFor(props.getProperty("1.email"))
                                .onPage( 0 )
                                .atPosition( 100, 100 )))
                .withDocument( newDocumentWithName( props.getProperty("2.email") )
                        .fromFile( "src/main/resources/document.pdf" )
                        .withSignature( signatureFor(props.getProperty("2.email"))
                                .onPage( 0 )
                                .atPosition( 100, 200 ) ) )
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );

        eslClient.sendPackage( packageId );
    }
}
