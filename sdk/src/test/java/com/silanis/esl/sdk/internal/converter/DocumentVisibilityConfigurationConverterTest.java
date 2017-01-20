package com.silanis.esl.sdk.internal.converter;

import org.junit.Test;

import java.util.Arrays;

import static com.silanis.esl.sdk.builder.DocumentVisibilityConfigurationBuilder.newDocumentVisibilityConfiguration;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;

/**
 * Created by schoi on 11/25/16.
 */
public class DocumentVisibilityConfigurationConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.DocumentVisibilityConfiguration sdkVisibilityConfiguration1 = null;
    private com.silanis.esl.sdk.DocumentVisibilityConfiguration sdkVisibilityConfiguration2 = null;
    private com.silanis.esl.api.model.DocumentVisibilityConfiguration apiVisibilityConfiguration1 = null;
    private com.silanis.esl.api.model.DocumentVisibilityConfiguration apiVisibilityConfiguration2 = null;
    private DocumentVisibilityConfigurationConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkVisibilityConfiguration1 = null;
        converter = new DocumentVisibilityConfigurationConverter(sdkVisibilityConfiguration1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIVisibilityConfiguration(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiVisibilityConfiguration1 = null;
        converter = new DocumentVisibilityConfigurationConverter(apiVisibilityConfiguration1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKVisibilityConfiguration(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkVisibilityConfiguration1 = null;
        converter = new DocumentVisibilityConfigurationConverter(sdkVisibilityConfiguration1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toSDKVisibilityConfiguration(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiVisibilityConfiguration1 = null;
        converter = new DocumentVisibilityConfigurationConverter(apiVisibilityConfiguration1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toAPIVisibilityConfiguration(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkVisibilityConfiguration1 = createTypicalSDKDocumentVisibilityConfiguration();
        sdkVisibilityConfiguration2 = new DocumentVisibilityConfigurationConverter(sdkVisibilityConfiguration1).toSDKVisibilityConfiguration();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkVisibilityConfiguration2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkVisibilityConfiguration2, is(sdkVisibilityConfiguration1));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiVisibilityConfiguration1 = createTypicalAPIDocumentVisibilityConfiguration();
        apiVisibilityConfiguration2 = new DocumentVisibilityConfigurationConverter(apiVisibilityConfiguration1).toAPIVisibilityConfiguration();

        assertThat("Converter returned a null api object for a non null api object", apiVisibilityConfiguration2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiVisibilityConfiguration2, is(apiVisibilityConfiguration1));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiVisibilityConfiguration1 = createTypicalAPIDocumentVisibilityConfiguration();
        sdkVisibilityConfiguration1 = new DocumentVisibilityConfigurationConverter(apiVisibilityConfiguration1).toSDKVisibilityConfiguration();

        assertThat("Converter returned a null api object for a non null sdk object", sdkVisibilityConfiguration1, notNullValue());
        assertThat("Document uid was not correctly set", sdkVisibilityConfiguration1.getDocumentUid(), is(apiVisibilityConfiguration1.getDocumentUid()));
        assertThat("Signer uids were not correctly set", sdkVisibilityConfiguration1.getSignerIds(), hasSize(2));
        assertArrayEquals("Signer uids were not correctly set", sdkVisibilityConfiguration1.getSignerIds().toArray(), apiVisibilityConfiguration1.getRoleUids().toArray());
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkVisibilityConfiguration1 = createTypicalSDKDocumentVisibilityConfiguration();
        apiVisibilityConfiguration1 = new DocumentVisibilityConfigurationConverter(sdkVisibilityConfiguration1).toAPIVisibilityConfiguration();

        assertThat("Converter returned a null api object for a non null sdk object", apiVisibilityConfiguration1, notNullValue());
        assertThat("Document uid was not correctly set", apiVisibilityConfiguration1.getDocumentUid(), is(sdkVisibilityConfiguration1.getDocumentUid()));
        assertThat("Role uids were not correctly set", apiVisibilityConfiguration1.getRoleUids(), hasSize(2));
        assertArrayEquals("Role uids were not correctly set", apiVisibilityConfiguration1.getRoleUids().toArray(), sdkVisibilityConfiguration1.getSignerIds().toArray());
    }

    private com.silanis.esl.sdk.DocumentVisibilityConfiguration createTypicalSDKDocumentVisibilityConfiguration() {
        return newDocumentVisibilityConfiguration("docId")
            .withSignerIds(Arrays.asList("signer1Id", "signer2Id")).build();
    }

    private com.silanis.esl.api.model.DocumentVisibilityConfiguration createTypicalAPIDocumentVisibilityConfiguration() {
        return new com.silanis.esl.api.model.DocumentVisibilityConfiguration()
            .setDocumentUid("docId")
            .setRoleUids(Arrays.asList("role1Id", "role2Id"));
    }
}
