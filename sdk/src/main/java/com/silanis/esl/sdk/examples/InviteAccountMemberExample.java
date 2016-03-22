package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.builder.AccountMemberBuilder;

public class InviteAccountMemberExample extends SDKSample {

    public static void main( String... args ) {
        new InviteAccountMemberExample().run();
    }

    public void execute() {
        eslClient.getAccountService().inviteUser(
                AccountMemberBuilder.newAccountMember(email1)
                                    .withFirstName( "firstName" )
                                    .withLastName( "lastName" )
                                    .withCompany( "company" )
                                    .withTitle( "title" )
                                    .withLanguage( "language" )
                                    .withPhoneNumber( "phoneNumber" )
                                    .build() );
    }
}
