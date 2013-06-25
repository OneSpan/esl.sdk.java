package com.silanis.esl.sdk;

import com.silanis.awsng.web.rest.model.FieldSubtype;

public enum SignatureStyle {
    ACCEPTANCE,
    HAND_DRAWN,
    FULL_NAME,
    INITIALS;

    public static SignatureStyle fromAPIFieldSubType( FieldSubtype subtype ) {
        switch( subtype ) {
            case INITIALS:
                return INITIALS;
            case CAPTURE:
                return HAND_DRAWN;
            case FULLNAME:
                return FULL_NAME;
            default:
                return null;
        }
    }
}
