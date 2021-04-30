package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.IdvWorkflow;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by schoi on 2021-04-30.
 */
public class IdvWorkflowBuilderTest {

    @Test
    public void test() {
        String id = "id";
        String type = "type";
        String tenant = "tenant";
        String desc = "desc";

        IdvWorkflow idvWorkflow = IdvWorkflowBuilder.newIdvWorkflow(id)
                .withType(type)
                .withTenant(tenant)
                .withDesc(desc)
                .build();

        assertThat(idvWorkflow.getId(), is(id));
        assertThat(idvWorkflow.getType(), is(type));
        assertThat(idvWorkflow.getTenant(), is(tenant));
        assertThat(idvWorkflow.getDesc(), is(desc));
    }
}