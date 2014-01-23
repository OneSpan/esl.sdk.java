package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.SessionToken;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

public class DesignerExample extends SDKSample {

    private String email1;
    private String email2;
    private InputStream documentInputStream1;

    public DesignerExample( Properties props ) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ),
                props.getProperty( "2.email" ) );
    }

    public DesignerExample( String apiKey, String apiUrl, String email1, String email2 ) {
        super( apiKey, apiUrl );
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
        this.email1 = email1;
        this.email2 = email2;
    }

    @Override
    void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "Designer Package " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .describedAs( "This is a package created using the e-SignLive SDK" )
                .withSigner( newSignerWithEmail( email1 )
                        .withCustomId( "Client1" )
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .withTitle( "Managing Director" )
                        .withCompany( "Acme Inc." ) )
                .withSigner( newSignerWithEmail( email2 )
                        .withFirstName( "Patty" )
                        .withLastName( "Galant" ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );

        SessionToken sessionToken = eslClient.createSenderSessionToken();
    }
}
