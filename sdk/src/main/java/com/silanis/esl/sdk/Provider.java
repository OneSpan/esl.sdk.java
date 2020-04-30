package com.silanis.esl.sdk;

import java.util.Map;

public class Provider {
    private String provides;
    private String name;
    private String id;
    private Map<String, Object> data;


    public void setProvides(String value){ provides = value; }

    public String getProvides(){
        return provides;
    }

    public Map<String, Object> getData() { return data; }

    public void setData(Map<String, Object> value) { data = value; }

    public void setId(String value) { id = value; }

    public String getId(){ return id; }

    public void setName(String value) { name = value; }

    public String getName() { return name; }
}