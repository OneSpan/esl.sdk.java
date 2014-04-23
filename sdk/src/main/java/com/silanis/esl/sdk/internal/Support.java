package com.silanis.esl.sdk.internal;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Support {

    private static final Logger LOG = Logger.getLogger(Support.class.getName());

    public void logRequest(String httpVerb, String path, String jsonPayload) {
        LOG.log(Level.FINE, "{0} on {1}", new Object[] {httpVerb, path});
        LOG.fine(jsonPayload);
    }
    public void logRequest(String httpVerb, String path) {
        LOG.log(Level.FINE, "{0} on {1}", new Object[] {httpVerb, path});
    }

    public void logResponse(String response) {
        LOG.fine(response);
    }

    public void logError( com.silanis.esl.api.model.Error errorMessage){
        LOG.severe("message:" + errorMessage.getMessage() + ", http code:" + errorMessage.getCode());
    }

    public void logMessage(String message) {
        LOG.fine("message: " + message);
    }
}