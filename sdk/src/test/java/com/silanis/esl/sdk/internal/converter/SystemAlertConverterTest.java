package com.silanis.esl.sdk.internal.converter;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

import java.util.HashMap;
import java.util.Map;

public class SystemAlertConverterTest implements ConverterTest {
    private com.silanis.esl.sdk.SystemAlert sdkSystemAlert1;
    private com.silanis.esl.sdk.SystemAlert sdkSystemAlert2;
    private com.silanis.esl.api.model.SystemAlert apiSystemAlert1;
    private com.silanis.esl.api.model.SystemAlert apiSystemAlert2;
    private SystemAlertConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkSystemAlert1 = null;
        converter = new SystemAlertConverter(sdkSystemAlert1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPISystemAlert(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiSystemAlert1 = null;
        converter = new SystemAlertConverter(apiSystemAlert1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKSystemAlert(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkSystemAlert1 = null;
        converter = new SystemAlertConverter(sdkSystemAlert1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKSystemAlert(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiSystemAlert1 = null;
        converter = new SystemAlertConverter(apiSystemAlert1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPISystemAlert(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkSystemAlert1 = createTypicalSDKSystemAlert();
        sdkSystemAlert2 = new SystemAlertConverter(sdkSystemAlert1).toSDKSystemAlert();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkSystemAlert2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkSystemAlert2, is(sdkSystemAlert1));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiSystemAlert1 = createTypicalAPISystemAlert();
        apiSystemAlert2 = new SystemAlertConverter(apiSystemAlert1).toAPISystemAlert();
        assertThat("Converter returned a null api object for a non null api object", apiSystemAlert2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiSystemAlert2, is(apiSystemAlert1));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiSystemAlert1 = createTypicalAPISystemAlert();
        sdkSystemAlert1 = new SystemAlertConverter(apiSystemAlert1).toSDKSystemAlert();
        assertThat("Converter returned a null sdk object for a non null api object", sdkSystemAlert1, is(notNullValue()));
        assertThat("Converter didn't properly convert api object to sdk object", sdkSystemAlert1.getCode(), is(apiSystemAlert1.getCode()));
        assertThat("Converter didn't properly convert api object to sdk object", sdkSystemAlert1.getDefaultMessage(), is(apiSystemAlert1.getDefaultMessage()));
        assertThat("Converter didn't properly convert api object to sdk object", sdkSystemAlert1.getSeverityLevel().name(), is(apiSystemAlert1.getSeverityLevel().name()));
        assertThat("Converter didn't properly convert api object to sdk object", sdkSystemAlert1.getParameters().size(), is(apiSystemAlert1.getParameters().size()));
        for (String key : apiSystemAlert1.getParameters().keySet()) {
            assertThat("Converter didn't properly convert api object to sdk object", sdkSystemAlert1.getParameters().get(key), is(apiSystemAlert1.getParameters().get(key)));
        }
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkSystemAlert1 = createTypicalSDKSystemAlert();
        apiSystemAlert1 = new SystemAlertConverter(sdkSystemAlert1).toAPISystemAlert();
        assertThat("Converter returned a null api object for a non null sdk object", apiSystemAlert1, is(notNullValue()));
        assertThat("Converter didn't properly convert sdk object to api object", apiSystemAlert1.getCode(), is(sdkSystemAlert1.getCode()));
        assertThat("Converter didn't properly convert sdk object to api object", apiSystemAlert1.getDefaultMessage(), is(sdkSystemAlert1.getDefaultMessage()));
        assertThat("Converter didn't properly convert sdk object to api object", apiSystemAlert1.getSeverityLevel().name(), is(sdkSystemAlert1.getSeverityLevel().name()));
        assertThat("Converter didn't properly convert sdk object to api object", apiSystemAlert1.getParameters().size(), is(sdkSystemAlert1.getParameters().size()));
        for (String key : sdkSystemAlert1.getParameters().keySet()) {
            assertThat("Converter didn't properly convert sdk object to api object", apiSystemAlert1.getParameters().get(key), is(sdkSystemAlert1.getParameters().get(key)));
        }
    }

    private com.silanis.esl.sdk.SystemAlert createTypicalSDKSystemAlert() {
        com.silanis.esl.sdk.SystemAlert systemAlert = new com.silanis.esl.sdk.SystemAlert();
        systemAlert.setSeverityLevel(com.silanis.esl.sdk.SystemAlert.SeverityLevel.WARNING);
        systemAlert.setCode("code");
        systemAlert.setDefaultMessage("defaultMessage");
        Map<String, String> params = new HashMap<>();
        params.put("key1", "value1");
        params.put("key2", "value2");
        systemAlert.setParameters(params);
        return systemAlert;
    }

    private com.silanis.esl.api.model.SystemAlert createTypicalAPISystemAlert() {
        com.silanis.esl.api.model.SystemAlert systemAlert = new com.silanis.esl.api.model.SystemAlert();
        systemAlert.setSeverityLevel(com.silanis.esl.api.model.SystemAlert.SeverityLevel.WARNING);
        systemAlert.setCode("code");
        systemAlert.setDefaultMessage("defaultMessage");
        Map<String, String> params = new HashMap<>();
        params.put("key1", "value1");
        params.put("key2", "value2");
        systemAlert.setParameters(params);
        return systemAlert;
    }
}
