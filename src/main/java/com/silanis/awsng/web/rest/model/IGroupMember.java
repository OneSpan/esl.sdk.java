package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
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