package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Page;
import com.silanis.esl.sdk.PageRequest;
import com.silanis.esl.sdk.builder.PackageBuilder;

import java.io.IOException;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Basic package with in-person mode set at the document package level. Expires in a month.
 */
public class CreatePackageFromTemplateExample {

    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    public static void main( String... args ) throws IOException {
        EslClient esl = new EslClient( API_KEY, API_URL );
        Page<DocumentPackage> templates = esl.getPackageService().getTemplates( new PageRequest( 0 ) );

        if ( templates.getTotalElements() == 0 ) {
            System.out.println( "No templates found" );
            return;
        }

        DocumentPackage template = templates.iterator().next();
        DocumentPackage packageData = PackageBuilder.newPackageNamed( "Package from template" )
                .withSigner( newSignerWithEmail( props.getProperty( "1.email" ) )
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .withTitle( "Manager" )
                        .withCompany( "Acme Inc." )
                        .withCustomId( "Signer1" ) )
                .withSigner( newSignerWithEmail( props.getProperty( "2.email" ) )
                        .withFirstName( "Elvis" )
                        .withLastName( "Presley" )
                        .withTitle( "The King" )
                        .withCompany( "Elvis Presley International" )
                        .withCustomId( "Signer2" ) )
                .build();

        PackageId id = esl.createPackageFromTemplate( packageData, template.getId() );

        esl.sendPackage( id );
    }
}
