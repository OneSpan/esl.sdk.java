package com.silanis.esl.sdk.internal;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpUriRequest;

public class Support {

    private static Logger LOG = Logger.getLogger(Support.class.getName());

    public void log(HttpUriRequest request) {
        LOG.log(Level.FINER, "Raw Request is: \nURL: {0} \nMETHOD: {1}\nHEADERS: {2}", new Object[]{request.getURI(), request.getMethod(),
                formatHeaders(request.getAllHeaders())});
    }

    private String formatHeaders(Header[] allHeaders) {
        if(allHeaders != null){
            String formattedHeaders = "{";
            for (Header allHeader : allHeaders) {
                formattedHeaders += "[";
                final String name = allHeader.getName();
                if(name != null){
                    formattedHeaders += name;
                }
                formattedHeaders += "=";
                final String value = allHeader.getValue();
                if(value != null){
                    formattedHeaders += value;
                }
                formattedHeaders += "]";
            }
            formattedHeaders += "}";
            return formattedHeaders;
        }
        return "";
    }

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
