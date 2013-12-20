package com.silanis.esl.api.model;
//
import java.util.Map;

public interface IUserCustomField extends IEntity {
    public IUserCustomField setData(Map<String, Object> value);
    public Map<String, Object> getData();
    public IUserCustomField setId(String value);
    public String getId();
    public IUserCustomField setName(String value);
    public String getName();
    public IUserCustomField setValue(String value);
    public String getValue();
    }