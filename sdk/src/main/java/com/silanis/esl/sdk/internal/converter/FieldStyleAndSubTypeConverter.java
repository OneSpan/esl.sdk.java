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
        return sdkFieldStyle.getApiValue();
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
            sdkFieldStyle = FieldStyle.valueOf(apiFieldSubType);
            if (sdkFieldStyle == null)
                return FieldStyle.UNRECOGNIZED(apiFieldSubType);
            else
                return sdkFieldStyle;

        } else {
            String binding = apiFieldBinding;
            if ( binding.equals( BINDING_DATE ) )
                return FieldStyle.BOUND_DATE;
            else if ( binding.equals( BINDING_TITLE ) )
                return FieldStyle.BOUND_TITLE;
            else if ( binding.equals( BINDING_NAME ) )
                return FieldStyle.BOUND_NAME;
            else if ( binding.equals( BINDING_COMPANY ) )
                return FieldStyle.BOUND_COMPANY;
            else
                return FieldStyle.UNRECOGNIZED(apiFieldSubType);
        }
    }


}
