package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.silanis.esl.sdk.Visibility;

import java.util.Arrays;
import java.util.NoSuchElementException;

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

        try {
            return Iterables.find(Arrays.asList(Visibility.values()), new Predicate<Visibility>() {
                public boolean apply(Visibility visibility) {
                    return apiVisibility.equals(visibility.getApiValue());
                }
            });
        } catch (NoSuchElementException e) {
            return Visibility.UNRECOGNIZED(apiVisibility);
        }
    }

    public String toAPIVisibility() {
        if (sdkVisibility == null) {
            return apiVisibility;
        }

        return sdkVisibility.getApiValue();
    }
}
