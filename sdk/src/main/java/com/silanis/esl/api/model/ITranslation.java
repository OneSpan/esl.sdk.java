package com.silanis.esl.api.model;
//
import java.util.Map;

public interface ITranslation extends IEntity {
    public ITranslation setData(Map<String, Object> value);
    public Map<String, Object> getData();
    public ITranslation setDescription(String value);
    public String getDescription();
    public ITranslation setId(String value);
    public String getId();
    public ITranslation setLanguage(String value);
    public String getLanguage();
    public ITranslation setName(String value);
    public String getName();
    }