package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Link extends Handover
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_AUTO_REDIRECT = "autoRedirect";
    @JsonIgnore
    public static final String FIELD_PARAMETERS = "parameters";
    
    // Empty Constructor
    public Link ( ) {}
    
    // Fields
    protected boolean autoRedirect = false;
    protected Set<String> parameters = new HashSet<String>(Arrays.asList(UrlParameter.PACKAGE.name(), UrlParameter.SIGNER.name(), UrlParameter.STATUS.name()));


    public Link setAutoRedirect(boolean value) {
        this.autoRedirect = value;
        setDirty(FIELD_AUTO_REDIRECT);
        return this;
    }

    public boolean getAutoRedirect() {
        return autoRedirect;
    }

    public Link setParameters(Set<String> value) {
        this.parameters = value;
        setDirty(FIELD_PARAMETERS);
        return this;
    }

    @JsonIgnore
    public Link safeSetParameters(Set<String> value) {
        if (value != null) {
            this.setParameters(value);
        }
        return this;
    }

    public Set<String> getParameters() {
        return parameters;
    }

    public enum UrlParameter {
        PACKAGE,SIGNER,STATUS
    }
}