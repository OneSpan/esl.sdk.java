package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.CreateTemplateFromPackageExample.DOCUMENT_ID;
import static com.silanis.esl.sdk.examples.CreateTemplateFromPackageExample.DOCUMENT_NAME;
import static com.silanis.esl.sdk.examples.CreateTemplateFromPackageExample.PACKAGE_SIGNER1_FIRST;
import static com.silanis.esl.sdk.examples.CreateTemplateFromPackageExample.PACKAGE_SIGNER1_LAST;
import static com.silanis.esl.sdk.examples.CreateTemplateFromPackageExample.PACKAGE_SIGNER2_FIRST;
import static com.silanis.esl.sdk.examples.CreateTemplateFromPackageExample.PACKAGE_SIGNER2_LAST;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by lena on 2014-05-01.
 */
public class CreateTemplateFromPackageExampleTest {

    @Test
    public void verifyResult() {
        CreateTemplateFromPackageExample example = new CreateTemplateFromPackageExample();
        example.run();

        DocumentPackage retrievedTemplate = example.eslClient.getPackageService().getPackage(example.templateId);
        Document document = retrievedTemplate.getDocument(DOCUMENT_NAME);

        assertThat("Document name is incorrectly returned.", document.getName(), is(DOCUMENT_NAME));
        assertThat("Document ID is incorrectly returned.", document.getId().toString(), is(DOCUMENT_ID));

        assertThat("Package name is incorrectly returned.", retrievedTemplate.getName(), is(example.newPackageName));
        assertThat("Number of package signers is incorrectly returned.", retrievedTemplate.getSigners().size(), is(3));
        assertThat("Package signer 1 first name is incorrectly returned.", retrievedTemplate.getSigner(example.email1).getFirstName(), is(PACKAGE_SIGNER1_FIRST));
        assertThat("Package signer 1 last name is incorrectly returned.", retrievedTemplate.getSigner(example.email1).getLastName(), is(PACKAGE_SIGNER1_LAST));
        assertThat("Package signer 2 first name is incorrectly returned.", retrievedTemplate.getSigner(example.email2).getFirstName(), is(PACKAGE_SIGNER2_FIRST));
        assertThat("Package signer 2 last name is incorrectly returned.", retrievedTemplate.getSigner(example.email2).getLastName(), is(PACKAGE_SIGNER2_LAST));
    }
}
