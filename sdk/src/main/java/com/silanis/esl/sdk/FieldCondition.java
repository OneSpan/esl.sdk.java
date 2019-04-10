package com.silanis.esl.sdk;

import java.io.Serializable;

public class FieldCondition implements Serializable {

    private String id;
    private String condition;
    private String action;


    public FieldCondition() {}

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition( String condition ) {
        this.condition = condition;
    }

    public String getAction() {
        return action;
    }

    public void setAction( String action ) {
        this.action = action;
    }
}
