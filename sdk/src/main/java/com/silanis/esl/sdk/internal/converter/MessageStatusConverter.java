package com.silanis.esl.sdk.internal.converter;

/**
 * Created by lena on 2014-07-03.
 * <p/>
 * Converter between API and SDK MessageStatus.
 */
public class MessageStatusConverter {

    private String apiMessageStatus = null;
    private com.silanis.esl.sdk.MessageStatus sdkMessageStatus = null;

    /**
     * Construct with API MessageStatus object involved in conversion.
     *
     * @param apiMessageStatus
     */
    public MessageStatusConverter(String apiMessageStatus) {
        this.apiMessageStatus = apiMessageStatus;
    }

    /**
     * Construct with SDK MessageStatus object involved in conversion.
     *
     * @param sdkMessageStatus
     */
    public MessageStatusConverter(com.silanis.esl.sdk.MessageStatus sdkMessageStatus) {
        this.sdkMessageStatus = sdkMessageStatus;
    }

    /**
     * Convert from API MessageStatus to SDK MessageStatus.
     *
     * @return the SDK MessageStatus
     */
    public com.silanis.esl.sdk.MessageStatus toSDKMessageStatus() {
        if (apiMessageStatus == null) {
            return sdkMessageStatus;
        }

        if (apiMessageStatus.equals("NEW"))
            return sdkMessageStatus.NEW;
        else if (apiMessageStatus.equals("READ"))
            return sdkMessageStatus.READ;
        else if (apiMessageStatus.equals("TRASHED"))
            return sdkMessageStatus.TRASHED;
        else
            return sdkMessageStatus.UNRECOGNIZED(apiMessageStatus);
    }

    /**
     * Convert from SDK MessageStatus to API MessageStatus.
     *
     * @return the API MessageStatus
     */
    public String toAPIMessageStatus() {
        if (sdkMessageStatus == null) {
            return apiMessageStatus;
        }

        if(sdkMessageStatus.getValue().equals("NEW"))
            return "NEW";
        else if (sdkMessageStatus.getValue().equals("READ"))
            return "READ";
        else if (sdkMessageStatus.getValue().equals("TRASHED"))
            return "TRASHED";
        else
            return "";
    }
}
