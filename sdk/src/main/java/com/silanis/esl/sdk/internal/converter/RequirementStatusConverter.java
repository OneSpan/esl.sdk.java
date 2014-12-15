package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.RequirementStatus;

/**
 * Created by lena on 2014-06-02.
 *
 * Convert between API and SDK RequirementStatus.
 */
public class RequirementStatusConverter {

    private com.silanis.esl.sdk.RequirementStatus sdkRequirementStatus = null;
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
    public RequirementStatusConverter(com.silanis.esl.sdk.RequirementStatus sdkRequirementStatus) {
        this.sdkRequirementStatus = sdkRequirementStatus;
    }

    public com.silanis.esl.sdk.RequirementStatus toSDKRequirementStatus() {
        if (apiRequirementStatus == null) {
            return sdkRequirementStatus;
        }

        sdkRequirementStatus = RequirementStatus.valueOf(apiRequirementStatus);
        if (sdkRequirementStatus == null)
            return RequirementStatus.UNRECOGNIZED(apiRequirementStatus);
        else
            return sdkRequirementStatus;
    }

    public String toAPIRequirementStatus() {
        if (sdkRequirementStatus == null) {
            return apiRequirementStatus;
        }

        return sdkRequirementStatus.getApiValue();
    }
}
