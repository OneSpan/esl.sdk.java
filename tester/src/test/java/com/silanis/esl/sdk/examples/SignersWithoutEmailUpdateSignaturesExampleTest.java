package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.Approval;
import com.silanis.esl.api.model.Document;
import com.silanis.esl.api.model.Package;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.silanis.esl.sdk.examples.SignersWithoutEmailExample.DOCUMENT_NAME;
import static com.silanis.esl.sdk.examples.SignersWithoutEmailExample.FIRST_SIGNER_ID;
import static com.silanis.esl.sdk.examples.SignersWithoutEmailExample.SECOND_SIGNER_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Reads a package with two signers without email and saves its signatures back
 * unchanged. Each signature must stay on its own signer's role.
 * Runs against the environment in signers.properties; the account must have the
 * optionalSignerEmail account feature ON.
 */
public class SignersWithoutEmailUpdateSignaturesExampleTest {

    @Test
    public void readSignaturesKeepTheirRolesWhenSavedBack() {
        SignersWithoutEmailExample example = new SignersWithoutEmailExample();
        example.run();

        Map<String, String> rolesBefore = roleBySignatureId(example);
        assertThat("Precondition: signatures were created on two roles",
                new java.util.HashSet<>(rolesBefore.values()).size(), is(2));

        DocumentPackage documentPackage = example.getRetrievedPackage();
        com.silanis.esl.sdk.Document document = documentPackage.getDocument(DOCUMENT_NAME);
        List<Signature> readSignatures = new java.util.ArrayList<>(document.getSignatures());

        example.eslClient.getApprovalService()
                .updateSignatures(documentPackage, document.getId().getId(), readSignatures);

        Map<String, String> rolesAfter = roleBySignatureId(example);
        for (Map.Entry<String, String> before : rolesBefore.entrySet()) {
            assertThat("Signature " + before.getKey() + " moved from role " + before.getValue(),
                    rolesAfter.get(before.getKey()), is(before.getValue()));
        }
        assertThat("First signer lost its signature",
                rolesAfter.containsValue(FIRST_SIGNER_ID), is(true));
        assertThat("Second signer lost its signature",
                rolesAfter.containsValue(SECOND_SIGNER_ID), is(true));
    }

    private static Map<String, String> roleBySignatureId(SignersWithoutEmailExample example) {
        Package apiPackage = example.eslClient.getPackageService().getApiPackage(example.packageId.getId());
        Map<String, String> roles = new HashMap<>();
        for (Document apiDocument : apiPackage.getDocuments()) {
            if (DOCUMENT_NAME.equals(apiDocument.getName())) {
                for (Approval approval : apiDocument.getApprovals()) {
                    roles.put(approval.getId(), approval.getRole());
                }
            }
        }
        return roles;
    }
}
