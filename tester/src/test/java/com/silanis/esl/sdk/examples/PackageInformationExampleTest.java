package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by schoi on 4/1/15.
 */
public class PackageInformationExampleTest {
    
    @Test
    public void verifyResult() {
        PackageInformationExample example = new PackageInformationExample(Props.get());
        example.run();

        assertThat("Support configuration was not set correctly. ", example.supportConfiguration, is(notNullValue()));
        assertThat("Support configuration was not set correctly. ", example.supportConfiguration.getEmail(), is(not(isEmptyOrNullString())));
        assertThat("Support configuration was not set correctly. ", example.supportConfiguration.getPhone(), is(not(isEmptyOrNullString())));
    }
}
