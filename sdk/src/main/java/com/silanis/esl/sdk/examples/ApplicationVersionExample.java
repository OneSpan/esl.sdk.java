package com.silanis.esl.sdk.examples;

import java.util.Properties;

/**
 * Created by schoi on 3/31/15.
 */
public class ApplicationVersionExample extends SDKSample {

    public String applicationVersion;

    public static void main( String... args ) {
        new ApplicationVersionExample(Props.get()).run();
    }

    public ApplicationVersionExample( Properties props ) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ));
    }

    public ApplicationVersionExample( String apiKey, String apiUrl ) {
        super( apiKey, apiUrl );
    }

    public void execute() {
        applicationVersion = eslClient.getSystemService().getApplicationVersion();
    }
}
