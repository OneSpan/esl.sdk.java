package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * User: dave
 */
public class DeletePackageExample extends SDKSample {

    private String email1;
    private InputStream documentInputStream;

    public static void main( String... args ) {
        new DeletePackageExample( Props.get() ).run();
    }

    public DeletePackageExample( Properties props ) {
        this(props.getProperty( "api.key" ), props.getProperty( "api.url" ), props.getProperty( "1.email" ));
    }

    public DeletePackageExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "Policy " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .describedAs( "This  is a package created using the e-SignLive SDK" )
                .expiresAt( now().plusMonths( 1 ).toDate() )
                .withEmailMessage( "This message should be delivered to all signers" )
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .withTitle( "Managing Director" )
                        .withCompany( "Acme Inc." ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream, DocumentType.PDF )
                        .withSignature( signatureFor( email1 )
                                .onPage( 0 )
                                .atPosition( 100, 100 )
                                .withField( FieldBuilder.textField()
                                        .onPage( 0 )
                                        .atPosition( 400, 100 )
                                        .withSize( 200, 50 ) ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        DocumentPackage unsentPackage = eslClient.getPackage( packageId );
        eslClient.getPackageService().deletePackage( packageId );
    }
}
