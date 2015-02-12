package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 1/28/15.
 */
public class GetGroupSummariesExampleTest {
    @Test
    public void verifyResult() {
        GetGroupSummariesExample example = new GetGroupSummariesExample(Props.get());
        example.run();

        assertThat("GroupSummaries is not set correctly.", example.retrievedGroupSummaries, is(notNullValue()));
        assertThat("GroupSummaries is not set correctly.", example.retrievedGroupSummaries.size(), is(greaterThanOrEqualTo(0)));
    }
}
