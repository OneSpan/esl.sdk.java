package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Group {
    private GroupId id;
    private String name;
    private String email;
    private Date created;
    private Date updated;
    private Boolean emailMembers;
    private List<GroupMember> members;

    public Group() {
        members = new ArrayList<GroupMember>();
    }

    public com.silanis.esl.api.model.Group toAPIGroup() {

        com.silanis.esl.api.model.Group result = toAPIGroupWithoutMembers();

        List<com.silanis.esl.api.model.GroupMember> apiMembers = new ArrayList<com.silanis.esl.api.model.GroupMember>();
        for ( GroupMember sdkGroupMember : members ) {
            apiMembers.add( sdkGroupMember.toAPIGroupMember() );
        }
        result.setMembers( apiMembers );

        return result;
    }

    public com.silanis.esl.api.model.Group toAPIGroupWithoutMembers() {
        com.silanis.esl.api.model.Group result = new com.silanis.esl.api.model.Group();
        result.setName( name );
        result.safeSetCreated( created );
        result.safeSetUpdated( updated );
        result.setEmail( email );
        result.setEmailMembers( emailMembers );

        if ( id != null ) {
            result.safeSetId( id.getId() );
        }

        return result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated( Date created ) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated( Date updated ) {
        this.updated = updated;
    }

    public Boolean getEmailMembers() {
        return emailMembers;
    }

    public void setEmailMembers( Boolean emailMembers ) {
        this.emailMembers = emailMembers;
    }

    public List<GroupMember> getMembers() {
        return members;
    }

    public void setMembers( List<GroupMember> members ) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public GroupId getId() {
        return id;
    }

    public void setId( GroupId id ) {
        this.id = id;
    }
}
