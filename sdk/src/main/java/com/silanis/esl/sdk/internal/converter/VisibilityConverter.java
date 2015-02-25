package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.Visibility;

/**
 * Created by schoi on 2/20/15.
 */
public class VisibilityConverter {

    private Visibility sdkVisibility = null;
    private String apiVisibility = null;

    /**
     * Construct with API Visibility object involved in conversion.
     *
     * @param apiVisibility
     */
    public VisibilityConverter(String apiVisibility) {
        this.apiVisibility = apiVisibility;
    }

    /**
     * Construct with SDK Visibility object involved in conversion.
     *
     * @param sdkVisibility
     */
    public VisibilityConverter(Visibility sdkVisibility) {
        this.sdkVisibility = sdkVisibility;
    }

    public Visibility toSDKVisibility() {
        if (apiVisibility == null) {
            return sdkVisibility;
        }

        Visibility[] visibilities = Visibility.values();
        for (Visibility visibility : visibilities) {
            if(apiVisibility.equals(visibility.getApiValue())){
                return visibility;
            }
        }
        return Visibility.UNRECOGNIZED(apiVisibility);
    }

    public String toAPIVisibility() {
        if (sdkVisibility == null) {
            return apiVisibility;
        }

        return sdkVisibility.getApiValue();
    }

}
