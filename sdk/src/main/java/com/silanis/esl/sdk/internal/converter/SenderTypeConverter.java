package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.silanis.esl.sdk.SenderType;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by lena on 2014-05-29.
 *
 * Converter between API and SDK SenderType.
 */
public class SenderTypeConverter {

    private SenderType sdkSenderType = null;
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
    public SenderTypeConverter(SenderType sdkSenderType) {
        this.sdkSenderType = sdkSenderType;
    }

    public SenderType toSDKSenderType() {
        if (apiSenderType == null) {
            return sdkSenderType;
        }

        try {
            return Iterables.find(Arrays.asList(SenderType.values()), new Predicate<SenderType>() {
                public boolean apply(SenderType senderType) {
                    return apiSenderType.equals(senderType.getApiValue());
                }
            });
        } catch (NoSuchElementException e) {
            return SenderType.UNRECOGNIZED(apiSenderType);
        }
    }

    public String toAPISenderType() {
        if (sdkSenderType == null) {
            return apiSenderType;
        }

        return sdkSenderType.getApiValue();
    }

}
