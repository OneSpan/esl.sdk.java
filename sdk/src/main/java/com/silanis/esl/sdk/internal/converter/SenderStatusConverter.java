package com.silanis.esl.sdk.internal.converter;

/**
 * Created by lena on 2014-05-30.
 *
 * Converter between API and SDK SenderStatus.
 */
public class SenderStatusConverter {

    private com.silanis.esl.sdk.SenderStatus sdkSenderStatus = null;
    private com.silanis.esl.api.model.SenderStatus apiSenderStatus = null;

    /**
     * Construct with API SenderStatus object involved in conversion.
     *
     * @param apiSenderStatus
     */
    public SenderStatusConverter(com.silanis.esl.api.model.SenderStatus apiSenderStatus) {
        this.apiSenderStatus = apiSenderStatus;
    }

    /**
     * Construct with SDK SenderStatus object involved in conversion.
     *
     * @param sdkSenderStatus
     */
    public SenderStatusConverter(com.silanis.esl.sdk.SenderStatus sdkSenderStatus) {
        this.sdkSenderStatus = sdkSenderStatus;
    }

    public com.silanis.esl.sdk.SenderStatus toSDKSenderStatus() {
        if (apiSenderStatus == null) {
            return sdkSenderStatus;
        }

        switch (apiSenderStatus) {
            case INVITED:
                return sdkSenderStatus.INVITED;
            case ACTIVE:
                return sdkSenderStatus.ACTIVE;
            case LOCKED:
                return sdkSenderStatus.LOCKED;
            default:
                return sdkSenderStatus;
        }
    }

    public com.silanis.esl.api.model.SenderStatus toAPISenderStatus() {
        if (sdkSenderStatus == null) {
            return apiSenderStatus;
        }

        switch (sdkSenderStatus) {
            case INVITED:
                return apiSenderStatus.INVITED;
            case ACTIVE:
                return apiSenderStatus.ACTIVE;
            case LOCKED:
                return apiSenderStatus.LOCKED;
            default:
                return apiSenderStatus;
        }
    }

}
