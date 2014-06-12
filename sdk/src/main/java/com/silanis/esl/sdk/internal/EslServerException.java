package com.silanis.esl.sdk.internal;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.ServerError;

public class EslServerException extends EslException {
    public ServerError serverError;

    public EslServerException(String message, RequestException e){
        super(message + " Exception: " + e.getMessage(), e);
        this.serverError = e.getServerError();
    }

    public ServerError getServerError(){
        return serverError;
    }
}