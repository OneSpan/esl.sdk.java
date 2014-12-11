package com.silanis.esl.sdk.internal.converter;

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

        if (apiRequirementStatus.equals("INCOMPLETE"))
            return sdkRequirementStatus.INCOMPLETE;
        else if (apiRequirementStatus.equals("REJECTED"))
            return sdkRequirementStatus.REJECTED;
        else if (apiRequirementStatus.equals("COMPLETE"))
            return sdkRequirementStatus.COMPLETE;
        else
            return sdkRequirementStatus.UNRECOGNIZED(apiRequirementStatus);
    }

    public String toAPIRequirementStatus() {
        if (sdkRequirementStatus == null) {
            return apiRequirementStatus;
        }

        if(sdkRequirementStatus.getValue().equals("INCOMPLETE"))
            return "INCOMPLETE";
        else if (sdkRequirementStatus.getValue().equals("REJECTED"))
            return "REJECTED";
        else if (sdkRequirementStatus.getValue().equals("COMPLETE"))
            return "COMPLETE";
        else if (sdkRequirementStatus.getValue().equals("UNRECOGNIZED"))
            return sdkRequirementStatus.getUnknownValue();
        else
            return "";
    }
}
