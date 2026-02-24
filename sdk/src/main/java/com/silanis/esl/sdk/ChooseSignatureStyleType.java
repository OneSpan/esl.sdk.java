package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

public class ChooseSignatureStyleType extends EslEnumeration {
    public static final ChooseSignatureStyleType STYLE = new ChooseSignatureStyleType("STYLE", "STYLE", 0);
    public static final ChooseSignatureStyleType DRAW = new ChooseSignatureStyleType("DRAW", "DRAW", 1);
    public static final ChooseSignatureStyleType UPLOAD = new ChooseSignatureStyleType("UPLOAD", "UPLOAD", 2);
    public static final ChooseSignatureStyleType MOBILE = new ChooseSignatureStyleType("MOBILE", "MOBILE", 3);

    private static Map<String, ChooseSignatureStyleType> sdkValues;
    static {
        sdkValues = new HashMap<String, ChooseSignatureStyleType>();
        sdkValues.put(STYLE.name(), STYLE);
        sdkValues.put(DRAW.name(), DRAW);
        sdkValues.put(UPLOAD.name(), UPLOAD);
        sdkValues.put(MOBILE.name(), MOBILE);
    }

    private ChooseSignatureStyleType(String apiValue, String sdkValue, int index) {
        super(apiValue, sdkValue, index);
    }

    public static ChooseSignatureStyleType[] values() {
        return sdkValues.values().toArray(new ChooseSignatureStyleType[sdkValues.size()]);
    }

    public static ChooseSignatureStyleType valueOf(String name) {
        ChooseSignatureStyleType result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const ChooseSignatureStyleType." + name);
    }

}
