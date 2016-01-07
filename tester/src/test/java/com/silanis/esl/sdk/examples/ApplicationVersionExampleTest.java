package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by schoi on 3/31/15.
 */
public class ApplicationVersionExampleTest {

    @Test
    public void verifyResult() {
        ApplicationVersionExample example = new ApplicationVersionExample(Props.get());
        example.run();

        assertThat("Application Version was not set correctly. ", example.applicationVersion, not(isEmptyOrNullString()));
    }
}
