package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NotificationMethod extends EslEnumeration {

    public static final NotificationMethod EMAIL = new NotificationMethod("EMAIL", "EMAIL", 0);
    public static final NotificationMethod SMS = new NotificationMethod("SMS", "SMS", 1);

    public NotificationMethod(String apiValue, String sdkValue, int index) {
        super(apiValue, sdkValue, index);
    }


    @Deprecated
    public static final NotificationMethod UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API AuthScheme(%s). The upgrade is required.", unknownValue));
        return new NotificationMethod(unknownValue, unknownValue, values().length);
    }


    private static Map<String, NotificationMethod> sdkValues;
    static {
        sdkValues = new HashMap<String, NotificationMethod>();
        sdkValues.put(EMAIL.name(), EMAIL);
        sdkValues.put(SMS.name(), SMS);
    }

    public static NotificationMethod[] values() {
        return sdkValues.values().toArray(new NotificationMethod[sdkValues.size()]);
    }


    public static NotificationMethod valueOf(String name) {
        NotificationMethod result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const NotificationMethod." + name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NotificationMethod that = (NotificationMethod) obj;
        return Objects.equals(this.getApiValue(), that.getApiValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getApiValue());
    }
}
