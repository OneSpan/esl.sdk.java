package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.FieldSubtype;
import com.silanis.esl.sdk.FieldStyle;
import com.silanis.esl.sdk.internal.ConversionException;

/**
 * User: jessica
 * Date: 19/11/13
 * Time: 11:32 AM
 *
 * Converter between FieldStyle and FieldSubtype.
 */

public class FieldStyleAndSubTypeConverter {

    FieldStyle sdkFieldStyle = null;
    FieldSubtype apiFieldSubType = null;

    public FieldStyleAndSubTypeConverter(FieldStyle sdkFieldStyle) {
        this.sdkFieldStyle = sdkFieldStyle;
    }

    public FieldStyleAndSubTypeConverter(FieldSubtype apiFieldSubType) {
        this.apiFieldSubType = apiFieldSubType;
    }

    public FieldSubtype toAPIFieldSubtype() {
        if (apiFieldSubType != null) {
            return apiFieldSubType;
        }
        switch (sdkFieldStyle) {
            case UNBOUND_TEXT_FIELD:
                return FieldSubtype.TEXTFIELD;
            case UNBOUND_CUSTOM_FIELD:
                return FieldSubtype.CUSTOMFIELD;
            case BOUND_DATE:
            case BOUND_NAME:
            case BOUND_TITLE:
            case BOUND_COMPANY:
            case LABEL:
                return FieldSubtype.LABEL;
            case UNBOUND_CHECK_BOX:
                return FieldSubtype.CHECKBOX;
            default:
                throw new ConversionException( com.silanis.esl.sdk.FieldStyle.class, com.silanis.esl.api.model.FieldSubtype.class, "Unable to decode the field subtype." );
        }
    }

    public FieldStyle toSDKFieldStyle() {
        if (sdkFieldStyle!= null) {
            return sdkFieldStyle;
        }

        switch (apiFieldSubType) {
            case TEXTFIELD:
                return FieldStyle.UNBOUND_TEXT_FIELD;
            case FULLNAME:
                return FieldStyle.BOUND_NAME;
            case LABEL:
                return FieldStyle.LABEL;
            case CUSTOMFIELD:
                return FieldStyle.UNBOUND_CUSTOM_FIELD;
            case CHECKBOX:
                return FieldStyle.UNBOUND_CHECK_BOX;
            case DATE:
                return FieldStyle.BOUND_DATE;
            case TEXTAREA:
            case INITIALS:
            case CAPTURE:
            case RADIO:
            case LIST:
            case QRCODE:
            default:
                throw new ConversionException( com.silanis.esl.api.model.FieldSubtype.class, com.silanis.esl.sdk.FieldStyle.class, "Unable to decode the field subtype." );
        }
    }


}
