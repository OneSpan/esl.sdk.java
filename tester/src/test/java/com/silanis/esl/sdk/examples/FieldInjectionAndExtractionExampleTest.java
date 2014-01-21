package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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

        DocumentPackage documentPackage = fieldInjectionAndExtractionExample.getEslClient().getPackage(fieldInjectionAndExtractionExample.getPackageId());

        // Verify if the fields were injected correctly into the document.
        Document document = documentPackage.getDocument(DocumentExtractionExample.DOCUMENT_NAME);

        //TODO

    }
}
