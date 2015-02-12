package com.silanis.esl.sdk;

import java.util.Map;

/**
 * Created by schoi on 1/28/15.
 */
public class GroupSummary {
    private Map<String, Object> data;
    private String email;
    private String id;
    private String name;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
