package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.Page;
import com.silanis.esl.sdk.PageRequest;

import java.util.Properties;

/**
 * Basic package with in-person mode set at the document package level. Expires in a month.
 */
public class ListTemplatesExample {

    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    public static void main( String... args ) {
        EslClient esl = new EslClient( API_KEY, API_URL );
        Page<DocumentPackage> templates = esl.getPackageService().getTemplates(new PageRequest(0));

        if (templates.getTotalElements() > 0) {
            for (DocumentPackage template : templates) {
                System.out.println(String.format("template id = %s, name = %s", template.getId(), template.getName()));
            }
        }
        else {
            System.out.println("No templates found");
        }
    }
}