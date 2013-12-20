package com.silanis.esl.sdk.internal;

import com.silanis.esl.sdk.EslException;

import static java.lang.String.format;

public class CommunicationException extends EslException {
    public CommunicationException(String method, String uri, int statusCode, String reasonPhrase) {
        super(format("HTTP %s on URI %s resulted in response with status code: [%d, %s]", method, uri, statusCode, reasonPhrase));
    }
}