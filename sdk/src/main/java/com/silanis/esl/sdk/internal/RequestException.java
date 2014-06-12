package com.silanis.esl.sdk.internal;

import com.silanis.esl.sdk.ServerError;
import com.silanis.esl.sdk.internal.converter.ErrorConverter;

import static java.lang.String.format;

/**
 * Created by chi-wing on 5/29/14.
 */
public class RequestException extends Exception {
    private ServerError serverError;

    public RequestException(String method, String uri, int statusCode, String reasonPhrase, String details) {
        super(format("HTTP %s on URI %s resulted in response with status code: [%d, %s]. Optional details: %s", method, uri, statusCode, reasonPhrase, details));

        com.silanis.esl.api.model.Error error = Serialization.fromJson(details, com.silanis.esl.api.model.Error.class);
        serverError = new ErrorConverter(error).toSDKError();
    }

    public ServerError getServerError(){
        return serverError;
    }

}
