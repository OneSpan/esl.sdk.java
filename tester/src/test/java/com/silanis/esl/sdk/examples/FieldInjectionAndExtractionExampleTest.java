package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

/**
 * User: jessica
 * Date: 16/01/14
 * Time: 11:47 AM
 * <p/>
 * Test FieldInjectionAndExtractionExample.
 */
public class FieldInjectionAndExtractionExampleTest {
    @Test
    public void verifyResult() {
        FieldInjectionAndExtractionExample fieldInjectionAndExtractionExample = new FieldInjectionAndExtractionExample(Props.get());
        fieldInjectionAndExtractionExample.run();

        DocumentPackage documentPackage = fieldInjectionAndExtractionExample.getRetrievedPackage();

        // Verify if the fields were injected correctly into the document.
        Document document = documentPackage.getDocument(DocumentExtractionExample.DOCUMENT_NAME);
    }
}
