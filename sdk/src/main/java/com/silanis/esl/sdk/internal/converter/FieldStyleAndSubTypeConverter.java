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
    private static final String BINDING_DATE = "{approval.signed}";
    private static final String BINDING_TITLE = "{signer.title}";
    private static final String BINDING_NAME = "{signer.name}";
    private static final String BINDING_COMPANY = "{signer.company}";

    FieldStyle sdkFieldStyle = null;
    FieldSubtype apiFieldSubType = null;
    String apiFieldBinding = null;

    /**
     * construct with SDK object involved in conversion.
     *
     * @param sdkFieldStyle
     */
    public FieldStyleAndSubTypeConverter(FieldStyle sdkFieldStyle) {
        this.sdkFieldStyle = sdkFieldStyle;
    }

    /**
     * Construct with API object involved in conversion.
     *
     * @param apiFieldSubType
     * @param apiFieldBinding
     */
    public FieldStyleAndSubTypeConverter(FieldSubtype apiFieldSubType, String apiFieldBinding) {
        this.apiFieldSubType = apiFieldSubType;
        this.apiFieldBinding = apiFieldBinding;
    }

    /**
     * Concert from SDK to API.
     *
     * @return a FieldSubType object.
     */
    public FieldSubtype toAPIFieldSubtype() {
        if (sdkFieldStyle == null) {
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
            case UNBOUND_RADIO_BUTTON:
                return FieldSubtype.RADIO;
            case BOUND_QRCODE:
                return FieldSubtype.QRCODE;
            default:
                throw new ConversionException( com.silanis.esl.sdk.FieldStyle.class, com.silanis.esl.api.model.FieldSubtype.class, "Unable to decode the field subtype." );
        }
    }

    /**
     * Convert from API to SDK.
     *
     * @return a FieldStyle object.
     */
    public FieldStyle toSDKFieldStyle() {
        if (apiFieldSubType == null && apiFieldBinding == null) {
            return sdkFieldStyle;
        }

        if ( apiFieldBinding == null ) {
            switch ( apiFieldSubType ) {
                case TEXTFIELD:
                    return FieldStyle.UNBOUND_TEXT_FIELD;
                case CUSTOMFIELD:
                    return FieldStyle.UNBOUND_CUSTOM_FIELD;
                case CHECKBOX:
                    return FieldStyle.UNBOUND_CHECK_BOX;
                case RADIO:
                    return FieldStyle.UNBOUND_RADIO_BUTTON;
                case QRCODE:
                    return FieldStyle.BOUND_QRCODE;
                default: {
                    throw new ConversionException( com.silanis.esl.api.model.FieldSubtype.class, com.silanis.esl.sdk.FieldStyle.class, "Unable to decode the field subtype." );
                }
            }
        } else {
            String binding = apiFieldBinding;
            if ( binding.equals( BINDING_DATE ) ) {
                return FieldStyle.BOUND_DATE;
            } else if ( binding.equals( BINDING_TITLE ) ) {
                return FieldStyle.BOUND_TITLE;
            } else if ( binding.equals( BINDING_NAME ) ) {
                return FieldStyle.BOUND_NAME;
            } else if ( binding.equals( BINDING_COMPANY ) ) {
                return FieldStyle.BOUND_COMPANY;
            } else {
                throw new ConversionException( com.silanis.esl.api.model.FieldSubtype.class, com.silanis.esl.sdk.FieldStyle.class, "Unable to decode the field subtype." );
            }
        }
    }


}
