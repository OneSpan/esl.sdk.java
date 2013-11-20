package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.builder.AccountMemberBuilder;

import java.util.Properties;

public class InviteAccountMemberExample {
    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        eslClient.getAccountService().inviteUser(
                AccountMemberBuilder.newAccountMember(props.getProperty( "4.email" ) )
                        .withFirstName( "firstName" )
                        .withLastName( "lastName" )
                        .withCompany( "company" )
                        .withTitle( "title" )
                        .withLanguage( "language" )
                        .withPhoneNumber( "phoneNumber" )
                        .build() );
    }
}
