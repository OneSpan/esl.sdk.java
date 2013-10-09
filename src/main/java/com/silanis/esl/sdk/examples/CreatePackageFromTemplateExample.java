package com.silanis.esl.sdk.examples;

import com.silanis.awsng.web.rest.model.Role;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import java.util.Properties;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

public class CreatePackageFromTemplateExample {
    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );
        DocumentPackage superDuperPackage = newPackageNamed( "Create Package Template Example" )
                .describedAs( "This is a package created from a template using the e-SignLive SDK" )
                .expiresAt( now().plusMonths( 1 ).toDate() )
                .withEmailMessage( "This message should be delivered to all signers" )
                .withSigner( newSignerWithEmail( props.getProperty( "1.email" ) )
                        .withCustomId( "Client1" )
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .withTitle( "Managing Director" )
                        .withCompany( "Acme Inc." ) )
                .withSigner( newSignerWithEmail( props.getProperty( "2.email" ) )
                        .withFirstName( "Patty" )
                        .withLastName( "Galant" ) )
                .build();

        PackageId packageId = eslClient.createPackageFromTemplate( superDuperPackage, "TestTemplate" );
        eslClient.sendPackage( packageId );
    }
}
