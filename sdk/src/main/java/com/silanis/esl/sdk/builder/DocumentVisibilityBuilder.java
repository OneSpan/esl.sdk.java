package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.DocumentVisibility;
import com.silanis.esl.sdk.DocumentVisibilityConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schoi on 11/23/16.
 */
public class DocumentVisibilityBuilder {

    private List<DocumentVisibilityConfiguration> configurations = new ArrayList<DocumentVisibilityConfiguration>();

    /**
     * <p>Creates the document visibility.</p>
     *
     * @return a document visibility builder
     */
    public static DocumentVisibilityBuilder newDocumentVisibility() {
        return new DocumentVisibilityBuilder();
    }

    /**
     * <p>Sets the document visibility configurations.</p>
     *
     * @param configurations the configuration of the document visibility to set.
     * @return a document visibility builder
     */
    public DocumentVisibilityBuilder withConfigurations(List<DocumentVisibilityConfiguration> configurations) {
        this.configurations = configurations;
        return this;
    }

    /**
     * <p>Adds the document visibility configuration.</p>
     *
     * @param configuration the configuration of the document visibility to add.
     * @return a document visibility builder
     */
    public DocumentVisibilityBuilder addConfiguration(DocumentVisibilityConfiguration configuration) {
        this.configurations.add(configuration);
        return this;
    }

    /**
     * <p>Adds the document visibility configuration.</p>
     *
     * @param builder the document visibility configuration builder
     * @return a document visibility builder
     */
    public DocumentVisibilityBuilder addConfiguration(DocumentVisibilityConfigurationBuilder builder) {
        return addConfiguration(builder.build());
    }

    /**
     * <p>Builds the actual document visibility</p>
     *
     * @return the document visibility
     */
    public DocumentVisibility build() {
        DocumentVisibility visibility = new DocumentVisibility();
        visibility.setConfigurations(configurations);
        return visibility;
    }
}
