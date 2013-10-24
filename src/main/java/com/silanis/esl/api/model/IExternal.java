package com.silanis.esl.api.model;
//
import java.util.Map;

public interface IExternal {
    public IExternal setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public IExternal setId( String value);
    public String getId();
    public IExternal setProvider( String value);
    public String getProvider();
    public IExternal setProviderName( String value);
    public String getProviderName();
    }