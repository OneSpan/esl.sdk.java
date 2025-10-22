package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.DocumentInfo;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class SupportingDocumentCloneExampleTest {

    @Test
    public void verifyResult() {
        SupportingDocumentCloneExample example = new SupportingDocumentCloneExample();
        example.run();

        Set<String> beforeCloning = example.getSupportingDocumentBeforeCloning().stream()
                .map(DocumentInfo::getDocumentName)
                .collect(Collectors.toSet());

        Set<String> afterCloning = example.getSupportingDocumentAfterCloning().stream()
                .map(DocumentInfo::getDocumentName)
                .collect(Collectors.toSet());

        assertEquals(beforeCloning, afterCloning);
    }
}
