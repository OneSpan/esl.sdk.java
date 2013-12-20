package com.silanis.esl.api.model;
//
import java.util.Map;

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