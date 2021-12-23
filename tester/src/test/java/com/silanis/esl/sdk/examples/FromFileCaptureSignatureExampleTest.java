package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FromFileCaptureSignatureExampleTest {
    @Test
    public void verifyResult() {
        FromFileCaptureSignatureExample example = new FromFileCaptureSignatureExample();
        example.run();

        // Verify if signature in retrieved package is set to be from file.
        assertThat(((ArrayList<Signature>)example.retrievedPackage.getDocuments().get(1).getSignatures()).get(0).isFromFile(), is(true));
    }
}
