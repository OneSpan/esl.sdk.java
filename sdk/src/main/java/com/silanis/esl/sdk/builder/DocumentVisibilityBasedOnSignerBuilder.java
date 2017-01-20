package com.silanis.esl.sdk.builder;

import com.google.common.base.Optional;
import com.silanis.esl.sdk.DocumentVisibility;
import com.silanis.esl.sdk.DocumentVisibilityConfiguration;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.silanis.esl.sdk.builder.DocumentVisibilityConfigurationBuilder.newDocumentVisibilityConfiguration;

/**
 * Created by schoi on 11/30/16.
 */
public class DocumentVisibilityBasedOnSignerBuilder {

    private List<DocumentVisibilityConfiguration> configurations = new ArrayList<DocumentVisibilityConfiguration>();
    private List<DocumentVisibilityConfigurationBasedOnSignerBuilder> configurationBuilders = new ArrayList<DocumentVisibilityConfigurationBasedOnSignerBuilder>();

    /**
     * <p>Creates the document visibility.</p>
     *
     * @return a document visibility based on signer builder
     */
    public static DocumentVisibilityBasedOnSignerBuilder newDocumentVisibilityBasedOnSigner() {
        return new DocumentVisibilityBasedOnSignerBuilder();
    }

    /**
     * <p>Sets the document visibility configurations.</p>
     *
     * @param configurationBuilders the configuration based on signer builders of the document visibility to set.
     * @return a document visibility based on signer builder
     */
    public DocumentVisibilityBasedOnSignerBuilder withConfigurations(List<DocumentVisibilityConfigurationBasedOnSignerBuilder> configurationBuilders) {
        this.configurationBuilders = configurationBuilders;
        return this;
    }

    /**
     * <p>Adds the document visibility configuration.</p>
     *
     * @param builder the document visibility configuration based on signer builder
     * @return a document visibility based on signer builder
     */
    public DocumentVisibilityBasedOnSignerBuilder addConfiguration(DocumentVisibilityConfigurationBasedOnSignerBuilder builder) {
        this.configurationBuilders.add(builder);
        return this;
    }

    /**
     * <p>Builds the actual document visibility</p>
     *
     * @return the document visibility
     */
    public DocumentVisibility build() {
        DocumentVisibility visibility = new DocumentVisibility();
        visibility.setConfigurations(convertToDocumentVisibilityConfigurations());
        return visibility;
    }

    private List<DocumentVisibilityConfiguration> convertToDocumentVisibilityConfigurations() {
        if (configurationBuilders == null || configurationBuilders.isEmpty())
            return Collections.emptyList();

        for (DocumentVisibilityConfigurationBasedOnSignerBuilder builder : configurationBuilders) {
            for (String documentId : builder.getDocumentIds()) {
                mergeConfiguration(newDocumentVisibilityConfiguration(documentId).withSignerId(builder.getSignerId()).build());
            }
        }

        return configurations;
    }

    private void mergeConfiguration(DocumentVisibilityConfiguration configuration) {
        Optional<DocumentVisibilityConfiguration> configurationOptional = findSameDocumentIdConfiguration(configuration);
        if (configurationOptional.isPresent()) {
            List<String> signerIds = configurationOptional.get().getSignerIds();
            signerIds.removeAll(configuration.getSignerIds());
            signerIds.addAll(configuration.getSignerIds());
        } else {
            configurations.add(configuration);
        }
    }

    private Optional<DocumentVisibilityConfiguration> findSameDocumentIdConfiguration(DocumentVisibilityConfiguration configuration) {
        for (DocumentVisibilityConfiguration documentVisibilityConfiguration : configurations) {
            if (StringUtils.equals(documentVisibilityConfiguration.getDocumentUid(), configuration.getDocumentUid())) {
                return Optional.of(documentVisibilityConfiguration);
            }
        }
        return Optional.absent();
    }
}
