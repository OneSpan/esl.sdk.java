package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.Approval;
import com.silanis.esl.api.model.Document;
import com.silanis.esl.api.model.Package;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Signer;
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

/**
 * Runs against the environment in signers.properties; the account must have the
 * optionalSignerEmail account feature ON.
 */
public class SignersWithoutEmailExampleTest {

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

        // The SDK model rebuilds a signer's signature by email, so check the role of each
        // signature on the API package as the backend stored it.
        Package apiPackage = example.eslClient.getPackageService().getApiPackage(example.packageId.getId());
        List<String> signatureRoles = new ArrayList<>();
        for (Document document : apiPackage.getDocuments()) {
            if (DOCUMENT_NAME.equals(document.getName())) {
                for (Approval approval : document.getApprovals()) {
                    signatureRoles.add(approval.getRole());
                }
            }
        }
        assertThat("Each signature must be assigned to its own signer's role",
                signatureRoles, containsInAnyOrder(FIRST_SIGNER_ID, SECOND_SIGNER_ID));
    }
}
