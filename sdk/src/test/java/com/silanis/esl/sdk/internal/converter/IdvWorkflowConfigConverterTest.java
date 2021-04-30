package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.IdvWorkflowConfiguration;
import com.silanis.esl.sdk.IdvWorkflowConfig;
import com.silanis.esl.sdk.builder.IdvWorkflowConfigBuilder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by schoi on 2021-05-10.
 */
public class IdvWorkflowConfigConverterTest implements ConverterTest {
    private IdvWorkflowConfiguration apiIdvWorkflowConfiguration1 = null;
    private IdvWorkflowConfiguration apiIdvWorkflowConfiguration2 = null;
    private IdvWorkflowConfig sdkIdvWorkflowConfig1 = null;
    private IdvWorkflowConfig sdkIdvWorkflowConfig2 = null;
    private IdvWorkflowConfigConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkIdvWorkflowConfig1 = null;
        converter = new IdvWorkflowConfigConverter(sdkIdvWorkflowConfig1);
        assertThat(converter.toAPIIdvWorkflowConfiguration(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiIdvWorkflowConfiguration1 = null;
        converter = new IdvWorkflowConfigConverter(apiIdvWorkflowConfiguration1);
        assertThat(converter.toSDKIdvWorkflowConfig(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkIdvWorkflowConfig1 = null;
        converter = new IdvWorkflowConfigConverter(sdkIdvWorkflowConfig1);
        assertThat(converter.toSDKIdvWorkflowConfig(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiIdvWorkflowConfiguration1 = null;
        converter = new IdvWorkflowConfigConverter(apiIdvWorkflowConfiguration1);
        assertThat(converter.toAPIIdvWorkflowConfiguration(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkIdvWorkflowConfig1 = createTypicalSDKIdvWorkflowConfig();
        sdkIdvWorkflowConfig2 = new IdvWorkflowConfigConverter(sdkIdvWorkflowConfig1).toSDKIdvWorkflowConfig();

        assertThat(sdkIdvWorkflowConfig2, notNullValue());
        assertThat(sdkIdvWorkflowConfig2.getId(), is(sdkIdvWorkflowConfig1.getId()));
        assertThat(sdkIdvWorkflowConfig2.getType(), is(sdkIdvWorkflowConfig1.getType()));
        assertThat(sdkIdvWorkflowConfig2.getTenant(), is(sdkIdvWorkflowConfig1.getTenant()));
        assertThat(sdkIdvWorkflowConfig2.getDesc(), is(sdkIdvWorkflowConfig1.getDesc()));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiIdvWorkflowConfiguration1 = createTypicalAPIIdvWorkflowConfiguration();
        apiIdvWorkflowConfiguration2 = new IdvWorkflowConfigConverter(apiIdvWorkflowConfiguration1).toAPIIdvWorkflowConfiguration();

        assertThat(apiIdvWorkflowConfiguration2, notNullValue());
        assertThat(apiIdvWorkflowConfiguration2.getWorkflowId(), is(apiIdvWorkflowConfiguration1.getWorkflowId()));
        assertThat(apiIdvWorkflowConfiguration2.getType(), is(apiIdvWorkflowConfiguration1.getType()));
        assertThat(apiIdvWorkflowConfiguration2.getTenant(), is(apiIdvWorkflowConfiguration1.getTenant()));
        assertThat(apiIdvWorkflowConfiguration2.getDesc(), is(apiIdvWorkflowConfiguration1.getDesc()));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiIdvWorkflowConfiguration1 = createTypicalAPIIdvWorkflowConfiguration();
        sdkIdvWorkflowConfig1 = new IdvWorkflowConfigConverter(apiIdvWorkflowConfiguration1).toSDKIdvWorkflowConfig();

        assertThat(sdkIdvWorkflowConfig1, notNullValue());
        assertThat(sdkIdvWorkflowConfig1.getId(), is(apiIdvWorkflowConfiguration1.getWorkflowId()));
        assertThat(sdkIdvWorkflowConfig1.getType(), is(apiIdvWorkflowConfiguration1.getType()));
        assertThat(sdkIdvWorkflowConfig1.getTenant(), is(apiIdvWorkflowConfiguration1.getTenant()));
        assertThat(sdkIdvWorkflowConfig1.getDesc(), is(apiIdvWorkflowConfiguration1.getDesc()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkIdvWorkflowConfig1 = createTypicalSDKIdvWorkflowConfig();
        apiIdvWorkflowConfiguration1 = new IdvWorkflowConfigConverter(sdkIdvWorkflowConfig1).toAPIIdvWorkflowConfiguration();

        assertThat(apiIdvWorkflowConfiguration1, notNullValue());
        assertThat(apiIdvWorkflowConfiguration1.getWorkflowId(), is(sdkIdvWorkflowConfig1.getId()));
        assertThat(apiIdvWorkflowConfiguration1.getType(), is(sdkIdvWorkflowConfig1.getType()));
        assertThat(apiIdvWorkflowConfiguration1.getTenant(), is(sdkIdvWorkflowConfig1.getTenant()));
        assertThat(apiIdvWorkflowConfiguration1.getDesc(), is(sdkIdvWorkflowConfig1.getDesc()));
    }

    private IdvWorkflowConfig createTypicalSDKIdvWorkflowConfig() {
        return IdvWorkflowConfigBuilder.newIdvWorkflowConfig("id")
                .withType("sdkType")
                .withTenant("sdkTenant")
                .withDesc("sdkDesc")
                .enableSkipWhenAccessingSignedDocuments()
                .build();
    }

    private IdvWorkflowConfiguration createTypicalAPIIdvWorkflowConfiguration() {
        IdvWorkflowConfiguration idvWorkflowConfiguration = new IdvWorkflowConfiguration();
        idvWorkflowConfiguration.setWorkflowId("id");
        idvWorkflowConfiguration.setType("apiType");
        idvWorkflowConfiguration.setTenant("apiTenant");
        idvWorkflowConfiguration.setDesc("apiDesc");
        return idvWorkflowConfiguration;
    }
}