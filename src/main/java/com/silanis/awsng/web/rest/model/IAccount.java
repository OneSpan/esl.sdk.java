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
public interface IAccount extends IEntity{
    public IAccount setCompany( Company value);
    public Company getCompany();
    public IAccount setCreated( java.util.Date value);
    public java.util.Date getCreated();
    public IAccount setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public IAccount setId( String value);
    public String getId();
    public IAccount setLicenses( List<License> value);
    public List<License> getLicenses();
    public IAccount setLogoUrl( String value);
    public String getLogoUrl();
    public IAccount setName( String value);
    public String getName();
    public IAccount setOwner( String value);
    public String getOwner();
    public IAccount setProviders( AccountProviders value);
    public AccountProviders getProviders();
    public IAccount setUpdated( java.util.Date value);
    public java.util.Date getUpdated();
    }