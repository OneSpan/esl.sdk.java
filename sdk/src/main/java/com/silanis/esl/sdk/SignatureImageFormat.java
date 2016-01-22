package com.silanis.esl.sdk;

/**
 * Created by afazaeli on 16/10/15.
 */
public enum SignatureImageFormat {
    PNG("image/png"), JPG("image/jpeg"), GIF("image/gif");

    private final String acceptType;

    private SignatureImageFormat(String acceptType) {
        this.acceptType = acceptType;
    }

    public String getAcceptType() {
        return acceptType;
    }
}