package com.silanis.esl.sdk.internal.converter;

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

        if (apiSenderStatus.equals("INVITED"))
            return sdkSenderStatus.INVITED;
        else if (apiSenderStatus.equals("ACTIVE"))
            return sdkSenderStatus.ACTIVE;
        else if (apiSenderStatus.equals("LOCKED"))
            return sdkSenderStatus.LOCKED;
        else
            return sdkSenderStatus.UNRECOGNIZED(apiSenderStatus);
    }

    public String toAPISenderStatus() {
        if (sdkSenderStatus == null) {
            return apiSenderStatus;
        }

        if(sdkSenderStatus.getValue().equals("INVITED"))
            return "INVITED";
        else if (sdkSenderStatus.getValue().equals("ACTIVE"))
            return "ACTIVE";
        else if (sdkSenderStatus.getValue().equals("LOCKED"))
            return "LOCKED";
        else if (sdkSenderStatus.getValue().equals("UNRECOGNIZED"))
            return sdkSenderStatus.getUnknownValue();
        else
            return "";
    }

}
