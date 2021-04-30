package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.IdvWorkflowConfig;

/**
 * Created by schoi on 2021-05-06.
 */
public class IdvWorkflowConfigBuilder {
    private String id;
    private String type;
    private String tenant;
    private String desc;
    private boolean skipWhenAccessingSignedDocuments;

    private IdvWorkflowConfigBuilder(String id) {
        this.id = id;
    }

    public static IdvWorkflowConfigBuilder newIdvWorkflowConfig(String id) {
        if (id == null) {
            throw new BuilderException("IdvWorkflowConfig id cannot be null.");
        }
        return new IdvWorkflowConfigBuilder(id);
    }

    public IdvWorkflowConfigBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public IdvWorkflowConfigBuilder withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public IdvWorkflowConfigBuilder withDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public IdvWorkflowConfigBuilder enableSkipWhenAccessingSignedDocuments() {
        this.skipWhenAccessingSignedDocuments = true;
        return this;
    }

    public IdvWorkflowConfigBuilder disableSkipWhenAccessingSignedDocuments() {
        this.skipWhenAccessingSignedDocuments = false;
        return this;
    }

    public IdvWorkflowConfig build() {
        IdvWorkflowConfig idvWorkflowConfig = new IdvWorkflowConfig();
        idvWorkflowConfig.setId(id);
        idvWorkflowConfig.setType(type);
        idvWorkflowConfig.setTenant(tenant);
        idvWorkflowConfig.setDesc(desc);
        idvWorkflowConfig.setSkipWhenAccessingSignedDocuments(skipWhenAccessingSignedDocuments);
        return idvWorkflowConfig;
    }
}