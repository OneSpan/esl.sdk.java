package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

abstract public class SDKSample {
    protected EslClient eslClient;
    protected PackageId packageId;

    public SDKSample( String apiKey, String apiUrl ) {
        eslClient = new EslClient(apiKey, apiUrl);
    }

    abstract void execute();

    public void run() {
        execute();
    }

    public EslClient getEslClient() {
        return eslClient;
    }

    public PackageId getPackageId() {
        return packageId;
    }


}
