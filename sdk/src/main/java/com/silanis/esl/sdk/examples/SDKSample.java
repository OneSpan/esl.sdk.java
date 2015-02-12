package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import java.util.UUID;

abstract public class SDKSample {
    protected EslClient eslClient;
    protected PackageId packageId;
    protected DocumentPackage retrievedPackage;

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

    public DocumentPackage getRetrievedPackage() {
        return retrievedPackage;
    }

    protected String getRandomEmail() {
        return UUID.randomUUID().toString().replace("-","") + "@e-signlive.com";
    }
}
