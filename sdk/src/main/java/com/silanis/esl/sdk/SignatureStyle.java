package com.silanis.esl.sdk;

import com.silanis.esl.api.model.FieldSubtype;

public enum SignatureStyle {
    ACCEPTANCE,
    HAND_DRAWN,
    FULL_NAME,
    INITIALS,
    MOBILE_CAPTURE;

    public static SignatureStyle fromAPIFieldSubType( FieldSubtype subtype ) {
        switch( subtype ) {
            case INITIALS:
                return INITIALS;
            case CAPTURE:
                return HAND_DRAWN;
            case FULLNAME:
                return FULL_NAME;
            case MOBILE_CAPTURE:
                return MOBILE_CAPTURE;
            default:
                return null;
        }
    }
}
