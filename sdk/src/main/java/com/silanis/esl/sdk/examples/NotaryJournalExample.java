package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.NotaryJournalEntry;
import com.silanis.esl.sdk.io.DownloadedFile;

import java.util.List;

/**
 * Created by schoi on 1/29/15.
 */
public class NotaryJournalExample extends SDKSample {

    public List<NotaryJournalEntry> sdkJournalEntries;
    public DownloadedFile csvJournalEntries;

    public static void main( String... args ) {
        new NotaryJournalExample().run();
    }

    public void execute() {
        sdkJournalEntries = eslClient.getPackageService().getJournalEntries(senderUID);
        csvJournalEntries = eslClient.getPackageService().getJournalEntriesAsCSV(senderUID);
    }
}
