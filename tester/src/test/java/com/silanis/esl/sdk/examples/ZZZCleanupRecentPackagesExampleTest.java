package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Cleanup test that runs last (alphabetically after all other *ExampleTest
 * classes) and deletes every package, transaction and template that was updated
 * within the last {@value CleanupRecentPackagesExample#CLEANUP_WINDOW_MINUTES}
 * minutes.  This keeps the test account tidy after a full test pass.
 *
 * The "ZZZ" prefix guarantees that Maven Surefire's default alphabetical run
 * order places this class after all other ExampleTest classes.
 */
public class ZZZCleanupRecentPackagesExampleTest {

    @Test(timeout = 300000)
    public void verifyResult() {
        CleanupRecentPackagesExample example = new CleanupRecentPackagesExample();
        example.run();

        assertThat("Deleted package IDs list should not be null", example.deletedPackageIds, notNullValue());
        assertThat("Deleted template IDs list should not be null", example.deletedTemplateIds, notNullValue());
        assertThat("Deleted sender IDs list should not be null", example.deletedSenderIds, notNullValue());
        assertThat("Deleted group IDs list should not be null", example.deletedGroupIds, notNullValue());
        assertThat("Deleted packages count should be non-negative", example.deletedPackagesCount, is(greaterThanOrEqualTo(0)));
        assertThat("Deleted templates count should be non-negative", example.deletedTemplatesCount, is(greaterThanOrEqualTo(0)));
        assertThat("Deleted senders count should be non-negative", example.deletedSendersCount, is(greaterThanOrEqualTo(0)));
        assertThat("Deleted groups count should be non-negative", example.deletedGroupsCount, is(greaterThanOrEqualTo(0)));
        assertThat("Deleted packages count should match ID list size", example.deletedPackagesCount, is(example.deletedPackageIds.size()));
        assertThat("Deleted templates count should match ID list size", example.deletedTemplatesCount, is(example.deletedTemplateIds.size()));
        assertThat("Deleted senders count should match ID list size", example.deletedSendersCount, is(example.deletedSenderIds.size()));
        assertThat("Deleted groups count should match ID list size", example.deletedGroupsCount, is(example.deletedGroupIds.size()));
    }
}
