package com.silanis.esl.api.model;
//
import java.util.List;
import java.util.Map;

public interface ISender extends IUser{
    public ISender setAccount( Account value);
    public Account getAccount();
    public ISender setActivated( java.util.Date value);
    public java.util.Date getActivated();
    public ISender setAddress( Address value);
    public Address getAddress();
    public ISender setCompany( String value);
    public String getCompany();
    public ISender setCreated( java.util.Date value);
    public java.util.Date getCreated();
    public ISender setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public ISender setEmail( String value);
    public String getEmail();
    public ISender setExternal( External value);
    public External getExternal();
    public ISender setFirstName( String value);
    public String getFirstName();
    public ISender setId( String value);
    public String getId();
    public ISender setLanguage( String value);
    public String getLanguage();
    public ISender setLastName( String value);
    public String getLastName();
    public ISender setLocked( java.util.Date value);
    public java.util.Date getLocked();
    public ISender setMemberships( List<GroupMembership> value);
    public List<GroupMembership> getMemberships();
    public ISender setName( String value);
    public String getName();
    public ISender setPhone( String value);
    public String getPhone();
    public ISender setSignature( SignatureStyle value);
    public SignatureStyle getSignature();
    public ISender setStatus( SenderStatus value);
    public SenderStatus getStatus();
    public ISender setTitle( String value);
    public String getTitle();
    public ISender setType( SenderType value);
    public SenderType getType();
    public ISender setUpdated( java.util.Date value);
    public java.util.Date getUpdated();
    }