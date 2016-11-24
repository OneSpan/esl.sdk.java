package com.silanis.esl.sdk;

import com.google.common.base.Predicate;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Collections2.filter;

/**
 * Created by schoi on 11/23/16.
 */
public class DocumentVisibility {

    private List<DocumentVisibilityConfiguration> configurations = new ArrayList<DocumentVisibilityConfiguration>();

    public List<DocumentVisibilityConfiguration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<DocumentVisibilityConfiguration> configurations) {
        this.configurations = configurations;
    }

    public void addConfiguration(DocumentVisibilityConfiguration configuration) {
        this.configurations.add(configuration);
    }

    public List<Document> getDocuments(DocumentPackage documentPackage, final String signerId) {
        return new ArrayList<Document>(filter(documentPackage.getDocuments(), new Predicate<Document>() {
            @Override
            public boolean apply(Document input) {
                DocumentVisibilityConfiguration configuration = getConfiguration(input.getId().getId());
                return configuration != null && configuration.getSignerIds().contains(signerId);
            }
        }));
    }

    public List<Signer> getSigners(DocumentPackage documentPackage, String documentId) {
        final DocumentVisibilityConfiguration configuration = getConfiguration(documentId);
        return new ArrayList<Signer>(filter(documentPackage.getSigners(), new Predicate<Signer>() {
            @Override
            public boolean apply(Signer input) {
                return configuration.getSignerIds().contains(input.getId());
            }
        }));
    }

    public DocumentVisibilityConfiguration getConfiguration(String documentUid) {
        for (DocumentVisibilityConfiguration configuration : getConfigurations()) {
            if (StringUtils.equals(documentUid, configuration.getDocumentUid()))
                return configuration;
        }
        return null;
    }
}
