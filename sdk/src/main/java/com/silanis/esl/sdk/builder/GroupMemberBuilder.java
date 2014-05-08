package com.silanis.esl.sdk.builder;

import com.silanis.esl.api.model.GroupMember;
import com.silanis.esl.api.model.MemberType;
import com.silanis.esl.sdk.GroupMemberType;

/**
 * Helper class to help create members of a group.
 *
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
     * Create a new group member
     * @param email the email address uniquely identifying the group member. @size(min="6")
     * @return
     */
    public static GroupMemberBuilder newGroupMember( String email ) {
        return new GroupMemberBuilder( email );
    }

    /**
     * Set the group member's type. A group member can be a regular member or a manager.
     * TODO: What does a manager do??
     * @param groupMemberType
     * @return
     */
    public GroupMemberBuilder as( GroupMemberType groupMemberType ) {
        this.groupMemberType = groupMemberType;
        return this;
    }

    /**
     * Set the group member's first name
     * @param firstName
     * @return
     */
    public GroupMemberBuilder withFirstName( String firstName ) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Set the group member's last name
     * @param lastName
     * @return
     */
    public GroupMemberBuilder withLastName( String lastName ) {
        this.lastName = lastName;
        return this;
    }

    public com.silanis.esl.sdk.GroupMember build() {
        com.silanis.esl.sdk.GroupMember result = new com.silanis.esl.sdk.GroupMember();
        result.setEmail( email );
        result.setFirstName( firstName );
        result.setLastName( lastName );
        result.setGroupMemberType( groupMemberType );
        return result;
    }
}
