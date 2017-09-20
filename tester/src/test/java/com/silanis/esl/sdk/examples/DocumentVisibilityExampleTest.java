package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.examples.DocumentVisibilityExample.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

/**
 * Created by schoi on 11/25/16.
 */
public class DocumentVisibilityExampleTest {

    @Test
    public void verifyResult() {
        DocumentVisibilityExample example = new DocumentVisibilityExample();
        example.run();

        assertThat("Document Visibility was not set correctly.", example.retrievedVisibility.getConfigurations(), hasSize(3));

        assertThat("Document Visibility was not set correctly.", example.retrievedVisibility.getConfiguration(DOC1_ID).getSignerIds(), hasItems(SIGNER1_ID, SIGNER3_ID));
        assertThat("Document Visibility was not set correctly.", example.retrievedVisibility.getConfiguration(DOC2_ID).getSignerIds(), hasItems(SIGNER2_ID, SIGNER3_ID));
        assertThat("Document Visibility was not set correctly.", example.retrievedVisibility.getConfiguration(DOC3_ID).getSignerIds(), hasItems(SIGNER3_ID, SIGNER2_ID));

        assertThat("Document list based on the visibility was not retrieved correctly.", example.documentsForSigner1, hasSize(1));
        assertThat("Document list based on the visibility was not retrieved correctly.", example.documentsForSigner1.get(0).getName(), is(DOC1_NAME));

        assertThat("Document list based on the visibility was not retrieved correctly.", example.documentsForSigner2, hasSize(2));
        assertThat("Document list based on the visibility was not retrieved correctly.", example.documentsForSigner2.get(0).getName(), is(DOC2_NAME));
        assertThat("Document list based on the visibility was not retrieved correctly.", example.documentsForSigner2.get(1).getName(), is(DOC3_NAME));

        assertThat("Document list based on the visibility was not retrieved correctly.", example.documentsForSigner3, hasSize(3));
        assertThat("Document list based on the visibility was not retrieved correctly.", example.documentsForSigner3.get(0).getName(), is(DOC1_NAME));
        assertThat("Document list based on the visibility was not retrieved correctly.", example.documentsForSigner3.get(1).getName(), is(DOC2_NAME));
        assertThat("Document list based on the visibility was not retrieved correctly.", example.documentsForSigner3.get(2).getName(), is(DOC3_NAME));

        assertThat("Signer list based on the visibility was not retrieved correctly.", example.signersForDocument1, hasSize(2));
        assertThat("Signer list based on the visibility was not retrieved correctly.", example.signersForDocument1.get(0).getId(), is(SIGNER1_ID));
        assertThat("Signer list based on the visibility was not retrieved correctly.", example.signersForDocument1.get(1).getId(), is(SIGNER3_ID));

        assertThat("Signer list based on the visibility was not retrieved correctly.", example.signersForDocument2, hasSize(2));
        assertThat("Signer list based on the visibility was not retrieved correctly.", example.signersForDocument2.get(0).getId(), is(SIGNER2_ID));
        assertThat("Signer list based on the visibility was not retrieved correctly.", example.signersForDocument2.get(1).getId(), is(SIGNER3_ID));

        assertThat("Signer list based on the visibility was not retrieved correctly.", example.signersForDocument3, hasSize(2));
        assertThat("Signer list based on the visibility was not retrieved correctly.", example.signersForDocument3.get(0).getId(), is(SIGNER2_ID));
        assertThat("Signer list based on the visibility was not retrieved correctly.", example.signersForDocument3.get(1).getId(), is(SIGNER3_ID));
    }
}
