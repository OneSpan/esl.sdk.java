package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IEntity {
    public IEntity setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public IEntity setId( String value);
    public String getId();
    public IEntity setName( String value);
    public String getName();
    }