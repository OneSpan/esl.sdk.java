package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.silanis.esl.sdk.BasePackageType;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by schoi on 12/16/14.
 */
public class BasePackageTypeConverter {
    private BasePackageType sdkBasePackageType = null;
    private String apiBasePackageType = null;

    /**
     * Construct with API BasePackageType object involved in conversion.
     *
     * @param apiBasePackageType
     */
    public BasePackageTypeConverter(String apiBasePackageType) {
        this.apiBasePackageType = apiBasePackageType;
    }

    /**
     * Construct with SDK BasePackageType object involved in conversion.
     *
     * @param sdkBasePackageType
     */
    public BasePackageTypeConverter(BasePackageType sdkBasePackageType) {
        this.sdkBasePackageType = sdkBasePackageType;
    }

    public BasePackageType toSDKBasePackageType() {
        if (apiBasePackageType == null) {
            return sdkBasePackageType;
        }

        try {
            return Iterables.find(Arrays.asList(BasePackageType.values()), new Predicate<BasePackageType>() {
                public boolean apply(BasePackageType basePackageType) {
                    return apiBasePackageType.equals(basePackageType.getApiValue());
                }
            });
        } catch (NoSuchElementException e) {
            return BasePackageType.UNRECOGNIZED(apiBasePackageType);
        }
    }

    public String toAPIBasePackageType() {
        if (sdkBasePackageType == null) {
            return apiBasePackageType;
        }

        return sdkBasePackageType.getApiValue();
    }
    
}
