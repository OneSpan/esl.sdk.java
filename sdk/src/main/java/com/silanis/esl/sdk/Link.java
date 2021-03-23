package com.silanis.esl.sdk;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Link extends Handover implements Serializable {
    private boolean autoRedirect = false;
    private Set<String> parameters = new HashSet<String>(Arrays.asList(UrlParameter.PACKAGE.name(), UrlParameter.SIGNER.name(), UrlParameter.STATUS.name()));

    public boolean isAutoRedirect() {
        return autoRedirect;
    }

    public void setAutoRedirect(boolean autoRedirect) {
        this.autoRedirect = autoRedirect;
    }

    public Set<String> getParameters() {
        return parameters;
    }

    public void setParameters(Set<String> parameters) {
        this.parameters = parameters;
    }

    public enum UrlParameter {
        PACKAGE,SIGNER,STATUS
    }
}