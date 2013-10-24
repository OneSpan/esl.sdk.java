package com.silanis.esl.api.model;
//

public interface IGroupMembership {
    public IGroupMembership setGroupId( String value);
    public String getGroupId();
    public IGroupMembership setGroupName( String value);
    public String getGroupName();
    public IGroupMembership setMemberType( MemberType value);
    public MemberType getMemberType();
    }