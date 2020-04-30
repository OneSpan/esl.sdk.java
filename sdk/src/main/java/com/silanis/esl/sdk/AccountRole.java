package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountRole implements Serializable {
    protected String id;
    protected String name;
    protected List<String> permissions;
    protected String description;
    protected boolean enabled;

    public AccountRole() {
    }

    public AccountRole(String id, String name, List<String> permissions, String description, boolean enabled) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
        this.description = description;
        this.enabled = enabled;
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

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
