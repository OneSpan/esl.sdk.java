package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.builder.AccountMemberBuilder;
import com.silanis.esl.sdk.builder.SenderInfoBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static org.joda.time.DateMidnight.now;

public class CustomSenderInfoExample extends SDKSample {

    public String senderEmail;

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
                props.getProperty( "sender.email" ) );
    }

    public CustomSenderInfoExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.senderEmail = email1;
    }

    public void execute() {

        // Note on the custom sender information:
        //
        // The custom sender information is disregarded if the sender is one of the signers for the process.
        // The custom sender must already be a member of the account
        eslClient.getAccountService().inviteUser(
                AccountMemberBuilder.newAccountMember(senderEmail )
                        .withFirstName( "firstName" )
                        .withLastName( "lastName" )
                        .withCompany( "company" )
                        .withTitle( "title" )
                        .withLanguage( "language" )
                        .withPhoneNumber( "phoneNumber" )
                        .build() );

        DocumentPackage superDuperPackage = newPackageNamed( "CustomSenderInfoExample " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .withSenderInfo( SenderInfoBuilder.newSenderInfo(senderEmail)
                        .withName( SENDER_FIRST_NAME, SENDER_SECOND_NAME )
                        .withTitle( SENDER_TITLE )
                        .withCompany( SENDER_COMPANY ) )
                .describedAs( "This is a package created using the e-SignLive SDK" )
                .expiresAt( now().plusMonths( 1 ).toDate() )
                .withEmailMessage( "This message should be delivered to all signers" )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
    }
}
