package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.NotaryJournalEntry;
import com.silanis.esl.sdk.internal.Converter;
import com.silanis.esl.sdk.io.DownloadedFile;

import java.util.List;
import java.util.Properties;

/**
 * Created by schoi on 1/29/15.
 */
public class NotaryJournalExample extends SDKSample {

    public List<NotaryJournalEntry> sdkJournalEntries;
    public DownloadedFile csvJournalEntries;
    private String senderUID;

    public static void main( String... args ) {
        new NotaryJournalExample(Props.get()).run();
    }

    public NotaryJournalExample( Properties props ) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ) );
    }

    public NotaryJournalExample( String apiKey, String apiUrl ) {
        super( apiKey, apiUrl );
        this.senderUID = Converter.apiKeyToUID(apiKey);
    }

    public void execute() {
        sdkJournalEntries = eslClient.getPackageService().getJournalEntries(senderUID);
        csvJournalEntries = eslClient.getPackageService().getJournalEntriesAsCSV(senderUID);
    }
}
