package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.GroupBuilder;
import com.silanis.esl.sdk.builder.GroupMemberBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;

public class GroupManagementExample extends SDKSample {
    private String email1;
    private String email2;
    private String email3;
    private String email4;
    private InputStream documentInputStream1;

    public static void main( String... args ) {
        new GroupManagementExample( Props.get() ).run();
    }

    public GroupManagementExample( Properties props ) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ),
                props.getProperty( "2.email" ),
                props.getProperty( "3.email" ),
                props.getProperty( "4.email" ));
    }

    public GroupManagementExample( String apiKey, String apiUrl, String email1, String email2, String email3, String email4 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        this.email4 = email4;
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

    public void execute() {

        // Let's create and manage some groups
        Group group1 = GroupBuilder.newGroup( UUID.randomUUID().toString() )
                .withId( new GroupId( UUID.randomUUID().toString() ) )
                .withMember( GroupMemberBuilder.newGroupMember( email1 )
                        .as( GroupMemberType.MANAGER ) )
                .withEmail( "bob@aol.com" )
                .withIndividualMemberEmailing()
                .build();
        Group createdGroup1 = eslClient.getGroupService().createGroup( group1 );
        System.out.println( "GroupId: " + createdGroup1.getId().toString() );

        eslClient.getGroupService().inviteMember( createdGroup1.getId(), GroupMemberBuilder.newGroupMember( email3 )
                .as( GroupMemberType.MANAGER )
                .build() );
        eslClient.getGroupService().inviteMember( createdGroup1.getId(), GroupMemberBuilder.newGroupMember( email4 )
                .as( GroupMemberType.MANAGER )
                .build() );
        Group retrievedGroup1 = eslClient.getGroupService().getGroup( createdGroup1.getId() );

        Group group2 = GroupBuilder.newGroup( UUID.randomUUID().toString() )
                .withMember( GroupMemberBuilder.newGroupMember( email2 )
                        .as( GroupMemberType.MANAGER ) )
                .withEmail( "bob@aol.com" )
                .withIndividualMemberEmailing()
                .build();
        Group createdGroup2 = eslClient.getGroupService().createGroup( group2 );
        Group retrievedGroup2 = eslClient.getGroupService().getGroup( createdGroup2.getId() );

        List<Group> allGroupsBeforeDelete = eslClient.getGroupService().getMyGroups();
        List<GroupMember> groupMembersBeforeDelete = eslClient.getGroupService().getGroupMembers( createdGroup1.getId() );
        List<String> groupMemberEmailsBeforeDelete = eslClient.getGroupService().getGroupMemberEmails( createdGroup1.getId() );

        eslClient.getGroupService().deleteGroup( createdGroup2.getId() );

        List<Group> allGroupsAfterDelete = eslClient.getGroupService().getMyGroups();


//        Now let's add a signature for a group member
        DocumentPackage superDuperPackage = newPackageNamed( "GroupManagementExample " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .withSigner( SignerBuilder.newSignerFromGroup( createdGroup1.getId() )
                        .canChangeSigner()
                        .deliverSignedDocumentsByEmail() )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature( signatureFor( createdGroup1.getId() )
                                .onPage( 0 )
                                .atPosition( 100, 100 ) ) )
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );

        DocumentPackage result = eslClient.getPackage( packageId );
    }
}
