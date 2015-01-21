package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import java.util.Collection;

import static com.silanis.esl.sdk.SignatureStyle.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: jessica
 * Date: 13/12/13
 * Time: 3:27 PM
 *
 * Test ConsentExample.
 */
public class ConsentExampleTest {

    @Test
    public void verifyResult() {
        ConsentExample consentExample = new ConsentExample( Props.get() );
        consentExample.run();

        DocumentPackage documentPackage = consentExample.getRetrievedPackage();

        Collection<Signature> signatures = documentPackage.getDocument("First Document").getSignatures();

        assertThat("Signatures not set correctly. ", signatures.iterator().hasNext(), is(true));
        assertThat("Signature acceptance not set correctly.", signatures.iterator().next().getStyle(), is( ACCEPTANCE));
   }
}


