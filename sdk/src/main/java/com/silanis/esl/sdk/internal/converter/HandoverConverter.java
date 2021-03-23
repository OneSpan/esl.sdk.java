package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.HandoverBuilder;

import java.util.Locale;

/**
 * Created by schoi on 2020-04-01.
 */
public class HandoverConverter {

    private com.silanis.esl.api.model.Handover apiHandover;
    private com.silanis.esl.sdk.Handover sdkHandover;

    /**
     * Construct with API object involved in conversion.
     *
     * @param apiHandover
     */
    public HandoverConverter(com.silanis.esl.api.model.Handover apiHandover) {
        this.apiHandover = apiHandover;
    }

    /**
     * Construct with SDK object involved in conversion.
     *
     * @param sdkHandover
     */
    public HandoverConverter(com.silanis.esl.sdk.Handover sdkHandover) {
        this.sdkHandover = sdkHandover;
    }

    /**
     * Convert from SDK to API.
     *
     * @return
     */
    public com.silanis.esl.api.model.Handover toAPIHandover() {
        if (sdkHandover == null) {
            return apiHandover;
        }

        return new com.silanis.esl.api.model.Handover()
                .safeSetHref(sdkHandover.getHref())
                .safeSetText(sdkHandover.getText())
                .safeSetTitle(sdkHandover.getTitle());
    }

    /**
     * Convert from API to SDK.
     *
     * @return
     */
    public com.silanis.esl.sdk.Handover toSDKHandover(Locale language) {
        if (apiHandover == null) {
            return sdkHandover;
        }

        return HandoverBuilder
                .newHandover(language)
                .withHref(apiHandover.getHref())
                .withText(apiHandover.getText())
                .withTitle(apiHandover.getTitle())
                .build();
    }

}