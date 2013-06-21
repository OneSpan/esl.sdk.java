package com.silanis.esl.sdk;

public class EslException extends RuntimeException {

    public EslException(String message) {
        super(message);
    }

    public EslException(String message, Throwable cause) {
        super(message, cause);
    }
}
