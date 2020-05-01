package com.silanis.esl.sdk;

import java.util.Map;

public class Provider {
    private String provides;
    private String name;
    private String id;
    private Map<String, Object> data;


    public void setProvides(String provides){
        this.provides = provides;
    }

    public String getProvides(){
        return provides;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}