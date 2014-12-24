package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by schoi on 12/24/14.
 */
public class PackageViewRedirectForPackageSenderExampleTest {

    @Test
    public void verifyResult() {
        PackageViewRedirectForPackageSenderExample example = new PackageViewRedirectForPackageSenderExample( Props.get() );
        example.run();

        assertThat(example.getGeneratedLinkToPackageViewForSender(), is(notNullValue()));
    }
}
