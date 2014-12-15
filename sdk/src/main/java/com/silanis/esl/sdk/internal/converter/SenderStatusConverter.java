package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.SenderStatus;

/**
 * Created by lena on 2014-05-30.
 *
 * Converter between API and SDK SenderStatus.
 */
public class SenderStatusConverter {

    private com.silanis.esl.sdk.SenderStatus sdkSenderStatus = null;
    private String apiSenderStatus = null;

    /**
     * Construct with API SenderStatus object involved in conversion.
     *
     * @param apiSenderStatus
     */
    public SenderStatusConverter(String apiSenderStatus) {
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

        sdkSenderStatus = SenderStatus.valueOf(apiSenderStatus);
        if (sdkSenderStatus == null)
            return SenderStatus.UNRECOGNIZED(apiSenderStatus);
        else
            return sdkSenderStatus;
    }

    public String toAPISenderStatus() {
        if (sdkSenderStatus == null) {
            return apiSenderStatus;
        }

        return sdkSenderStatus.getApiValue();
    }

}
