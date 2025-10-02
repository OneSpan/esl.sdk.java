package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.silanis.esl.sdk.MessageStatus;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by lena on 2014-07-03.
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

        try {
            return Iterables.find(Arrays.asList(MessageStatus.values()), new Predicate<MessageStatus>() {
                public boolean apply(MessageStatus messageStatus) {
                    return apiMessageStatus.equals(messageStatus.getApiValue());
                }
            });
        } catch (NoSuchElementException e) {
            return MessageStatus.UNRECOGNIZED(apiMessageStatus);
        }
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
