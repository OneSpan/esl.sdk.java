package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;
import org.mockftpserver.fake.FakeFtpServer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by chi-wing on 6/30/14.
 */
public class HistoryDocumentExampleTest {

    private FakeFtpServer fakeFtpServer;
    private final String PATH = "/test/completed/files/";
    private final String HOME_DIRECTORY = "/home/test";

    @Test
    public void verifyResult() {

        HistoryDocumentExample example = new HistoryDocumentExample(Props.get());
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        // History document
        Document historyDocument = documentPackage.getDocument(example.externalDocumentName);
        assertThat("The history document was not added correctly", historyDocument, notNullValue());
        assertThat("The history document name was not set correctly", historyDocument.getName(), is(example.externalDocumentName));
    }

}
