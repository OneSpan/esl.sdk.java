package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 1/23/15.
 */
public class StartFastTrackExampleTest {
    @Test
    public void verifyResult() {
        StartFastTrackExample example = new StartFastTrackExample(Props.get());
        example.run();

        DocumentPackage retrievedPackage = example.getRetrievedPackage();

        Document document = retrievedPackage.getDocument(example.DOCUMENT_NAME);

        assertThat("Document name is incorrectly returned.", document.getName().toString(), is(example.DOCUMENT_NAME));
    }
}
