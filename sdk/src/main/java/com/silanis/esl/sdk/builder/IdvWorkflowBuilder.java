package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.IdvWorkflow;

/**
 * Created by schoi on 2021-03-10.
 */
public class IdvWorkflowBuilder {

    private String id;
    private String type;
    private String tenant;
    private String desc;

    private IdvWorkflowBuilder(String id) {
        this.id = id;
    }

    public static IdvWorkflowBuilder newIdvWorkflow(String id) {
        if (id == null) {
            throw new BuilderException("IdvWorkflow id cannot be null.");
        }
        return new IdvWorkflowBuilder(id);
    }

    public IdvWorkflowBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public IdvWorkflowBuilder withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public IdvWorkflowBuilder withDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public IdvWorkflow build() {
        IdvWorkflow idvWorkflow = new IdvWorkflow();
        idvWorkflow.setId(id);
        idvWorkflow.setType(type);
        idvWorkflow.setTenant(tenant);
        idvWorkflow.setDesc(desc);
        return idvWorkflow;
    }
}