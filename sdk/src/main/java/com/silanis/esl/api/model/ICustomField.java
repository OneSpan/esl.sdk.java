package com.silanis.esl.api.model;
//
import java.util.List;
import java.util.Map;

public interface ICustomField extends IEntity {
    public ICustomField setData(Map<String, Object> value);
    public Map<String, Object> getData();
    public ICustomField setId(String value);
    public String getId();
    public ICustomField setName(String value);
    public String getName();
    public ICustomField setRequired(Boolean value);
    public Boolean getRequired();
    public ICustomField setTranslations(List<Translation> value);
    public List<Translation> getTranslations();
    public ICustomField setValue(String value);
    public String getValue();
    }