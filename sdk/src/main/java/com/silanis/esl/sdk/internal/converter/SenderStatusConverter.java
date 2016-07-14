package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.silanis.esl.sdk.SenderStatus;

import java.util.Arrays;
import java.util.NoSuchElementException;

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

        try {
            return Iterables.find(Arrays.asList(SenderStatus.values()), new Predicate<SenderStatus>() {
                public boolean apply(SenderStatus senderStatus) {
                    return apiSenderStatus.equals(senderStatus.getApiValue());
                }
            });
        } catch (NoSuchElementException e) {
            return SenderStatus.UNRECOGNIZED(apiSenderStatus);
        }
    }

    public String toAPISenderStatus() {
        if (sdkSenderStatus == null) {
            return apiSenderStatus;
        }

        return sdkSenderStatus.getApiValue();
    }

}
