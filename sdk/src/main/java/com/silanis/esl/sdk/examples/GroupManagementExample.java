package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.*;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.captureFor;

public class GroupManagementExample extends SDKSample {
    public String email1;
    public String email2;
    public String email3;
    public String email4;
    private InputStream documentInputStream1;

    public List<String> groupMemberEmailsAfterUpdate;

    public Group createdGroup1;
    public Group retrievedGroup1;
    public Group createdGroup2;
    public Group retrievedGroup2;
    public Group createdGroup3;
    public Group retrievedGroup3;
    public Group createdGroup3Updated;

    public static void main( String... args ) {
        new GroupManagementExample( Props.get() ).run();
    }

    public GroupManagementExample( Properties props ) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ));
    }

    public GroupManagementExample( String apiKey, String apiUrl) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    private void displayAccountGroupsAndMembers() {
        {
            List<Group> allGroups = eslClient.getGroupService().getMyGroups();
            for ( Group group : allGroups ) {
                System.out.println( group.getName() + " with email " + group.getEmail() + " and id " + group.getId() );
                List<GroupMember> allMembers = eslClient.getGroupService().getGroupMembers( group.getId() );
                for ( GroupMember member : allMembers ) {
                    System.out.println( member.getGroupMemberType().toString() + " " + member.getFirstName() + " " + member.getLastName() + " with email " + member.getEmail());
                }
            }
        }
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
        Group group1 = GroupBuilder.newGroup( UUID.randomUUID().toString() )
                .withId( new GroupId( UUID.randomUUID().toString() ) )
                .withMember( GroupMemberBuilder.newGroupMember( email1 )
                        .as( GroupMemberType.MANAGER ) )
                .withEmail( "bob@aol.com" )
                .withIndividualMemberEmailing()
                .build();
        createdGroup1 = eslClient.getGroupService().createGroup( group1 );
        retrievedGroup1 = eslClient.getGroupService().getGroup(createdGroup1.getId());

        // Inviting members with the two different calls
        // addMember returns the group member while inviteMember returns the group itself
        GroupMember groupMemberAdded = eslClient.getGroupService().addMember(createdGroup1.getId(), GroupMemberBuilder.newGroupMember(email3)
                .as(GroupMemberType.MANAGER)
                .build());
        Group groupMemberInvited = eslClient.getGroupService().inviteMember(createdGroup1.getId(), GroupMemberBuilder.newGroupMember(email4)
                .as(GroupMemberType.MANAGER)
                .build());

        Group group2 = GroupBuilder.newGroup( UUID.randomUUID().toString() )
                .withMember( GroupMemberBuilder.newGroupMember( email2 )
                        .as( GroupMemberType.MANAGER ) )
                .withEmail( "bob@aol.com" )
                .withIndividualMemberEmailing()
                .build();

        createdGroup2 = eslClient.getGroupService().createGroup( group2 );
        retrievedGroup2 = eslClient.getGroupService().getGroup(createdGroup2.getId());

        Group group3 = GroupBuilder.newGroup( UUID.randomUUID().toString() )
                .withMember( GroupMemberBuilder.newGroupMember( email3 )
                        .as( GroupMemberType.MANAGER ) )
                .withEmail( "bob@aol.com" )
                .withIndividualMemberEmailing()
                .build();

        createdGroup3 = eslClient.getGroupService().createGroup( group3 );
        retrievedGroup3 = eslClient.getGroupService().getGroup(createdGroup3.getId());

        // Here is how we delete group 2
        eslClient.getGroupService().deleteGroup( createdGroup2.getId() );

        // This shows up how to update group 3
        Group groupUpdated = GroupBuilder.newGroup( UUID.randomUUID().toString() )
                .withMember( GroupMemberBuilder.newGroupMember( email2 )
                        .as( GroupMemberType.MANAGER ) )
                .withMember( GroupMemberBuilder.newGroupMember( email3 )
                        .as( GroupMemberType.REGULAR ))
                .withMember( GroupMemberBuilder.newGroupMember( email4 )
                        .as( GroupMemberType.REGULAR ))
                .withEmail( "bob@aol.com" )
                .withIndividualMemberEmailing()
                .build();

        createdGroup3Updated = eslClient.getGroupService().updateGroup( groupUpdated , createdGroup3.getId() );

        groupMemberEmailsAfterUpdate = eslClient.getGroupService().getGroupMemberEmails( createdGroup3.getId() );

//        Now let's add a signature for a group member
        DocumentPackage superDuperPackage = newPackageNamed( "GroupManagementExample " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
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
    }
}
