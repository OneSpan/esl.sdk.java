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
public interface IApproval extends IEntity{
    public IApproval setAccepted( java.util.Date value);
    public java.util.Date getAccepted();
    public IApproval setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public IApproval setFields( List<Field> value);
    public List<Field> getFields();
    public IApproval setId( String value);
    public String getId();
    public IApproval setName( String value);
    public String getName();
    public IApproval setRole( String value);
    public String getRole();
    public IApproval setSigned( java.util.Date value);
    public java.util.Date getSigned();
    }