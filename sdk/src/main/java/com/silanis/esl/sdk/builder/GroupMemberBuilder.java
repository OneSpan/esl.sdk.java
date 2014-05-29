package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.GroupMemberType;

/**
 * <p>GroupMemberBuilder is a convenient class used to create and customize members of a group.</p>
 */
public class GroupMemberBuilder {
    private String email;
    private String firstName = "";
    private String lastName = "";
    private GroupMemberType groupMemberType = GroupMemberType.REGULAR;

    private GroupMemberBuilder( String email ) {
        this.email = email;

    }

    /**
     * Create a new group member.
     *
     * @param email the email address uniquely identifying the group member. @size(min="6", max="255", valid email address)
     * @return the group member builder itself
     */
    public static GroupMemberBuilder newGroupMember( String email ) {
        return new GroupMemberBuilder( email );
    }

    /**
     * Set the group member's type. A group member can be a regular member or a manager.
     * <p>A group manager has privileges to invite new members to the group and update the group.</p>
     *
     * @param groupMemberType the group member's type
     * @return the group member builder itself
     */
    public GroupMemberBuilder as( GroupMemberType groupMemberType ) {
        this.groupMemberType = groupMemberType;
        return this;
    }

    /**
     * Set the group member's first name.
     *
     * @param firstName the group member's first name @size(min="1", max="64")
     * @return the group member builder itself
     */
    public GroupMemberBuilder withFirstName( String firstName ) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Set the group member's last name.
     *
     * @param lastName the group member's last name @size(min="1", max="64")
     * @return the group member builder itself
     */
    public GroupMemberBuilder withLastName( String lastName ) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Builds the group member object.
     *
     * @return the group member
     */
    public com.silanis.esl.sdk.GroupMember build() {
        com.silanis.esl.sdk.GroupMember result = new com.silanis.esl.sdk.GroupMember();
        result.setEmail( email );
        result.setFirstName( firstName );
        result.setLastName( lastName );
        result.setGroupMemberType( groupMemberType );
        return result;
    }
}
