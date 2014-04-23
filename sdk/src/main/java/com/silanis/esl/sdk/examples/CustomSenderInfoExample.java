package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.AccountMemberBuilder;
import com.silanis.esl.sdk.builder.SenderInfoBuilder;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class CustomSenderInfoExample extends SDKSample {


    public String senderEmail;
    public String signerEmail;
    private InputStream documentInputStream1;

    public static final String SENDER_FIRST_NAME = "Rob";
    public static final String SENDER_SECOND_NAME = "Mason";
    public static final String SENDER_TITLE = "Chief Vizier";
    public static final String SENDER_COMPANY = "The Masons";

    public static void main( String... args ) {
        new CustomSenderInfoExample( Props.get() ).run();
    }

    public CustomSenderInfoExample( Properties props ) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ));
    }

    public CustomSenderInfoExample( String apiKey, String apiUrl, String signerEmail ) {
        super( apiKey, apiUrl );
        this.senderEmail = UUID.randomUUID().toString().replace( "-", "" ) + "@e-signlive.com";
        this.signerEmail = signerEmail;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        // Note on the custom sender information:
        //
        // The custom sender information is disregarded if the sender is one of the signers for the process.
        // The custom sender must already be a member of the account
        eslClient.getAccountService().inviteUser(
                AccountMemberBuilder.newAccountMember( senderEmail )
                        .withFirstName( "firstName" )
                        .withLastName( "lastName" )
                        .withCompany( "company" )
                        .withTitle( "title" )
                        .withLanguage( "language" )
                        .withPhoneNumber( "phoneNumber" )
                        .build() );

        DocumentPackage superDuperPackage = newPackageNamed( "CustomSenderInfoExample " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .withSenderInfo( SenderInfoBuilder.newSenderInfo( senderEmail )
                        .withName( SENDER_FIRST_NAME, SENDER_SECOND_NAME )
                        .withTitle( SENDER_TITLE )
                        .withCompany( SENDER_COMPANY ) )
                .withSigner( newSignerWithEmail( signerEmail )
                        .withFirstName( "Patty" )
                        .withLastName( "Galant" ) )
                .withDocument( newDocumentWithName( "Second Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature( signatureFor( senderEmail )
                                .onPage( 0 )
                                .atPosition( 100, 100 ) )
                        .withSignature( signatureFor( signerEmail )
                                .onPage( 0 )
                                .atPosition( 100, 200 )
                        )
                )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
    }
}
