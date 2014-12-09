package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.FieldStyle;

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
    String apiFieldSubType = null;
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
    public FieldStyleAndSubTypeConverter(String apiFieldSubType, String apiFieldBinding) {
        this.apiFieldSubType = apiFieldSubType;
        this.apiFieldBinding = apiFieldBinding;
    }

    /**
     * Concert from SDK to API.
     *
     * @return a FieldSubType object.
     */
    public String toAPIFieldSubtype() {
        if (sdkFieldStyle == null) {
            return apiFieldSubType;
        }
        if(sdkFieldStyle.getValue().equals("TEXTFIELD"))
            return "TEXTFIELD";
        else if (sdkFieldStyle.getValue().equals("CUSTOMFIELD"))
                return "CUSTOMFIELD";
        else if (sdkFieldStyle.getValue().equals("BINDING_DATE") ||
                sdkFieldStyle.getValue().equals("BINDING_NAME") ||
                sdkFieldStyle.getValue().equals("BINDING_TITLE") ||
                sdkFieldStyle.getValue().equals("BINDING_COMPANY") ||
                sdkFieldStyle.getValue().equals("LABEL"))
            return "LABEL";
        else if (sdkFieldStyle.getValue().equals("CHECKBOX"))
            return "CHECKBOX";
        else if (sdkFieldStyle.getValue().equals("RADIO"))
            return "RADIO";
        else if (sdkFieldStyle.getValue().equals("TEXTAREA"))
            return "TEXTAREA";
        else if (sdkFieldStyle.getValue().equals("LIST"))
            return "LIST";
        else if (sdkFieldStyle.getValue().equals("QRCODE"))
            return "QRCODE";
        else if (sdkFieldStyle.getValue().equals("SEAL"))
            return "SEAL";
        else
            return "";
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
            if (apiFieldSubType.equals("TEXTFIELD"))
                return FieldStyle.TEXTFIELD;
            else if (apiFieldSubType.equals("CUSTOMFIELD"))
                return FieldStyle.CUSTOMFIELD;
            else if (apiFieldSubType.equals("CHECKBOX"))
                return FieldStyle.CHECKBOX;
            else if (apiFieldSubType.equals("RADIO"))
                return FieldStyle.RADIO;
            else if (apiFieldSubType.equals("TEXTAREA"))
                return FieldStyle.TEXTAREA;
            else if (apiFieldSubType.equals("LIST"))
                return FieldStyle.LIST;
            else if (apiFieldSubType.equals("QRCODE"))
                return FieldStyle.QRCODE;
            else if (apiFieldSubType.equals("SEAL"))
                return FieldStyle.SEAL;
            else
                return FieldStyle.UNRECOGNIZED(apiFieldSubType);
        } else {
            String binding = apiFieldBinding;
            if ( binding.equals( BINDING_DATE ) )
                return FieldStyle.BINDING_DATE;
            else if ( binding.equals( BINDING_TITLE ) )
                return FieldStyle.BINDING_TITLE;
            else if ( binding.equals( BINDING_NAME ) )
                return FieldStyle.BINDING_NAME;
            else if ( binding.equals( BINDING_COMPANY ) )
                return FieldStyle.BINDING_COMPANY;
            else
                return FieldStyle.UNRECOGNIZED(apiFieldSubType);
        }
    }


}
