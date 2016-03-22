package com.silanis.esl.sdk.examples;

import au.com.bytecode.opencsv.CSVReader;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * Created by schoi on 1/29/15.
 */
public class NotaryJournalExampleTest {
    @Test
    public void verifyResult() throws IOException {
        NotaryJournalExample example = new NotaryJournalExample();
        example.run();

        assertThat("Cannot get the completion report.", example.sdkJournalEntries, notNullValue());

        assertThat("Cannot get the completion report in csv format.", example.csvJournalEntries.getFilename(), notNullValue());
        assertThat("Cannot get the completion report in csv format.", example.csvJournalEntries.getContents(), notNullValue());

        CSVReader reader = new CSVReader(new InputStreamReader(new ByteArrayInputStream(example.csvJournalEntries.getContents())));
        List<String[]> rows = reader.readAll();

        if(example.sdkJournalEntries.size() > 0) {
            assertThat(rows, hasSize(example.sdkJournalEntries.size() + 1));
        }
    }
}
