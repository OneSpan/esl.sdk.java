package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example of how to configure the SMS authentication method for a signer
 */

public class SignerSMSAuthenticationExample extends SDKSample {

    public final String email1;
    public final String sms1;
    private InputStream documentInputStream1;

    public static void main( String... args ) {
        new SignerSMSAuthenticationExample( Props.get() ).run();
    }

    public SignerSMSAuthenticationExample( Properties properties ) {
        this( properties.getProperty( "api.key" ),
                properties.getProperty( "api.url" ),
                properties.getProperty( "1.email" ),
                properties.getProperty( "1.sms" ) );
    }

    public SignerSMSAuthenticationExample( String apiKey, String apiUrl, String email1, String sms1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        this.sms1 = sms1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "SignerSMSAuthenticationExample: " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .describedAs( "This is a SMS authentication example" )
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .withSmsSentTo( sms1 ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature( signatureFor( email1 )
                                .onPage( 0 )
                                .atPosition( 100, 100 ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
    }
}