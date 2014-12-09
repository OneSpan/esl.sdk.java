package com.silanis.esl.sdk;

public enum SignatureStyle {
    ACCEPTANCE,
    HAND_DRAWN,
    FULL_NAME,
    INITIALS,
    MOBILE_CAPTURE;

    public static SignatureStyle fromAPIFieldSubType( String subtype ) {
        if (subtype == null) {
            return null;
        }
        if(subtype.equals("INITIALS")) {
            return INITIALS;
        } else if (subtype.equals("CAPTURE")) {
            return HAND_DRAWN;
        } else if (subtype.equals("FULLNAME")) {
            return FULL_NAME;
        } else if (subtype.equals("MOBILE_CAPTURE")) {
            return MOBILE_CAPTURE;
        } else {
            return null;
        }
    }
}
