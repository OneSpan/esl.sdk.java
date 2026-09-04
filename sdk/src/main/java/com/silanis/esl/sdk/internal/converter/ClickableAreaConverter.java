package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.FieldClickableArea;
import com.silanis.esl.sdk.ClickableArea;
import com.silanis.esl.sdk.ClickableAreaAlignment;

/**
 * Converter between SDK ClickableArea and API FieldClickableArea.
 */
public class ClickableAreaConverter {

    private ClickableArea sdkClickableArea = null;
    private FieldClickableArea apiClickableArea = null;

    /**
     * Construct with SDK object involved in conversion.
     *
     * @param clickableArea
     */
    public ClickableAreaConverter(ClickableArea clickableArea) {
        this.sdkClickableArea = clickableArea;
    }

    /**
     * Construct with API object involved in conversion.
     *
     * @param clickableArea
     */
    public ClickableAreaConverter(FieldClickableArea clickableArea) {
        this.apiClickableArea = clickableArea;
    }

    /**
     * Convert from SDK to API object.
     *
     * @return a FieldClickableArea object.
     */
    public FieldClickableArea toAPIFieldClickableArea() {

        if (sdkClickableArea == null) {
            return apiClickableArea;
        }

        FieldClickableArea result = new FieldClickableArea();

        result.safeSetWidth(sdkClickableArea.getWidth());
        result.safeSetHeight(sdkClickableArea.getHeight());
        if (sdkClickableArea.getAlignment() != null) {
            result.setAlignment(sdkClickableArea.getAlignment().name());
        }

        return result;
    }

    /**
     * Convert from API to SDK object.
     *
     * @return a ClickableArea object.
     */
    public ClickableArea toSDKClickableArea() {

        if (apiClickableArea == null) {
            return sdkClickableArea;
        }

        ClickableArea result = new ClickableArea();

        if ( apiClickableArea.getWidth() != null )
            result.setWidth(apiClickableArea.getWidth());
        if ( apiClickableArea.getHeight() != null )
            result.setHeight(apiClickableArea.getHeight());
        if ( apiClickableArea.getAlignment() != null )
            result.setAlignment(ClickableAreaAlignment.valueOf(apiClickableArea.getAlignment()));

        return result;
    }
}
