package com.silanis.esl.api.model;
//
import java.util.Map;

public interface ICurrency extends IEntity{
    public ICurrency setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public ICurrency setId( String value);
    public String getId();
    public ICurrency setName( String value);
    public String getName();
    }