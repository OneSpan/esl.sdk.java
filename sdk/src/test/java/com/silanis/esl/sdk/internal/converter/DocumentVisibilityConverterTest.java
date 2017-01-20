package com.silanis.esl.sdk.internal.converter;

import org.junit.Test;

import java.util.Arrays;

import static com.silanis.esl.sdk.builder.DocumentVisibilityBuilder.newDocumentVisibility;
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
public class DocumentVisibilityConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.DocumentVisibility sdkVisibility1 = null;
    private com.silanis.esl.sdk.DocumentVisibility sdkVisibility2 = null;
    private com.silanis.esl.api.model.DocumentVisibility apiVisibility1 = null;
    private com.silanis.esl.api.model.DocumentVisibility apiVisibility2 = null;
    private DocumentVisibilityConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkVisibility1 = null;
        converter = new DocumentVisibilityConverter(sdkVisibility1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIDocumentVisibility(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiVisibility1 = null;
        converter = new DocumentVisibilityConverter(apiVisibility1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKDocumentVisibility(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkVisibility1 = null;
        converter = new DocumentVisibilityConverter(sdkVisibility1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toSDKDocumentVisibility(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiVisibility1 = null;
        converter = new DocumentVisibilityConverter(apiVisibility1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toAPIDocumentVisibility(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkVisibility1 = createTypicalSDKDocumentVisibility();
        sdkVisibility2 = new DocumentVisibilityConverter(sdkVisibility1).toSDKDocumentVisibility();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkVisibility2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkVisibility2, is(sdkVisibility1));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiVisibility1 = createTypicalAPIDocumentVisibility();
        apiVisibility2 = new DocumentVisibilityConverter(apiVisibility1).toAPIDocumentVisibility();

        assertThat("Converter returned a null api object for a non null api object", apiVisibility2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiVisibility2, is(apiVisibility1));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiVisibility1 = createTypicalAPIDocumentVisibility();
        sdkVisibility1 = new DocumentVisibilityConverter(apiVisibility1).toSDKDocumentVisibility();

        assertThat("Converter returned a null api object for a non null sdk object", sdkVisibility1, notNullValue());
        assertThat("Document Visibility Configurations were not correctly set", sdkVisibility1.getConfigurations(), hasSize(2));
        assertArrayEquals("Document Visibility Configurations were not correctly set", sdkVisibility1.getConfiguration("doc1Id").getSignerIds().toArray(), apiVisibility1.getConfigurations().get(0).getRoleUids().toArray());
        assertArrayEquals("Document Visibility Configurations were not correctly set", sdkVisibility1.getConfiguration("doc2Id").getSignerIds().toArray(), apiVisibility1.getConfigurations().get(1).getRoleUids().toArray());
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkVisibility1 = createTypicalSDKDocumentVisibility();
        apiVisibility1 = new DocumentVisibilityConverter(sdkVisibility1).toAPIDocumentVisibility();

        assertThat("Converter returned a null api object for a non null sdk object", apiVisibility1, notNullValue());
        assertThat("Document Visibility Configurations were not correctly set", apiVisibility1.getConfigurations(), hasSize(2));
        assertArrayEquals("Document Visibility Configurations were not correctly set", apiVisibility1.getConfigurations().get(0).getRoleUids().toArray(), sdkVisibility1.getConfiguration("doc1Id").getSignerIds().toArray());
        assertArrayEquals("Document Visibility Configurations were not correctly set", apiVisibility1.getConfigurations().get(1).getRoleUids().toArray(), sdkVisibility1.getConfiguration("doc2Id").getSignerIds().toArray());
    }

    private com.silanis.esl.sdk.DocumentVisibility createTypicalSDKDocumentVisibility() {
        return newDocumentVisibility()
            .addConfiguration(newDocumentVisibilityConfiguration("doc1Id")
                                  .withSignerIds(Arrays.asList("signer1Id", "signer2Id")))
            .addConfiguration(newDocumentVisibilityConfiguration("doc2Id")
                                  .withSignerIds(Arrays.asList("signer2Id", "signer3Id")))
            .build();
    }

    private com.silanis.esl.api.model.DocumentVisibility createTypicalAPIDocumentVisibility() {
        return new com.silanis.esl.api.model.DocumentVisibility()
            .addDocumentVisibilityConfiguration(new com.silanis.esl.api.model.DocumentVisibilityConfiguration()
                                                    .setDocumentUid("doc1Id")
                                                    .setRoleUids(Arrays.asList("role1Id", "role2Id")))
            .addDocumentVisibilityConfiguration(new com.silanis.esl.api.model.DocumentVisibilityConfiguration()
                                                    .setDocumentUid("doc2Id")
                                                    .setRoleUids(Arrays.asList("role2Id", "role3Id")));
    }
}
