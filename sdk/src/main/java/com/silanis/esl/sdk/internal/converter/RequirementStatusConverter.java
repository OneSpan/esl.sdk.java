package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.silanis.esl.sdk.RequirementStatus;

import java.util.Arrays;
import java.util.NoSuchElementException;

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

        try {
            return Iterables.find(Arrays.asList(RequirementStatus.values()), new Predicate<RequirementStatus>() {
                public boolean apply(RequirementStatus requirementStatus) {
                    return apiRequirementStatus.equals(requirementStatus.getApiValue());
                }
            });
        } catch (NoSuchElementException e) {
            return RequirementStatus.UNRECOGNIZED(apiRequirementStatus);
        }
    }

    public String toAPIRequirementStatus() {
        if (sdkRequirementStatus == null) {
            return apiRequirementStatus;
        }

        return sdkRequirementStatus.getApiValue();
    }
}
