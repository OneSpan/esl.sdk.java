package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
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