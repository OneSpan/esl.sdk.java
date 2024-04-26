package com.silanis.esl.sdk;

public class GroupMember {
    private String email;
    private String firstName;
    private String lastName;
    private GroupMemberType groupMemberType;
    private String userId;

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public GroupMemberType getGroupMemberType() {
        return groupMemberType;
    }

    public void setGroupMemberType( GroupMemberType groupMemberType ) {
        this.groupMemberType = groupMemberType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }
}
