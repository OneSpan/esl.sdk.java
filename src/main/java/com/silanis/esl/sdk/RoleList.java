package com.silanis.esl.sdk;

import com.silanis.awsng.web.rest.model.Role;

import java.io.Serializable;
import java.util.List;

public class RoleList implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Role> results;
    private int count;

    public List<Role> getResults() {
        return results;
    }

    public int getCount() {
        return count;
    }

}
