package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by lena on 2014-04-30.
 */
public class TemplateExampleTest {

    @Test
    public void verifyResult() {
        TemplateExample example = new TemplateExample( Props.get() );
        example.run();

        DocumentPackage retrievedPackage = example.getRetrievedTemplate();

        Document document = retrievedPackage.getDocument( example.DOCUMENT_NAME );

        assertThat("Document name is incorrectly returned.", document.getName().toString(), is(example.DOCUMENT_NAME));
        assertThat("Document ID is incorrectly returned.", document.getId().toString(), is(example.DOCUMENT_ID));

        assertThat("Template name is incorrectly returned.", retrievedPackage.getName(), is(example.UPDATED_TEMPLATE_NAME));
        assertThat("Template description is incorrectly returned.", retrievedPackage.getDescription(), is(example.UPDATED_TEMPLATE_DESCRIPTION));
        assertThat("Template email message is incorrectly returned.", retrievedPackage.getPackageMessage(), is(example.TEMPLATE_EMAIL_MESSAGE));
        assertThat("Number of template signers is incorrectly returned.", retrievedPackage.getSigners().size(), is(3));
        assertThat("Template signer 1 first name is incorrectly returned.", retrievedPackage.getSigner(example.getEmail1()).getFirstName(), is(example.TEMPLATE_SIGNER1_FIRST));
        assertThat("Template signer 1 last name is incorrectly returned.", retrievedPackage.getSigner(example.getEmail1()).getLastName(), is(example.TEMPLATE_SIGNER1_LAST));
        assertThat("Template signer 2 first name is incorrectly returned.", retrievedPackage.getSigner(example.getEmail2()).getFirstName(), is(example.TEMPLATE_SIGNER2_FIRST));
        assertThat("Template signer 2 last name is incorrectly returned.", retrievedPackage.getSigner(example.getEmail2()).getLastName(), is(example.TEMPLATE_SIGNER2_LAST));

        assertThat("Number of template placeholders is incorrectly returned.", retrievedPackage.getPlaceholders().size(), is(1));
        assertThat("Template placeholder id is incorrectly returned", retrievedPackage.getPlaceholders().containsKey(example.PLACEHOLDER_ID));

        DocumentPackage instantiatedTemplate = example.eslClient.getPackage(example.instantiatedTemplateId);

        assertThat("Package name from template is incorrectly returned.", instantiatedTemplate.getName(), is(example.PACKAGE_NAME));

        assertThat("Number of package signers from template is incorrectly returned.", instantiatedTemplate.getSigners().size(), is(3));
        assertThat("Package signer 1 first name is incorrectly returned.", instantiatedTemplate.getSigner(example.getEmail1()).getFirstName(), is(example.TEMPLATE_SIGNER1_FIRST));
        assertThat("Package signer 1 last name is incorrectly returned.", instantiatedTemplate.getSigner(example.getEmail1()).getLastName(), is(example.TEMPLATE_SIGNER1_LAST));
        assertThat("Package signer 2 first name is incorrectly returned.", instantiatedTemplate.getSigner(example.getEmail2()).getFirstName(), is(example.TEMPLATE_SIGNER2_FIRST));
        assertThat("Package signer 2 last name is incorrectly returned.", instantiatedTemplate.getSigner(example.getEmail2()).getLastName(), is(example.TEMPLATE_SIGNER2_LAST));
    }

}
