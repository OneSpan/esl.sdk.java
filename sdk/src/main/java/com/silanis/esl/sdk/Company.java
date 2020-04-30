package com.silanis.esl.sdk;

import java.util.Map;

public class Company {

    private Address address;
    private String name;
    private String id;
    private Map<String, Object> data;


    public void setAddress(Address value){ address = value; }

    public Address getAddress(){
        return address;
    }

    public Map<String, Object> getData() { return data; }

    public void setData(Map<String, Object> value) { data = value; }

    public void setId(String value) { id = value; }

    public String getId(){ return id; }

    public void setName(String value) { name = value; }

    public String getName() { return name; }
}