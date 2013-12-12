package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.CeremonyLayoutSettingsBuilder.newCeremonyLayoutSettings;
import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder.newDocumentPackageSettings;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Basic package with in-person mode set at the document package level. Expires in a month.
 */
public class BrandingBarConfigurationExample extends SDKSample {

    private String email1;
    private InputStream documentInputStream;


    public static void main( String... args ) {
        new BrandingBarConfigurationExample(Props.get()).run();
    }

    public BrandingBarConfigurationExample( Properties props ) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ) );
    }

    public BrandingBarConfigurationExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "Policy " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .describedAs( "This is a package created using the e-SignLive SDK" )
                .withSettings( newDocumentPackageSettings()
                        .withoutOptOut()
                        .withoutDocumentToolbarDownloadButton()
                        .withCeremonyLayoutSettings( newCeremonyLayoutSettings()
                                .withoutGlobalNavigation()
                                .withoutGlobalDownloadButton()
                            ) )
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .withTitle( "Managing Director" )
                        .withCompany( "Acme Inc." ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream, DocumentType.PDF )
                        .withSignature( signatureFor( email1 )
                                .onPage( 0 )
                                .atPosition( 100, 100 ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );

        eslClient.sendPackage( packageId );
    }

    @Override
    void postExecute() {
        // Verify if the branding bar configuration was set up correctly.
        DocumentPackage documentPackage = eslClient.getPackage(packageId);

        assertThat( "Opt out button was not set correctly.", documentPackage.getSettings().getEnableOptOut(), is( false ) );
        assertThat( "Tool bar download button was not set correctly.", documentPackage.getSettings().getShowDocumentToolbarDownloadButton(), is( false ) );
        assertThat( "Global navigation button was not set correctly.", documentPackage.getSettings().getCeremonyLayoutSettings().getGlobalNavigation(), is( false ) );
        assertThat( "Global download button was not set correctly.", documentPackage.getSettings().getCeremonyLayoutSettings().getShowGlobalDownloadButton(), is( false ) );

    }
}