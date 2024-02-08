package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Group;
import com.silanis.esl.sdk.GroupId;
import com.silanis.esl.sdk.GroupMember;
import com.silanis.esl.sdk.GroupMemberType;
import com.silanis.esl.sdk.builder.AccountMemberBuilder;
import com.silanis.esl.sdk.builder.AddressBuilder;
import com.silanis.esl.sdk.builder.GroupBuilder;
import com.silanis.esl.sdk.builder.GroupMemberBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.util.List;
import java.util.UUID;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.captureFor;

public class GroupManagementExample extends SDKSample {
    public List<String> groupMemberEmailsAfterUpdate;

    public static final String GROUP_NAME_PREFIX = "GROUP_";
    public static final String EMAIL = "bob@aol.com";
    public final String UPDATED_EMAIL = "bob1@aol.com";

    public String updatedGroupName3;

    public Group createdGroup1;
    public Group retrievedGroup1;
    public Group createdGroup2;
    public Group retrievedGroup2;
    public Group createdGroup3;
    public Group retrievedGroup3;
    public Group createdGroup3Updated;

    public List<Group> retrievedGroupByName1;
    public List<Group> retrievedByNamePrefix;

    public static void main( String... args ) {
        new GroupManagementExample().run();
    }

    private void inviteAccountMember( String email ) {
        try {
            eslClient.getAccountService().inviteUser( AccountMemberBuilder.newAccountMember( email )
                    .withPhoneNumber( "1234567890" )
                    .withLanguage( "en" )
                    .withTitle( "title" )
                    .withCompany( "company" )
                    .withFirstName( "firstName" )
                    .withLastName( "lastName" )
                    .withAddress( AddressBuilder.newAddress()
                            .withAddress1( "address1" )
                            .withAddress2( "address2" )
                            .withZipCode( "zipcode" )
                            .withState( "state" )
                            .withCountry( "country" )
                            .withCity( "city" ) )
                    .build() );
        } catch (Exception e) {
            // We don't care about exceptions as they'll be on account of the sender already being in the system.
        }
    }

    public void execute() {

        email1 = getRandomEmail();
        email2 = getRandomEmail();
        email3 = getRandomEmail();
        email4 = getRandomEmail();

        // Since the user needs to already exist in the system, we invite all the emails we plan on using
        // The group members need to be account members, if they aren't already you may need to invite them to your account.
        inviteAccountMember( email1 );
        inviteAccountMember( email2 );
        inviteAccountMember( email3 );
        inviteAccountMember( email4 );

        // Let's create and manage some groups
        String groupName = UUID.randomUUID().toString();
        Group group1 = GroupBuilder.newGroup( GROUP_NAME_PREFIX + groupName )
                .withId( new GroupId( UUID.randomUUID().toString() ) )
                .withMember( GroupMemberBuilder.newGroupMember( email1 )
                        .as( GroupMemberType.MANAGER ) )
                .withEmail( EMAIL )
                .withIndividualMemberEmailing()
                .build();
        createdGroup1 = eslClient.getGroupService().createGroup( group1 );
        retrievedGroup1 = eslClient.getGroupService().getGroup(createdGroup1.getId());
        // Retrieve by group name
        retrievedGroupByName1 = eslClient.getGroupService().getMyGroups(groupName);

        groupName = UUID.randomUUID().toString();
        Group group2 = GroupBuilder.newGroup( GROUP_NAME_PREFIX + groupName )
                .withMember( GroupMemberBuilder.newGroupMember( email2 )
                        .as( GroupMemberType.MANAGER ) )
                .withEmail( EMAIL )
                .withIndividualMemberEmailing()
                .build();

        createdGroup2 = eslClient.getGroupService().createGroup( group2 );
        retrievedGroup2 = eslClient.getGroupService().getGroup(createdGroup2.getId());

        groupName = UUID.randomUUID().toString();
        Group group3 = GroupBuilder.newGroup( GROUP_NAME_PREFIX + groupName )
                .withMember( GroupMemberBuilder.newGroupMember( email3 )
                        .as( GroupMemberType.MANAGER ) )
                .withEmail( EMAIL )
                .withIndividualMemberEmailing()
                .build();

        createdGroup3 = eslClient.getGroupService().createGroup( group3 );
        retrievedGroup3 = eslClient.getGroupService().getGroup(createdGroup3.getId());
        retrievedByNamePrefix  = eslClient.getGroupService().getMyGroups(GROUP_NAME_PREFIX);

        // This shows up how to update group 3
        updatedGroupName3 = UUID.randomUUID().toString();
        Group groupUpdated = GroupBuilder.newGroup(updatedGroupName3)
                .withEmail( UPDATED_EMAIL )
                .withIndividualMemberEmailing()
                .build();

        // Inviting members with the two different calls
        // addMember returns the group member while inviteMember returns the group itself
        GroupMember groupMemberAdded = eslClient.getGroupService().addMember(createdGroup3.getId(), GroupMemberBuilder.newGroupMember(email3)
                .as(GroupMemberType.MANAGER)
                .build());
        Group groupMemberInvited = eslClient.getGroupService().inviteMember(createdGroup3.getId(), GroupMemberBuilder.newGroupMember(email4)
                .as(GroupMemberType.MANAGER)
                .build());

        createdGroup3Updated = eslClient.getGroupService().updateGroup( groupUpdated , createdGroup3.getId() );

        groupMemberEmailsAfterUpdate = eslClient.getGroupService().getGroupMemberEmails( createdGroup3.getId() );

//        Now let's add a signature for a group member
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withSigner( SignerBuilder.newSignerFromGroup( createdGroup1.getId() )
                        .canChangeSigner()
                        .deliverSignedDocumentsByEmail() )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature( captureFor( createdGroup1.getId() )
                                .onPage( 0 )
                                .atPosition( 100, 100 ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );

        eslClient.getPackageService().notifySigner( packageId, createdGroup1.getId() );

        DocumentPackage result = eslClient.getPackage( packageId );
        eslClient.changePackageStatusToDraft(packageId);

        // Here is how to delete groups
        eslClient.getGroupService().deleteGroup(createdGroup1.getId());
        eslClient.getGroupService().deleteGroup(createdGroup2.getId());
        eslClient.getGroupService().deleteGroup(createdGroup3.getId());
    }
}
