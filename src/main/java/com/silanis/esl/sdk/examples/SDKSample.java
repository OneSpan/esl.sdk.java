package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EslClient;

abstract public class SDKSample {
    protected EslClient eslClient;

    public SDKSample( String apiKey, String apiUrl ) {
        eslClient = new EslClient(apiKey, apiUrl);
    }

    void preExecute() {
        // To be overridden by sub classes to put in pre-execution logic if applicable.
    }

    abstract void execute();

    void postExecute() {
        // To be overridden by sub classes to put in post-execution logic if applicable.
    }

    public void run() {
        preExecute();
        execute();
        postExecute();
    }
}
