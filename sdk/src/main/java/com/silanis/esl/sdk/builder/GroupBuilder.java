package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Group;
import com.silanis.esl.sdk.GroupId;
import com.silanis.esl.sdk.GroupMember;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * Helper class to help define groups of senders for a given e-SignLive account.
 *
 */
public class GroupBuilder {
    private GroupId id;
    private String name;
    private String email;
    private Date created;
    private Date updated;
    private Boolean emailMembers;
    private List<GroupMember> members;

    private GroupBuilder(String name) {
        this.name = name;
        members = new ArrayList<GroupMember>();
    }

    /**
     * Instanciate a new Group of senders
     * @param name name of the new group @size(max="64")
     * @return
     */
    public static GroupBuilder newGroup( String name ) {
        return new GroupBuilder( name );
    }

    /**
     * Set this group email's address.
     * 
     * @param email @size(min="6")
     * @return
     */
    public GroupBuilder withEmail( String email ) {
        this.email = email;
        return this;
    }

    /**
     * TODO: Why is this method public??
     * @param created
     * @return
     */
    public GroupBuilder createdOn( Date created ) {
        this.created = created;
        return this;
    }

    /**
     * TODO: Why is this method public??
     * @param updated
     * @return
     */
    public GroupBuilder updatedOn( Date updated ) {
        this.updated = updated;
        return this;
    }

    /**
     * When setting this method, e-SignLive will email each individual members of the group as opposed to sending an email to the group email address.
     * <p>
     * @see #withEmail(String)
     * @return
     */
    public GroupBuilder withIndividualMemberEmailing() {
        this.emailMembers = true;
        return this;
    }

    /**
     * Invoking this method will result in e-SignLive sending emails to the group email {@link #withEmail(String)} address as opposed to each individual members.
     * <p>
     * @see #withIndividualMemberEmailing()
     * @return
     */
    public GroupBuilder withoutIndividualMemberEmailing() {
        this.emailMembers = false;
        return this;
    }

    /**
     * Adds a sender to the group
     * @param builder
     * @return
     */
    public GroupBuilder withMember( GroupMemberBuilder builder ) {
        return withMember( builder.build() );
    }

    /**
     * Adds a sender to the group
     * @param groupMember
     * @return
     */
    public GroupBuilder withMember( GroupMember groupMember ) {
        this.members.add( groupMember );
        return this;
    }

    public Group build() {
        Group result = new Group();
        result.setEmail( email );
        result.setCreated( created );
        result.setUpdated( updated );
        result.setName( name );
        result.setEmailMembers( emailMembers );
        result.setMembers( members );
        result.setId( id );
        return result;
    }

    /**
     * Defines a unique ID for this group. E.g.: Two groups can have the same name but they MUST have different IDs.
     * @param groupId
     * @return
     */
    public GroupBuilder withId( GroupId groupId ) {
        this.id = groupId;
        return this;
    }
}
