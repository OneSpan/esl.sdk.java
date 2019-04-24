package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.PackageStatus.COMPLETED;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by whou on 08/12/14.
 */
public class ProxyConfigurationExampleTest {

    @Test
    public void verifyResult() {
        ProxyConfigurationExample example = new ProxyConfigurationExample(Props.get());
        example.run();

        assertThat(example.retrievedPackage1, notNullValue());
        assertThat(example.retrievedPackage1.getStatus(), is(COMPLETED));
        assertThat(example.retrievedPackage2, notNullValue());
        assertThat(example.retrievedPackage2.getStatus(), is(COMPLETED));
    }
}
