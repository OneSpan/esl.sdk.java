package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.AccountMemberBuilder;
import com.silanis.esl.sdk.builder.SenderInfoBuilder;

import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by chi-wing on 6/6/14.
 */
public class SenderManipulationExample extends SDKSample {
    public final String email1;
    public final String email2;
    public final String email3;

    public AccountMember accountMember1;
    public AccountMember accountMember2;
    public AccountMember accountMember3;
    public SenderInfo updatedSenderInfo;
    Sender retrievedSender1, retrievedSender2, retrievedSender3;
    Sender retrievedUpdatedSender3;

    public static void main( String... args ) {
        new SenderManipulationExample( Props.get() ).run();
    }

    public SenderManipulationExample(Properties properties){
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"),
                properties.getProperty("1.email"),
                properties.getProperty("2.email"),
                properties.getProperty("3.email"));
    }

    public SenderManipulationExample(String apiKey, String apiUrl, String email1, String email2, String email3) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        this.email2 = UUID.randomUUID().toString().replace("-","") + "@e-signlive.com";
        this.email3 = email3;
    }

    @Override
    void execute() {
        accountMember1 = AccountMemberBuilder.newAccountMember(email1)
                    .withFirstName( "firstName1" )
                    .withLastName( "lastName1" )
                    .withCompany( "company1" )
                    .withTitle( "title1" )
                    .withLanguage( "language1" )
                    .withPhoneNumber( "phoneNumber1" )
                    .build();

        accountMember2 = AccountMemberBuilder.newAccountMember(email2)
                .withFirstName( "firstName2" )
                .withLastName( "lastName2" )
                .withCompany( "company2" )
                .withTitle( "title2" )
                .withLanguage( "language2" )
                .withPhoneNumber( "phoneNumber2" )
                .build();

        accountMember3 = AccountMemberBuilder.newAccountMember(email3)
                .withFirstName( "firstName3" )
                .withLastName( "lastName3" )
                .withCompany( "company3" )
                .withTitle( "title3" )
                .withLanguage("language3")
                .withPhoneNumber( "phoneNumber3" )
                .build();

        Sender createdSender1 = eslClient.getAccountService().inviteUser(accountMember1);
        Sender createdSender2 = eslClient.getAccountService().inviteUser(accountMember2);
        Sender createdSender3 = eslClient.getAccountService().inviteUser(accountMember3);

        retrievedSender1 = eslClient.getAccountService().getSender(createdSender1.getId());
        retrievedSender2 = eslClient.getAccountService().getSender(createdSender2.getId());
        retrievedSender3 = eslClient.getAccountService().getSender(createdSender3.getId());

        eslClient.getAccountService().sendInvite(createdSender2.getId());

        eslClient.getAccountService().deleteSender(createdSender2.getId());

        updatedSenderInfo = SenderInfoBuilder.newSenderInfo(email3)
                .withName( "updatedFirstName", "updatedLastName" )
                .withTitle( "updatedTitle" )
                .withCompany("updatedCompany")
                .build();

        eslClient.getAccountService().updateSender(updatedSenderInfo, createdSender3.getId());
        retrievedUpdatedSender3 = eslClient.getAccountService().getSender(createdSender3.getId());

        // Get senders in account
        Map<String, Sender> senders = eslClient.getAccountService().getSenders(Direction.ASCENDING, new PageRequest(1, 100));
    }
}
