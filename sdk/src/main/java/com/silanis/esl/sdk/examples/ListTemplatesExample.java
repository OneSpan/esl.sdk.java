package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Page;
import com.silanis.esl.sdk.PageRequest;

import java.util.Properties;

/**
 * Basic package with in-person mode set at the document package level. Expires in a month.
 */
public class ListTemplatesExample extends SDKSample {

    public static void main( String... args ) {
        new ListTemplatesExample( Props.get() ).run();
    }

    public ListTemplatesExample( Properties properties ) {
        this( properties.getProperty( "api.key" ),
                properties.getProperty( "api.url" ) );
    }

    public ListTemplatesExample( String apiKey, String apiUrl ) {
        super( apiKey, apiUrl );
    }
    public void execute() {
        Page<DocumentPackage> templates = eslClient.getPackageService().getTemplates(new PageRequest(0));
    }
}