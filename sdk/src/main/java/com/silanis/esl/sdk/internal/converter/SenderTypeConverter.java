package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.SenderType;

/**
 * Created by lena on 2014-05-29.
 *
 * Converter between API and SDK SenderType.
 */
public class SenderTypeConverter {

    private com.silanis.esl.sdk.SenderType sdkSenderType = null;
    private String apiSenderType = null;

    /**
     * Construct with API SenderType object involved in conversion.
     *
     * @param apiSenderType
     */
    public SenderTypeConverter(String apiSenderType) {
        this.apiSenderType = apiSenderType;
    }

    /**
     * Construct with SDK SenderType object involved in conversion.
     *
     * @param sdkSenderType
     */
    public SenderTypeConverter(com.silanis.esl.sdk.SenderType sdkSenderType) {
        this.sdkSenderType = sdkSenderType;
    }

    public com.silanis.esl.sdk.SenderType toSDKSenderType() {
        if (apiSenderType == null) {
            return sdkSenderType;
        }

        sdkSenderType = SenderType.values().get(apiSenderType);
        if (sdkSenderType == null)
            return SenderType.UNRECOGNIZED(apiSenderType);
        else
            return sdkSenderType;
    }

    public String toAPISenderType() {
        if (sdkSenderType == null) {
            return apiSenderType;
        }

        return sdkSenderType.toString();
    }

}
