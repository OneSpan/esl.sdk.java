package com.silanis.esl.sdk.builder;

import com.silanis.esl.api.model.GroupMember;
import com.silanis.esl.api.model.MemberType;
import com.silanis.esl.sdk.GroupMemberType;

public class GroupMemberBuilder {
    private String email;
    private String firstName = "";
    private String lastName = "";
    private GroupMemberType groupMemberType = GroupMemberType.REGULAR;

    private GroupMemberBuilder( String email ) {
        this.email = email;

    }

    public static GroupMemberBuilder newGroupMember( String email ) {
        return new GroupMemberBuilder( email );
    }

    public GroupMemberBuilder as( GroupMemberType groupMemberType ) {
        this.groupMemberType = groupMemberType;
        return this;
    }

    public GroupMemberBuilder withFirstName( String firstName ) {
        this.firstName = firstName;
        return this;
    }

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
