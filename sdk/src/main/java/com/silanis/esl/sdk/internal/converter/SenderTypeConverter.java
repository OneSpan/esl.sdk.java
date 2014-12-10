package com.silanis.esl.sdk.internal.converter;

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

        if (apiSenderType.equals("MANAGER"))
            return sdkSenderType.MANAGER;
        else if (apiSenderType.equals("REGULAR"))
            return sdkSenderType.REGULAR;
        else
            return sdkSenderType.UNRECOGNIZED(apiSenderType);
    }

    public String toAPISenderType() {
        if (sdkSenderType == null) {
            return apiSenderType;
        }

        if(sdkSenderType.getValue().equals("MANAGER"))
            return "MANAGER";
        else if (sdkSenderType.getValue().equals("REGULAR"))
            return "REGULAR";
        else
            return "";
    }

}
