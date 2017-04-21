package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;

public class ExternalProviderKey extends EslEnumeration {

    public static final ExternalProviderKey DIGIPASS = new ExternalProviderKey("DIGIPASS", "DIGIPASS", 0);

    /**
     * DO NOT USE! This is an internal implementation concern. It is there to avoid crashes in existing code when new values are added to the enumerations
     * by new versions of eSignLive. If you need access to those new values, you should upgrade your SDK version.
     * @deprecated Please upgrade your SDK version to support new types in this enumeration.
     */
    @Deprecated
    public static final ExternalProviderKey UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API FieldSubtype(%s). The upgrade is required.", unknownValue));
        return new ExternalProviderKey(unknownValue, unknownValue, values().length);
    }

    private static Map<String, ExternalProviderKey> sdkValues;
    static {
        sdkValues = new HashMap<String, ExternalProviderKey>();
        sdkValues.put(DIGIPASS.name(), DIGIPASS);
    }

    private ExternalProviderKey(String apiValue, String sdkValue, int index) {
        super(apiValue, sdkValue, index);
    }

    public static ExternalProviderKey fromAPIExternalProviderKey( String subtype ) {
        if (subtype == null) {
            return null;
        }

        for(ExternalProviderKey externalProviderKey: values()) {
            if(subtype.equals(externalProviderKey.getApiValue())){
                return externalProviderKey;
            }
        }
        return ExternalProviderKey.UNRECOGNIZED(subtype);
    }

    public static ExternalProviderKey[] values() {
        return sdkValues.values().toArray(new ExternalProviderKey[sdkValues.size()]);
    }

    public static ExternalProviderKey valueOf(String name) {
        ExternalProviderKey result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const ExternalProviderKey." + name);
    }
}