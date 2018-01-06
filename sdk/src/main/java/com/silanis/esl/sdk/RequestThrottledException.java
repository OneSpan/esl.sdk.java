package com.silanis.esl.sdk;

/**
 * @author ehardy
 */
public class RequestThrottledException extends EslException {
    public RequestThrottledException(int attempts) {
        super("API request has been throttled after " + attempts + " attempts to send it");
    }
}