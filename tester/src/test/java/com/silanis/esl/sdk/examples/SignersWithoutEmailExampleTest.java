package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.Signer;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.silanis.esl.sdk.examples.SignersWithoutEmailExample.DOCUMENT_NAME;
import static com.silanis.esl.sdk.examples.SignersWithoutEmailExample.FIRST_SIGNER_ID;
import static com.silanis.esl.sdk.examples.SignersWithoutEmailExample.SECOND_SIGNER_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class SignersWithoutEmailExampleTest {

    @Ignore("Requires the optionalSignerEmail account feature ON.")
    @Test
    public void verifyResult() {
        SignersWithoutEmailExample example = new SignersWithoutEmailExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        Signer first = documentPackage.getSignerById(FIRST_SIGNER_ID);
        Signer second = documentPackage.getSignerById(SECOND_SIGNER_ID);
        assertThat("First signer was not read", first, notNullValue());
        assertThat("First signer email was not read as empty", first.getEmail(), is(""));
        assertThat("First signer first name was not read", first.getFirstName(), is("John"));
        assertThat("Second signer was not read", second, notNullValue());
        assertThat("Second signer email was not read as empty", second.getEmail(), is(""));
        assertThat("Second signer first name was not read", second.getFirstName(), is("Jane"));

        List<String> signatureRoles = new ArrayList<>();
        for (Signature signature : documentPackage.getDocument(DOCUMENT_NAME).getSignatures()) {
            assertThat("Signature has no role id", signature.getRoleId(), notNullValue());
            signatureRoles.add(signature.getRoleId().getId());
        }
        assertThat("Each signature must be assigned to its own signer's role",
                signatureRoles, containsInAnyOrder(FIRST_SIGNER_ID, SECOND_SIGNER_ID));
    }
}
