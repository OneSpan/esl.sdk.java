package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AccountMember;
import com.silanis.esl.sdk.DelegationUser;
import com.silanis.esl.sdk.Sender;
import com.silanis.esl.sdk.builder.AccountMemberBuilder;
import com.silanis.esl.sdk.builder.DelegationUserBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by schoi on 3/23/15.
 */
public class DelegationExample extends SDKSample {
    public String ownerEmail, email1, email2, email3, email4, email5, email6, email7, email8, email9;

    public Sender retrievedOwner, retrievedSender1, retrievedSender2, retrievedSender3,
            retrievedSender4, retrievedSender5, retrievedSender6, retrievedSender7, retrievedSender8, retrievedSender9;
    public DelegationUser delegationUser1, delegationUser2, delegationUser3,
            delegationUser4, delegationUser5, delegationUser6, delegationUser7, delegationUser8, delegationUser9;;
    public List<DelegationUser> delegationUserListAfterAdding, delegationUserListAfterRemoving, delegationUserListAfterUpdating
            ,delegationUserListAfterClearing;

    public static void main( String... args ) {
        new DelegationExample(Props.get()).run();
    }

    public DelegationExample( Properties props ) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "sender.email" ));
    }

    public DelegationExample( String apiKey, String apiUrl, String ownerEmail ) {
        super( apiKey, apiUrl );
        this.ownerEmail = ownerEmail;
        this.email1 = getRandomEmail();
        this.email2 = getRandomEmail();
        this.email3 = getRandomEmail();
        this.email4 = getRandomEmail();
        this.email5 = getRandomEmail();
        this.email6 = getRandomEmail();
        this.email7 = getRandomEmail();
        this.email8 = getRandomEmail();
        this.email9 = getRandomEmail();
    }

    public void execute() {
        AccountMember ownerMember = getAccountMember(ownerEmail, "firstName", "lastName", "company", "title", "language", "phoneNumber");
        AccountMember accountMember1 = getAccountMember(email1, "firstName1", "lastName", "company1", "title1", "language1", "phoneNumber1");
        AccountMember accountMember2 = getAccountMember(email2, "firstName2", "lastName2", "company2", "title2", "language2", "phoneNumber2");
        AccountMember accountMember3 = getAccountMember(email3, "firstName3", "lastName3", "company3", "title3", "language3", "phoneNumber3");
        AccountMember accountMember4 = getAccountMember(email4, "firstName4", "lastName4", "company4", "title4", "language4", "phoneNumber4");
        AccountMember accountMember5 = getAccountMember(email5, "firstName5", "lastName5", "company5", "title5", "language5", "phoneNumber5");
        AccountMember accountMember6 = getAccountMember(email6, "firstName6", "lastName6", "company6", "title6", "language6", "phoneNumber6");
        AccountMember accountMember7 = getAccountMember(email7, "firstName7", "lastName7", "company7", "title7", "language7", "phoneNumber7");
        AccountMember accountMember8 = getAccountMember(email8, "firstName8", "lastName8", "company8", "title8", "language8", "phoneNumber8");
        AccountMember accountMember9 = getAccountMember(email9, "firstName9", "lastName9", "company9", "title9", "language9", "phoneNumber9");

        Sender createdOwnerMember = eslClient.getAccountService().inviteUser(ownerMember);
        Sender createdSender1 = eslClient.getAccountService().inviteUser(accountMember1);
        Sender createdSender2 = eslClient.getAccountService().inviteUser(accountMember2);
        Sender createdSender3 = eslClient.getAccountService().inviteUser(accountMember3);
        Sender createdSender4 = eslClient.getAccountService().inviteUser(accountMember4);
        Sender createdSender5 = eslClient.getAccountService().inviteUser(accountMember5);
        Sender createdSender6 = eslClient.getAccountService().inviteUser(accountMember6);
        Sender createdSender7 = eslClient.getAccountService().inviteUser(accountMember7);
        Sender createdSender8 = eslClient.getAccountService().inviteUser(accountMember8);
        Sender createdSender9 = eslClient.getAccountService().inviteUser(accountMember9);

        retrievedOwner = eslClient.getAccountService().getSender(createdOwnerMember.getId());
        retrievedSender1 = eslClient.getAccountService().getSender(createdSender1.getId());
        retrievedSender2 = eslClient.getAccountService().getSender(createdSender2.getId());
        retrievedSender3 = eslClient.getAccountService().getSender(createdSender3.getId());
        retrievedSender4 = eslClient.getAccountService().getSender(createdSender4.getId());
        retrievedSender5 = eslClient.getAccountService().getSender(createdSender5.getId());
        retrievedSender6 = eslClient.getAccountService().getSender(createdSender6.getId());
        retrievedSender7 = eslClient.getAccountService().getSender(createdSender7.getId());
        retrievedSender8 = eslClient.getAccountService().getSender(createdSender8.getId());
        retrievedSender9 = eslClient.getAccountService().getSender(createdSender9.getId());

        delegationUser1 = DelegationUserBuilder.newDelegationUser(retrievedSender1).build();
        delegationUser2 = DelegationUserBuilder.newDelegationUser(retrievedSender2).build();
        delegationUser3 = DelegationUserBuilder.newDelegationUser(retrievedSender3).build();
        delegationUser4 = DelegationUserBuilder.newDelegationUser(retrievedSender4).build();
        delegationUser5 = DelegationUserBuilder.newDelegationUser(retrievedSender5).build();
        delegationUser6 = DelegationUserBuilder.newDelegationUser(retrievedSender6).build();
        delegationUser7 = DelegationUserBuilder.newDelegationUser(retrievedSender7).build();
        delegationUser8 = DelegationUserBuilder.newDelegationUser(retrievedSender8).build();
        delegationUser9 = DelegationUserBuilder.newDelegationUser(retrievedSender9).build();

        eslClient.getAccountService().clearDelegates(createdOwnerMember.getId());

        eslClient.getAccountService().addDelegate(createdOwnerMember.getId(), delegationUser1);
        eslClient.getAccountService().addDelegate(createdOwnerMember.getId(), delegationUser2);
        eslClient.getAccountService().addDelegate(createdOwnerMember.getId(), delegationUser3);
        delegationUserListAfterAdding = eslClient.getAccountService().getDelegates(createdOwnerMember.getId());

        eslClient.getAccountService().removeDelegate(createdOwnerMember.getId(), delegationUser2.getId());
        delegationUserListAfterRemoving = eslClient.getAccountService().getDelegates(createdOwnerMember.getId());

        List<String> delegateIds = new ArrayList<String>();
        delegateIds.add(delegationUser4.getId());
        delegateIds.add(delegationUser5.getId());
        delegateIds.add(delegationUser6.getId());
        delegateIds.add(delegationUser7.getId());
        delegateIds.add(delegationUser8.getId());
        delegateIds.add(delegationUser9.getId());

        eslClient.getAccountService().updateDelegates(createdOwnerMember.getId(), delegateIds);
        delegationUserListAfterUpdating = eslClient.getAccountService().getDelegates(createdOwnerMember.getId());

        eslClient.getAccountService().clearDelegates(createdOwnerMember.getId());
        delegationUserListAfterClearing = eslClient.getAccountService().getDelegates(createdOwnerMember.getId());
    }

    private AccountMember getAccountMember(String email, String firstName, String lastName, String company, String title, String language, String phoneNumber) {
        return AccountMemberBuilder.newAccountMember(email)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withCompany(company)
                .withTitle(title)
                .withLanguage(language)
                .withPhoneNumber(phoneNumber)
                .build();
    }
}
