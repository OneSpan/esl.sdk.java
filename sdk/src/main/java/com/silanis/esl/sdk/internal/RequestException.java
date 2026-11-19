package com.silanis.esl.sdk.internal;

import com.silanis.esl.api.JsonDeserializationException;
import com.silanis.esl.sdk.ServerError;
import com.silanis.esl.sdk.internal.converter.ErrorConverter;

import static java.lang.String.format;

/**
 * Created by chi-wing on 5/29/14.
 */
public class RequestException extends Exception {
    private final int statusCode;
    private final String details;
    private ServerError serverError;

    public RequestException(String method, String uri, int statusCode, String reasonPhrase, String details) {
        super(format("HTTP %s on URI %s resulted in response with status code: [%d, %s]. Optional details: %s", method, uri, statusCode, reasonPhrase, details));
        this.statusCode = statusCode;
        this.details = details;

        try {
            com.silanis.esl.api.model.Error error = Serialization.fromJson(details, com.silanis.esl.api.model.Error.class);
            serverError = new ErrorConverter(error).toSDKError();
        } catch (JsonDeserializationException e) {
            // An empty or non-JSON body, e.g. from a gateway, is still an HTTP error rather than a parse failure.
            serverError = new ServerError();
            serverError.setCode(statusCode);
            serverError.setMessage(reasonPhrase);
        }
    }

    public ServerError getServerError(){
        return serverError;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDetails() {
        return details;
    }

}
