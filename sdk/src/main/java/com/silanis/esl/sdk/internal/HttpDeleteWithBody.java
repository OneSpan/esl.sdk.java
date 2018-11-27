package com.silanis.esl.sdk.internal;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {

    public static final String METHOD_NAME = "DELETE";

    public HttpDeleteWithBody(String uri) {
        this.setURI(URI.create(uri));
    }

    public String getMethod() {
        return METHOD_NAME;
    }
}
