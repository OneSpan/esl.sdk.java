package com.silanis.esl.sdk;

import java.io.Serializable;

/**
 * Created by chi-wing on 5/16/14.
 */
public class Placeholder implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    public Placeholder(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }
}
