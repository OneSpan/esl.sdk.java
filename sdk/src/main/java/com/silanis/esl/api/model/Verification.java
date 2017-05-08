package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import static com.silanis.esl.api.util.SchemaSanitizer.throwOnNull;
import static com.silanis.esl.api.util.SchemaSanitizer.trim;

public class Verification extends Entity
        implements java.io.Serializable {

    @JsonIgnore
    public static final String FIELD_TYPE_KEY = "typeKey";
    @JsonIgnore
    public static final String FIELD_PAYLOAD = "payload";

    // Empty Constructor
    public Verification() {
    }

    protected String _typeKey = "";
    protected String _payload = "";

    public Verification setTypeKey(String value) {
        throwOnNull(FIELD_TYPE_KEY, value);
        value = trim(value);

        if (StringUtils.equals(this._typeKey, value)) return this;

        this._typeKey = value;
        setDirty(FIELD_TYPE_KEY);
        return this;
    }

    @JsonIgnore
    public Verification safeSetTypeKey(String value) {
        if (value != null) {
            this.setTypeKey(value);
        }
        return this;
    }

    public String getTypeKey() {
        return _typeKey;
    }


    public Verification setPayload(String value) {
        if (StringUtils.equals(this._payload, value)) return this;

        this._payload = value;
        setDirty(FIELD_PAYLOAD);
        return this;
    }

    @JsonIgnore
    public Verification safeSetPayload(String value) {
        if (value != null) {
            this.setPayload(value);
        }
        return this;
    }

    public String getPayload() {
        return _payload;
    }
}
