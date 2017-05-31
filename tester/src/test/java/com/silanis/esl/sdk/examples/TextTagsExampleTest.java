package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.TextTagsExample.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * Created by schoi on 23/05/17.
 */
public class TextTagsExampleTest {

    @Test
    public void verifyResult() {
        TextTagsExample example = new TextTagsExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        Document document1 = documentPackage.getDocument(DOCUMENT1_NAME);
        Document document2 = documentPackage.getDocument(DOCUMENT2_NAME);
        Document document3 = documentPackage.getDocument(DOCUMENT3_NAME);

        assertThat("TextTags are not set correctly.", document1.getSignatures(), hasSize(3));
        assertThat("TextTags are not set correctly.", document2.getSignatures(), hasSize(4));
        assertThat("TextTags are not set correctly.", document3.getSignatures(), hasSize(7));
    }
}
