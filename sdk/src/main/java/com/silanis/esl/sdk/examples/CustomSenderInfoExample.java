package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.builder.SenderInfoBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

public class CustomSenderInfoExample extends SDKSample {

    public static String email1;
    public static String email2;
    private InputStream documentInputStream1;
    public static final String senderFirstName = "Rob";
    public static final String senderSecondName = "Mason";
    public static final String senderTitle = "Chief Vizierj";
    public static final String senderCompany = "The Masons";

    public static void main( String... args ) {
        new CustomSenderInfoExample( Props.get() ).run();
    }

    public CustomSenderInfoExample( Properties props ) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ),
                props.getProperty( "2.email" ) );
    }

    public CustomSenderInfoExample( String apiKey, String apiUrl, String email1, String email2 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        this.email2 = email2;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {

        // Note on the custom sender information:
        //
        // The custom sender information is disregarded if the sender is one of the signers for the process.


        DocumentPackage superDuperPackage = newPackageNamed( "CustomSenderInfoExample " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .withSenderInfo( SenderInfoBuilder.newSenderInfo(email1)
                        .withName( senderFirstName, senderSecondName )
                        .withTitle( senderTitle )
                        .withCompany( senderCompany ) )
                .describedAs( "This is a package created using the e-SignLive SDK" )
                .expiresAt( now().plusMonths( 1 ).toDate() )
                .withEmailMessage( "This message should be delivered to all signers" )
                .withSigner( newSignerWithEmail( email2 )
                        .withFirstName( "John" )
                        .withLastName( "Smith" ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature( signatureFor( email2 )
                                .onPage( 0 )
                                .atPosition( 100, 100 ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );

        DocumentPackage finalPackage = eslClient.getPackage( packageId );
    }
}
