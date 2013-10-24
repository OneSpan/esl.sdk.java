package com.silanis.esl.api.model;
//
import java.util.Map;

public interface IUser extends IEntity{
    public IUser setAddress( Address value);
    public Address getAddress();
    public IUser setCompany( String value);
    public String getCompany();
    public IUser setCreated( java.util.Date value);
    public java.util.Date getCreated();
    public IUser setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public IUser setEmail( String value);
    public String getEmail();
    public IUser setExternal( External value);
    public External getExternal();
    public IUser setFirstName( String value);
    public String getFirstName();
    public IUser setId( String value);
    public String getId();
    public IUser setLanguage( String value);
    public String getLanguage();
    public IUser setLastName( String value);
    public String getLastName();
    public IUser setName( String value);
    public String getName();
    public IUser setPhone( String value);
    public String getPhone();
    public IUser setSignature( SignatureStyle value);
    public SignatureStyle getSignature();
    public IUser setTitle( String value);
    public String getTitle();
    public IUser setUpdated( java.util.Date value);
    public java.util.Date getUpdated();
    }