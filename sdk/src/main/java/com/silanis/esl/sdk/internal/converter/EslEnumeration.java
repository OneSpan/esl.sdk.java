package com.silanis.esl.sdk.internal.converter;

import java.util.logging.Logger;

/**
 * Created by schoi on 12/12/14.
 */
public abstract class EslEnumeration {
    protected static final Logger log = Logger.getLogger(EslEnumeration.class.getName());

    private final String sdkValue;
    private final String apiValue;
    private final int index;

    protected EslEnumeration(String apiValue, String sdkValue, int index) {
        this.apiValue = apiValue;
        this.sdkValue = sdkValue;
        this.index = index;
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

    public int ordinal() {
        return index;
    }

}
