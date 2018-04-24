package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sessionToken;

    SessionToken(){}

    public SessionToken( String token ) {
        sessionToken = token;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    @Override
    public String toString() {
        return sessionToken;
    }
}
