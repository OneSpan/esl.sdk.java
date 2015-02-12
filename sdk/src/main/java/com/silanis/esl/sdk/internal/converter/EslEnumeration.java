package com.silanis.esl.sdk.internal.converter;

import java.util.logging.Logger;

/**
 * Created by schoi on 12/12/14.
 */
public abstract class EslEnumeration {
    protected static final Logger log = Logger.getLogger(EslEnumeration.class.getName());

    private final String sdkValue;
    private final String apiValue;

    protected EslEnumeration(String apiValue, String sdkValue) {
        this.apiValue = apiValue;
        this.sdkValue = sdkValue;
    }

    public String toString() {
        return name();
    }

    public String name() {
        return sdkValue;
    }

    protected String getApiValue() {
        return apiValue;
    }

}
