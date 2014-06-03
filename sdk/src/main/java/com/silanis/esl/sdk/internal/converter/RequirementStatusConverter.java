package com.silanis.esl.sdk.internal.converter;

/**
 * Created by lena on 2014-06-02.
 *
 * Convert between API and SDK RequirementStatus.
 */
public class RequirementStatusConverter {

    private com.silanis.esl.sdk.RequirementStatus sdkRequirementStatus = null;
    private com.silanis.esl.api.model.RequirementStatus apiRequirementStatus = null;

    /**
     * Construct with API RequirementStatus object involved in conversion.
     *
     * @param apiRequirementStatus
     */
    public RequirementStatusConverter(com.silanis.esl.api.model.RequirementStatus apiRequirementStatus) {
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

        switch (apiRequirementStatus) {
            case INCOMPLETE:
                return sdkRequirementStatus.INCOMPLETE;
            case REJECTED:
                return sdkRequirementStatus.REJECTED;
            case COMPLETE:
                return sdkRequirementStatus.COMPLETE;
            default:
                return sdkRequirementStatus;
        }
    }

    public com.silanis.esl.api.model.RequirementStatus toAPIRequirementStatus() {
        if (sdkRequirementStatus == null) {
            return apiRequirementStatus;
        }

        switch (sdkRequirementStatus) {
            case INCOMPLETE:
                return apiRequirementStatus.INCOMPLETE;
            case REJECTED:
                return apiRequirementStatus.REJECTED;
            case COMPLETE:
                return apiRequirementStatus.COMPLETE;
            default:
                return apiRequirementStatus;
        }
    }
}
