package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.BuilderException;

/**
 * Created by lena on 2014-05-29.
 *
 * Converter between API and SDK SenderType.
 */
public class SenderTypeConverter {

    private com.silanis.esl.sdk.SenderType sdkSenderType = null;
    private com.silanis.esl.api.model.SenderType apiSenderType = null;

    /**
     * Construct with API SenderType object involved in conversion.
     *
     * @param apiSenderType
     */
    public SenderTypeConverter(com.silanis.esl.api.model.SenderType apiSenderType) {
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

        switch (apiSenderType) {
            case MANAGER:
                return sdkSenderType.MANAGER;
            case REGULAR:
                return sdkSenderType.REGULAR;
            default:
                throw new BuilderException("Unrecognized sender type.");
        }
    }

    public com.silanis.esl.api.model.SenderType toAPISenderType() {
        if (sdkSenderType == null) {
            return apiSenderType;
        }

        switch (sdkSenderType) {
            case MANAGER:
                return apiSenderType.MANAGER;
            case REGULAR:
                return apiSenderType.REGULAR;
            default:
                throw new BuilderException("Unrecognized sender type.");
        }
    }

}
