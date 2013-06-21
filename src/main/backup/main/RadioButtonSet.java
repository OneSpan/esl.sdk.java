package com.silanis.esl.sdk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * User: dave
 */
public class RadioButtonSet implements Serializable {

    private static final long serialVersionUID = 1L;

    private Collection<RadioButtonOption> options;
    private String email;
    private String group;

    public RadioButtonSet(String group) {
        options = new ArrayList<RadioButtonOption>();
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup( String group ) {
        this.group = group;
    }

    public Collection<RadioButtonOption> getOptions() {
        return options;
    }
}
