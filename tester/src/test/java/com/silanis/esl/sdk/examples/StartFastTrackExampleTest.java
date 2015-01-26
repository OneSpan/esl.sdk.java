package com.silanis.esl.sdk.examples;

import org.junit.Test;

/**
 * Created by schoi on 1/23/15.
 */
public class StartFastTrackExampleTest {
    @Test
    public void verifyResult() {
        StartFastTrackExample example = new StartFastTrackExample(Props.get());
        example.run();

/*        DocumentPackage retrievedPackage = example.getRetrievedPackage();

        Document document = retrievedPackage.getDocument(example.DOCUMENT_NAME);

        assertThat("Document name is incorrectly returned.", document.getName().toString(), is(example.DOCUMENT_NAME));*/
    }
}
