package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Link;
import com.silanis.esl.sdk.Handover;
import com.silanis.esl.sdk.builder.HandoverBuilder;

import java.util.Locale;

/**
 * Created by schoi on 2020-04-01.
 */
public class HandoverConverter {

    private Link apiLink;
    private Handover sdkHandover;

    /**
     * Construct with API object involved in conversion.
     *
     * @param apiLink
     */
    public HandoverConverter(Link apiLink) {
        this.apiLink = apiLink;
    }

    /**
     * Construct with SDK object involved in conversion.
     *
     * @param sdkHandover
     */
    public HandoverConverter(Handover sdkHandover) {
        this.sdkHandover = sdkHandover;
    }

    /**
     * Convert from SDK to API.
     *
     * @return
     */
    public Link toAPILink() {
        if (sdkHandover == null) {
            return apiLink;
        }

        return new Link()
                .safeSetHref(sdkHandover.getHref())
                .safeSetText(sdkHandover.getText())
                .safeSetTitle(sdkHandover.getTitle());
    }

    /**
     * Convert from API to SDK.
     *
     * @return
     */
    public Handover toSDKHandover(Locale language) {
        if (apiLink == null) {
            return sdkHandover;
        }

        return HandoverBuilder
                .newHandover(language)
                .withHref(apiLink.getHref())
                .withText(apiLink.getText())
                .withTitle(apiLink.getTitle())
                .build();
    }

}