package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.IdvWorkflow;
import com.silanis.esl.sdk.builder.IdvWorkflowBuilder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by schoi on 2021-04-30.
 */
public class IdvWorkflowConverterTest implements ConverterTest {
    private com.silanis.esl.api.model.IdvWorkflow apiIdvWorkflow1 = null;
    private com.silanis.esl.api.model.IdvWorkflow apiIdvWorkflow2 = null;
    private com.silanis.esl.sdk.IdvWorkflow sdkIdvWorkflow1 = null;
    private com.silanis.esl.sdk.IdvWorkflow sdkIdvWorkflow2 = null;
    private IdvWorkflowConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkIdvWorkflow1 = null;
        converter = new IdvWorkflowConverter(sdkIdvWorkflow1);
        assertThat(converter.toAPIIdvWorkflow(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiIdvWorkflow1 = null;
        converter = new IdvWorkflowConverter(apiIdvWorkflow1);
        assertThat(converter.toSDKIdvWorkflow(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkIdvWorkflow1 = null;
        converter = new IdvWorkflowConverter(sdkIdvWorkflow1);
        assertThat(converter.toSDKIdvWorkflow(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiIdvWorkflow1 = null;
        converter = new IdvWorkflowConverter(apiIdvWorkflow1);
        assertThat(converter.toAPIIdvWorkflow(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkIdvWorkflow1 = createTypicalSDKIdvWorkflow();
        sdkIdvWorkflow2 = new IdvWorkflowConverter(sdkIdvWorkflow1).toSDKIdvWorkflow();

        assertThat(sdkIdvWorkflow2, notNullValue());
        assertThat(sdkIdvWorkflow2.getId(), is(sdkIdvWorkflow1.getId()));
        assertThat(sdkIdvWorkflow2.getType(), is(sdkIdvWorkflow1.getType()));
        assertThat(sdkIdvWorkflow2.getTenant(), is(sdkIdvWorkflow1.getTenant()));
        assertThat(sdkIdvWorkflow2.getDesc(), is(sdkIdvWorkflow1.getDesc()));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiIdvWorkflow1 = createTypicalAPIIdvWorkflow();
        apiIdvWorkflow2 = new IdvWorkflowConverter(apiIdvWorkflow1).toAPIIdvWorkflow();

        assertThat(apiIdvWorkflow2, notNullValue());
        assertThat(apiIdvWorkflow2.getId(), is(apiIdvWorkflow1.getId()));
        assertThat(apiIdvWorkflow2.getType(), is(apiIdvWorkflow1.getType()));
        assertThat(apiIdvWorkflow2.getTenant(), is(apiIdvWorkflow1.getTenant()));
        assertThat(apiIdvWorkflow2.getDesc(), is(apiIdvWorkflow1.getDesc()));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiIdvWorkflow1 = createTypicalAPIIdvWorkflow();
        sdkIdvWorkflow1 = new IdvWorkflowConverter(apiIdvWorkflow1).toSDKIdvWorkflow();

        assertThat(sdkIdvWorkflow1, notNullValue());
        assertThat(sdkIdvWorkflow1.getId(), is(apiIdvWorkflow1.getId()));
        assertThat(sdkIdvWorkflow1.getType(), is(apiIdvWorkflow1.getType()));
        assertThat(sdkIdvWorkflow1.getTenant(), is(apiIdvWorkflow1.getTenant()));
        assertThat(sdkIdvWorkflow1.getDesc(), is(apiIdvWorkflow1.getDesc()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkIdvWorkflow1 = createTypicalSDKIdvWorkflow();
        apiIdvWorkflow1 = new IdvWorkflowConverter(sdkIdvWorkflow1).toAPIIdvWorkflow();

        assertThat(apiIdvWorkflow1, notNullValue());
        assertThat(apiIdvWorkflow1.getId(), is(sdkIdvWorkflow1.getId()));
        assertThat(apiIdvWorkflow1.getType(), is(sdkIdvWorkflow1.getType()));
        assertThat(apiIdvWorkflow1.getTenant(), is(sdkIdvWorkflow1.getTenant()));
        assertThat(apiIdvWorkflow1.getDesc(), is(sdkIdvWorkflow1.getDesc()));
    }

    private com.silanis.esl.sdk.IdvWorkflow createTypicalSDKIdvWorkflow() {
        return IdvWorkflowBuilder.newIdvWorkflow("id")
                .withType("sdkType")
                .withTenant("sdkTenant")
                .withDesc("sdkDesc")
                .build();
    }

    private IdvWorkflow createTypicalAPIIdvWorkflow() {
        IdvWorkflow idvWorkflow = new IdvWorkflow();
        idvWorkflow.setId("id");
        idvWorkflow.setType("apiType");
        idvWorkflow.setTenant("apiTenant");
        idvWorkflow.setDesc("apiDesc");
        return idvWorkflow;
    }
}