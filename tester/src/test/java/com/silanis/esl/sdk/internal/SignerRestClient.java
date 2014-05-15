package com.silanis.esl.sdk.internal;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * Created by lena on 2014-05-14.
 */
public class SignerRestClient extends RestClient {

    private String sessionId;

    public SignerRestClient(String sessionId) {
        super("");
        this.sessionId = sessionId;
    }

    @Override
    protected void addAuthorizationHeader(HttpUriRequest request) {
        request.addHeader("Cookie", "ESIGNLIVE_SESSION_ID" + "=" + sessionId);
    }
}
