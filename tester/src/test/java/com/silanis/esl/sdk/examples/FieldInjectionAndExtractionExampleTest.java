package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 16/01/14
 * Time: 11:47 AM
 * <p/>
 * Test FieldInjectionAndExtractionExample.
 */
public class FieldInjectionAndExtractionExampleTest {
    @Test
    public void verifyResult() {
        FieldInjectionAndExtractionExample example = new FieldInjectionAndExtractionExample(Props.get());
        example.run();

        // InjectedField list is not returned by the esl-backend.
        assertThat("Package id was not set correctly", example.getPackageId(), is(notNullValue()));
    }
}
