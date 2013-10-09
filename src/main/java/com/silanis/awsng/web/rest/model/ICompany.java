package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ICompany extends IEntity{
    public ICompany setAddress( Address value);
    public Address getAddress();
    public ICompany setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public ICompany setId( String value);
    public String getId();
    public ICompany setName( String value);
    public String getName();
    }