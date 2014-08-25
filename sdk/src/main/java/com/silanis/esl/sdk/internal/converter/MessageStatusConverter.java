package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.BuilderException;

/**
 * Created by lena on 2014-07-03.
 * <p/>
 * Converter between API and SDK MessageStatus.
 */
public class MessageStatusConverter {

    private com.silanis.esl.api.model.MessageStatus apiMessageStatus = null;
    private com.silanis.esl.sdk.MessageStatus sdkMessageStatus = null;

    /**
     * Construct with API MessageStatus object involved in conversion.
     *
     * @param apiMessageStatus
     */
    public MessageStatusConverter(com.silanis.esl.api.model.MessageStatus apiMessageStatus) {
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

        switch (apiMessageStatus) {
            case NEW:
                return sdkMessageStatus.NEW;
            case READ:
                return sdkMessageStatus.READ;
            case TRASHED:
                return sdkMessageStatus.TRASHED;
            default:
                throw new BuilderException("Unrecognized message status type.");
        }
    }

    /**
     * Convert from SDK MessageStatus to API MessageStatus.
     *
     * @return the API MessageStatus
     */
    public com.silanis.esl.api.model.MessageStatus toAPIMessageStatus() {
        if (sdkMessageStatus == null) {
            return apiMessageStatus;
        }

        switch (sdkMessageStatus) {
            case NEW:
                return apiMessageStatus.NEW;
            case READ:
                return apiMessageStatus.READ;
            case TRASHED:
                return apiMessageStatus.TRASHED;
            default:
                throw new BuilderException("Unrecognized message status type.");
        }
    }
}
