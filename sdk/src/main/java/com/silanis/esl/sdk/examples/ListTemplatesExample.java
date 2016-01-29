package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Page;
import com.silanis.esl.sdk.PageRequest;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;

/**
 * Basic package with in-person mode set at the document package level. Expires in a month.
 */
public class ListTemplatesExample extends SDKSample {

    private Page<DocumentPackage> templates;

    public static void main( String... args ) {
        new ListTemplatesExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("Create template using java sdk")
                .build();

        packageId = eslClient.getTemplateService().createTemplate(superDuperPackage);
        templates = eslClient.getPackageService().getTemplates(new PageRequest(0));
    }

    public Page<DocumentPackage> getTemplates() {
        return templates;
    }
}