package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.mockftpserver.fake.FakeFtpServer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chi-wing on 6/30/14.
 */
public class HistoryDocumentExampleTest {

    private FakeFtpServer fakeFtpServer;
    private final String PATH = "/test/completed/files/";
    private final String HOME_DIRECTORY = "/home/test";

    @Test
    public void verifyResult() {

        HistoryDocumentExample historyDocumentExample = new HistoryDocumentExample(Props.get());
        historyDocumentExample.run();

        DocumentPackage documentPackage = historyDocumentExample.getRetrievedPackage();

        // History document
        Document historyDocument = documentPackage.getDocument(historyDocumentExample.externalDocumentName);
        assertThat("The history document was not added correctly", historyDocument, IsNull.notNullValue());
        assertThat("The history document name was not set correctly", historyDocument.getName(), is(historyDocumentExample.externalDocumentName));
    }

}
