package com.silanis.esl.sdk.examples;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by schoi on 1/29/15.
 */
public class NotaryJournalExampleTest {
    @Test
    public void verifyResult() {
        NotaryJournalExample example = new NotaryJournalExample(Props.get());
        example.run();

        assertThat("Cannot get the completion report.", example.sdkJournalEntries, is(notNullValue()));
        assertThat("Cannot get the completion report in csv format.", example.csvJournalEntries, Is.is(notNullValue()));
    }
}
