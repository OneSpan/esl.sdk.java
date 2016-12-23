package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.DocumentVisibilityConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schoi on 11/23/16.
 */
public class DocumentVisibilityConfigurationBuilder {

    private String documentId;
    private List<String> signerIds = new ArrayList<String>();

    /**
     * <p>The constructor of this class.</p>
     *
     * @param documentId the ID of the document to set a visibility configuration.
     */
    public DocumentVisibilityConfigurationBuilder(String documentId) {
        this.documentId = documentId;
    }


    /**
     * <p>Creates the document visibility configuration for a document.</p>
     *
     * @param documentId the ID of the document to set a visibility configuration.
     * @return a document visibility configuration builder
     */
    public static DocumentVisibilityConfigurationBuilder newDocumentVisibilityConfiguration(String documentId) {
        return new DocumentVisibilityConfigurationBuilder(documentId);
    }

    /**
     * <p>Add signers to the document visibility configuration.</p>
     *
     * @param signerIds the signers that can not see the document
     * @return the visibility configuration builder itself
     */
    public DocumentVisibilityConfigurationBuilder withSignerIds(List<String> signerIds) {
        this.signerIds.addAll(signerIds);
        return this;
    }

    /**
     * <p>Add a signer to the document visibility configuration.</p>
     *
     * @param signerId the signer that can not see the document
     * @return the visibility configuration builder itself
     */
    public DocumentVisibilityConfigurationBuilder withSignerId(String signerId) {
        this.signerIds.add(signerId);
        return this;
    }

    /**
     * <p>Builds the actual document visibility configuration</p>
     *
     * @return the document visibility configuration
     */
    public DocumentVisibilityConfiguration build() {
        DocumentVisibilityConfiguration configuration = new DocumentVisibilityConfiguration();
        configuration.setDocumentUid(documentId);
        configuration.setSignerIds(signerIds);
        return configuration;
    }
}
