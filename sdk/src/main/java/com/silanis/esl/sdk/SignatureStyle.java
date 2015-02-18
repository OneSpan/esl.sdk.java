package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;

public class SignatureStyle extends EslEnumeration {

    public static final SignatureStyle ACCEPTANCE = new SignatureStyle("FULLNAME", "ACCEPTANCE", 0);
    public static final SignatureStyle HAND_DRAWN = new SignatureStyle("CAPTURE", "HAND_DRAWN", 1);
    public static final SignatureStyle FULL_NAME = new SignatureStyle("FULLNAME", "FULL_NAME", 2);
    public static final SignatureStyle INITIALS = new SignatureStyle("INITIALS", "INITIALS", 3);
    public static final SignatureStyle MOBILE_CAPTURE = new SignatureStyle("MOBILE_CAPTURE", "MOBILE_CAPTURE", 4);

    /**
     * DO NOT USE! This is an internal implementation concern. It is there to avoid crashes in existing code when new values are added to the enumerations
     * by new versions of e-SignLive. If you need access to those new values, you should upgrade your SDK version.
     * @deprecated Please upgrade your SDK version to support new types in this enumeration.
     */
    @Deprecated
    public static final SignatureStyle UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API FieldSubtype(%s). The upgrade is required.", unknownValue));
        return new SignatureStyle(unknownValue, unknownValue, values().length);
    }

    private static Map<String, SignatureStyle> sdkValues;
    static {
        sdkValues = new HashMap<String, SignatureStyle>();
        sdkValues.put(ACCEPTANCE.name(), ACCEPTANCE);
        sdkValues.put(HAND_DRAWN.name(), HAND_DRAWN);
        sdkValues.put(FULL_NAME.name(), FULL_NAME);
        sdkValues.put(INITIALS.name(), INITIALS);
        sdkValues.put(MOBILE_CAPTURE.name(), MOBILE_CAPTURE);
    }

    private SignatureStyle(String apiValue, String sdkValue, int index) {
        super(apiValue, sdkValue, index);
    }

    public static SignatureStyle fromAPIFieldSubType( String subtype ) {
        if (subtype == null) {
            return null;
        }

        for(SignatureStyle signatureStyle: values()) {
            if(subtype.equals(signatureStyle.getApiValue())){
                return signatureStyle;
            }
        }
        return SignatureStyle.UNRECOGNIZED(subtype);
    }

    public static SignatureStyle[] values() {
        return sdkValues.values().toArray(new SignatureStyle[sdkValues.size()]);
    }

    public static SignatureStyle valueOf(String name) {
        SignatureStyle result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const SignatureStyle." + name);
    }
}