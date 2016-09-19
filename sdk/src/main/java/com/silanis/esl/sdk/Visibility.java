package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by schoi on 2/20/15.
 */
public class Visibility extends EslEnumeration {

    public static final Visibility ACCOUNT = new Visibility("ACCOUNT", "ACCOUNT", 0);
    public static final Visibility SENDER = new Visibility("SENDER", "SENDER", 1);

    /**
     * DO NOT USE! This is an internal implementation concern. It is there to avoid crashes in existing code when new values are added to the enumerations
     * by new versions of eSignLive. If you need access to those new values, you should upgrade your SDK version.
     * @deprecated Please upgrade your SDK version to support new types in this enumeration.
     */
    @Deprecated
    public static final Visibility UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Visibility(%s). The upgrade is required.", unknownValue));
        return new Visibility(unknownValue, unknownValue, values().length);
    }

    private static Map<String, Visibility> sdkValues;
    static {
        sdkValues = new HashMap<String, Visibility>();
        sdkValues.put(ACCOUNT.name(), ACCOUNT);
        sdkValues.put(SENDER.name(), SENDER);
    }

    private Visibility(String apiValue, String sdkValue, int index) {
        super(apiValue, sdkValue, index);
    }

    public static Visibility[] values() {
        return sdkValues.values().toArray(new Visibility[sdkValues.size()]);
    }

    public static Visibility valueOf(String name) {
        Visibility result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const Visibility." + name);
    }
}
