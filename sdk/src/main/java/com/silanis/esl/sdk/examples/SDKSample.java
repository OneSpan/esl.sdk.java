package com.silanis.esl.sdk.examples;

import java.util.Collections;

import com.silanis.esl.sdk.EslClient;

public abstract class SDKSample extends BaseSDKSample{

    public SDKSample() {
        eslClient = setupEslClientFromProps(Collections.<String, String>emptyMap(), null);
    }

    public SDKSample(String apiKey, String apiUrl) {
        eslClient = new EslClient(apiKey, apiUrl);
    }
}
