package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.silanis.esl.api.util.SchemaSanitizer.throwOnNull;

/**
 * Created by schoi on 2021-05-06.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdvWorkflowConfiguration extends Model implements java.io.Serializable {

    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_TYPE = "type";
    @JsonIgnore
    public static final String FIELD_TENANT = "tenant";
    @JsonIgnore
    public static final String FIELD_DESC = "desc";
    @JsonIgnore
    public static final String FIELD_SKIP_WHEN_ACCESSING_SIGNED_DOCUMENTS = "skipWhenAccessingSignedDocuments";

    @JsonProperty("id")
    protected String workflowId;
    @JsonProperty("type")
    protected String type;
    @JsonProperty("tenant")
    protected String tenant;
    @JsonProperty("desc")
    protected String desc;
    @JsonProperty("skipWhenAccessingSignedDocuments")
    protected boolean skipWhenAccessingSignedDocuments;

    public IdvWorkflowConfiguration setWorkflowId(String value) {
        throwOnNull(FIELD_ID, value);

        this.workflowId = value;
        setDirty(FIELD_ID);
        return this;
    }

    public String getWorkflowId() {
        return workflowId;
    }


    public IdvWorkflowConfiguration setType(String value) {
        throwOnNull(FIELD_TYPE, value);

        this.type = value;
        setDirty(FIELD_TYPE);
        return this;
    }

    public String getType() {
        return type;
    }


    public IdvWorkflowConfiguration setTenant(String value) {
        throwOnNull(FIELD_TENANT, value);

        this.tenant = value;
        setDirty(FIELD_TENANT);
        return this;
    }

    public String getTenant() {
        return tenant;
    }


    public IdvWorkflowConfiguration setDesc(String value) {
        this.desc = value;
        setDirty(FIELD_DESC);
        return this;
    }

    public String getDesc() {
        return desc;
    }


    public IdvWorkflowConfiguration setSkipWhenAccessingSignedDocuments(boolean value) {
        this.skipWhenAccessingSignedDocuments = value;
        setDirty(FIELD_SKIP_WHEN_ACCESSING_SIGNED_DOCUMENTS);
        return this;
    }

    public boolean isSkipWhenAccessingSignedDocuments() {
        return skipWhenAccessingSignedDocuments;
    }

}