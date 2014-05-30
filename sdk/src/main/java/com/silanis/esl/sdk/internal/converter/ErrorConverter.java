package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Error;
import com.silanis.esl.sdk.ServerError;

/**
 * Created by chi-wing on 5/28/14.
 */
public class ErrorConverter {
    private com.silanis.esl.api.model.Error apiError = null;
    private ServerError serverError = null;

    public ErrorConverter(com.silanis.esl.api.model.Error apiError){
        this.apiError = apiError;
    }

    public ErrorConverter(ServerError serverError){
        this.serverError = serverError;
    }

    public Error toAPIError(){
        if(serverError == null){
            return apiError;
        }

        Error result = new Error();
        result.setCode(serverError.getCode());
        result.setEntity(serverError.getEntity());
        result.setMessage(serverError.getMessage());
        result.setMessageKey(serverError.getMessageKey());
        result.setName(serverError.getName());
        result.setTechnical(serverError.getTechnical());

        return result;
    }

    public ServerError toSDKError(){
        if(apiError == null){
            return serverError;
        }

        ServerError result = new ServerError();
        result.setCode(apiError.getCode());
        result.setEntity(apiError.getEntity());
        result.setMessage(apiError.getMessage());
        result.setMessageKey(apiError.getMessageKey());
        result.setName(apiError.getName());
        result.setTechnical(apiError.getTechnical());

        return result;
    }
}
