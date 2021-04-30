package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.IdvWorkflowConfig;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by schoi on 2021-05-10.
 */
public class IdvWorkflowConfigBuilderTest {

    @Test
    public void test() {
        String id = "id";
        String type = "type";
        String tenant = "tenant";
        String desc = "desc";

        IdvWorkflowConfig idvWorkflowConfig = IdvWorkflowConfigBuilder.newIdvWorkflowConfig(id)
                .withType(type)
                .withTenant(tenant)
                .withDesc(desc)
                .enableSkipWhenAccessingSignedDocuments()
                .build();

        assertThat(idvWorkflowConfig.getId(), is(id));
        assertThat(idvWorkflowConfig.getType(), is(type));
        assertThat(idvWorkflowConfig.getTenant(), is(tenant));
        assertThat(idvWorkflowConfig.getDesc(), is(desc));
        assertTrue(idvWorkflowConfig.isSkipWhenAccessingSignedDocuments());
    }
}