package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IGroupMembership {
    public IGroupMembership setGroupId( String value);
    public String getGroupId();
    public IGroupMembership setGroupName( String value);
    public String getGroupName();
    public IGroupMembership setMemberType( MemberType value);
    public MemberType getMemberType();
    }