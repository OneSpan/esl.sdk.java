package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by schoi on 1/23/15.
 */
public class StartFastTrackExampleTest {
    @Test
    public void verifyResult() {
        StartFastTrackExample example = new StartFastTrackExample(Props.get());
        example.run();

        assertThat(example.signingUrl, is(not(isEmptyOrNullString())));
    }
}
