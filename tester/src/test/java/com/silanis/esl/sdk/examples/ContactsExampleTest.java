package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Signer;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by lena on 2014-08-18.
 */
public class ContactsExampleTest {

    @Test
    public void verifyResult() {
        ContactsExample example = new ContactsExample(Props.get());
        example.run();

        DocumentPackage documentPackage = example.eslClient.getPackage(example.packageId);
        Signer signer = documentPackage.getSigner(example.email1);

        // Assert signer information is correct
        assertThat("Signer's email was not set correctly", signer.getEmail(), is(equalTo(example.signerForPackage.getEmail())));
        assertThat("Signer's first was not set correctly", signer.getFirstName(), is(equalTo(example.signerForPackage.getFirstName())));
        assertThat("Signer's last was not set correctly", signer.getLastName(), is(equalTo(example.signerForPackage.getLastName())));
        assertThat("Signer's title was not set correctly", signer.getTitle(), is(equalTo(example.signerForPackage.getTitle())));
        assertThat("Signer's company was not set correctly", signer.getCompany(), is(equalTo(example.signerForPackage.getCompany())));

        // Assert new signer is added to the contacts
        assertThat("New signer was not added to the contacts", example.afterContacts.get(example.email2), notNullValue());
        assertThat("New signer first name was not set correctly", example.afterContacts.get(example.email2).getFirstName(), equalTo("John"));
        assertThat("New signer last name was not set correctly", example.afterContacts.get(example.email2).getLastName(), equalTo("Smith"));
    }
}
