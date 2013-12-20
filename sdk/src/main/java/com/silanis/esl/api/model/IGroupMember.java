package com.silanis.esl.api.model;
//

public interface IGroupMember {
    public IGroupMember setEmail( String value);
    public String getEmail();
    public IGroupMember setFirstName( String value);
    public String getFirstName();
    public IGroupMember setLastName( String value);
    public String getLastName();
    public IGroupMember setMemberType( MemberType value);
    public MemberType getMemberType();
    public IGroupMember setUserId( String value);
    public String getUserId();
    }