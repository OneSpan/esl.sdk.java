package com.silanis.esl.sdk.internal;

import java.util.logging.*;

public class Support {

    private static Logger LOG = Logger.getLogger(Support.class.getName());

    public void logRequest(String httpVerb, String path, String jsonPayload) {
        LOG.log(Level.FINE, "{0} on {1}\n {2}", new Object[]{httpVerb, path, jsonPayload});
    }
    public void logRequest(String httpVerb, String path) {
        LOG.log(Level.FINE, "{0} on {1}", new Object[]{httpVerb, path});
    }

    public void logResponse(String response) {
        LOG.fine("RESPONSE: \n" + response);
    }

    public void logError( com.silanis.esl.api.model.Error errorMessage){
        LOG.severe("message:" + errorMessage.getMessage() + ", http code:" + errorMessage.getCode());
    }

    public void logMessage(String message) {
        LOG.fine("message: " + message);
    }
}