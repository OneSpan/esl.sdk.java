package com.silanis.esl.sdk;

/**
 * Extension types that can be requested when retrieving a package.
 */
public enum DocumentPackageRequestExtension {
    ALERTS("alerts");
    private final String value;

    DocumentPackageRequestExtension(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
