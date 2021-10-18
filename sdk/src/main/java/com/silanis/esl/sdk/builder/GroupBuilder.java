package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Group;
import com.silanis.esl.sdk.GroupId;
import com.silanis.esl.sdk.GroupMember;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>GroupBuilder is a convenient class to used to define groups of senders for a given eSignLive account.</p>
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
     * Instantiate a new Group of senders.
     *
     * @param name name of the new group @size(max="255")
     * @return the group builder itself
     */
    public static GroupBuilder newGroup( String name ) {
        return new GroupBuilder( name );
    }

    /**
     * Set this group email's address.
     *
     * @param email @size(min="6" max="255")
     * @return the group builder itself
     */
    public GroupBuilder withEmail( String email ) {
        this.email = email;
        return this;
    }

    /**
     * Set the date the group was created.
     *
     * @param created
     * @return the group builder itself
     * @deprecated
     */
    public GroupBuilder createdOn( Date created ) {
        this.created = created;
        return this;
    }

    /**
     * Set the date the group was updated on.
     *
     * @param updated
     * @return the group builder itself
     * @deprecated
     */
    public GroupBuilder updatedOn( Date updated ) {
        this.updated = updated;
        return this;
    }

    /**
     * When setting this method, eSignLive will email each individual members of the group as opposed to
     * sending an email to the group email address.
     * <p>
     * @see #withEmail(String)
     * @return the group builder itself
     */
    public GroupBuilder withIndividualMemberEmailing() {
        this.emailMembers = true;
        return this;
    }

    /**
     * Invoking this method will result in eSignLive sending emails to the group email {@link #withEmail(String)}
     * address as opposed to each individual members.
     * <p>
     * @see #withIndividualMemberEmailing()
     * @return the group builder itself
     */
    public GroupBuilder withoutIndividualMemberEmailing() {
        this.emailMembers = false;
        return this;
    }

    /**
     * Adds a sender to the group.
     *
     * @param builder the group member builder
     * @return the group builder itself
     */
    public GroupBuilder withMember( GroupMemberBuilder builder ) {
        return withMember( builder.build() );
    }

    /**
     * Adds a sender to the group.
     *
     * @param groupMember the group member
     * @return the group builder itself
     */
    public GroupBuilder withMember( GroupMember groupMember ) {
        this.members.add( groupMember );
        return this;
    }

    /**
     * Builds the group object.
     *
     * @return the group
     */
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
     *
     * @param groupId the group ID
     * @return the group builder itself
     */
    public GroupBuilder withId( GroupId groupId ) {
        this.id = groupId;
        return this;
    }
}
