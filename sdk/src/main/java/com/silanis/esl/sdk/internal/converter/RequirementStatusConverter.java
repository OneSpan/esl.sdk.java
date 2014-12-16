package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.RequirementStatus;

/**
 * Created by lena on 2014-06-02.
 *
 * Convert between API and SDK RequirementStatus.
 */
public class RequirementStatusConverter {

    private RequirementStatus sdkRequirementStatus = null;
    private String apiRequirementStatus = null;

    /**
     * Construct with API RequirementStatus object involved in conversion.
     *
     * @param apiRequirementStatus
     */
    public RequirementStatusConverter(String apiRequirementStatus) {
        this.apiRequirementStatus = apiRequirementStatus;
    }

    /**
     * Construct with SDK RequirementStatus object involved in conversion.
     *
     * @param sdkRequirementStatus
     */
    public RequirementStatusConverter(RequirementStatus sdkRequirementStatus) {
        this.sdkRequirementStatus = sdkRequirementStatus;
    }

    public RequirementStatus toSDKRequirementStatus() {
        if (apiRequirementStatus == null) {
            return sdkRequirementStatus;
        }

        RequirementStatus[] values = RequirementStatus.values();
        for (RequirementStatus value : values) {
            if(apiRequirementStatus.equals(value.getApiValue())){
                return value;
            }
        }
        return RequirementStatus.UNRECOGNIZED(apiRequirementStatus);
    }

    public String toAPIRequirementStatus() {
        if (sdkRequirementStatus == null) {
            return apiRequirementStatus;
        }

        return sdkRequirementStatus.getApiValue();
    }
}
