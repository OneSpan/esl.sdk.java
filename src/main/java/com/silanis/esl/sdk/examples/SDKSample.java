package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EslClient;

abstract public class SDKSample {
    protected EslClient eslClient;

    public SDKSample( String apiKey, String apiUrl ) {
        eslClient = new EslClient(apiKey, apiUrl);
    }

    abstract void execute();

    public void run() {
        execute();
    }
}
