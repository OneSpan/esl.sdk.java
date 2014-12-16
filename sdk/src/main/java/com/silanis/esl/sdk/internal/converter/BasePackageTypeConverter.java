package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.BasePackageType;

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

        BasePackageType[] values = BasePackageType.values();
        for (BasePackageType value : values) {
            if(apiBasePackageType.equals(value.getApiValue())){
                return value;
            }
        }
        return BasePackageType.UNRECOGNIZED(apiBasePackageType);
    }

    public String toAPIBasePackageType() {
        if (sdkBasePackageType == null) {
            return apiBasePackageType;
        }

        return sdkBasePackageType.getApiValue();
    }
    
}
