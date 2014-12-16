package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.SenderStatus;

/**
 * Created by lena on 2014-05-30.
 *
 * Converter between API and SDK SenderStatus.
 */
public class SenderStatusConverter {

    private SenderStatus sdkSenderStatus = null;
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
    public SenderStatusConverter(SenderStatus sdkSenderStatus) {
        this.sdkSenderStatus = sdkSenderStatus;
    }

    public SenderStatus toSDKSenderStatus() {
        if (apiSenderStatus == null) {
            return sdkSenderStatus;
        }

        SenderStatus[] values = SenderStatus.values();
        for (SenderStatus value : values) {
            if(apiSenderStatus.equals(value.getApiValue())){
                return value;
            }
        }
        return SenderStatus.UNRECOGNIZED(apiSenderStatus);
    }

    public String toAPISenderStatus() {
        if (sdkSenderStatus == null) {
            return apiSenderStatus;
        }

        return sdkSenderStatus.getApiValue();
    }

}
