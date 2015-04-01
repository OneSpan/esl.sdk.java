package com.silanis.esl.sdk.internal.converter;

/**
 * Created by schoi on 4/1/15.
 */
public class ImageConverter {

    private com.silanis.esl.sdk.Image sdkImage = null;
    private com.silanis.esl.api.model.Image apiImage = null;

    /**
     * Construct with API Image object involved in conversion.
     *
     * @param apiImage
     */
    public ImageConverter(com.silanis.esl.api.model.Image apiImage) {
        this.apiImage = apiImage;
    }

    /**
     * Construct with SDK Image object involved in conversion.
     *
     * @param sdkImage
     */
    public ImageConverter(com.silanis.esl.sdk.Image sdkImage) {
        this.sdkImage = sdkImage;
    }

    public com.silanis.esl.sdk.Image toSDKImage() {
        if (apiImage == null) {
            return sdkImage;
        }

        com.silanis.esl.sdk.Image result = new com.silanis.esl.sdk.Image();

        result.setLink(apiImage.getLink());
        result.setSrc(apiImage.getSrc());
        return result;
    }

    public com.silanis.esl.api.model.Image toAPIImage() {
        if (sdkImage == null) {
            return apiImage;
        }

        com.silanis.esl.api.model.Image result = new com.silanis.esl.api.model.Image();

        result.setLink(sdkImage.getLink());
        result.setSrc(sdkImage.getSrc());
        return result;
    }
}
