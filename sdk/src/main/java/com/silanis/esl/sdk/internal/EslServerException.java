package com.silanis.esl.sdk.internal;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.ServerError;

import static java.lang.String.format;

public class EslServerException extends EslException {
    public EslServerException(String method, String uri, int statusCode, String reasonPhrase, String details) {
        super(format("HTTP %s on URI %s resulted in response with status code: [%d, %s]. Optional details: %s", method, uri, statusCode, reasonPhrase, details));
        serverError = new ServerError(method, uri, statusCode, reasonPhrase, details);
    }
}