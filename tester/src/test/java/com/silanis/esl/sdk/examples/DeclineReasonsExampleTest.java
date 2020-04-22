package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

/**
 * @author singhlo1
 */
public class DeclineReasonsExampleTest {

    @Test
    public void verifyResult() {
        DeclineReasonExample example = new DeclineReasonExample();
        example.run();

        assertThat(example.retrievedDeclineReasons, hasSize(0));
        assertThat(example.createdDeclineReasons, notNullValue());
        assertTrue(example.createdDeclineReasons.contains("Decline Reason 1"));
        assertTrue(example.createdDeclineReasons.contains("Decline Reason 2"));

        assertThat(example.updatedDeclineReasons, notNullValue());
        assertTrue(example.createdDeclineReasons.contains("Decline Reason 1"));
        assertTrue(example.updatedDeclineReasons.contains("Decline Reason 3"));

        assertThat(example.declineReasonsAfterDelete, hasSize(0));
    }
}
