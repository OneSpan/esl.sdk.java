package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import static com.silanis.esl.api.util.SchemaSanitizer.throwOnNull;
import static com.silanis.esl.api.util.SchemaSanitizer.trim;

public class VerificationType extends Model {
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_METHOD = "method";

    // Empty Constructor
    public VerificationType() {
    }

    protected String _id = "";
    protected String _name = "";
    protected String _method = "";

    public VerificationType setId(String value) {
        throwOnNull(FIELD_ID, value);
        value = trim(value);

        if (StringUtils.equals(this._id, value)) return this;

        this._id = value;
        setDirty(FIELD_ID);
        return this;
    }

    @JsonIgnore
    public VerificationType safeSetId(String value) {
        if (value != null) {
            this.setId(value);
        }
        return this;
    }

    public String getId() {
        return _id;
    }

    public VerificationType setName(String value) {
        throwOnNull(FIELD_NAME, value);
        value = trim(value);

        if (StringUtils.equals(this._name, value)) return this;

        this._name = value;
        setDirty(FIELD_NAME);
        return this;
    }

    @JsonIgnore
    public VerificationType safeSetName(String value) {
        if (value != null) {
            this.setName(value);
        }
        return this;
    }

    public String getName() {
        return _name;
    }

    public VerificationType setMethod(String value) {
        throwOnNull(FIELD_METHOD, value);
        value = trim(value);

        if (StringUtils.equals(this._method, value)) return this;

        this._method = value;
        setDirty(FIELD_METHOD);
        return this;
    }

    @JsonIgnore
    public VerificationType safeSetMethod(String value) {
        if (value != null) {
            this.setMethod(value);
        }
        return this;
    }

    public String getMethod() {
        return _method;
    }
}
