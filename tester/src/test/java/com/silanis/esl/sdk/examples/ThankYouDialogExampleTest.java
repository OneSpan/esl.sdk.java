package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by schoi on 3/31/15.
 */
public class ThankYouDialogExampleTest {

    @Test
    public void verifyResult() {
        ThankYouDialogExample example = new ThankYouDialogExample(Props.get());
        example.run();

        assertThat("Thank you Dialog was not set correctly. ", example.thankYouDialogContent, not(isEmptyOrNullString()));
    }
}
