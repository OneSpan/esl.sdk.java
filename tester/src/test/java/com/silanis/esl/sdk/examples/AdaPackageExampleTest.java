package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.examples.AdaPackageExample.DOCUMENT_NAME;
import static junit.framework.Assert.assertTrue;

/**
 * Created by schoi on 12/02/18.
 */
public class AdaPackageExampleTest {

    @Test
    public void verifyResult() {
        AdaPackageExample example = new AdaPackageExample();
        example.run();

        assertTrue("Document tag is not set correctly.", example.getRetrievedPackage().getDocument(DOCUMENT_NAME).isTagged());
    }
}