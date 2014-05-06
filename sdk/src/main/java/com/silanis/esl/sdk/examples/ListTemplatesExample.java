package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Page;
import com.silanis.esl.sdk.PageRequest;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.util.Properties;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;

/**
 * Basic package with in-person mode set at the document package level. Expires in a month.
 */
public class ListTemplatesExample extends SDKSample {

    private Page<DocumentPackage> templates;

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

    @Override
    public void execute() {

        DocumentPackage superDuperPackage = newPackageNamed("Example template")
                .describedAs("Create template using java sdk")
                .build();

        packageId = eslClient.getTemplateService().createTemplate(superDuperPackage);
        templates = eslClient.getPackageService().getTemplates(new PageRequest(0));
    }

    public Page<DocumentPackage> getTemplates() {
        return templates;
    }
}