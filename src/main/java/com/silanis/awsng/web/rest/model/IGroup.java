package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.JsonDateDeserializer;
import com.silanis.awsng.web.rest.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IGroup extends IEntity{
    public IGroup setAccount( Account value);
    public Account getAccount();
    public IGroup setCreated( java.util.Date value);
    public java.util.Date getCreated();
    public IGroup setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public IGroup setEmail( String value);
    public String getEmail();
    public IGroup setEmailMembers( Boolean value);
    public Boolean getEmailMembers();
    public IGroup setId( String value);
    public String getId();
    public IGroup setMembers( List<GroupMember> value);
    public List<GroupMember> getMembers();
    public IGroup setName( String value);
    public String getName();
    public IGroup setUpdated( java.util.Date value);
    public java.util.Date getUpdated();
    }