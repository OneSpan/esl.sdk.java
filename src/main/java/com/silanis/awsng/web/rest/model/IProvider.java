package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
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