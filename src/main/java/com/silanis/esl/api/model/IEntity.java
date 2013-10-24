package com.silanis.esl.api.model;
//
import java.util.Map;

public interface IEntity {
    public IEntity setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public IEntity setId( String value);
    public String getId();
    public IEntity setName( String value);
    public String getName();
    }