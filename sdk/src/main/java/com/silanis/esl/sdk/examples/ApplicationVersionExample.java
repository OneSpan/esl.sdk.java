package com.silanis.esl.sdk.examples;

/**
 * Created by schoi on 3/31/15.
 */
public class ApplicationVersionExample extends SDKSample {

    public String applicationVersion;

    public static void main( String... args ) {
        new ApplicationVersionExample().run();
    }

    public void execute() {
        applicationVersion = eslClient.getSystemService().getApplicationVersion();
    }
}
