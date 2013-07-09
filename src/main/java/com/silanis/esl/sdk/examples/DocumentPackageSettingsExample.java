package com.silanis.esl.sdk.examples;

import com.silanis.awsng.web.rest.model.PackageSettings;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.LayoutOptionsBuilder.newLayoutOptions;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.PackageSettingsBuilder.newPackageSettings;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * User: dave
 */
public class DocumentPackageSettingsExample {
    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );


    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );
        DocumentPackage superDuperPackage = newPackageNamed( "DocumentPackageSettings " + format.format( new Date() ) )
                .withSettings( newPackageSettings()
                        .withLayoutOptions( newLayoutOptions()
                                .withoutProgressBar()
                                .withoutSessionBar()
                                .withoutTitle()
                                .withoutNavigator()
                                .withoutGlobalNavigation()
                                .withoutBreadCrumbs()

                                .withLogoLink( "sps" )
                                .withLogoSource( "sps" )

                        )
//                        .withHandOverLink( newLink( "http://www.google.ca" )
//                                .withText( "Return to blah" )
//                                .withTooltip( "Link Title" )
//                        )
                        .disableDecline()
                        .enableInPerson()
                        .disableOptOut()
//                        .withOptOutReason( "Reason One" )
//                        .withOptOutReason( "Reason Two" )
//                        .withOptOutReason( "Reason Three" )
                )
                .withSigner( newSignerWithEmail( props.getProperty( "1.email" ) )
                        .withFirstName( "John" )
                        .withLastName( "Smith" ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromFile( "src/main/resources/document.pdf" )
                        .withSignature( signatureFor( props.getProperty( "1.email" ) )
                                .onPage( 0 )
                                .atPosition( 100, 100 ) ) )
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        DocumentPackage aPackage = eslClient.getPackage( packageId );

        PackageSettings packageSettings = aPackage.getSettings();
        System.out.println( "AHA!" );
    }
}
