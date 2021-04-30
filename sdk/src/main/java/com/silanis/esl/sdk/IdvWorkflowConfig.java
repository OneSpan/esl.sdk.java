package com.silanis.esl.sdk;

import java.io.Serializable;

/**
 * Created by schoi on 2021-05-06.
 */
public class IdvWorkflowConfig implements Serializable {
    private String id;
    private String type;
    private String tenant;
    private String desc;
    private boolean skipWhenAccessingSignedDocuments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isSkipWhenAccessingSignedDocuments() {
        return skipWhenAccessingSignedDocuments;
    }

    public void setSkipWhenAccessingSignedDocuments(boolean skipWhenAccessingSignedDocuments) {
        this.skipWhenAccessingSignedDocuments = skipWhenAccessingSignedDocuments;
    }
}