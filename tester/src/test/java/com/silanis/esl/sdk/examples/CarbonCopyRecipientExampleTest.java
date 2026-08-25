package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.Signer;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.CarbonCopyRecipientExample.CARBON_COPY_FIRST;
import static com.silanis.esl.sdk.examples.CarbonCopyRecipientExample.CARBON_COPY_ID;
import static com.silanis.esl.sdk.examples.CarbonCopyRecipientExample.CARBON_COPY_LAST;
import static com.silanis.esl.sdk.examples.CarbonCopyRecipientExample.SIGNER_FIRST;
import static com.silanis.esl.sdk.examples.CarbonCopyRecipientExample.SIGNER_ID;
import static com.silanis.esl.sdk.examples.CarbonCopyRecipientExample.SIGNER_LAST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CarbonCopyRecipientExampleTest {

    @Test
    public void verifyResult() {
        CarbonCopyRecipientExample example = new CarbonCopyRecipientExample();
        example.run();

        // Transaction should be in SENT state
        assertThat("Transaction should have been sent", example.retrievedPackage.getStatus(), is(PackageStatus.SENT));

        // Role 1 — regular external signer
        Signer signer = example.retrievedPackage.getSigner(example.email1);
        assertNotNull("Signer should be present", signer);
        assertThat("Signer first name should match", signer.getFirstName(), is(SIGNER_FIRST));
        assertThat("Signer last name should match", signer.getLastName(), is(SIGNER_LAST));
        assertThat("Signer ID should match", signer.getId(), is(SIGNER_ID));
        assertFalse("Regular signer should not be a carbon copy recipient", signer.isCarbonCopyRecipient());

        // Role 2 — carbon copy recipient: the CARBON_COPY_RECIPIENT role type must survive the
        // round-trip so that a subsequent update does not downgrade it to a regular signer
        Signer carbonCopyRecipient = example.retrievedPackage.getSigner(example.email2);
        assertNotNull("Carbon copy recipient should be present", carbonCopyRecipient);
        assertThat("Carbon copy recipient first name should match", carbonCopyRecipient.getFirstName(), is(CARBON_COPY_FIRST));
        assertThat("Carbon copy recipient last name should match", carbonCopyRecipient.getLastName(), is(CARBON_COPY_LAST));
        assertThat("Carbon copy recipient ID should match", carbonCopyRecipient.getId(), is(CARBON_COPY_ID));
        assertTrue("Carbon copy recipient role type should remain CARBON_COPY_RECIPIENT after round-trip",
                carbonCopyRecipient.isCarbonCopyRecipient());
    }
}
