package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.SenderType;

/**
 * Created by lena on 2014-05-29.
 *
 * Converter between API and SDK SenderType.
 */
public class SenderTypeConverter {

    private SenderType sdkSenderType = null;
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
    public SenderTypeConverter(SenderType sdkSenderType) {
        this.sdkSenderType = sdkSenderType;
    }

    public SenderType toSDKSenderType() {
        if (apiSenderType == null) {
            return sdkSenderType;
        }

        SenderType[] values = SenderType.values();
        for (SenderType value : values) {
            if(apiSenderType.equals(value.getApiValue())){
                return value;
            }
        }
        return SenderType.UNRECOGNIZED(apiSenderType);
    }

    public String toAPISenderType() {
        if (sdkSenderType == null) {
            return apiSenderType;
        }

        return sdkSenderType.getApiValue();
    }

}
