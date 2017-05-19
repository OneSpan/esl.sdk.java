package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import static com.silanis.esl.api.util.SchemaSanitizer.throwOnNull;
import static com.silanis.esl.api.util.SchemaSanitizer.trim;

public class Verification extends Model {

    @JsonIgnore
    public static final String FIELD_TYPE_ID = "typeId";

    @JsonIgnore
    public static final String FIELD_PAYLOAD = "payload";

    // Empty Constructor
    public Verification() {
    }

    protected String _typeId = "";
    protected String _payload = "";

    public Verification setTypeId(String value) {
        throwOnNull(FIELD_TYPE_ID, value);
        value = trim(value);

        if (StringUtils.equals(this._typeId, value)) return this;

        this._typeId = value;
        setDirty(FIELD_TYPE_ID);
        return this;
    }

    @JsonIgnore
    public Verification safeSetTypeId(String value) {
        if (value != null) {
            this.setTypeId(value);
        }
        return this;
    }

    public String getTypeId() {
        return _typeId;
    }

    public Verification setPayload(String value) {
        throwOnNull(FIELD_PAYLOAD, value);
        value = trim(value);

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
