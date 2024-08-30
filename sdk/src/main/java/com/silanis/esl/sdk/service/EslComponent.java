package com.silanis.esl.sdk.service;

import com.silanis.esl.sdk.internal.RestClient;

public class EslComponent {

    private RestClient client;
    private String baseUrl;

    public EslComponent(RestClient client, String baseUrl) {
        this.client = client;
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public RestClient getClient() {
        return client;
    }
}