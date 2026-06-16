package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.Signer;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.SpecifyRecipientExample.PLACEHOLDER_ID;
import static com.silanis.esl.sdk.examples.SpecifyRecipientExample.REGULAR_SIGNER_FIRST;
import static com.silanis.esl.sdk.examples.SpecifyRecipientExample.REGULAR_SIGNER_ID;
import static com.silanis.esl.sdk.examples.SpecifyRecipientExample.REGULAR_SIGNER_LAST;
import static com.silanis.esl.sdk.examples.SpecifyRecipientExample.SPECIFIER_FIRST;
import static com.silanis.esl.sdk.examples.SpecifyRecipientExample.SPECIFIER_ID;
import static com.silanis.esl.sdk.examples.SpecifyRecipientExample.SPECIFIER_LAST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SpecifyRecipientExampleTest {

    @Test
    public void verifyResult() {
        SpecifyRecipientExample example = new SpecifyRecipientExample();
        example.run();

        // Transaction should be in SENT state
        assertThat("Transaction should have been sent", example.retrievedPackage.getStatus(), is(PackageStatus.SENT));

        // Role 1 — regular external signer
        Signer regularSigner = example.retrievedPackage.getSigner(example.email1);
        assertNotNull("Regular signer should be present", regularSigner);
        assertThat("Regular signer first name should match", regularSigner.getFirstName(), is(REGULAR_SIGNER_FIRST));
        assertThat("Regular signer last name should match", regularSigner.getLastName(), is(REGULAR_SIGNER_LAST));
        assertThat("Regular signer ID should match", regularSigner.getId(), is(REGULAR_SIGNER_ID));

        // Role 2 — PLACEHOLDER role type: role contains a signer but signer fields are empty;
        // after round-trip the SDK must identify it as the new placeholder type
        Signer placeholderSigner = example.retrievedPackage.getPlaceholder(PLACEHOLDER_ID);
        assertNotNull("Placeholder signer should be present", placeholderSigner);
        assertThat("Placeholder ID should match", placeholderSigner.getId(), is(PLACEHOLDER_ID));
        assertTrue("Placeholder role type should remain PLACEHOLDER after round-trip",
                placeholderSigner.isNewPlaceholderSigner());

        // Role 3 — specifier: regular signer whose specifier flag must come back as true
        Signer specifierSigner = example.retrievedPackage.getSigner(example.email2);
        assertNotNull("Specifier signer should be present", specifierSigner);
        assertThat("Specifier signer first name should match", specifierSigner.getFirstName(), is(SPECIFIER_FIRST));
        assertThat("Specifier signer last name should match", specifierSigner.getLastName(), is(SPECIFIER_LAST));
        assertThat("Specifier signer ID should match", specifierSigner.getId(), is(SPECIFIER_ID));
        assertTrue("Specifier recipient should have specifier set to true", specifierSigner.getSpecifier());
    }
}
