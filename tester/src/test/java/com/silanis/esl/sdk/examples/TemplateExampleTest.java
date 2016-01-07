package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.TemplateExample.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

/**
 * Created by lena on 2014-04-30.
 */
public class TemplateExampleTest {

    @Test
    public void verifyResult() {
        TemplateExample example = new TemplateExample( Props.get() );
        example.run();

        DocumentPackage retrievedPackage = example.getRetrievedTemplate();

        Document document = retrievedPackage.getDocument( DOCUMENT_NAME );

        assertThat("Document name is incorrectly returned.", document.getName().toString(), is(DOCUMENT_NAME));
        assertThat("Document ID is incorrectly returned.", document.getId().toString(), is(DOCUMENT_ID));

        assertThat("Template name is incorrectly returned.", retrievedPackage.getName(), is(UPDATED_TEMPLATE_NAME));
        assertThat("Template description is incorrectly returned.", retrievedPackage.getDescription(), is(UPDATED_TEMPLATE_DESCRIPTION));
        assertThat("Template email message is incorrectly returned.", retrievedPackage.getPackageMessage(), is(TEMPLATE_EMAIL_MESSAGE));
        assertThat("Number of template signers is incorrectly returned.", retrievedPackage.getSigners().size(), is(3));
        assertThat("Template signer 1 first name is incorrectly returned.", retrievedPackage.getSigner(example.email1).getFirstName(), is(TEMPLATE_SIGNER1_FIRST));
        assertThat("Template signer 1 last name is incorrectly returned.", retrievedPackage.getSigner(example.email1).getLastName(), is(TEMPLATE_SIGNER1_LAST));
        assertThat("Template signer 2 first name is incorrectly returned.", retrievedPackage.getSigner(example.email2).getFirstName(), is(TEMPLATE_SIGNER2_FIRST));
        assertThat("Template signer 2 last name is incorrectly returned.", retrievedPackage.getSigner(example.email2).getLastName(), is(TEMPLATE_SIGNER2_LAST));

        assertThat("Number of template placeholders is incorrectly returned.", retrievedPackage.getPlaceholders().size(), is(1));
        assertThat("Template placeholder id is incorrectly returned", retrievedPackage.getPlaceholder(PLACEHOLDER_ID), not(nullValue()));

        DocumentPackage instantiatedTemplate = example.eslClient.getPackage(example.instantiatedTemplateId);

        assertThat("Package name from template is incorrectly returned.", instantiatedTemplate.getName(), is(PACKAGE_NAME));

        assertThat("Number of package signers from template is incorrectly returned.", instantiatedTemplate.getSigners().size(), is(3));
        assertThat("Package signer 1 first name is incorrectly returned.", instantiatedTemplate.getSigner(example.email1).getFirstName(), is(TEMPLATE_SIGNER1_FIRST));
        assertThat("Package signer 1 last name is incorrectly returned.", instantiatedTemplate.getSigner(example.email1).getLastName(), is(TEMPLATE_SIGNER1_LAST));
        assertThat("Package signer 2 first name is incorrectly returned.", instantiatedTemplate.getSigner(example.email2).getFirstName(), is(TEMPLATE_SIGNER2_FIRST));
        assertThat("Package signer 2 last name is incorrectly returned.", instantiatedTemplate.getSigner(example.email2).getLastName(), is(TEMPLATE_SIGNER2_LAST));
    }

}
