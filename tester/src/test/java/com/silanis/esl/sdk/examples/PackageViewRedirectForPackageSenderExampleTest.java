package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by schoi on 12/24/14.
 */
public class PackageViewRedirectForPackageSenderExampleTest {

    @Test
    public void verifyResult() {
        PackageViewRedirectForPackageSenderExample example = new PackageViewRedirectForPackageSenderExample();
        example.run();

        assertThat(example.generatedLinkToPackageViewForSender, not(isEmptyOrNullString()));
    }
}
