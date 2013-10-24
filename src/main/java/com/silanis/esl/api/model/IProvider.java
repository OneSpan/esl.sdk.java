package com.silanis.esl.api.model;
//
import java.util.Map;

public interface IProvider extends IEntity{
    public IProvider setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public IProvider setId( String value);
    public String getId();
    public IProvider setName( String value);
    public String getName();
    public IProvider setProvides( String value);
    public String getProvides();
    }