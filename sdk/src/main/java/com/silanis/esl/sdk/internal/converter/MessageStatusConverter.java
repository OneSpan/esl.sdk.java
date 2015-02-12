package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.MessageStatus;

/**
 * Created by lena on 2014-07-03.
 * <p/>
 * Converter between API and SDK MessageStatus.
 */
public class MessageStatusConverter {

    private String apiMessageStatus = null;
    private MessageStatus sdkMessageStatus = null;

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
    public MessageStatusConverter(MessageStatus sdkMessageStatus) {
        this.sdkMessageStatus = sdkMessageStatus;
    }

    /**
     * Convert from API MessageStatus to SDK MessageStatus.
     *
     * @return the SDK MessageStatus
     */
    public MessageStatus toSDKMessageStatus() {
        if (apiMessageStatus == null) {
            return sdkMessageStatus;
        }

        MessageStatus[] messageStatuses = MessageStatus.values();
        for (MessageStatus messageStatus : messageStatuses) {
            if(apiMessageStatus.equals(messageStatus.getApiValue())){
                return messageStatus;
            }
        }
        return MessageStatus.UNRECOGNIZED(apiMessageStatus);
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

        return sdkMessageStatus.getApiValue();
    }
}
