package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static com.silanis.esl.api.util.SchemaSanitizer.throwOnNull;

/**
 * Created by schoi on 2021-03-10.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdvWorkflow extends Model implements java.io.Serializable {

    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_TYPE = "type";
    @JsonIgnore
    public static final String FIELD_TENANT = "tenant";
    @JsonIgnore
    public static final String FIELD_DESC = "desc";

    // Empty Constructor
    public IdvWorkflow() {
    }

    // Fields
    protected String id;
    protected String type;
    protected String tenant;
    protected String desc;


    public IdvWorkflow setId(String value) {
        throwOnNull(FIELD_ID, value);

        this.id = value;
        setDirty(FIELD_ID);
        return this;
    }

    public String getId() {
        return id;
    }


    public IdvWorkflow setType(String value) {
        throwOnNull(FIELD_TYPE, value);

        this.type = value;
        setDirty(FIELD_TYPE);
        return this;
    }

    public String getType() {
        return type;
    }


    public IdvWorkflow setTenant(String value) {
        throwOnNull(FIELD_TENANT, value);

        this.tenant = value;
        setDirty(FIELD_TENANT);
        return this;
    }

    public String getTenant() {
        return tenant;
    }


    public IdvWorkflow setDesc(String value) {
        this.desc = value;
        setDirty(FIELD_DESC);
        return this;
    }

    public String getDesc() {
        return desc;
    }
}