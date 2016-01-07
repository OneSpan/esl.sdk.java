package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertFalse;

/**
 * Created by chi-wing on 4/30/14.
 */
public class SignerManipulationExampleTest {

    @Test
    public void verifyResult() {
        SignerManipulationExample example = new SignerManipulationExample(Props.get());
        example.run();
        DocumentPackage documentPackage = example.createdPackageWithAddedSigner;

        assertThat("Not correct number of signers", documentPackage.getSigners().size(), is(4));

        assertThat("Signer was not added", documentPackage.getSigner(example.email3), notNullValue());
        assertThat("Added signer first name was not set correctly", documentPackage.getSigner(example.email3).getFirstName(), is("firstName3"));
        assertThat("Added signer last name was not set correctly", documentPackage.getSigner(example.email3).getLastName(), is("lastName3"));
        assertThat("Added signer title was not set correctly", documentPackage.getSigner(example.email3).getTitle(), is("Title3"));

        documentPackage = example.createdPackageWithRemovedSigner;

        assertThat("Not correct number of signers", documentPackage.getSigners().size(), is(3));

        assertThat("Signer 1 was not removed", documentPackage.getSigner(example.email1), nullValue());

        documentPackage = example.createdPackageWithUpdatedSigner;

        assertThat("Not correct number of signers", documentPackage.getSigners().size(), is(3));

        assertThat("Signer 2 email was not updated correctly", example.updatedSigner.getEmail(), is("timbob@aol.com"));
        assertThat("Signer 2 first name was not updated correctly", example.updatedSigner.getFirstName(), is("updateFirstName1"));
        assertThat("Signer 2 last name was not updated correctly", example.updatedSigner.getLastName(), is("updateLastName1"));
        assertThat("Signer 2 title was updated set correctly", example.updatedSigner.getTitle(), is("updateTitle1"));

        assertFalse("Signer 3 was not unlocked properly", documentPackage.getSigner(example.email3).isLocked());
    }
}
