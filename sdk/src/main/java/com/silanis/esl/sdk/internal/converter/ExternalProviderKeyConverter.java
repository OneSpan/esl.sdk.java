package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.silanis.esl.sdk.ExternalProviderKey;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ExternalProviderKeyConverter {

    private ExternalProviderKey sdkExternalProviderKey = null;
    private String apiExternalProviderKey = null;


    public ExternalProviderKeyConverter(String apiExternalProviderKey) {
        this.apiExternalProviderKey = apiExternalProviderKey;
    }


    public ExternalProviderKeyConverter(ExternalProviderKey sdkExternalProviderKey) {
        this.sdkExternalProviderKey = sdkExternalProviderKey;
    }

    public ExternalProviderKey toSDKSenderType() {
        if (apiExternalProviderKey== null) {
            return sdkExternalProviderKey;
        }

        try {
            return Iterables.find(Arrays.asList(ExternalProviderKey.values()), new Predicate<ExternalProviderKey>() {
                public boolean apply(ExternalProviderKey externalProviderKey) {
                    return apiExternalProviderKey.equals(externalProviderKey.getApiValue());
                }
            });
        } catch (NoSuchElementException e) {
            return ExternalProviderKey.UNRECOGNIZED(apiExternalProviderKey);
        }
    }

    public String toAPISenderType() {
        if (sdkExternalProviderKey == null) {
            return apiExternalProviderKey;
        }

        return sdkExternalProviderKey.getApiValue();
    }

}
