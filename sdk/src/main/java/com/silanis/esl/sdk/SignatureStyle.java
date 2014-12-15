package com.silanis.esl.sdk;

public enum SignatureStyle {
    ACCEPTANCE,
    HAND_DRAWN,
    FULL_NAME,
    INITIALS,
    MOBILE_CAPTURE;

    public static SignatureStyle fromAPIFieldSubType( String subtype ) {
        if("INITIALS".equals(subtype)) {
            return INITIALS;
        } else if ("CAPTURE".equals(subtype)) {
            return HAND_DRAWN;
        } else if ("FULLNAME".equals(subtype)) {
            return FULL_NAME;
        } else if ("MOBILE_CAPTURE".equals(subtype)) {
            return MOBILE_CAPTURE;
        } else {
            return null;
        }
    }
}
