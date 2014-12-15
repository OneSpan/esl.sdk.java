package com.silanis.esl.sdk;

import com.silanis.esl.api.util.JacksonUtil;

import java.util.logging.Logger;

/**
 * Created by schoi on 12/12/14.
 */
public abstract class EslEnumeration {
    private static final String CLASS = JacksonUtil.class.getName();
    protected static Logger log = Logger.getLogger(CLASS);
    private final String apiValue;

    protected EslEnumeration(String apiValue) {
        this.apiValue = apiValue;
    }

    public String toString() {
        return apiValue;
    }

    public String name() {
        return toString();
    }

}
