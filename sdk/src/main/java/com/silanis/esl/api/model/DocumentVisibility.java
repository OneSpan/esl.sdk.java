package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by schoi on 11/23/16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class DocumentVisibility extends Entity
    implements java.io.Serializable {

    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CONFIGURATIONS = "configurations";


    // Empty Constructor
    public DocumentVisibility () {}

    // Fields
    protected List<DocumentVisibilityConfiguration> _configurations = Lists.newArrayList();


    public DocumentVisibility setConfigurations(List<DocumentVisibilityConfiguration> value) {
        this._configurations = value;
        setDirty(FIELD_CONFIGURATIONS);
        return this;
    }

    public List<DocumentVisibilityConfiguration> getConfigurations() {
        return _configurations;
    }

    @JsonIgnore
    public DocumentVisibility safeSetConfigurations(List<DocumentVisibilityConfiguration> value) {
        if (value != null) {
            this.setConfigurations(value);
        }
        return this;
    }

    // List adder
    public DocumentVisibility addDocumentVisibilityConfiguration(DocumentVisibilityConfiguration value) {
        if (value == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        this._configurations.add(value);
        setDirty(FIELD_CONFIGURATIONS);
        return this;
    }

}
