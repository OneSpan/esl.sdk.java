package com.silanis.esl.api.model;
//
import java.util.List;
import java.util.Map;

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