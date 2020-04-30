package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountRole extends Model implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_PERMISSIONS = "permissions";
    @JsonIgnore
    public static final String FIELD_DESCRIPTION = "description";
    @JsonIgnore
    public static final String FIELD_ENABLED = "enabled";

    protected String id;
    protected String name;
    protected List<String> permissions;
    protected String description;
    protected boolean enabled;

    public AccountRole() {
    }

    public AccountRole(String id, String name, List<String> permissions, String description, Boolean enabled) {
        setId(id);
        setName(name);
        setPermissions(permissions);
        setDescription(description);
        setEnabled(enabled);
    }

    public String getId() {
        return id;
    }

    public AccountRole setId(String id) {
        SchemaSanitizer.throwOnNull(FIELD_ID, id);

        if (id.equals(this.id)) return this;

        this.id = id;
        setDirty(FIELD_ID);
        return this;
    }

    public String getName() {
        return name;
    }

    public AccountRole setName(String name) {
        SchemaSanitizer.throwOnNull(FIELD_NAME, name);

        if (name.equals(this.name)) return this;

        this.name = name;
        setDirty(FIELD_NAME);
        return this;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public AccountRole setPermissions(List<String> permissions) {
        SchemaSanitizer.throwOnNull(FIELD_PERMISSIONS, permissions);

        if (permissions.equals(this.permissions)) return this;

        this.permissions = permissions;
        setDirty(FIELD_PERMISSIONS);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AccountRole setDescription(String description) {
        SchemaSanitizer.throwOnNull(FIELD_DESCRIPTION, description);

        if (description.equals(this.description)) return this;

        this.description = description;
        setDirty(FIELD_DESCRIPTION);
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public AccountRole setEnabled(Boolean enabled) {
        SchemaSanitizer.throwOnNull(FIELD_ENABLED, enabled);

        if (enabled.equals(this.enabled)) return this;

        this.enabled = enabled;
        setDirty(FIELD_ENABLED);
        return this;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AccountRole that = (AccountRole) o;

        if (enabled != that.enabled)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (permissions != null ? !permissions.equals(that.permissions) : that.permissions != null)
            return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (permissions != null ? permissions.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }
}
