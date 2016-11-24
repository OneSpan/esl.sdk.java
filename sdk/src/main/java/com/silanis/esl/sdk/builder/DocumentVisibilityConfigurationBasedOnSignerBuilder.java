package com.silanis.esl.sdk.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schoi on 11/30/16.
 */
public class DocumentVisibilityConfigurationBasedOnSignerBuilder {

    private String signerId;
    private List<String> documentIds = new ArrayList<String>();

    /**
     * <p>The constructor of this class.</p>
     *
     * @param signerId the ID of the signer to set a visibility configuration.
     */
    public DocumentVisibilityConfigurationBasedOnSignerBuilder(String signerId) {
        this.signerId = signerId;
    }

    /**
     * <p>Creates the document visibility configuration for a document.</p>
     *
     * @param signerId the ID of the signer to set a visibility configuration.
     * @return a document visibility configuration builder
     */
    public static DocumentVisibilityConfigurationBasedOnSignerBuilder newDocumentVisibilityConfigurationBasedOnSigner(String signerId) {
        return new DocumentVisibilityConfigurationBasedOnSignerBuilder(signerId);
    }

    /**
     * <p>Add documents to the document visibility configuration.</p>
     *
     * @param documentIds the visible documents for the signer
     * @return the visibility configuration builder itself
     */
    public DocumentVisibilityConfigurationBasedOnSignerBuilder withDocumentIds(List<String> documentIds) {
        this.documentIds.addAll(documentIds);
        return this;
    }

    /**
     * <p>Add a document to the document visibility configuration.</p>
     *
     * @param documentId the visible documents for the signer
     * @return the visibility configuration builder itself
     */
    public DocumentVisibilityConfigurationBasedOnSignerBuilder withDocumentId(String documentId) {
        this.documentIds.add(documentId);
        return this;
    }

    public String getSignerId() {
        return signerId;
    }

    public List<String> getDocumentIds() {
        return documentIds;
    }
}
