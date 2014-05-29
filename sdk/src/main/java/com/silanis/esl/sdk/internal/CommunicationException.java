package com.silanis.esl.sdk.internal;

import com.silanis.esl.api.model.Error;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.ServerError;
import com.silanis.esl.sdk.internal.converter.ErrorConverter;

import static java.lang.String.format;

public class CommunicationException extends EslException {
    public ServerError serverError;

    public CommunicationException(String method, String uri, int statusCode, String reasonPhrase, String details) {
        super(format("HTTP %s on URI %s resulted in response with status code: [%d, %s]. Optional details: %s", method, uri, statusCode, reasonPhrase, details));

        com.silanis.esl.api.model.Error error = Serialization.fromJson(details, Error.class);

        serverError = new ErrorConverter(error).toSDKError();
    }

    public ServerError getServerError(){
        return serverError;
    }
}