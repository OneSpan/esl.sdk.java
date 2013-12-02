package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Group;
import com.silanis.esl.sdk.GroupId;
import com.silanis.esl.sdk.GroupMember;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static GroupBuilder newGroup( String name ) {
        return new GroupBuilder( name );
    }

    public GroupBuilder withEmail( String email ) {
        this.email = email;
        return this;
    }

    public GroupBuilder createdOn( Date created ) {
        this.created = created;
        return this;
    }

    public GroupBuilder updatedOn( Date updated ) {
        this.updated = updated;
        return this;
    }

    public GroupBuilder withIndividualMemberEmailing() {
        this.emailMembers = true;
        return this;
    }

    public GroupBuilder withoutIndividualMemberEmailing() {
        this.emailMembers = false;
        return this;
    }

    public GroupBuilder withMember( GroupMemberBuilder builder ) {
        return withMember( builder.build() );
    }

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

    public GroupBuilder withId( GroupId groupId ) {
        this.id = groupId;
        return this;
    }
}
