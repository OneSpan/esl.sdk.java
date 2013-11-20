package com.silanis.esl.sdk;

public class GroupMember {
    private String email;
    private String firstName;
    private String lastName;
    private GroupMemberType groupMemberType;

    public com.silanis.esl.api.model.GroupMember toAPIGroupMember() {
        com.silanis.esl.api.model.GroupMember result = new com.silanis.esl.api.model.GroupMember();
        result.setEmail( email );
        result.setFirstName( firstName );
        result.setLastName( lastName );
        result.setMemberType( groupMemberType.toAPIMemberType() );
        return result;
    }

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
}
