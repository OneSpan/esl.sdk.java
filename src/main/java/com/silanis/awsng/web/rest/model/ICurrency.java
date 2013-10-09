package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ICurrency extends IEntity{
    public ICurrency setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public ICurrency setId( String value);
    public String getId();
    public ICurrency setName( String value);
    public String getName();
    }