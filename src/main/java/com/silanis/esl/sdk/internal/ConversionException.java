package com.silanis.esl.sdk.internal;

public class ConversionException extends RuntimeException {
    private Class sourceType;
    private Class destType;

    public ConversionException( Class sourceType, Class destType, String message ) {
        super( "Failed to convert from " + sourceType.getName() + " to " + destType.getName() + ". " + message );
        this.sourceType = sourceType;
        this.destType = destType;
    }

    public Class<Object> getSourceType() {
        return sourceType;
    }

    public Class<Object> getDestType() {
        return destType;
    }
}
